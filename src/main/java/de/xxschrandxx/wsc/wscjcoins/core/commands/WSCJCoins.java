package de.xxschrandxx.wsc.wscjcoins.core.commands;

import java.io.IOException;
import java.util.UUID;

import de.xxschrandxx.wsc.wscbridge.core.IMinecraftBridgePlugin;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;
import de.xxschrandxx.wsc.wscjcoins.core.api.IMinecraftJCoinsCoreAPI;

public class WSCJCoins {
    private IMinecraftBridgePlugin<? extends IMinecraftJCoinsCoreAPI> instance;
    public WSCJCoins(IMinecraftBridgePlugin<? extends IMinecraftJCoinsCoreAPI> instance) {
        this.instance = instance;
    }
    public void execute(ISender<?> sender, String[] args) {
        if (!sender.hasPermission(instance.getConfiguration().getString(MinecraftJCoinsVars.Configuration.PermCmdWSCJCoins))) {
            sender.send(MinecraftJCoinsVars.Configuration.LangCmdNoPerm);
            return;
        }
        if (args.length != 2) {
            sender.send(MinecraftJCoinsVars.Configuration.LangCmdUsage);
            return;
        }
        ISender<?> target = null;
        try {
            UUID uuid = UUID.fromString(args[0]);
            target = instance.getAPI().getSender(uuid, instance);
            }
        catch (IllegalArgumentException e) {
            target = instance.getAPI().getSender(args[0], instance);
        }
        if (target == null) {
            sender.send(MinecraftJCoinsVars.Configuration.LangCmdNoPlayer);
            return;
        }
        Integer amount;
        try {
            amount = Integer.valueOf(args[1]);
        }
        catch (NumberFormatException e) {
            sender.send(MinecraftJCoinsVars.Configuration.LangCmdNoInt);
            return;
        }
        try {
            instance.getAPI().sendJCoins(sender.getUniqueId(), amount);
            sender.send(MinecraftJCoinsVars.Configuration.LangCmdSuccess);
        } catch (IOException e) {
            String message = instance.getConfiguration().getString(MinecraftJCoinsVars.Configuration.LangCmdError);
            sender.sendMessage(message.replaceAll("%error%", e.getMessage()));
        }
    }
}
