package de.xxschrandxx.wsc.wscjcoins.bungee.listeners;

import de.xxschrandxx.wsc.wscjcoins.bungee.MinecraftJCoinsBungee;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        MinecraftJCoinsBungee.getInstance().getAPI().addPlayer(player);
    }
    @EventHandler
    public void onPlayerDisconnect(PlayerDisconnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        MinecraftJCoinsBungee.getInstance().getAPI().removePlayer(player);
    }
}
