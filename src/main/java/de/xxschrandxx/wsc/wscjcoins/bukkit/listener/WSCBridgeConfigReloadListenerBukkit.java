package de.xxschrandxx.wsc.wscjcoins.bukkit.listener;

import java.util.logging.Level;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.WSCBridgeConfigReloadEventBukkit;
import de.xxschrandxx.wsc.wscjcoins.bukkit.MinecraftJCoinsBukkit;
import de.xxschrandxx.wsc.wscjcoins.bukkit.api.event.WSCJCoinsConfigReloadEventBukkit;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;

public class WSCBridgeConfigReloadListenerBukkit implements Listener {
    @EventHandler
    public void onConfigReload(WSCBridgeConfigReloadEventBukkit event) {
        MinecraftJCoinsBukkit instance = MinecraftJCoinsBukkit.getInstance();
        String configStart = instance.getConfiguration().getString(MinecraftJCoinsVars.Configuration.LangCmdReloadConfigStart);
        event.getSender().sendMessage(configStart);
        if (!instance.reloadConfiguration(event.getSender())) {
                String configError = instance.getConfiguration().getString(MinecraftJCoinsVars.Configuration.LangCmdReloadConfigError);
                event.getSender().sendMessage(configError);
                instance.getLogger().log(Level.WARNING, "Could not load config.yml!");
            return;
        }
        String configSuccess = instance.getConfiguration().getString(MinecraftJCoinsVars.Configuration.LangCmdReloadConfigSuccess);
        event.getSender().sendMessage(configSuccess);
        instance.getServer().getPluginManager().callEvent(new WSCJCoinsConfigReloadEventBukkit(event.getSender()));
    }
}
