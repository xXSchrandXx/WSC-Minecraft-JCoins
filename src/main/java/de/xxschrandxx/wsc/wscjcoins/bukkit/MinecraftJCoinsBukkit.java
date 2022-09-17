package de.xxschrandxx.wsc.wscjcoins.bukkit;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import de.xxschrandxx.wsc.wscbridge.bukkit.MinecraftBridgeBukkit;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.ConfigurationBukkit;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;
import de.xxschrandxx.wsc.wscbridge.core.IMinecraftBridgePlugin;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;
import de.xxschrandxx.wsc.wscjcoins.bukkit.api.MinecraftJCoinsBukkitAPI;
import de.xxschrandxx.wsc.wscjcoins.bukkit.commands.WSCJCoinsBukkit;
import de.xxschrandxx.wsc.wscjcoins.bukkit.listener.*;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;

public class MinecraftJCoinsBukkit extends JavaPlugin implements IMinecraftBridgePlugin<MinecraftJCoinsBukkitAPI> {

    // start of api part
    private static MinecraftJCoinsBukkit instance;

    public static MinecraftJCoinsBukkit getInstance() {
        return instance;
    }

    private MinecraftJCoinsBukkitAPI api;

    public void loadAPI(ISender<?> sender) {
        String urlString = getConfiguration().getString(MinecraftJCoinsVars.Configuration.url);
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            getLogger().log(Level.INFO, "Could not load api, disabeling plugin!.", e);
            return;
        }
        MinecraftBridgeBukkit wsc = MinecraftBridgeBukkit.getInstance();
        this.api = new MinecraftJCoinsBukkitAPI(
            url,
            getLogger(),
            wsc.getAPI()
        );
    }
    public MinecraftJCoinsBukkitAPI getAPI() {
        return this.api;
    }
    // end of api part

    // start of plugin part
    @Override
    public void onEnable() {
        instance = this;

        // Load configuration
        getLogger().log(Level.INFO, "Loading Configuration.");
        SenderBukkit sender = new SenderBukkit(getServer().getConsoleSender(), getInstance());
        if (!reloadConfiguration(sender)) {
            getLogger().log(Level.WARNING, "Could not load config.yml, disabeling plugin!");
            onDisable();
            return;
        }

        // Load api
        getLogger().log(Level.INFO, "Loading API.");
        loadAPI(sender);

        // Load listener
        getLogger().log(Level.INFO, "Loading Listener.");
        getServer().getPluginManager().registerEvents(new WSCBridgeConfigReloadListenerBukkit(), getInstance());
        getServer().getPluginManager().registerEvents(new WSCBridgePluginReloadListenerBukkit(), getInstance());
        if (getConfiguration().getBoolean(MinecraftJCoinsVars.Configuration.timedJCoinsEnabled)) {
            getServer().getPluginManager().registerEvents(new WSCJCoinsTimedBukkitListener(), getInstance());
        }

        // Load commands
        getLogger().log(Level.INFO, "Loading Commands.");
        getCommand("wscjcoins").setExecutor(new WSCJCoinsBukkit());
    }

    @Override
    public void onDisable() {
    }
    // end of plugin part

    // start config part
    public ConfigurationBukkit getConfiguration() {
        return new ConfigurationBukkit(getInstance().getConfig());
    }

    public boolean reloadConfiguration(ISender<?> sender) {
        reloadConfig();

        if (MinecraftJCoinsVars.startConfig(getConfiguration(), getLogger())) {
            if (!saveConfiguration()) {
                return false;
            }
            return reloadConfiguration(sender);
        }
        return true;
    }

    public boolean saveConfiguration() {
        saveConfig();
        return true;
    }
    // end config part
}
