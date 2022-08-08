package de.xxschrandxx.wsc.wscjcoins.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.xxschrandxx.wsc.wscjcoins.bukkit.MinecraftJCoinsBukkit;
import de.xxschrandxx.wsc.wscjcoins.bukkit.api.SenderBukkit;
import de.xxschrandxx.wsc.wscjcoins.core.commands.JCoins;

public class WSCJCoins implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        SenderBukkit sb = new SenderBukkit(sender);
        new JCoins(MinecraftJCoinsBukkit.getInstance()).execute(sb, args);
        return true;
    }
}
