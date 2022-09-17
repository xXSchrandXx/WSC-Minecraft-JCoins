package de.xxschrandxx.wsc.wscjcoins.bukkit.api.event;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.WSCBridgeConfigReloadEventBukkit;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class WSCJCoinsConfigReloadEventBukkit extends WSCBridgeConfigReloadEventBukkit {
    public WSCJCoinsConfigReloadEventBukkit(ISender<?> sender) {
        super(sender);
    }   
}
