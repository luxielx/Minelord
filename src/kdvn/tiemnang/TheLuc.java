/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 */
package kdvn.tiemnang;

import kdvn.config.TiemNangConfig;
import kdvn.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TheLuc {
    public static final double HEALTH = 0.4000000059604645;

    public static int getPoint(Player player) {
        int point = TiemNangConfig.getConfig((Plugin)Main.plugin).getInt("TheLuc." + player.getName());
        return point;
    }

    public static void setPoint(Player player, int point) {
        TiemNangConfig.getConfig((Plugin)Main.plugin).set("TheLuc." + player.getName(), (Object)point);
    }

    public static void addOnePoint(Player player) {
        TiemNangConfig.getConfig((Plugin)Main.plugin).set("TheLuc." + player.getName(), (Object)(TheLuc.getPoint(player) + 1));
        TiemNangConfig.saveConfig();
    }

    public static float getAddedHealth(Player player) {
        return (float)TheLuc.getPoint(player) * 0.2f;
    }

    public static int getAddedNoiLuc(Player player) {
        return TheLuc.getPoint(player) * 1;
    }
}

