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
import kdvn.minelord.player.EnumClassMineLord;
import kdvn.minelord.player.MineLordPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PhepThuat {
    public static final int NANGLUONG = 5;
    public static final double DAMAGE = 0.18;

    public static int getPoint(Player player) {
        int point = TiemNangConfig.getConfig((Plugin)Main.plugin).getInt("PhepThuat." + player.getName());
        return point;
    }

    public static void setPoint(Player player, int point) {
        TiemNangConfig.getConfig((Plugin)Main.plugin).set("PhepThuat." + player.getName(), (Object)point);
        TiemNangConfig.saveConfig();
    }

    public static void addOnePoint(Player player) {
        TiemNangConfig.getConfig((Plugin)Main.plugin).set("PhepThuat." + player.getName(), (Object)(PhepThuat.getPoint(player) + 1));
        TiemNangConfig.saveConfig();
    }

    public static int getAddedMana(Player player) {
        int nl = PhepThuat.getPoint(player) * 5;
        if (new MineLordPlayer(player).getClassMineLord().equals(EnumClassMineLord.MAGE.toString())) {
            nl += PhepThuat.getPoint(player) * 5;
        }
        return nl;
    }

    public static double getDamageOnlyMage(Player player) {
        return (double)PhepThuat.getPoint(player) * 0.18;
    }
}

