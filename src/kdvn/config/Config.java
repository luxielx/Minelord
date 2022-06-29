/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.FileConfigurationOptions
 *  org.bukkit.plugin.Plugin
 */
package kdvn.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.plugin.Plugin;

public class Config {
    public static void loadConfig(Plugin plugin) {
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }
}

