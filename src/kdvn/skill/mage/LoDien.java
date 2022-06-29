/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package kdvn.skill.mage;

import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import kdvn.minelord.dosat.DoSat;
import kdvn.safezone.SafeZone;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LoDien {
    public static final String NAME = "LoDien";
    private static final int LEVEL = 50;

    public static int getMinLevelOfSkill(Player player) {
        return 50;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = LoDien.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 50 + 50 * point;
    }

    public static int getPoint(Player player) {
        return SkillMageConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "LoDien");
    }

    public static void add1Point(Player player) {
        if (LoDien.getPoint(player) == 3) {
            return;
        }
        SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "LoDien", (Object)(LoDien.getPoint(player) + 1));
        SkillMageConfig.saveConfig();
    }

    public static void doIt(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity entity = (LivingEntity)e.getEntity();
        if (!DoSat.canAttack(e)) {
            return;
        }
        if (e.getDamager() instanceof Player) {
            Player player = (Player)e.getDamager();
            if (!SafeZone.isAllowedtoPVP(player)) {
                return;
            }
            if (LoDien.getPoint(player) == 0) {
                return;
            }
            if (LoDien.getPoint(player) == 1) {
                e.setDamage(e.getDamage() * 1.100000023841858);
                entity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 20, 0));
            } else if (LoDien.getPoint(player) == 2) {
                e.setDamage(e.getDamage() * 1.100000023841858);
                entity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 30, 0));
            } else if (LoDien.getPoint(player) == 3) {
                e.setDamage(e.getDamage() * 1.2000000476837158);
                entity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 50, 0));
            }
        }
    }
}

