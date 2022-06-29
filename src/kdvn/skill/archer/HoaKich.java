/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Damageable
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
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
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class HoaKich {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static List<Player> fired = new ArrayList<Player>();
    public static final int TIME_DELAY = 5;
    public static final int MANA_COST = 10;
    private static int[] combo = new int[2];
    public static final String NAME = "HoaKich";
    private static final int LEVEL = 100;

    public static int getMinLevelOfSkill(Player player) {
        return 100;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = HoaKich.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 100 + 50 * point;
    }

    public static int[] getCombo() {
        HoaKich.combo[0] = 0;
        HoaKich.combo[1] = 0;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillArcherConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "HoaKich");
    }

    public static void add1Point(Player player) {
        if (HoaKich.getPoint(player) == 3) {
            return;
        }
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "HoaKich", (Object)(HoaKich.getPoint(player) + 1));
        SkillArcherConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!HoaKich.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (HoaKich.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void fire(Player player) {
        if (HoaKich.getPoint(player) == 0) {
            return;
        }
        if (!ClassSetting.manaCost(player, 10)) {
            return;
        }
        if (HoaKich.isTriggered(player)) {
            return;
        }
        if (!fired.contains((Object)player)) {
            fired.add(player);
        }
        if (HoaKich.isTriggered(player)) {
            return;
        }
        HoaKich.triggerPlayer(player);
        Location loc = player.getLocation();
        loc.add(0.0, 1.100000023841858, 0.0);
        int i = 0;
        while (i <= 40) {
            loc.add(loc.getDirection().getX(), loc.getDirection().getY(), loc.getDirection().getZ());
            Particle.sendParticle(EnumParticle.FLAME, loc, 0.1f, 0.1f, 0.1f, 0.01f, 2);
            loc.getWorld().playSound(loc, Sound.ENTITY_GENERIC_BURN, 1.0f, 1.0f);
            for (Entity e : SettingMethod.getEntitiesByLocation(loc, 2.0f)) {
                LivingEntity target;
                if (!(e instanceof LivingEntity) || (target = (LivingEntity)e) instanceof Player && !SafeZone.isAllowedtoPVP((Player)target) || target == player) continue;
                HoaKich.damageEntity(player, target, new MineLordPlayer(player).getAddedDamage());
                break;
            }
            if (loc.getBlock().getType().isSolid()) break;
            ++i;
        }
        player.sendMessage(SettingMethod.colorDecomplier("&aTh\u1eddi gian h\u1ed3i &6H\u1ecfa K\u00edch: &c5"));
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                HoaKich.remove(Player.this);
                Player.this.sendMessage(SettingMethod.colorDecomplier("&3H\u1ed3i chi\u00eau &6H\u1ecfa K\u00edch"));
            }
        }, 100);
    }

    public static void damageEntity(Player shooter, LivingEntity entity, double damage) {
        double satThuong = damage;
        if (!fired.contains((Object)shooter)) {
            return;
        }
        fired.remove((Object)shooter);
        if (HoaKich.getPoint(shooter) == 0) {
            return;
        }
        if (HoaKich.getPoint(shooter) == 1) {
            satThuong *= 1.0;
        } else if (HoaKich.getPoint(shooter) == 2) {
            satThuong *= 1.2000000476837158;
        } else if (HoaKich.getPoint(shooter) == 3) {
            satThuong *= 1.5;
        }
        if (entity instanceof Player) {
            if (SafeZone.isAllowedtoPVP((Player)entity)) {
                entity.damage(damage, (Entity)shooter);
                entity.setFireTicks(200);
            }
        } else {
            entity.damage(damage, (Entity)shooter);
            entity.setFireTicks(200);
        }
        int i = 0;
        while (i < 10) {
            Particle.sendParticle(EnumParticle.FLAME, entity.getLocation(), 3.0f, 3.0f, 3.0f, 0.01f, 10);
            ++i;
        }
        for (Entity e : SettingMethod.getEntitiesByLocation(entity.getLocation(), 25.0f)) {
            if (!(e instanceof LivingEntity) || e.equals((Object)entity) || e.equals((Object)shooter)) continue;
            ((Damageable)e).damage(satThuong * 0.5, (Entity)shooter);
            e.setFireTicks(200);
        }
    }

}

