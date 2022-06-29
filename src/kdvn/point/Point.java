/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.black_ixx.playerpoints.PlayerPointsAPI
 *  org.bukkit.entity.Player
 */
package kdvn.point;

import java.util.UUID;
import kdvn.main.Main;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.entity.Player;

public class Point {
    public static int getPoint(Player player) {
        int points = Main.getPP().getAPI().look(player.getName());
        return points;
    }

    public static boolean pointCost(Player player, int points) {
        if (points > Point.getPoint(player)) {
            return false;
        }
        Main.getPP().getAPI().take(player.getUniqueId(), points);
        return true;
    }
}

