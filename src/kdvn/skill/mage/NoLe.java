/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Enderman
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityTargetLivingEntityEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package kdvn.skill.mage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import kdvn.minelord.dosat.DoSat;
import kdvn.safezone.SafeZone;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NoLe
implements Listener {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    public static HashMap<Player, Enderman> owningMob = new HashMap();
    public static final String NAME = "NoLe";
    public static final int TIME_DELAY = 5;
    private static final int LEVEL = 100;

    public static int getMinLevelOfSkill(Player player) {
        return 100;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = NoLe.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 100 + 50 * point;
    }

    public static int getPoint(Player player) {
        return SkillMageConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "NoLe");
    }

    public static void add1Point(Player player) {
        if (NoLe.getPoint(player) == 3) {
            return;
        }
        SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "NoLe", (Object)(NoLe.getPoint(player) + 1));
        SkillMageConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!NoLe.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (NoLe.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void doIt(Player player, LivingEntity target) {
        if (!ClassSetting.getClass(player).equals("mage")) {
            return;
        }
        if (NoLe.getPoint(player) == 0) {
            return;
        }
        if (NoLe.isTriggered(player)) {
            return;
        }
        NoLe.triggerPlayer(player);
        NoLe.createMob(player, target);
        player.sendMessage("\u00a76Th\u1eddi gian h\u1ed3i chi\u00eau \u00a7fN\u00f4 L\u1ec7: \u00a7c5s");
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                NoLe.remove(Player.this);
                Player.this.sendMessage("\u00a76H\u1ed3i chi\u00eau \u00a7fN\u00f4 L\u1ec7");
            }
        }, 100);
    }

    public static void createMob(Player player, LivingEntity target) {
        Enderman z = (Enderman)player.getWorld().spawnEntity(player.getLocation().clone().add(0.0, 2.0, 0.0), EntityType.ENDERMAN);
        if (NoLe.getPoint(player) == 0) {
            return;
        }
        z.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue((double)(5 + 5 * NoLe.getPoint(player)));
        if (NoLe.getPoint(player) == 1) {
            z.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50.0);
            z.setHealth(50.0);
            z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 190, 1));
        } else if (NoLe.getPoint(player) == 2) {
            z.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(70.0);
            z.setHealth(70.0);
            z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 190, 1));
        } else if (NoLe.getPoint(player) == 3) {
            z.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100.0);
            z.setHealth(100.0);
            z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 190, 1));
        }
        z.setCustomName("   \u00a7f\u00a7lN\u00f4 L\u1ec7 \u00a7c\u00a7l[C\u1ea5p " + NoLe.getPoint(player) + "]\u00a7f\u00a7l c\u1ee7a \u00a7a\u00a7l" + player.getName());
        z.setTarget(target);
        owningMob.put(player, z);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                if (!Enderman.this.isDead()) {
                    Enderman.this.remove();
                }
            }
        }, 400);
    }

    public static Enderman getMob(Player player) {
        return owningMob.get((Object)player);
    }

    public static boolean hasMob(Player player) {
        return owningMob.containsKey((Object)player);
    }

    @EventHandler
    public static void xuLyEventDamage(EntityDamageByEntityEvent e) {
        if (!DoSat.canAttack(e)) {
            return;
        }
        if (e.getEntity() instanceof Player && e.getDamager() instanceof LivingEntity) {
            if (e.getEntity() == e.getDamager()) {
                return;
            }
            LivingEntity target = (LivingEntity)e.getDamager();
            Player player = (Player)e.getEntity();
            if (NoLe.getPoint(player) == 0) {
                return;
            }
            if (NoLe.isTriggered(player)) {
                return;
            }
            NoLe.doIt(player, target);
            player.sendMessage("\u00a7aTri\u1ec7u h\u1ed3i \u00a7cN\u00f4 L\u1ec7");
        }
    }

    @EventHandler
    public static void onTargetEvent(EntityTargetLivingEntityEvent e) {
        if (e.getEntity() instanceof Enderman) {
            if (!(e.getTarget() instanceof Player)) {
                return;
            }
            Enderman em = (Enderman)e.getEntity();
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!NoLe.hasMob(player) || !NoLe.getMob(player).getName().equals(em.getName()) || e.getTarget() != player) continue;
                e.setCancelled(true);
                break;
            }
            Player p = (Player)e.getTarget();
            if (!SafeZone.isAllowedtoPVP(p)) {
                e.setCancelled(true);
                return;
            }
        }
    }

}

