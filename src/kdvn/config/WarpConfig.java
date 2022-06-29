/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 */
package kdvn.config;

import java.io.File;
import java.io.IOException;
import kdvn.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class WarpConfig {
    private static final String fileName = "/Warps/Warp.yml";
    private static File file = null;
    private static FileConfiguration secondConfig = null;

    public static void setUpConfig() {
        file = new File(Main.plugin.getDataFolder(), "/Warps/Warp.yml");
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

    public static FileConfiguration getConfig() {
        if (secondConfig == null) {
            WarpConfig.setUpConfig();
        }
        return secondConfig;
    }
}

