package de.xxschrandxx.wsc.wscjcoins.bukkit.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.MinecraftBridgeBukkitAPI;
import de.xxschrandxx.wsc.wscbridge.core.api.Response;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;
import de.xxschrandxx.wsc.wscjcoins.bukkit.MinecraftJCoinsBukkit;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;
import de.xxschrandxx.wsc.wscjcoins.core.api.IMinecraftJCoinsCoreAPI;
import de.xxschrandxx.wsc.wscjcoins.core.api.MinecraftJCoinsCoreAPI;
import de.xxschrandxx.wsc.wscjcoins.core.runnable.TimedJCoinsRunnable;

public class MinecraftJCoinsBukkitAPI extends MinecraftBridgeBukkitAPI implements IMinecraftJCoinsCoreAPI {

    protected final URL url;

    public MinecraftJCoinsBukkitAPI(URL url, Logger logger, MinecraftBridgeBukkitAPI api) {
        super(api, logger);
        this.url = url;
    }

    public Response<String, Object> sendJCoins(UUID uuid, Integer amount) throws MalformedURLException, UnknownServiceException, SocketTimeoutException, IOException {
        return MinecraftJCoinsCoreAPI.sendJCoins(this, url, uuid, amount);
    }

    protected HashMap<ISender<?>, BukkitTask> tasks = new HashMap<ISender<?>, BukkitTask>();
    public void addTask(ISender<?> sender) {
        if (tasks.containsKey(sender)) {
            if (isDebugModeEnabled()) {
                log("Sender already in tasks " + sender.getName());
            }
            return;
        }
        MinecraftJCoinsBukkit instance = MinecraftJCoinsBukkit.getInstance();
        Integer interval = instance.getConfiguration().getInt(MinecraftJCoinsVars.Configuration.timedJCoinsInterval);
        BukkitTask task = Bukkit.getScheduler().runTaskTimerAsynchronously(instance, new TimedJCoinsRunnable(instance, sender), interval, interval);
        if (isDebugModeEnabled()) {
            log("Adding task for " + sender.getName());
        }
        tasks.put(sender, task);
    }

    public void removeTask(ISender<?> sender) {
        if (!tasks.containsKey(sender)) {
            if (isDebugModeEnabled()) {
                log("Sender not in tasks " + sender.getName());
            }
            return;
        }
        Bukkit.getScheduler().cancelTask(tasks.get(sender).getTaskId());
        if (isDebugModeEnabled()) {
            log("Removing task for " + sender.getName());
        }
        tasks.remove(sender);
    }
    
}
