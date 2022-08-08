package de.xxschrandxx.wsc.wscjcoins.bukkit.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.xxschrandxx.wsc.wscjcoins.bukkit.MinecraftJCoinsBukkit;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        MinecraftJCoinsBukkit.getInstance().getAPI().addPlayer(player);
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        MinecraftJCoinsBukkit.getInstance().getAPI().removePlayer(player);
    }
}
