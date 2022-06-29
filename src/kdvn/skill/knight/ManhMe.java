/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.plugin.Plugin
 */
package kdvn.skill.knight;

import kdvn.classes.ClassSetting;
import kdvn.config.SkillKnightConfig;
import kdvn.main.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class ManhMe {
    public static final String NAME = "ManhMe";
    private static final int LEVEL = 100;

    public static int getMinLevelOfSkill(Player player) {
        return 100;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = ManhMe.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 100 + 50 * point;
    }

    public static int getPoint(Player player) {
        return SkillKnightConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "ManhMe");
    }

    public static void add1Point(Player player) {
        if (ManhMe.getPoint(player) == 3) {
            return;
        }
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "ManhMe", (Object)(ManhMe.getPoint(player) + 1));
        SkillKnightConfig.saveConfig();
    }

    public static double getDamage(Player player, double damage) {
        if (ManhMe.getPoint(player) == 0) {
            return damage;
        }
        double st = damage * 0.8999999761581421;
        if (ManhMe.getPoint(player) == 2) {
            st *= 0.8500000238418579;
        } else if (ManhMe.getPoint(player) == 3) {
            st *= 0.800000011920929;
        }
        return st;
    }

    public static void doIt(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player)e.getEntity();
            if (!ClassSetting.getClass(player).equals("knight")) {
                return;
            }
            e.setDamage(ManhMe.getDamage(player, e.getDamage()));
        }
    }
}

