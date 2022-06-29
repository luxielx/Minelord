/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Projectile
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.projectiles.ProjectileSource
 *  org.bukkit.util.Vector
 */
package kdvn.skill.archer;

import java.util.HashMap;
import kdvn.config.SkillArcherConfig;
import kdvn.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class LienHoanTen {
    public static final String NAME = "LienHoanTen";
    private static HashMap<Player, Integer> timeClick = new HashMap();
    private static final int LEVEL = 150;

    public static int getMinLevelOfSkill(Player player) {
        return 150;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = LienHoanTen.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 150 + 50 * point;
    }

    public static int getPoint(Player player) {
        return SkillArcherConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "LienHoanTen");
    }

    public static void add1Point(Player player) {
        if (LienHoanTen.getPoint(player) == 3) {
            return;
        }
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "LienHoanTen", (Object)(LienHoanTen.getPoint(player) + 1));
        SkillArcherConfig.saveConfig();
    }

    public static void add1Click(Player player) {
        if (LienHoanTen.getPoint(player) == 0) {
            return;
        }
        if (!timeClick.containsKey((Object)player)) {
            timeClick.put(player, 1);
        } else {
            timeClick.put(player, timeClick.get((Object)player) + 1);
        }
    }

    public static int getClick(Player player) {
        if (timeClick.containsKey((Object)player)) {
            return timeClick.get((Object)player);
        }
        return 0;
    }

    public static void xuLySatThuong(Player player, double damage) {
        timeClick.remove((Object)player);
        if (LienHoanTen.getPoint(player) == 0) {
            return;
        }
        int amount = 3;
        if (LienHoanTen.getPoint(player) == 2) {
            amount = 5;
        } else if (LienHoanTen.getPoint(player) == 3) {
            amount = 9;
        }
        double angleBetweenArrows = (double)(45 / (amount - 1)) * 3.141592653589793 / 180.0;
        double pitch = (double)(player.getLocation().getPitch() + 90.0f) * 3.141592653589793 / 180.0;
        double yaw = (double)(player.getLocation().getYaw() + 90.0f - 22.0f) * 3.141592653589793 / 180.0;
        double sZ = Math.cos(pitch);
        int i = 0;
        while (i < amount) {
            double nX = Math.sin(pitch) * Math.cos(yaw + angleBetweenArrows * (double)i);
            double nY = Math.sin(pitch) * Math.sin(yaw + angleBetweenArrows * (double)i);
            Vector newDir = new Vector(nX, sZ, nY);
            Arrow arrow = (Arrow)player.launchProjectile(Arrow.class);
            arrow.setShooter((ProjectileSource)player);
            arrow.setVelocity(newDir.normalize().multiply(3.0f));
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

                @Override
                public void run() {
                    Arrow.this.remove();
                }
            }, 10);
            ++i;
        }
    }

}

