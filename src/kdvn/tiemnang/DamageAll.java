/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package kdvn.tiemnang;

import kdvn.minelord.player.EnumClassMineLord;
import kdvn.minelord.player.MineLordPlayer;
import kdvn.tiemnang.NhanhNhen;
import kdvn.tiemnang.PhepThuat;
import kdvn.tiemnang.SucManh;
import org.bukkit.entity.Player;

public class DamageAll {
    public static double getDamage(Player player) {
        double damage = SucManh.getAddedDamage(player);
        if (new MineLordPlayer(player).getClassMineLord().equals(EnumClassMineLord.ARCHER.toString())) {
            damage += (double)((float)NhanhNhen.getPoint(player) * 0.1f);
        }
        if (new MineLordPlayer(player).getClassMineLord().equals(EnumClassMineLord.MAGE.toString())) {
            damage += PhepThuat.getDamageOnlyMage(player);
        }
        return damage;
    }
}

