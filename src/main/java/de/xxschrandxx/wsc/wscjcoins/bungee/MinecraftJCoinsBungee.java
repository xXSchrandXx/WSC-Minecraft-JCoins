package de.xxschrandxx.wsc.wscjcoins.bungee;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import de.xxschrandxx.wsc.wscbridge.bungee.MinecraftBridgeBungee;
import de.xxschrandxx.wsc.wscbridge.bungee.api.ConfigurationBungee;
import de.xxschrandxx.wsc.wscbridge.bungee.api.command.SenderBungee;
import de.xxschrandxx.wsc.wscbridge.core.IMinecraftBridgePlugin;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;
import de.xxschrandxx.wsc.wscjcoins.bungee.api.MinecraftJCoinsBungeeAPI;
import de.xxschrandxx.wsc.wscjcoins.bungee.commands.WSCJCoinsBungee;
import de.xxschrandxx.wsc.wscjcoins.bungee.listener.*;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class MinecraftJCoinsBungee extends Plugin implements IMinecraftBridgePlugin<MinecraftJCoinsBungeeAPI> {

    // start of api part
    private static MinecraftJCoinsBungee instance;

    public static MinecraftJCoinsBungee getInstance() {
        return instance;
    }

    private MinecraftJCoinsBungeeAPI api;

    public void loadAPI(ISender<?> sender) {
        String urlString = getConfiguration().getString(MinecraftJCoinsVars.Configuration.url);
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            getLogger().log(Level.INFO, "Could not load api, disabeling plugin!.", e);
            return;
        }
        MinecraftBridgeBungee wsc = MinecraftBridgeBungee.getInstance();
        this.api = new MinecraftJCoinsBungeeAPI(
            url,
            getLogger(),
            wsc.getAPI()
        );
    }

    public MinecraftJCoinsBungeeAPI getAPI() {
        return this.api;
    }
    // end of api part

    // start of plugin part
    @Override
    public void onEnable() {
        instance = this;

        // Load configuration
        getLogger().log(Level.INFO, "Loading Configuration.");
        SenderBungee sender = new SenderBungee(getProxy().getConsole(), getInstance());
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
        getProxy().getPluginManager().registerListener(getInstance(), new WSCBridgeConfigReloadListenerBungee());
        getProxy().getPluginManager().registerListener(getInstance(), new WSCBridgePluginReloadListenerBungee());
        if (getConfiguration().getBoolean(MinecraftJCoinsVars.Configuration.timedJCoinsEnabled)) {
            getProxy().getPluginManager().registerListener(getInstance(), new WSCJCoinsTimedBungeeListener());
        }
        getProxy().getPluginManager().registerListener(getInstance(), new AddModuleListenerBungee());

        // load commands
        getLogger().log(Level.INFO, "Loading Commands.");
        getProxy().getPluginManager().registerCommand(getInstance(), new WSCJCoinsBungee("wscjcoins"));
    }

    @Override
    public void onDisable() {
    }
    // end of plugin part

    // start config part
    private File configFile = new File(getDataFolder(), "config.yml");
    private ConfigurationBungee config;

    public ConfigurationBungee getConfiguration() {
        return getInstance().config;
    }

    public boolean reloadConfiguration(ISender<?> sender) {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        if (configFile.exists()) {
            try {
                config = new ConfigurationBungee(ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile));
            }
            catch (IOException e) {
                getLogger().log(Level.WARNING, "Could not load config.yml.", e);
                return false;
            }
        }
        else {
            try {
                configFile.createNewFile();
            }
            catch (IOException e) {
                getLogger().log(Level.WARNING, "Could not create config.yml.", e);
                return false;
            }
            config = new ConfigurationBungee();
        }

        if (MinecraftJCoinsVars.startConfig(getConfiguration(), getLogger())) {
            if (!saveConfiguration()) {
                return false;
            }
            return reloadConfiguration(sender);
        }
        return true;
    }

    public boolean saveConfiguration() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config.getConfiguration(), configFile);
        }
        catch (IOException e) {
            getLogger().log(Level.WARNING, "Could not save config.yml.", e);
            return false;
        }
        return true;
    }
    // end config part
}
