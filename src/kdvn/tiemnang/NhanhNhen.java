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

public class NhanhNhen {
    public static final float DAMAGE_ARCHER = 0.1f;
    public static final float NEDON = 0.1f;

    public static int getPoint(Player player) {
        int point = TiemNangConfig.getConfig((Plugin)Main.plugin).getInt("NhanhNhen." + player.getName());
        return point;
    }

    public static void setPoint(Player player, int point) {
        TiemNangConfig.getConfig((Plugin)Main.plugin).set("NhanhNhen." + player.getName(), (Object)point);
        TiemNangConfig.saveConfig();
    }

    public static void addOnePoint(Player player) {
        TiemNangConfig.getConfig((Plugin)Main.plugin).set("NhanhNhen." + player.getName(), (Object)(NhanhNhen.getPoint(player) + 1));
        TiemNangConfig.saveConfig();
    }

    public static float getNeDon(Player player) {
        float tiLe = (float)NhanhNhen.getPoint(player) * 0.1f;
        if (tiLe > 80.0f) {
            tiLe = 80.0f;
        }
        return tiLe;
    }

    public static float getTocDo(Player player) {
        float speed = 0.2f + (float)NhanhNhen.getPoint(player) * 0.001f;
        if (speed > 3.0f) {
            speed = 3.0f;
        }
        return speed;
    }

    public static double getTocDoBan(Player player) {
        double speed = 3.0 - (double)NhanhNhen.getPoint(player) * 0.025;
        if (speed < 0.800000011920929) {
            speed = 0.800000011920929;
        }
        return speed;
    }
}

