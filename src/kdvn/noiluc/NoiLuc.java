/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 */
package kdvn.noiluc;

import java.util.HashMap;
import kdvn.config.NoiLucConfig;
import kdvn.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class NoiLuc {
    public static final int DEFAULT_NoiLuc = 10;
    private static HashMap<Player, Integer> NoiLuc = new HashMap();

    public static int getNoiLuc(Player player) {
        if (!NoiLuc.containsKey((Object)player)) {
            return 0;
        }
        return NoiLuc.get((Object)player);
    }

    public static int getMaxNoiLuc(Player player) {
        int nl = player.getLevel() + 10;
        return nl;
    }

    public static void setMaxNoiLuc(Player player, int p) {
        NoiLucConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName(), (Object)p);
        NoiLucConfig.saveConfig();
    }

    public static void setNoiLuc(Player player, int p) {
        NoiLuc.put(player, p);
    }
}

