/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.projectiles.ProjectileSource
 *  org.bukkit.util.Vector
 */
package kdvn.skill.archer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillArcherConfig;
import kdvn.main.Main;
import kdvn.settings.SettingMethod;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class MuaTen {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    public static final int TIME_DELAY = 60;
    public static final int MANA_COST = 20;
    private static int[] combo = new int[4];
    public static final String NAME = "MuaTen";
    private static HashMap<Player, Integer> scheList = new HashMap();
    private static HashMap<Player, Integer> playerCount = new HashMap();
    private static final int LEVEL = 500;

    public static int getMinLevelOfSkill(Player player) {
        return 500;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = MuaTen.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 500 + 50 * point;
    }

    public static int[] getCombo() {
        MuaTen.combo[0] = 1;
        MuaTen.combo[1] = 1;
        MuaTen.combo[2] = 0;
        MuaTen.combo[3] = 0;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillArcherConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "MuaTen");
    }

    public static void add1Point(Player player) {
        if (MuaTen.getPoint(player) == 3) {
            return;
        }
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "MuaTen", (Object)(MuaTen.getPoint(player) + 1));
        SkillArcherConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!MuaTen.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (MuaTen.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void addSche(Player player, int sche) {
        if (!scheList.containsKey((Object)player)) {
            scheList.put(player, sche);
        }
    }

    public static void removeSche(Player player) {
        if (scheList.containsKey((Object)player)) {
            scheList.remove((Object)player);
        }
    }

    public static int getSche(Player player) {
        return scheList.get((Object)player);
    }

    public static void muaTen(Player player) {
        if (MuaTen.getPoint(player) == 0) {
            return;
        }
        if (MuaTen.isTriggered(player)) {
            return;
        }
        Block block = player.getTargetBlock(null, 30);
        if (block.getType() == Material.AIR) {
            player.sendMessage("\u00a7cB\u1ea1n ph\u1ea3i ch\u1ec9 v\u00e0o 1 BLOCK");
            return;
        }
        if (!ClassSetting.manaCost(player, 20)) {
            return;
        }
        MuaTen.triggerPlayer(player);
        player.sendMessage("\u00a7aTh\u1eddi gian h\u1ed3i \u00a7cM\u01b0a t\u00ean: \u00a7660");
        int fireTime = 7;
        int radius = 2;
        if (MuaTen.getPoint(player) == 2) {
            fireTime = 9;
            radius = 3;
        } else if (MuaTen.getPoint(player) == 3) {
            fireTime = 12;
            radius = 3;
        }
        Location location = block.getLocation();
        int r = radius;
        playerCount.put(player, 0);
        int i = 0;
        while (i < fireTime * 5) {
            Location mainLocation = location.clone().add(0.0, 40.0, 0.0);
            float r1 = (float)(new Random().nextInt(4000) - 2000) * 0.001f * (float)r;
            float r2 = (float)(new Random().nextInt(4000) - 2000) * 0.001f * (float)r;
            mainLocation.clone().add((double)r1, 0.0, (double)r2);
            Arrow a = mainLocation.getWorld().spawnArrow(mainLocation, new Vector(0, -5, 0), 10.0f, 7.0f);
            a.setShooter((ProjectileSource)player);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

                @Override
                public void run() {
                    Arrow.this.remove();
                }
            }, 10);
            ++i;
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                MuaTen.remove(Player.this);
                Player.this.sendMessage(SettingMethod.colorDecomplier("&3H\u1ed3i chi\u00eau &cM\u01b0a t\u00ean"));
            }
        }, 1200);
    }

    public static void doIt(Player player) {
        MuaTen.muaTen(player);
    }

}

