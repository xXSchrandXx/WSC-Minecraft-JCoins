package de.xxschrandxx.wsc.wscjcoins.core.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.util.UUID;

import de.xxschrandxx.wsc.wscbridge.core.api.Response;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public interface IMinecraftJCoinsCoreAPI {
    public void addTask(ISender<?> sender);
    public void removeTask(ISender<?> sender);
    public Response<String, Object> sendJCoins(UUID uuid, Integer amount) throws MalformedURLException, UnknownServiceException, SocketTimeoutException, IOException;
}
