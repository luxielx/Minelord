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
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.util.Vector
 */
package kdvn.skill.mage;

import java.util.ArrayList;
import java.util.List;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import kdvn.phepthuat.NangLuong;
import kdvn.settings.Particle;
import kdvn.settings.SettingMethod;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class BaoTuyet {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static List<Player> fired = new ArrayList<Player>();
    public static final int TIME_DELAY = 3;
    public static final int MANA_COST = 50;
    private static int[] combo = new int[3];
    public static final String NAME = "BaoTuyet";
    private static final int LEVEL = 200;

    public static int getMinLevelOfSkill(Player player) {
        return 200;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = BaoTuyet.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 200 + 50 * point;
    }

    public static int[] getCombo() {
        BaoTuyet.combo[0] = 0;
        BaoTuyet.combo[1] = 0;
        BaoTuyet.combo[2] = 0;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillMageConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "BaoTuyet");
    }

    public static void add1Point(Player player) {
        if (BaoTuyet.getPoint(player) == 3) {
            return;
        }
        SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "BaoTuyet", (Object)(BaoTuyet.getPoint(player) + 1));
        SkillMageConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!BaoTuyet.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (BaoTuyet.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void fire(Player player) {
        if (BaoTuyet.getPoint(player) == 0) {
            return;
        }
        if (!ClassSetting.manaCost(player, 50)) {
            return;
        }
        if (BaoTuyet.isTriggered(player)) {
            return;
        }
        if (!fired.contains((Object)player)) {
            fired.add(player);
        }
        if (BaoTuyet.isTriggered(player)) {
            return;
        }
        BaoTuyet.triggerPlayer(player);
        Location loc = player.getLocation();
        loc.add(0.0, 1.100000023841858, 0.0);
        int i = 0;
        while (i <= 40) {
            loc.add(loc.getDirection().getX(), loc.getDirection().getY(), loc.getDirection().getZ());
            Particle.sendParticle(EnumParticle.END_ROD, loc, 0.7f, 0.7f, 0.7f, 0.01f, 5);
            loc.getWorld().playSound(loc, Sound.ENTITY_SNOWBALL_THROW, 1.0f, 1.0f);
            for (Entity e : SettingMethod.getEntitiesByLocation(loc, 2.0f)) {
                LivingEntity target;
                if (!(e instanceof LivingEntity) || (target = (LivingEntity)e) == player) continue;
                BaoTuyet.damageEntity(player, target);
                break;
            }
            if (loc.getBlock().getType().isSolid()) break;
            ++i;
        }
        player.sendMessage(SettingMethod.colorDecomplier("&aTh\u1eddi gian h\u1ed3i &6Gi\u00f3 Tuy\u1ebft: &c3"));
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                BaoTuyet.remove(Player.this);
                Player.this.sendMessage(SettingMethod.colorDecomplier("&3H\u1ed3i chi\u00eau &6Gi\u00f3 Tuy\u1ebft"));
            }
        }, 60);
    }

    public static void damageEntity(Player shooter, LivingEntity entity) {
        if (!fired.contains((Object)shooter)) {
            return;
        }
        fired.remove((Object)shooter);
        if (BaoTuyet.getPoint(shooter) == 0) {
            return;
        }
        double satThuong = 0.0;
        if (BaoTuyet.getPoint(shooter) == 1) {
            satThuong = (float)NangLuong.getMaxNangLuong(shooter) * 0.05f;
        } else if (BaoTuyet.getPoint(shooter) == 2) {
            satThuong = (float)NangLuong.getMaxNangLuong(shooter) * 0.07f;
        } else if (BaoTuyet.getPoint(shooter) == 3) {
            satThuong = (float)NangLuong.getMaxNangLuong(shooter) * 0.1f;
        }
        entity.damage(satThuong, (Entity)shooter);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1));
        int i = 0;
        while (i < 3) {
            Particle.sendParticle(EnumParticle.END_ROD, entity.getLocation(), 3.0f, 3.0f, 3.0f, 0.01f, 10);
            ++i;
        }
        for (Entity e : SettingMethod.getEntitiesByLocation(entity.getLocation(), 25.0f)) {
            if (!(e instanceof LivingEntity) || e.equals((Object)entity) || e.equals((Object)shooter)) continue;
            ((LivingEntity)e).damage(satThuong, (Entity)shooter);
            ((LivingEntity)e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1));
            int i2 = 0;
            while (i2 < 1) {
                Particle.sendParticle(EnumParticle.END_ROD, entity.getLocation(), 3.0f, 3.0f, 3.0f, 0.01f, 10);
                ++i2;
            }
        }
    }

}

