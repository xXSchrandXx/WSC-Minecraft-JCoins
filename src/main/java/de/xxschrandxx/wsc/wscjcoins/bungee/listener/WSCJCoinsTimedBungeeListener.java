package de.xxschrandxx.wsc.wscjcoins.bungee.listener;

import de.xxschrandxx.wsc.wscbridge.bungee.api.command.SenderBungee;
import de.xxschrandxx.wsc.wscjcoins.bungee.MinecraftJCoinsBungee;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class WSCJCoinsTimedBungeeListener implements Listener {
    @EventHandler
    public void onJoin(ServerConnectedEvent event) {
        MinecraftJCoinsBungee instance = MinecraftJCoinsBungee.getInstance();
        SenderBungee sender = new SenderBungee(event.getPlayer(), instance);
        instance.getAPI().addTask(sender);
    }
    @EventHandler
    public void onDisconned(PlayerDisconnectEvent event) {
        MinecraftJCoinsBungee instance = MinecraftJCoinsBungee.getInstance();
        SenderBungee sender = new SenderBungee(event.getPlayer(), instance);
        instance.getAPI().removeTask(sender);
    }
}
