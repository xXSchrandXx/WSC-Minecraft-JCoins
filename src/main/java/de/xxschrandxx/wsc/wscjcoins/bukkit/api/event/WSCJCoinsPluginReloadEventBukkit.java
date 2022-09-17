package de.xxschrandxx.wsc.wscjcoins.bukkit.api.event;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.WSCBridgePluginReloadEventBukkit;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class WSCJCoinsPluginReloadEventBukkit extends WSCBridgePluginReloadEventBukkit {
    public WSCJCoinsPluginReloadEventBukkit(ISender<?> sender) {
        super(sender);
    }    
}
