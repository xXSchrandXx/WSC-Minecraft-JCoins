package de.xxschrandxx.wsc.wscjcoins.bungee.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsCoreAPI;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MinecraftJCoinsBungeeAPI extends MinecraftJCoinsCoreAPI {
    public MinecraftJCoinsBungeeAPI(String url, String key, Logger logger) throws MalformedURLException {
        super(url, key, logger);
    }
    public boolean addJCoins(ProxiedPlayer player, Integer amount) throws SocketTimeoutException, IOException {
       return addJCoins(player.getUniqueId(), amount);
    }
    public boolean addJCoinsB(ArrayList<ProxiedPlayer> players, Integer amount) throws SocketTimeoutException, IOException {
        ArrayList<UUID> uuids = new ArrayList<UUID>();
        for (ProxiedPlayer player : players) {
            uuids.add(player.getUniqueId());
        }
        return addJCoins(uuids, amount);
    }
    public boolean addPlayer(ProxiedPlayer player) {
        return addUUID(player.getUniqueId());
    }
    public boolean removePlayer(ProxiedPlayer player) {
        return removeUUID(player.getUniqueId());
    }
}
