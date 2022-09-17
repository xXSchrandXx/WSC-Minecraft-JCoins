package de.xxschrandxx.wsc.wscjcoins.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;
import de.xxschrandxx.wsc.wscjcoins.bukkit.MinecraftJCoinsBukkit;
import de.xxschrandxx.wsc.wscjcoins.core.commands.WSCJCoins;

public class WSCJCoinsBukkit implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MinecraftJCoinsBukkit instance = MinecraftJCoinsBukkit.getInstance();
        SenderBukkit sb = new SenderBukkit(sender, instance);
        new WSCJCoins(instance).execute(sb, args);
        return true;
    }
}
