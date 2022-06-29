/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.plugin.Plugin
 */
package kdvn.config;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class TiemNangConfig {
    private static final String fileName = "/Points/TiemNang.yml";
    private static File file = null;
    private static FileConfiguration secondConfig = null;

    public static FileConfiguration TienNang(Plugin plugin) {
        return TiemNangConfig.getConfig(plugin);
    }

    public static void setUpConfig(Plugin plugin) {
        file = new File(plugin.getDataFolder(), "/Points/TiemNang.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        secondConfig = YamlConfiguration.loadConfiguration((File)file);
    }

    public static void reloadConfig() {
        secondConfig = YamlConfiguration.loadConfiguration((File)file);
    }

    public static void saveConfig() {
        try {
            secondConfig.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration getConfig(Plugin plugin) {
        if (secondConfig == null) {
            TiemNangConfig.setUpConfig(plugin);
        }
        return secondConfig;
    }
}

