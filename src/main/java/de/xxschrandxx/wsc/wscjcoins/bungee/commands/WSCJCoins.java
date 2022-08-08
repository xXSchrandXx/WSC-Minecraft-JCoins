package de.xxschrandxx.wsc.wscjcoins.bungee.commands;

import de.xxschrandxx.wsc.wscjcoins.bungee.MinecraftJCoinsBungee;
import de.xxschrandxx.wsc.wscjcoins.bungee.api.SenderBungee;
import de.xxschrandxx.wsc.wscjcoins.core.commands.JCoins;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class WSCJCoins extends Command {

    public WSCJCoins(String label) {
        super(label);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        SenderBungee sb = new SenderBungee(sender);
        new JCoins(MinecraftJCoinsBungee.getInstance()).execute(sb, args);
    }
}
