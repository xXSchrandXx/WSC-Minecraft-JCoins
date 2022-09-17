package de.xxschrandxx.wsc.wscjcoins.bungee.api.event;

import de.xxschrandxx.wsc.wscbridge.bungee.api.event.WSCBridgeConfigReloadEventBungee;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class WSCJCoinsConfigReloadEventBungee extends WSCBridgeConfigReloadEventBungee {
    public WSCJCoinsConfigReloadEventBungee(ISender<?> sender) {
        super(sender);
    }   
}
