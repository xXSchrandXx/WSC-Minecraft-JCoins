package de.xxschrandxx.wsc.wscjcoins.core.commands;

import java.io.IOException;
import java.util.UUID;

import de.xxschrandxx.wsc.wscjcoins.core.IMinecraftJCoins;
import de.xxschrandxx.wsc.wscjcoins.core.MinecraftJCoinsVars;

public class JCoins {

    private IMinecraftJCoins instance;
    public JCoins(IMinecraftJCoins instance) {
        this.instance = instance;
    }

    public void execute(ISender sender, String[] args) {
        if (args.length == 0) {
            sender.send(MinecraftJCoinsVars.Configuration.CommandUsage);
            return;
        }
        switch (args[0].toLowerCase()) {
            case "reload":
                reload(sender);
                break;
            case "add":
                add(sender, args);
                break;
            case "remove":
                remove(sender, args);
                break;
            default:
                sender.send(MinecraftJCoinsVars.Configuration.CommandUsage);
                break;
        }
    }

    protected void reload(ISender sender) {
        if (instance.getConfiguration().getBoolean(MinecraftJCoinsVars.Configuration.Enabled)) {
            sender.send(MinecraftJCoinsVars.Configuration.CommandReloadConfigSuccess);
            if (!instance.stop()) {
                sender.sendMessage(MinecraftJCoinsVars.Configuration.CommandReloadTaskStopFailure);
                return;
            }
        }
        if (!instance.reloadConfiguration()) {
            sender.sendMessage(MinecraftJCoinsVars.Configuration.CommandReloadConfigFailure);
            return;
        }
        if (instance.getConfiguration().getBoolean(MinecraftJCoinsVars.Configuration.Enabled)) {
            sender.sendMessage(MinecraftJCoinsVars.Configuration.CommandReloadTaskStopSuccess);
            if (!instance.start()) {
                sender.sendMessage(MinecraftJCoinsVars.Configuration.CommandReloadTaskStartFailure);
                return ;
            }
            sender.sendMessage(MinecraftJCoinsVars.Configuration.CommandReloadTaskStartSuccess);
        }
    }

    protected void add(ISender sender, String[] args) {
        if (args.length < 3) {
            sender.send(MinecraftJCoinsVars.Configuration.CommandAddUsage);
            return;
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(args[1]);
        }
        catch (IllegalArgumentException e) {
            sender.send(MinecraftJCoinsVars.Configuration.CommandAddInvalidUUID);
            return;
        }
        Integer amount;
        try {
            amount = Integer.parseInt(args[2]);
        }
        catch (NumberFormatException e) {
            sender.send(MinecraftJCoinsVars.Configuration.CommandAddInvalidAmount);
            return;
        }
        if (amount <= 0) {
            sender.send(MinecraftJCoinsVars.Configuration.CommandAddInvalidAmount);
            return;
        }
        boolean valid;
        try {
            valid = instance.getAPI().addJCoins(uuid, amount);
        } catch (IOException e) {
            e.printStackTrace();
            sender.send(MinecraftJCoinsVars.Configuration.CommandAddResponseError);
            return;
        }
        if (!valid) {
            sender.send(MinecraftJCoinsVars.Configuration.CommandAddInvalid);
            return;
        }
        sender.send(MinecraftJCoinsVars.Configuration.CommandAddValid.replace("%amount%", amount.toString()).replace("%taget%", uuid.toString()));
    }

    protected void remove(ISender sender, String[] args) {
        if (args.length < 3) {
            sender.send(MinecraftJCoinsVars.Configuration.CommandRemoveUsage);
            return;
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(args[1]);
        }
        catch (IllegalArgumentException e) {
            sender.send(MinecraftJCoinsVars.Configuration.CommandRemoveInvalidUUID);
            return;
        }
        Integer amount;
        try {
            amount = Integer.parseInt(args[2]);
        }
        catch (NumberFormatException e) {
            sender.send(MinecraftJCoinsVars.Configuration.CommandRemoveInvalidAmount);
            return;
        }
        if (amount <= 0) {
            sender.send(MinecraftJCoinsVars.Configuration.CommandRemoveInvalidAmount);
            return;
        }
        boolean valid;
        try {
            valid = instance.getAPI().addJCoins(uuid, amount);
        } catch (IOException e) {
            e.printStackTrace();
            sender.send(MinecraftJCoinsVars.Configuration.CommandRemoveResponseError);
            return;
        }
        if (!valid) {
            sender.send(MinecraftJCoinsVars.Configuration.CommandRemoveInvalid);
            return;
        }
        sender.send(MinecraftJCoinsVars.Configuration.CommandRemoveValid.replace("%amount%", amount.toString()).replace("%taget%", uuid.toString()));
    }
}
