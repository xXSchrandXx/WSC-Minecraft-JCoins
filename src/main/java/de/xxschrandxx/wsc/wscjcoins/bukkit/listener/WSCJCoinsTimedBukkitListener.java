package de.xxschrandxx.wsc.wscjcoins.bukkit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;
import de.xxschrandxx.wsc.wscjcoins.bukkit.MinecraftJCoinsBukkit;

public class WSCJCoinsTimedBukkitListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        MinecraftJCoinsBukkit instance = MinecraftJCoinsBukkit.getInstance();
        SenderBukkit sender = new SenderBukkit(event.getPlayer(), instance);
        instance.getAPI().addTask(sender);
    }
    @EventHandler
    public void onDisconned(PlayerQuitEvent event) {
        MinecraftJCoinsBukkit instance = MinecraftJCoinsBukkit.getInstance();
        SenderBukkit sender = new SenderBukkit(event.getPlayer(), instance);
        instance.getAPI().removeTask(sender);
    }
}
