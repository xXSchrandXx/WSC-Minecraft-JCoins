package de.xxschrandxx.wsc.wscjcoins.bukkit.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.entity.Player;

import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsCoreAPI;

public class MinecraftJCoinsBukkitAPI extends MinecraftJCoinsCoreAPI {
    public MinecraftJCoinsBukkitAPI(String url, String key, Logger logger) throws MalformedURLException {
        super(url, key, logger);
    }
    public boolean addJCoins(Player player, Integer amount) throws SocketTimeoutException, IOException {
        return addJCoins(player.getUniqueId(), amount);
     }
     public boolean addJCoinsB(ArrayList<Player> players, Integer amount) throws SocketTimeoutException, IOException {
        ArrayList<UUID> uuids = new ArrayList<UUID>();
        for (Player player : players) {
            uuids.add(player.getUniqueId());
        }
        return addJCoins(uuids, amount);
    }
    public boolean addPlayer(Player player) {
        return addUUID(player.getUniqueId());
    }
    public boolean removePlayer(Player player) {
        return removeUUID(player.getUniqueId());
    }
}
