package de.xxschrandxx.wsc.wscjcoins.core.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.UUID;

import de.xxschrandxx.wsc.wscbridge.core.api.MinecraftBridgeCoreAPI;
import de.xxschrandxx.wsc.wscbridge.core.api.Response;

public class MinecraftJCoinsCoreAPI {
    public static Response<String, Object> sendJCoins(MinecraftBridgeCoreAPI api, URL url, UUID uuid, Integer amount) throws MalformedURLException, UnknownServiceException, SocketTimeoutException, IOException {
        HashMap<String, Object> postData = new HashMap<String, Object>();
        postData.put("uuid", uuid.toString());
        postData.put("amount", amount);
        Response<String, Object> request = api.requestObject(url, postData);
        return request;
    }
}
