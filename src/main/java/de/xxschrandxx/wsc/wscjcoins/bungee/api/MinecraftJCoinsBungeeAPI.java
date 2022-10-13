package de.xxschrandxx.wsc.wscjcoins.bungee.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import de.xxschrandxx.wsc.wscbridge.bungee.api.MinecraftBridgeBungeeAPI;
import de.xxschrandxx.wsc.wscbridge.core.api.Response;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;
import de.xxschrandxx.wsc.wscjcoins.bungee.MinecraftJCoinsBungee;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;
import de.xxschrandxx.wsc.wscjcoins.core.api.IMinecraftJCoinsCoreAPI;
import de.xxschrandxx.wsc.wscjcoins.core.api.MinecraftJCoinsCoreAPI;
import de.xxschrandxx.wsc.wscjcoins.core.runnable.TimedJCoinsRunnable;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.scheduler.ScheduledTask;

public class MinecraftJCoinsBungeeAPI extends MinecraftBridgeBungeeAPI implements IMinecraftJCoinsCoreAPI {

    protected final URL url;

    public MinecraftJCoinsBungeeAPI(URL url, Logger logger, MinecraftBridgeBungeeAPI api) {
        super(api, logger);
        this.url = url;
    }

    public Response<String, Object> sendJCoins(UUID uuid, Integer amount) throws MalformedURLException, UnknownServiceException, SocketTimeoutException, IOException {
        return MinecraftJCoinsCoreAPI.sendJCoins(this, url, uuid, amount);
    }

    protected HashMap<ISender<?>, ScheduledTask> tasks = new HashMap<ISender<?>, ScheduledTask>();
    public void addTask(ISender<?> sender) {
        if (tasks.containsKey(sender)) {
            if (isDebugModeEnabled()) {
                log("Sender already in tasks " + sender.getName());
            }
            return;
        }
        MinecraftJCoinsBungee instance = MinecraftJCoinsBungee.getInstance();
        Integer interval = instance.getConfiguration().getInt(MinecraftJCoinsVars.Configuration.timedJCoinsInterval);
        ScheduledTask task = ProxyServer.getInstance().getScheduler().schedule(instance, new TimedJCoinsRunnable(instance, sender), interval, interval, TimeUnit.MINUTES);
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
        ProxyServer.getInstance().getScheduler().cancel(tasks.get(sender));
        if (isDebugModeEnabled()) {
            log("Removing task for " + sender.getName());
        }
        tasks.remove(sender);
    }
}
