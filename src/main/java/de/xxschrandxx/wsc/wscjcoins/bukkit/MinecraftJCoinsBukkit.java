package de.xxschrandxx.wsc.wscjcoins.bukkit;

import java.net.MalformedURLException;
import java.util.logging.Level;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import de.xxschrandxx.wsc.wscjcoins.bukkit.api.ConfigurationBukkit;
import de.xxschrandxx.wsc.wscjcoins.bukkit.api.MinecraftJCoinsBukkitAPI;
import de.xxschrandxx.wsc.wscjcoins.bukkit.commands.*;
import de.xxschrandxx.wsc.wscjcoins.bukkit.listeners.*;
import de.xxschrandxx.wsc.wscjcoins.core.IMinecraftJCoins;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;

public class MinecraftJCoinsBukkit extends JavaPlugin implements IMinecraftJCoins {

    // start of api part
    private static MinecraftJCoinsBukkit instance;
    public static MinecraftJCoinsBukkit getInstance() {
        return instance;
    }
    private MinecraftJCoinsBukkitAPI api;
    public MinecraftJCoinsBukkitAPI getAPI() {
        return this.api;
    }
    private BukkitTask task = null;
    private PlayerListener listener = new PlayerListener();
    public boolean start() {
        if (!getConfiguration().getBoolean(MinecraftJCoinsVars.Configuration.Enabled)) {
            return true;
        }
        if (task != null) {
            if (getServer().getScheduler().isCurrentlyRunning(task.getTaskId())) {
                return false;
            }
            if (getServer().getScheduler().isQueued(task.getTaskId())) {
                return false;
            }
        }
        task = getServer().getScheduler().runTaskTimerAsynchronously(
            getInstance(),
            getAPI().getRunnable(getConfiguration().getInt(MinecraftJCoinsVars.Configuration.Amount)),
            0,
            getConfiguration().getLong(MinecraftJCoinsVars.Configuration.Interval)
        );
        for (Player player : getServer().getOnlinePlayers()) {
            getAPI().addPlayer(player);
        }
        getServer().getPluginManager().registerEvents(listener, getInstance());
        return true;
    }
    public boolean stop() {
        if (!getConfiguration().getBoolean(MinecraftJCoinsVars.Configuration.Enabled)) {
            return true;
        }
        if (task == null) {
            return false;
        }
        if (!getServer().getScheduler().isCurrentlyRunning(task.getTaskId())) {
            return false;
        }
        if (!getServer().getScheduler().isQueued(task.getTaskId())) {
            return false;
        }
        getServer().getScheduler().cancelTask(task.getTaskId());
        HandlerList.unregisterAll(listener);
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
            this.api = new MinecraftJCoinsBukkitAPI(
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
        getCommand("wscjcoins").setExecutor(new WSCJCoins());

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
    public ConfigurationBukkit getConfiguration() {
        return new ConfigurationBukkit(getInstance().getConfig());
    }

    public boolean reloadConfiguration() {
        reloadConfig();

        if (MinecraftJCoinsVars.startConfig(getConfiguration(), getLogger())) {
            if (!saveConfiguration()) {
                return false;
            }
            return reloadConfiguration();
        }
        return true;
    }

    public boolean saveConfiguration() {
        saveConfig();
        return true;
    }
    // end config part
}
