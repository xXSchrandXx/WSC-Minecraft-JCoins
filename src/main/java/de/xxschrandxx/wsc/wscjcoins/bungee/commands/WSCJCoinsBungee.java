package de.xxschrandxx.wsc.wscjcoins.bungee.commands;

import de.xxschrandxx.wsc.wscbridge.bungee.api.command.SenderBungee;
import de.xxschrandxx.wsc.wscjcoins.bungee.MinecraftJCoinsBungee;
import de.xxschrandxx.wsc.wscjcoins.core.commands.WSCJCoins;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class WSCJCoinsBungee extends Command {
    public WSCJCoinsBungee(String name) {
        super(name);
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        MinecraftJCoinsBungee instance = MinecraftJCoinsBungee.getInstance();
        SenderBungee sb = new SenderBungee(sender, instance);
        new WSCJCoins(instance).execute(sb, args);;
    }
}
