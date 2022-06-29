/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package kdvn.phepthuat;

import java.util.HashMap;
import kdvn.tiemnang.PhepThuat;
import org.bukkit.entity.Player;

public class NangLuong {
    public static final int DEFAULT_NANGLUONG = 40;
    private static HashMap<Player, Integer> NangLuong = new HashMap();

    public static int getNangLuong(Player player) {
        if (!NangLuong.containsKey((Object)player)) {
            return 0;
        }
        return NangLuong.get((Object)player);
    }

    public static int getMaxNangLuong(Player player) {
        int NoiLuc2 = 40 + PhepThuat.getAddedMana(player);
        return NoiLuc2;
    }

    public static void setNangLuong(Player player, int p) {
        NangLuong.put(player, p);
    }

    public static void addNangLuong(Player player, int mana) {
        if (NangLuong.getNangLuong(player) + mana > NangLuong.getMaxNangLuong(player)) {
            NangLuong.setNangLuong(player, NangLuong.getMaxNangLuong(player));
            return;
        }
        NangLuong.setNangLuong(player, NangLuong.getNangLuong(player) + mana);
    }
}

