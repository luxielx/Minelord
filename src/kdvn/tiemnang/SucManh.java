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

public class SucManh {
    public static final float DAMAGE = 0.2f;

    public static int getPoint(Player player) {
        int point = TiemNangConfig.getConfig((Plugin)Main.plugin).getInt("SucManh." + player.getName());
        return point;
    }

    public static void setPoint(Player player, int point) {
        TiemNangConfig.getConfig((Plugin)Main.plugin).set("SucManh." + player.getName(), (Object)point);
        TiemNangConfig.saveConfig();
    }

    public static void addOnePoint(Player player) {
        TiemNangConfig.getConfig((Plugin)Main.plugin).set("SucManh." + player.getName(), (Object)(SucManh.getPoint(player) + 1));
        TiemNangConfig.saveConfig();
    }

    public static float getAddedDamage(Player player) {
        float damage = (float)SucManh.getPoint(player) * 0.2f;
        return damage;
    }
}

