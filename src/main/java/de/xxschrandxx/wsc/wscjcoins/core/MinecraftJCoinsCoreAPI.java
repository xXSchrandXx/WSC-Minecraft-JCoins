package de.xxschrandxx.wsc.wscjcoins.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class MinecraftJCoinsCoreAPI {

    private final URL url;
    private final String key;
    protected final Logger logger;
    private final Gson gson = new Gson();
    private final static ArrayList<UUID> uuids = new ArrayList<UUID>();
 
    public MinecraftJCoinsCoreAPI(String url, String key, Logger logger) throws MalformedURLException {
        this.url = new URL(url);
        this.key = key;
        this.logger = logger;
        if (!this.url.getProtocol().equals("https")) {
            throw new MalformedURLException("Only https is supportet. Given protocol: \"" + this.url.getProtocol() + "\"");
        }
    }

    public boolean addJCoins(UUID uuid, Integer amount) throws SocketTimeoutException, IOException {
        URLConnection c = this.url.openConnection();
        if (!(c instanceof HttpsURLConnection)) {
            throw new IOException("opened connection is not an HttpsURLConnection.");
        }
        HttpsURLConnection connection = (HttpsURLConnection) c;

        String boundary = UUID.randomUUID().toString();

        String postData =
        "--" + boundary + "\r\n" +
        "Content-Disposition: form-data; name=\"key\"" + "\r\n" +
        "Content-Type: text/plain; charset=UTF-8" + "\r\n" +
        "\r\n" +
        this.key + "\r\n" +
        "--" + boundary + "\r\n" +
        "Content-Disposition: form-data; name=\"uuid\"" + "\r\n" +
        "Content-Type: text/plain; charset=UTF-8" + "\r\n" +
        "\r\n" +
        uuid.toString() + "\r\n" +
        "--" + boundary + "\r\n" +
        "Content-Disposition: form-data; name=\"amount\"" + "\r\n" +
        "Content-Type: text/plain; charset=UTF-8" + "\r\n" +
        "\r\n" +
        amount + "\r\n" +
        "--" + boundary + "--" + "\r\n"
        ;

        byte[] postDataByes = postData.getBytes();
        int postDataLength = postDataByes.length;
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.getOutputStream().write(postDataByes);
        connection.getOutputStream().flush();
        connection.getOutputStream().close();

        connection.connect();

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("Response code: " + connection.getResponseCode());
        }

        byte[] responseBytes = connection.getInputStream().readAllBytes();
        String responseString = new String(responseBytes, StandardCharsets.UTF_8);

        HashMap<String, Object> response = this.gson.fromJson(responseString, new TypeToken<HashMap<String, Object>>(){}.getType());

        if (!response.containsKey("valid")) {
            throw new IOException("Response does not contain key \"valid\".");
        }
        Object valid = response.get("valid");
        if (valid instanceof Boolean) {
            return (Boolean) valid;
        }
        else {
            throw new IOException("Response value from key \"valid\" is not a boolean.");
        }
    }

    public boolean addJCoins(ArrayList<UUID> uuids, Integer amount) throws SocketTimeoutException, IOException {
        URLConnection c = this.url.openConnection();
        if (!(c instanceof HttpsURLConnection)) {
            throw new IOException("opened connection is not an HttpsURLConnection.");
        }
        HttpsURLConnection connection = (HttpsURLConnection) c;

        String boundary = UUID.randomUUID().toString();

        String postData =
        "--" + boundary + "\r\n" +
        "Content-Disposition: form-data; name=\"key\"" + "\r\n" +
        "Content-Type: text/plain; charset=UTF-8" + "\r\n" +
        "\r\n" +
        this.key + "\r\n" +
        "--" + boundary + "\r\n" +
        "Content-Disposition: form-data; name=\"uuid\"" + "\r\n" +
        "Content-Type: text/plain; charset=UTF-8" + "\r\n" +
        "\r\n" +
        // Style: "[UUID1, UUID2, UUID3]"
        uuids.toString() + "\r\n" +
        "--" + boundary + "\r\n" +
        "Content-Disposition: form-data; name=\"amount\"" + "\r\n" +
        "Content-Type: text/plain; charset=UTF-8" + "\r\n" +
        "\r\n" +
        amount + "\r\n" +
        "--" + boundary + "--" + "\r\n"
        ;

        byte[] postDataByes = postData.getBytes();
        int postDataLength = postDataByes.length;
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.getOutputStream().write(postDataByes);
        connection.getOutputStream().flush();
        connection.getOutputStream().close();

        connection.connect();

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("Response code: " + connection.getResponseCode());
        }

        byte[] responseBytes = connection.getInputStream().readAllBytes();
        String responseString = new String(responseBytes, StandardCharsets.UTF_8);

        HashMap<String, Object> response = this.gson.fromJson(responseString, new TypeToken<HashMap<String, Object>>(){}.getType());

        if (!response.containsKey("valid")) {
            throw new IOException("Response does not contain key \"valid\".");
        }
        Object valid = response.get("valid");
        if (valid instanceof Boolean) {
            return (Boolean) valid;
        }
        else {
            throw new IOException("Response value from key \"valid\" is not a boolean.");
        }
    }

    public void clear() {
        uuids.clear();
    }

    public boolean addUUID(UUID uuid) {
        return uuids.add(uuid);
    }

    public boolean removeUUID(UUID uuid) {
        return uuids.remove(uuid);
    }

    public Runnable getRunnable(Integer amount) {
        return new Runnable() {
            public void run() {
                try {
                    addJCoins(uuids, amount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}