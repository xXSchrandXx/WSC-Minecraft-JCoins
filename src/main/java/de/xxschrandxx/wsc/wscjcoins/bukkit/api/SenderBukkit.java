package de.xxschrandxx.wsc.wscjcoins.bukkit.api;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import de.xxschrandxx.wsc.wscjcoins.bukkit.MinecraftJCoinsBukkit;
import de.xxschrandxx.wsc.wscjcoins.core.commands.ISender;

public class SenderBukkit implements ISender {

    protected final CommandSender sender;
    public SenderBukkit(CommandSender sender) {
        this.sender = sender;
    }

    public void sendMessage(String message) {
        try {
            Class.forName("org.bukkit.command.CommandSender.Spigot");
            new SenderSpigot(this.sender).sendMessage(message);
        }
        catch (ClassNotFoundException e) {
            this.sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

    public void send(String path) {
        this.sendMessage(MinecraftJCoinsBukkit.getInstance().getConfiguration().getString(path));
    }

    public boolean hasPermission(String permission) {
        return this.sender.hasPermission(permission);
    }
}
