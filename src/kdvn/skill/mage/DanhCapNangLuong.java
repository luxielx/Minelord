/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDeathEvent
 *  org.bukkit.plugin.Plugin
 */
package kdvn.skill.mage;

import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import kdvn.phepthuat.NangLuong;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

public class DanhCapNangLuong {
    public static final String NAME = "DanhCapNangLuong";
    private static final int LEVEL = 100;

    public static int getMinLevelOfSkill(Player player) {
        return 100;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = DanhCapNangLuong.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 100 + 50 * point;
    }

    public static int getPoint(Player player) {
        return SkillMageConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "DanhCapNangLuong");
    }

    public static void add1Point(Player player) {
        if (DanhCapNangLuong.getPoint(player) == 3) {
            return;
        }
        SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "DanhCapNangLuong", (Object)(DanhCapNangLuong.getPoint(player) + 1));
        SkillMageConfig.saveConfig();
    }

    public static void doIt(EntityDeathEvent e) {
        if (e.getEntity() instanceof LivingEntity && e.getEntity().getKiller() instanceof Player) {
            Player player = e.getEntity().getKiller();
            if (DanhCapNangLuong.getPoint(player) == 0) {
                return;
            }
            int manaStole = 5;
            if (DanhCapNangLuong.getPoint(player) == 2) {
                manaStole = 7;
            } else if (DanhCapNangLuong.getPoint(player) == 3) {
                manaStole = 10;
            }
            NangLuong.addNangLuong(player, manaStole);
            player.sendMessage("\u00a7a\u0110\u00e1nh c\u1eafp \u00a73" + manaStole + " \u00a73N\u0103ng L\u01b0\u1ee3ng t\u1eeb " + e.getEntity().getName());
        }
    }
}

