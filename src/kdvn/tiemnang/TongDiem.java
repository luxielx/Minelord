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
import kdvn.tiemnang.NhanhNhen;
import kdvn.tiemnang.PhepThuat;
import kdvn.tiemnang.SucManh;
import kdvn.tiemnang.TheLuc;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TongDiem {
    public static int getPoint(Player player) {
        int point = TiemNangConfig.getConfig((Plugin)Main.plugin).getInt("TongDiem." + player.getName());
        return point;
    }

    public static void setPoint(Player player, int point) {
        TiemNangConfig.getConfig((Plugin)Main.plugin).set("TongDiem." + player.getName(), (Object)point);
        TiemNangConfig.saveConfig();
    }

    public static void addOnePoint(Player player) {
        TiemNangConfig.getConfig((Plugin)Main.plugin).set("TongDiem." + player.getName(), (Object)(TongDiem.getPoint(player) + 1));
        TiemNangConfig.saveConfig();
    }

    public static void add5Point(Player player) {
        TiemNangConfig.getConfig((Plugin)Main.plugin).set("TongDiem." + player.getName(), (Object)(TongDiem.getPoint(player) + 5));
        TiemNangConfig.saveConfig();
    }

    public static int getRemain(Player player) {
        int remain = TongDiem.getPoint(player) - SucManh.getPoint(player) - TheLuc.getPoint(player) - PhepThuat.getPoint(player) - NhanhNhen.getPoint(player);
        return remain;
    }
}

