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

public class SkillArcherConfig {
    private static final String fileName = "/Skills/SkillArcher.yml";
    private static File file = null;
    private static FileConfiguration secondConfig = null;

    public static FileConfiguration TienNang(Plugin plugin) {
        return SkillArcherConfig.getConfig(plugin);
    }

    public static void setUpConfig(Plugin plugin) {
        file = new File(plugin.getDataFolder(), "/Skills/SkillArcher.yml");
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
            SkillArcherConfig.setUpConfig(plugin);
        }
        return secondConfig;
    }
}

