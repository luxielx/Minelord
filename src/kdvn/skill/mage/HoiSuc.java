/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package kdvn.skill.mage;

import java.util.HashMap;
import java.util.Map;
import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class HoiSuc {
    private static Map<Player, Integer> count = new HashMap<Player, Integer>();
    public static final String NAME = "HoiSuc";
    private static final int LEVEL = 50;

    public static int getMinLevelOfSkill(Player player) {
        return 50;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = HoiSuc.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 50 + 50 * point;
    }

    public static int getPoint(Player player) {
        return SkillMageConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "HoiSuc");
    }

    public static void add1Point(Player player) {
        if (HoiSuc.getPoint(player) == 3) {
            return;
        }
        SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "HoiSuc", (Object)(HoiSuc.getPoint(player) + 1));
        SkillMageConfig.saveConfig();
    }

    public static void doEvent(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player)e.getEntity();
            HoiSuc.doIt(player);
            if (count.containsKey((Object)player)) {
                count.put(player, 0);
            }
        }
    }

    public static void doIt(Player player) {
        if (HoiSuc.getPoint(player) == 0) {
            return;
        }
        if (count.containsKey((Object)player)) {
            return;
        }
        count.put(player, 0);
        int time = 10;
        if (HoiSuc.getPoint(player) == 2) {
            time = 12;
        } else if (HoiSuc.getPoint(player) == 3) {
            time = 15;
        }
        final int timeTemp = time;
        new BukkitRunnable(){

            public void run() {
                if (!count.containsKey((Object)Player.this)) {
                    this.cancel();
                    return;
                }
                count.put(Player.this, (Integer)count.get((Object)Player.this) + 1);
                if ((Integer)count.get((Object)Player.this) >= 20) {
                    Player.this.sendMessage("\u00a7aB\u1ea1n \u0111\u01b0\u1ee3c h\u1ed3i s\u1ee9c");
                    Player.this.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, timeTemp * 20, 1));
                    count.remove((Object)Player.this);
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)Main.plugin, 0, 20);
    }

}

