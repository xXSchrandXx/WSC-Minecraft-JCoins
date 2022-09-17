package de.xxschrandxx.wsc.wscjcoins.bungee.listener;

import de.xxschrandxx.wsc.wscbridge.bungee.api.event.WSCBridgePluginReloadEventBungee;
import de.xxschrandxx.wsc.wscjcoins.bungee.MinecraftJCoinsBungee;
import de.xxschrandxx.wsc.wscjcoins.bungee.api.event.WSCJCoinsPluginReloadEventBungee;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class WSCBridgePluginReloadListenerBungee implements Listener {
    @EventHandler
    public void onPluginReload(WSCBridgePluginReloadEventBungee event) {
        MinecraftJCoinsBungee instance = MinecraftJCoinsBungee.getInstance();
        String apiStart = instance.getConfiguration().getString(MinecraftJCoinsVars.Configuration.LangCmdReloadAPIStart);
        event.getSender().sendMessage(apiStart);
        instance.loadAPI(event.getSender());
        String apiSuccess = instance.getConfiguration().getString(MinecraftJCoinsVars.Configuration.LangCmdReloadAPISuccess);
        event.getSender().sendMessage(apiSuccess);
        instance.getProxy().getPluginManager().callEvent(new WSCJCoinsPluginReloadEventBungee(event.getSender()));
    }
}
