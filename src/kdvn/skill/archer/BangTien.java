/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.projectiles.ProjectileSource
 */
package kdvn.skill.archer;

import kdvn.config.SkillArcherConfig;
import kdvn.main.Main;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

public class BangTien {
    public static final String NAME = "BangTien";
    private static final int LEVEL = 50;

    public static int getMinLevelOfSkill(Player player) {
        return 50;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = BangTien.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 50 + 50 * point;
    }

    public static int getPoint(Player player) {
        return SkillArcherConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "BangTien");
    }

    public static void add1Point(Player player) {
        if (BangTien.getPoint(player) == 3) {
            return;
        }
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "BangTien", (Object)(BangTien.getPoint(player) + 1));
        SkillArcherConfig.saveConfig();
    }

    public static void doIt(EntityDamageByEntityEvent e) {
        Arrow arrow;
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity entity = (LivingEntity)e.getEntity();
        if (e.getDamager() instanceof Arrow && (arrow = (Arrow)e.getDamager()).getShooter() instanceof Player) {
            Player player = (Player)arrow.getShooter();
            int point = BangTien.getPoint(player);
            if (point == 0) {
                return;
            }
            if (point == 1) {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 1, false));
            } else if (point == 2) {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 1, false));
            } else if (point == 3) {
                entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1, false));
            }
        }
    }
}

