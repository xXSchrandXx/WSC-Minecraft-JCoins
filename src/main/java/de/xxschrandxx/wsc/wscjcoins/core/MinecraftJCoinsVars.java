package de.xxschrandxx.wsc.wscjcoins.core;

import java.util.logging.Logger;

import de.xxschrandxx.wsc.wscbridge.core.api.configuration.AbstractConfiguration;
import de.xxschrandxx.wsc.wscbridge.core.api.configuration.IConfiguration;

public class MinecraftJCoinsVars extends AbstractConfiguration {
    public static boolean startConfig(IConfiguration<?> configuration, Logger logger) {
        return startConfig(configuration, Configuration.class, defaults.class, logger);
    }

    public static class Configuration {
        // universal
        // url
        public static final String url ="url";
        // timedJCoins.enabled
        public static final String timedJCoinsEnabled = "timedJCoins.enabled";
        // timedJCoins.interval
        public static final String timedJCoinsInterval = "timedJCoins.interval";
        // timedJCoins.amount
        public static final String timedJCoinsAmount = "timedJCoins.amount";

        // permission
        // permission.command.wscjcoins
        public static final String PermCmdWSCJCoins = "permission.command.wscjcoins";

        // language
        // language.timedJCoins
        public static final String LangTimedJCoins = "language.timedJCoins";
        // language.command.noperm
        public static final String LangCmdNoPerm = "language.command.noperm";
        // language.command.reload.config.start
        public static final String LangCmdReloadConfigStart = "language.command.reload.config.start";
        // language.command.reload.config.error
        public static final String LangCmdReloadConfigError = "language.command.reload.config.error";
        // language.command.reload.config.success
        public static final String LangCmdReloadConfigSuccess = "language.command.reload.config.success";
        // language.command.reload.api.start
        public static final String LangCmdReloadAPIStart = "language.command.reload.api.start";
        // language.command.reload.api.success
        public static final String LangCmdReloadAPISuccess = "language.command.reload.api.success";
        // language.command.usage
        public static final String LangCmdUsage = "language.command.usage";
        // language.command.noplayer
        public static final String LangCmdNoPlayer = "language.command.noplayer";
        // language.command.noint
        public static final String LangCmdNoInt = "language.command.noint";
        // language.command.success
        public static final String LangCmdSuccess = "language.command.success";
        // language.command.error
        public static final String LangCmdError = "language.command.error";
    }
    // Default values
    public static final class defaults {
        // universal
        // url
        public static final String url = "https://example.domain/index.php?minecraft-jcoins-code/";
        // timedJCoins.enabled
        public static final Boolean timedJCoinsEnabled = true;
        // timedJCoins.interval
        public static final Integer timedJCoinsInterval = 5;
        // timedJCoins.amount
        public static final Integer timedJCoinsAmount = 5;

        // permission
        // permission.command.wscjcoins
        public static final String PermCmdWSCJCoins = "wscjcoins.command.wscjcoins";

        // language
        // language.timedJCoins
        public static final String LangTimedJCoins = "&8[&6WSC-JCoins&8]&7 You got &a%amount%&7 JCoins because you were on the server for %minutes% minutes.";
        // language.command.noperm
        public static final String LangCmdNoPerm = "&8[&6WSC-JCoins&8]&c You don't have permission to do this.";
        // language.command.playeronly
        public static final String LangCmdPlayerOnly = "&8[&6WSC-JCoins&8]&c You need to be a player.";
        // language.command.reload.config.start
        public static final String LangCmdReloadConfigStart = "&8[&6WSC-JCoins&8]&7 Reloading configuration.";
        // language.command.reload.config.error
        public static final String LangCmdReloadConfigError = "&8[&6WSC-JCoins&8]&e Reloading configuration failed.";
        // language.command.reload.config.success
        public static final String LangCmdReloadConfigSuccess = "&8[&6WSC-JCoins&8]&7 Configuration reloaded successfully.";
        // language.command.reload.api.start
        public static final String LangCmdReloadAPIStart = "&8[&6WSC-JCoins&8]&7 Reloading API.";
        // language.command.reload.api.success
        public static final String LangCmdReloadAPISuccess = "&8[&6WSC-JCoins&8]&7 API reloaded successfully.";
        // language.command.usage
        public static final String LangCmdUsage = "&8[&6WSC-JCoins&8]&7 /wscjcoins <Player> <Amount>";
        // language.command.noplayer
        public static final String LangCmdNoPlayer = "&8[&6WSC-JCoins&8]&c Cannot find player.";
        // language.command.noint
        public static final String LangCmdNoInt = "&8[&6WSC-JCoins&8]&c Amount is no integer.";
        // language.command.success
        public static final String LangCmdSuccess = "&8[&6WSC-JCoins&8]&7 JCoins successfilly modified.";
        // language.command.error
        public static final String LangCmdError = "&8[&6WSC-JCoins&8]&7 Could not modify JCoins: %error%";
    }
}
