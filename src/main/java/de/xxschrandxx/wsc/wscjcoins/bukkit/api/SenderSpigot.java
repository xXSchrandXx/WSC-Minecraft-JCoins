package de.xxschrandxx.wsc.wscjcoins.bukkit.api;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class SenderSpigot extends SenderBukkit {

    public SenderSpigot(CommandSender sender) {
        super(sender);
    }
    
    @Override
    public void sendMessage(String message) {
        this.sender.spigot().sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
    }
}
