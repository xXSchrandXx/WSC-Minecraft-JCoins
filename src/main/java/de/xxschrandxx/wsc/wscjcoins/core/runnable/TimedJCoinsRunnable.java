package de.xxschrandxx.wsc.wscjcoins.core.runnable;

import java.io.IOException;

import de.xxschrandxx.wsc.wscbridge.core.IMinecraftBridgePlugin;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;
import de.xxschrandxx.wsc.wscjcoins.core.api.IMinecraftJCoinsCoreAPI;

public class TimedJCoinsRunnable implements Runnable {
    private IMinecraftBridgePlugin<? extends IMinecraftJCoinsCoreAPI> instance;
    protected final ISender<?> sender;

    public TimedJCoinsRunnable(IMinecraftBridgePlugin<? extends IMinecraftJCoinsCoreAPI> instance, ISender<?> sender) {
        this.instance = instance;
        this.sender = sender;
    }

    public void run() {
        Integer amount = instance.getConfiguration().getInt(MinecraftJCoinsVars.Configuration.timedJCoinsAmount);
        Integer minutes = instance.getConfiguration().getInt(MinecraftJCoinsVars.Configuration.timedJCoinsInterval);
        try {
            instance.getAPI().sendJCoins(sender.getUniqueId(), amount);
            String message = instance.getConfiguration().getString(MinecraftJCoinsVars.Configuration.LangTimedJCoins);
            sender.sendMessage(message.replaceAll("%amount%", amount.toString()).replaceAll("%minutes%", minutes.toString()));
        } catch (IOException e) {
            if (instance.getAPI().isDebugModeEnabled()) {
                instance.getAPI().log("Could not modify jcoins.", e);
            }
        }
    }
}
