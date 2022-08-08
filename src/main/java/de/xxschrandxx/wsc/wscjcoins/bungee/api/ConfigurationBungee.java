package de.xxschrandxx.wsc.wscjcoins.bungee.api;

import de.xxschrandxx.wsc.wscjcoins.core.IConfiguration;
import net.md_5.bungee.config.Configuration;

public class ConfigurationBungee implements IConfiguration<Configuration> {
    protected final Configuration configuration;
    public ConfigurationBungee() {
        this.configuration = new Configuration();
    }
    public ConfigurationBungee(Configuration configuration) {
        this.configuration = configuration;
    }
    public Configuration getConfiguration() {
        return this.configuration;
    }
    public Object get(String path) {
        return this.configuration.get(path);
    }
    public void set(String path, Object value) {
        this.configuration.set(path, value);
    }
    public boolean getBoolean(String path) {
        return this.configuration.getBoolean(path);
    }
    public String getString(String path) {
        return this.configuration.getString(path);
    }
    public int getInt(String path) {
        return this.configuration.getInt(path);
    }
    public long getLong(String path) {
        return this.configuration.getLong(path);
    }
}
