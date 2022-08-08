package de.xxschrandxx.wsc.wscjcoins.core;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinecraftJCoinsVars {
    public static boolean startConfig(IConfiguration<?> configuration, Logger logger) {
        boolean error = false;
        for (Field constant : Configuration.class.getDeclaredFields()) {
            try {
                if (error) {
                    checkConfiguration(
                        configuration,
                        (String) constant.get(Configuration.class),
                        Configuration.defaults.class.getDeclaredField(constant.getName()).get(Configuration.defaults.class),
                        logger
                        );
                }
                else {
                    error = checkConfiguration(
                        configuration,
                        (String) constant.get(Configuration.class),
                        Configuration.defaults.class.getDeclaredField(constant.getName()).get(Configuration.defaults.class),
                        logger
                        );
                }
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            }
        }
        return error;
    }

    public static boolean checkConfiguration(IConfiguration<?> configuration, String path, Object def, Logger logger) {
        if (configuration.get(path) == null) {
            logger.log(Level.WARNING, path + " is not set. Resetting it.");
            configuration.set(path, def);
            return true;
        }
        else {
            return false;
        }
    }
    public static class Configuration {
        // universal
        // url
        public static final String URL = "url";
        // key
        public static final String Key = "key";
        // enabled
        public static final String Enabled = "enabled";
        // interval
        public static final String Interval = "interval";
        // amount
        public static final String Amount = "amount";
        // locale
        // CommandUsage
        public static final String CommandUsage = "locale.usage";
        // CommandReloadConfigSuccess
        public static final String CommandReloadConfigSuccess = "locale.reload.config.success";
        // CommandReloadConfigFailure
        public static final String CommandReloadConfigFailure = "locale.reload.config.failure";
        // CommandReloadTaskStopSuccess
        public static final String CommandReloadTaskStopSuccess = "locale.reload.task.stop.success";
        // CommandReloadTaskStopFailure
        public static final String CommandReloadTaskStopFailure = "locale.reload.task.stop.failure";
        // CommandReloadTaskStartSuccess
        public static final String CommandReloadTaskStartSuccess = "locale.reload.task.start.success";
        // CommandReloadTaskStartFailure
        public static final String CommandReloadTaskStartFailure = "locale.reload.task.start.failure";
        // CommandAddUsage
        public static final String CommandAddUsage = "locale.add.usage";
        // CommandAddInvalidUUID
        public static final String CommandAddInvalidUUID = "locale.add.invaliduuid";
        // CommandAddInvalidAmount
        public static final String CommandAddInvalidAmount = "locale.add.invalidamount";
        // CommandAddResponseError
        public static final String CommandAddResponseError = "locale.add.responseerror";
        // CommandAddInvalid
        public static final String CommandAddInvalid = "locale.add.invalid";
        // CommandAddValid
        public static final String CommandAddValid = "locale.add.valid";
        // CommandRemoveUsage
        public static final String CommandRemoveUsage = "locale.remove.usage";
        // CommandRemoveInvalidUUID
        public static final String CommandRemoveInvalidUUID = "locale.remove.invaliduuid";
        // CommandRemoveInvalidAmount
        public static final String CommandRemoveInvalidAmount = "locale.remove.invalidamount";
        // CommandRemoveResponseError
        public static final String CommandRemoveResponseError = "locale.remove.responseerror";
        // CommandRemoveInvalid
        public static final String CommandRemoveInvalid = "locale.remove.invalid";
        // CommandRemoveValid
        public static final String CommandRemoveValid = "locale.remove.valid";

        // Default values
        public static final class defaults {
            // universal
            // url
            public static final String URL = "https://example.com/index.php?minecraft-jcoins/";
            // key
            public static final String Key = "MySuperSecretKey";
            // enabled
            public static final Boolean Enabled = false;
            // interval
            public static final Long Interval = Long.valueOf(5);
            // amount
            public static final Integer Amount = 5;
            // locale
            // CommandUsage
            public static final String CommandUsage = "&7Usage: &e/wscjcoins <reload/add/remove> [target] [amount]";
            // CommandReloadConfigSuccess
            public static final String CommandReloadConfigSuccess = "&aSuccessfully reloaded &7config.yml&a.";
            // CommandReloadConfigFailure
            public static final String CommandReloadConfigFailure = "&cFailed to reload &7config.yml&c.";
            // CommandReloadTaskStopSuccess
            public static final String CommandReloadTaskStopSuccess = "&aSuccessfully stopped task.";
            // CommandReloadTaskStopFailure
            public static final String CommandReloadTaskStopFailure = "&cFailed to stop task.";
            // CommandReloadTaskStartSuccess
            public static final String CommandReloadTaskStartSuccess = "&aSuccessfully started task.";
            // CommandReloadTaskStartFailure
            public static final String CommandReloadTaskStartFailure = "&cFailed to start task.";
        // CommandAddUsage
        public static final String CommandAddUsage = "&7Usage: &e/wscjcoins add [target] [amount]";
        // CommandAddInvalidUUID
        public static final String CommandAddInvalidUUID = "&cInvalid UUID format given.";
        // CommandAddInvalidAmount
        public static final String CommandAddInvalidAmount = "&cInvalid amount given.";
        // CommandAddResponseError
        public static final String CommandAddResponseError = "&cInternal error. Check console for more information.";
        // CommandAddInvalid
        public static final String CommandAddInvalid = "&cInvalid request send. Check if the user is linked.";
        // CommandAddValid
        public static final String CommandAddValid = "&aSuccessfully added &e%amount%&a to &e%target%&a`s wallet.";
        // CommandRemoveUsage
        public static final String CommandRemoveUsage = "&7Usage: &e/wscjcoins remove [target] [amount]";
        // CommandRemoveInvalidUUID
        public static final String CommandRemoveInvalidUUID = "&cInvalid UUID format given.";
        // CommandRemoveInvalidAmount
        public static final String CommandRemoveInvalidAmount = "&cInvalid amount given.";
        // CommandRemoveResponseError
        public static final String CommandRemoveResponseError = "&cInternal error. Check console for more information.";
        // CommandRemoveInvalid
        public static final String CommandRemoveInvalid = "&cInvalid request send. Check if the user is linked.";
        // CommandRemoveValid
        public static final String CommandRemoveValid = "&aSuccessfully added &e%amount%&a to &e%target%&a`s wallet.";
        }
    }
}
