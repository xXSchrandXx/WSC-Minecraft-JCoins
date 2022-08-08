package de.xxschrandxx.wsc.wscjcoins.bungee.api;

import de.xxschrandxx.wsc.wscjcoins.bungee.MinecraftJCoinsBungee;
import de.xxschrandxx.wsc.wscjcoins.core.commands.ISender;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

public class SenderBungee implements ISender {

    protected final CommandSender sender;
    public SenderBungee(CommandSender sender) {
        this.sender = sender;
    }

    public void sendMessage(String message) {
        this.sender.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public void send(String path) {
        this.sendMessage(MinecraftJCoinsBungee.getInstance().getConfiguration().getString(path));
    }

    public boolean hasPermission(String permission) {
        return this.sender.hasPermission(permission);
    }
    
}
