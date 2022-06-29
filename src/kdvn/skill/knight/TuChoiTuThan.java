/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package kdvn.skill.knight;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillKnightConfig;
import kdvn.main.Main;
import kdvn.settings.Particle;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TuChoiTuThan {
    public static final String NAME = "TuChoiTuThan";
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static Set<Player> buffed = new HashSet<Player>();
    public static final int TIME_DELAY = 60;
    private static final int LEVEL = 500;

    public static int getMinLevelOfSkill(Player player) {
        return 500;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = TuChoiTuThan.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 500 + 50 * point;
    }

    public static int getPoint(Player player) {
        return SkillKnightConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "TuChoiTuThan");
    }

    public static void add1Point(Player player) {
        if (TuChoiTuThan.getPoint(player) == 3) {
            return;
        }
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "TuChoiTuThan", (Object)(TuChoiTuThan.getPoint(player) + 1));
        SkillKnightConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!TuChoiTuThan.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (TuChoiTuThan.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static boolean isBuffed(Player player) {
        return buffed.contains((Object)player);
    }

    public static void buffPlayer(Player player) {
        if (TuChoiTuThan.getPoint(player) == 0) {
            return;
        }
        int time = 5;
        if (TuChoiTuThan.getPoint(player) == 2) {
            time = 7;
        } else if (TuChoiTuThan.getPoint(player) == 3) {
            time = 10;
        }
        buffed.add(player);
        player.sendMessage("\u00a7a* K\u00edch ho\u1ea1t k\u1ef9 n\u0103ng \u00a7cT\u1eeb Ch\u1ed1i T\u1eed Th\u1ea7n");
        player.sendMessage("\u00a7a* B\u1ea1n \u0111\u01b0\u1ee3c b\u1ea5t t\u1eed trong \u00a7c" + time + "s");
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, time * 20, 1));
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                buffed.remove((Object)Player.this);
                Player.this.sendMessage("\u00a76x H\u1ebft hi\u1ec7u l\u1ef1c c\u1ee7a \u00a7cT\u1eeb Ch\u1ed1i T\u1eed Th\u1ea7n");
            }
        }, (long)(time * 20));
    }

    public static void doIt(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player)e.getEntity();
            if (!ClassSetting.getClass(player).equals("knight")) {
                return;
            }
            if (TuChoiTuThan.getPoint(player) == 0) {
                return;
            }
            if (TuChoiTuThan.isBuffed(player)) {
                e.setDamage(0.0);
                int i = 1;
                while (i < 5) {
                    Particle.sendParticle(EnumParticle.SMOKE_NORMAL, player.getLocation().clone().add(0.0, 1.0, 0.0), 0.5f, 0.5f, 0.5f, 0.01f, 3);
                    ++i;
                }
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1.0f, 1.0f);
            } else if (player.getHealth() < player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.15000000596046448) {
                if (TuChoiTuThan.isTriggered(player)) {
                    return;
                }
                TuChoiTuThan.buffPlayer(player);
                e.setCancelled(true);
                TuChoiTuThan.triggerPlayer(player);
                player.sendMessage("\u00a76Th\u1eddi gian h\u1ed3i chi\u00eau: \u00a7c60");
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

                    @Override
                    public void run() {
                        TuChoiTuThan.remove(Player.this);
                        Player.this.sendMessage("\u00a76H\u1ed3i chi\u00eau \u00a7cT\u1eeb Ch\u1ed1i T\u1eed Th\u1ea7n");
                    }
                }, 1200);
            }
        }
    }

}

