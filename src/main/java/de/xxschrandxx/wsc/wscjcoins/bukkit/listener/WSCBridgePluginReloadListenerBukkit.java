package de.xxschrandxx.wsc.wscjcoins.bukkit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.WSCBridgePluginReloadEventBukkit;
import de.xxschrandxx.wsc.wscjcoins.bukkit.MinecraftJCoinsBukkit;
import de.xxschrandxx.wsc.wscjcoins.bukkit.api.event.WSCJCoinsPluginReloadEventBukkit;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;

public class WSCBridgePluginReloadListenerBukkit implements Listener {
    @EventHandler
    public void onPluginReload(WSCBridgePluginReloadEventBukkit event) {
        MinecraftJCoinsBukkit instance = MinecraftJCoinsBukkit.getInstance();
        String apiStart = instance.getConfiguration().getString(MinecraftJCoinsVars.Configuration.LangCmdReloadAPIStart);
        event.getSender().sendMessage(apiStart);
        instance.loadAPI(event.getSender());
        String apiSuccess = instance.getConfiguration().getString(MinecraftJCoinsVars.Configuration.LangCmdReloadAPISuccess);
        event.getSender().sendMessage(apiSuccess);
        instance.getServer().getPluginManager().callEvent(new WSCJCoinsPluginReloadEventBukkit(event.getSender()));
    }
}
