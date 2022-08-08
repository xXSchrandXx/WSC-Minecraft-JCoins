package de.xxschrandxx.wsc.wscjcoins.core;

public interface IMinecraftJCoins {
    public boolean start();
    public boolean stop();
    public MinecraftJCoinsCoreAPI getAPI();
    public boolean reloadConfiguration();
    public boolean saveConfiguration();
    public IConfiguration<?> getConfiguration();
}
