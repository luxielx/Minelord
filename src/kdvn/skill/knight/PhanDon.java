/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.plugin.Plugin
 */
package kdvn.skill.knight;

import kdvn.classes.ClassSetting;
import kdvn.config.SkillKnightConfig;
import kdvn.main.Main;
import kdvn.safezone.SafeZone;
import kdvn.settings.SettingMethod;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class PhanDon {
    public static final String NAME = "PhanDon";
    private static final int LEVEL = 200;

    public static int getMinLevelOfSkill(Player player) {
        return 200;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = PhanDon.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 200 + 50 * point;
    }

    public static int getPoint(Player player) {
        return SkillKnightConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "PhanDon");
    }

    public static void add1Point(Player player) {
        if (PhanDon.getPoint(player) == 3) {
            return;
        }
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "PhanDon", (Object)(PhanDon.getPoint(player) + 1));
        SkillKnightConfig.saveConfig();
    }

    public static int getTilePhanDon(Player player) {
        if (PhanDon.getPoint(player) == 0) {
            return 0;
        }
        int tiLe = 5;
        if (PhanDon.getPoint(player) == 2) {
            tiLe = 7;
        } else if (PhanDon.getPoint(player) == 3) {
            tiLe = 10;
        }
        return tiLe;
    }

    public static void doIt(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player)e.getEntity();
            if (!SafeZone.isAllowedtoPVP(player)) {
                return;
            }
            if (!ClassSetting.getClass(player).equals("knight")) {
                return;
            }
            if (SettingMethod.tiLe(PhanDon.getTilePhanDon(player)) && e.getDamager() instanceof LivingEntity) {
                LivingEntity le = (LivingEntity)e.getDamager();
                e.setDamage(e.getDamage() * 1.5);
                player.sendMessage("\u00a73Ph\u1ea3n \u0111\u00f2n \u0111\u1ed1i ph\u01b0\u01a1ng");
                le.sendMessage("\u00a7cB\u1ea1n b\u1ecb ph\u1ea3n \u0111\u00f2n");
            }
        }
    }
}

