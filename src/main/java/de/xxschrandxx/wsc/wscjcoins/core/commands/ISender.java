package de.xxschrandxx.wsc.wscjcoins.core.commands;

public interface ISender {
    public void sendMessage(String string);
    public boolean hasPermission(String permission);
    public void send(String path);
}
