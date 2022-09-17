package de.xxschrandxx.wsc.wscjcoins.bungee.listener;

import java.util.logging.Level;

import de.xxschrandxx.wsc.wscbridge.bungee.api.event.WSCBridgeConfigReloadEventBungee;
import de.xxschrandxx.wsc.wscjcoins.bungee.MinecraftJCoinsBungee;
import de.xxschrandxx.wsc.wscjcoins.bungee.api.event.WSCJCoinsConfigReloadEventBungee;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class WSCBridgeConfigReloadListenerBungee implements Listener {
    @EventHandler
    public void onConfigReload(WSCBridgeConfigReloadEventBungee event) {
        MinecraftJCoinsBungee instance = MinecraftJCoinsBungee.getInstance();
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
        instance.getProxy().getPluginManager().callEvent(new WSCJCoinsConfigReloadEventBungee(event.getSender()));
    }
}
