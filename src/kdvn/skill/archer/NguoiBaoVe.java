/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.IronGolem
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityTargetLivingEntityEvent
 *  org.bukkit.plugin.Plugin
 */
package kdvn.skill.archer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillArcherConfig;
import kdvn.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.plugin.Plugin;

public class NguoiBaoVe
implements Listener {
    public static HashMap<Player, IronGolem> owningMob = new HashMap();
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    public static final int TIME_DELAY = 60;
    public static final int MANA_COST = 40;
    private static int[] combo = new int[3];
    public static final String NAME = "NguoiBaoVe";
    private static final int LEVEL = 200;

    public static int getMinLevelOfSkill(Player player) {
        return 200;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = NguoiBaoVe.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 200 + 50 * point;
    }

    public static void createMob(Player player) {
        IronGolem ig = (IronGolem)player.getWorld().spawnEntity(player.getLocation().clone().add(0.0, 2.0, 0.0), EntityType.IRON_GOLEM);
        if (NguoiBaoVe.getPoint(player) == 0) {
            return;
        }
        if (NguoiBaoVe.getPoint(player) == 1) {
            ig.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200.0);
            ig.setHealth(200.0);
        } else if (NguoiBaoVe.getPoint(player) == 2) {
            ig.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(300.0);
            ig.setHealth(300.0);
        } else if (NguoiBaoVe.getPoint(player) == 3) {
            ig.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500.0);
            ig.setHealth(500.0);
        }
        ig.setCustomName("   \u00a7f\u00a7lNg\u01b0\u1eddi B\u1ea3o V\u1ec7 \u00a7c\u00a7l[C\u1ea5p " + NguoiBaoVe.getPoint(player) + "]\u00a7f\u00a7l c\u1ee7a \u00a7a\u00a7l" + player.getName());
        owningMob.put(player, ig);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                NguoiBaoVe.getMob(Player.this).remove();
                NguoiBaoVe.owningMob.remove((Object)Player.this);
            }
        }, 1200);
    }

    public static IronGolem getMob(Player player) {
        return owningMob.get((Object)player);
    }

    public static int[] getCombo() {
        NguoiBaoVe.combo[0] = 1;
        NguoiBaoVe.combo[1] = 1;
        NguoiBaoVe.combo[2] = 1;
        return combo;
    }

    public static boolean hasMob(Player player) {
        return owningMob.containsKey((Object)player);
    }

    public static int getPoint(Player player) {
        return SkillArcherConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "NguoiBaoVe");
    }

    public static void add1Point(Player player) {
        if (NguoiBaoVe.getPoint(player) == 3) {
            return;
        }
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "NguoiBaoVe", (Object)(NguoiBaoVe.getPoint(player) + 1));
        SkillArcherConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!NguoiBaoVe.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (NguoiBaoVe.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void doIt(Player player) {
        if (NguoiBaoVe.getPoint(player) == 0) {
            return;
        }
        if (NguoiBaoVe.isTriggered(player)) {
            return;
        }
        if (!ClassSetting.manaCost(player, 40)) {
            return;
        }
        NguoiBaoVe.triggerPlayer(player);
        player.sendMessage("\u00a76Th\u1eddi gian h\u1ed3i chi\u00eau \u00a7fTri\u1ec7u H\u1ed3i: \u00a7c60s");
        NguoiBaoVe.createMob(player);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                NguoiBaoVe.remove(Player.this);
                Player.this.sendMessage("\u00a76H\u1ed3i chi\u00eau \u00a7fTri\u1ec7u H\u1ed3i");
            }
        }, 1200);
    }

    @EventHandler
    public static void onDamage(EntityDamageByEntityEvent e) {
        Player player;
        if (e.getEntity() instanceof Player && NguoiBaoVe.hasMob(player = (Player)e.getEntity()) && e.getDamager() instanceof LivingEntity) {
            NguoiBaoVe.getMob(player).setTarget((LivingEntity)e.getDamager());
        }
        if (e.getDamager() instanceof Player && NguoiBaoVe.hasMob(player = (Player)e.getDamager()) && e.getEntity() instanceof LivingEntity) {
            NguoiBaoVe.getMob(player).setTarget((LivingEntity)e.getEntity());
        }
    }

    @EventHandler
    public static void onTargetEvent(EntityTargetLivingEntityEvent e) {
        Player player;
        if (e.getEntity() instanceof IronGolem && e.getTarget() instanceof Player && NguoiBaoVe.hasMob(player = (Player)e.getTarget()) && NguoiBaoVe.getMob(player).equals((Object)((IronGolem)e.getEntity()))) {
            e.setCancelled(true);
        }
    }

}

