/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Fireball
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.projectiles.ProjectileSource
 *  org.bukkit.util.Vector
 */
package kdvn.skill.mage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import kdvn.minelord.dosat.DoSat;
import kdvn.phepthuat.NangLuong;
import kdvn.settings.SettingMethod;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class MuaSaoBang {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static int[] combo = new int[4];
    public static final String NAME = "MuaSaoBang";
    public static final int MANA_COST = 800;
    public static final int TIME_DELAY = 120;
    private static final int LEVEL = 500;

    public static int getMinLevelOfSkill(Player player) {
        return 500;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = MuaSaoBang.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 500 + 50 * point;
    }

    public static int[] getCombo() {
        MuaSaoBang.combo[0] = 0;
        MuaSaoBang.combo[1] = 1;
        MuaSaoBang.combo[2] = 0;
        MuaSaoBang.combo[3] = 1;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillMageConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "MuaSaoBang");
    }

    public static void add1Point(Player player) {
        if (MuaSaoBang.getPoint(player) == 3) {
            return;
        }
        SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "MuaSaoBang", (Object)(MuaSaoBang.getPoint(player) + 1));
        SkillMageConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!MuaSaoBang.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (MuaSaoBang.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void doIt(Player player) {
        if (MuaSaoBang.getPoint(player) == 0) {
            return;
        }
        if (MuaSaoBang.isTriggered(player)) {
            return;
        }
        if (!ClassSetting.manaCost(player, 800)) {
            return;
        }
        Block block = player.getTargetBlock(null, 100);
        if (block.getType() == Material.AIR) {
            player.sendMessage("\u00a7cB\u1ea1n ph\u1ea3i ch\u1ec9 v\u00e0o 1 Block");
            return;
        }
        MuaSaoBang.triggerPlayer(player);
        int time = 5;
        if (MuaSaoBang.getPoint(player) == 2) {
            time = 7;
        } else if (MuaSaoBang.getPoint(player) == 3) {
            time = 10;
        }
        MuaSaoBang.muaSaoBang(block.getLocation(), player, time);
        player.sendMessage(SettingMethod.colorDecomplier("&aTh\u1eddi gian h\u1ed3i &6M\u01b0a Thi\u00ean Th\u1ea1ch: &c120"));
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                MuaSaoBang.remove(Player.this);
                Player.this.sendMessage(SettingMethod.colorDecomplier("&3H\u1ed3i chi\u00eau &6M\u01b0a Thi\u00ean Th\u1ea1ch"));
            }
        }, 2400);
    }

    public static void muaSaoBang(Location location, Player player, int time) {
        Location main = location.clone().add(0.0, 10.0, 0.0);
        int i = 1;
        while (i < time * 4 * 2) {
            double x = (new Random().nextInt(2000) - 1000) / 150;
            double z = (new Random().nextInt(2000) - 1000) / 150;
            Location fireLocation = main;
            fireLocation = i / 2 == 0 ? main.clone().add(x, 40.0, z) : main.clone().add(x, 10.0, z);
            Fireball fb = (Fireball)fireLocation.getWorld().spawnEntity(fireLocation, EntityType.FIREBALL);
            fb.setDirection(new Vector(0.0, -1.0E-6, 0.0));
            ++i;
        }
    }

    public static void xuLyEvent(EntityDamageByEntityEvent e) {
        Fireball fb;
        if (e.getDamager() instanceof Fireball && (fb = (Fireball)e.getDamager()).getShooter() instanceof Player) {
            Player player = (Player)fb.getShooter();
            if (e.getEntity() instanceof LivingEntity) {
                Player target;
                if (e.getEntity() instanceof Player && !DoSat.canAttack(player, target = (Player)e.getEntity())) {
                    return;
                }
                if (MuaSaoBang.getPoint(player) == 0) {
                    return;
                }
                double dame = (float)NangLuong.getMaxNangLuong(player) * 0.05f;
                if (MuaSaoBang.getPoint(player) == 2) {
                    dame = (float)NangLuong.getMaxNangLuong(player) * 0.07f;
                } else if (MuaSaoBang.getPoint(player) == 3) {
                    dame = (float)NangLuong.getMaxNangLuong(player) * 0.1f;
                }
                e.setDamage(e.getDamage() + dame);
            }
        }
    }

}

