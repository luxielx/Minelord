/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Monster
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerMoveEvent
 *  org.bukkit.event.player.PlayerToggleFlightEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 *  org.bukkit.util.Vector
 */
package kdvn.skill.archer;

import java.util.ArrayList;
import java.util.List;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillArcherConfig;
import kdvn.main.Main;
import kdvn.minelord.player.MineLordPlayer;
import kdvn.safezone.SafeZone;
import kdvn.settings.Particle;
import kdvn.settings.SettingMethod;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class BangKich
implements Listener {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static List<LivingEntity> freezedEntity = new ArrayList<LivingEntity>();
    public static final int TIME_DELAY = 15;
    public static final int MANA_COST = 20;
    private static final int LEVEL = 100;
    private static int[] combo = new int[2];
    public static final String NAME = "BangKich";

    public static int[] getCombo() {
        BangKich.combo[0] = 1;
        BangKich.combo[1] = 1;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillArcherConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "BangKich");
    }

    public static void add1Point(Player player) {
        if (BangKich.getPoint(player) == 3) {
            return;
        }
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "BangKich", (Object)(BangKich.getPoint(player) + 1));
        SkillArcherConfig.saveConfig();
    }

    public static int getMinLevelOfSkill(Player player) {
        return 100;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = BangKich.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 100 + 50 * point;
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!BangKich.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (BangKich.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static boolean isFreezed(LivingEntity e) {
        if (freezedEntity.contains((Object)e)) {
            return true;
        }
        return false;
    }

    public static void dongBang(LivingEntity entity, double damage) {
        if (!freezedEntity.contains((Object)entity)) {
            freezedEntity.add(entity);
            entity.damage(damage);
            entity.sendMessage(SettingMethod.colorDecomplier("&3B\u1ea1n \u0111\u00e3 b\u1ecb \u0111\u00f3ng b\u0103ng kh\u00f4ng th\u1ec3 di chuy\u1ec3n"));
        }
    }

    public static void dongBang(LivingEntity entity) {
        if (!freezedEntity.contains((Object)entity)) {
            freezedEntity.add(entity);
            entity.sendMessage(SettingMethod.colorDecomplier("&3B\u1ea1n \u0111\u00e3 b\u1ecb \u0111\u00f3ng b\u0103ng kh\u00f4ng th\u1ec3 di chuy\u1ec3n"));
            new BukkitRunnable(){

                public void run() {
                    if (!BangKich.isFreezed(LivingEntity.this)) {
                        this.cancel();
                        return;
                    }
                    Particle.sendParticle(EnumParticle.CLOUD, LivingEntity.this.getLocation(), 0.1f, 0.1f, 0.1f, 0.001f, 1);
                }
            }.runTaskTimer((Plugin)Main.plugin, 0, 20);
        }
    }

    public static void phaBang(LivingEntity entity) {
        if (freezedEntity.contains((Object)entity)) {
            freezedEntity.remove((Object)entity);
        }
    }

    public static void fire(Player player) {
        if (BangKich.getPoint(player) == 0) {
            return;
        }
        if (!ClassSetting.manaCost(player, 20)) {
            return;
        }
        if (BangKich.isTriggered(player)) {
            player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a h\u1ed3i chi\u00eau xong");
            return;
        }
        BangKich.triggerPlayer(player);
        Location loc = player.getLocation();
        loc.add(0.0, 1.5, 0.0);
        int i = 0;
        while (i <= 40) {
            loc.add(loc.getDirection().getX(), loc.getDirection().getY(), loc.getDirection().getZ());
            Particle.sendParticle(EnumParticle.SNOW_SHOVEL, loc, 0.1f, 0.1f, 0.1f, 0.01f, 2);
            for (Entity e : SettingMethod.getEntitiesByLocation(loc, 2.0f)) {
                LivingEntity target;
                if (!(e instanceof LivingEntity) || (target = (LivingEntity)e) == player || !SafeZone.isAllowedtoPVP(player)) continue;
                BangKich.freezeEntity(player, target, new MineLordPlayer(player).getAddedDamage());
            }
            if (loc.getBlock().getType().isSolid()) break;
            ++i;
        }
        player.sendMessage(SettingMethod.colorDecomplier("&aTh\u1eddi gian h\u1ed3i &6B\u0103ng K\u00edch: &c15"));
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                BangKich.remove(Player.this);
                Player.this.sendMessage(SettingMethod.colorDecomplier("&3H\u1ed3i chi\u00eau &6B\u0103ng K\u00edch"));
            }
        }, 300);
    }

    public static void freezeEntity(Player player, LivingEntity entity, double damage) {
        if (entity instanceof Player && !SafeZone.isAllowedtoPVP((Player)entity)) {
            return;
        }
        if (BangKich.isFreezed(entity)) {
            return;
        }
        BangKich.dongBang(entity, damage);
        int freezeTime = 5;
        if (BangKich.getPoint(player) == 1) {
            freezeTime = 5;
        } else if (BangKich.getPoint(player) == 2) {
            freezeTime = 7;
        } else if (BangKich.getPoint(player) == 3) {
            freezeTime = 10;
        }
        if (entity instanceof Monster) {
            ((Monster)entity).setAI(false);
            entity.setVelocity(entity.getLocation().getDirection().multiply(-1.0f));
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

                @Override
                public void run() {
                    ((Monster)LivingEntity.this).setAI(true);
                    BangKich.phaBang(LivingEntity.this);
                }
            }, (long)(freezeTime * 20));
        } else {
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

                @Override
                public void run() {
                    if (BangKich.isFreezed(LivingEntity.this)) {
                        BangKich.phaBang(LivingEntity.this);
                        LivingEntity.this.sendMessage(SettingMethod.colorDecomplier("&aB\u1ea1n \u0111\u00e3 c\u00f3 th\u1ec3 di chuy\u1ec3n"));
                    }
                }
            }, (long)(freezeTime * 20));
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (BangKich.isFreezed((LivingEntity)e.getPlayer())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onJump(PlayerToggleFlightEvent e) {
        if (BangKich.isFreezed((LivingEntity)e.getPlayer())) {
            e.getPlayer().setVelocity(new Vector(0, -100, 0));
        }
    }

    public void fezzeMob(Monster m) {
        m.setAI(false);
    }

}

