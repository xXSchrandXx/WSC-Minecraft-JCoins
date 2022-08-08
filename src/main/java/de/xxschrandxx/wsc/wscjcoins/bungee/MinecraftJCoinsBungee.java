package de.xxschrandxx.wsc.wscjcoins.bungee;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import de.xxschrandxx.wsc.wscjcoins.bungee.api.ConfigurationBungee;
import de.xxschrandxx.wsc.wscjcoins.bungee.api.MinecraftJCoinsBungeeAPI;
import de.xxschrandxx.wsc.wscjcoins.bungee.commands.*;
import de.xxschrandxx.wsc.wscjcoins.bungee.listeners.*;
import de.xxschrandxx.wsc.wscjcoins.core.IMinecraftJCoins;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class MinecraftJCoinsBungee extends Plugin implements IMinecraftJCoins {

    // start of api part
    private static MinecraftJCoinsBungee instance;
    public static MinecraftJCoinsBungee getInstance() {
        return instance;
    }
    private MinecraftJCoinsBungeeAPI api;
    public MinecraftJCoinsBungeeAPI getAPI() {
        return this.api;
    }
    private ScheduledTask task = null;
    private PlayerListener listener = new PlayerListener();
    public boolean start() {
        if (!getConfiguration().getBoolean(MinecraftJCoinsVars.Configuration.Enabled)) {
            return true;
        }
        if (task != null) {
            return false;
        }
        task = getProxy().getScheduler().schedule(
            getInstance(),
            getAPI().getRunnable(getConfiguration().getInt(MinecraftJCoinsVars.Configuration.Amount)),
            0,
            getConfiguration().getLong(MinecraftJCoinsVars.Configuration.Interval),
            TimeUnit.SECONDS
        );
        for (ProxiedPlayer player : getProxy().getPlayers()) {
            getAPI().addPlayer(player);
        }
        getProxy().getPluginManager().registerListener(getInstance(), listener);
        return true;
    }
    public boolean stop() {
        if (!getConfiguration().getBoolean(MinecraftJCoinsVars.Configuration.Enabled)) {
            return true;
        }
        if (task == null) {
            return false;
        }
        getProxy().getScheduler().cancel(task);
        task = null;
        getProxy().getPluginManager().unregisterListener(listener);
        getAPI().clear();
        return true;
    }
    // end of api part

    // start of plugin part
    @Override
    public void onEnable() {
        instance = this;

        if (!reloadConfiguration()) {
            getLogger().log(Level.WARNING, "Could not load config.yml, disabeling plugin!");
            onDisable();
            return;
        }

        // set api
        try {
            this.api = new MinecraftJCoinsBungeeAPI(
                getConfiguration().getString(MinecraftJCoinsVars.Configuration.URL),
                getConfiguration().getString(MinecraftJCoinsVars.Configuration.Key),
                getLogger()
                );
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }

        // register command
        getProxy().getPluginManager().registerCommand(getInstance(), new WSCJCoins("wscjcoins"));

        // register listener

        // register runnable
        start();
    }

    @Override
    public void onDisable() {
        stop();
    }
    // end of plugin part

    // start config part
    private File configFile = new File(getDataFolder(), "config.yml");
    private ConfigurationBungee config;

    public ConfigurationBungee getConfiguration() {
        return getInstance().config;
    }

    public boolean reloadConfiguration() {
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
            return reloadConfiguration();
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
