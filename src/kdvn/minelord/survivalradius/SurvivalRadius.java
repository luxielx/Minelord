/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerMoveEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.util.Vector
 */
package kdvn.minelord.survivalradius;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kdvn.config.SurvivalRadiusConfig;
import kdvn.main.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class SurvivalRadius
implements Listener {
    private static FileConfiguration dataConfig = SurvivalRadiusConfig.getConfig((Plugin)Main.plugin);

    public static void setLocation(Location location, String name) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        dataConfig.set("Location." + name + ".x", (Object)x);
        dataConfig.set("Location." + name + ".y", (Object)y);
        dataConfig.set("Location." + name + ".z", (Object)z);
        dataConfig.set("Location." + name + ".world", (Object)location.getWorld().getName());
        List list = dataConfig.getStringList("List");
        if (!list.contains(name)) {
            list.add(name);
        }
        dataConfig.set("List", (Object)list);
        SurvivalRadiusConfig.saveConfig();
        Bukkit.broadcastMessage((String)((Object)ChatColor.GREEN + "Set warp th\u00e0nh c\u00f4ng!"));
    }

    public static HashMap<String, Location> getViTri() {
        List list = dataConfig.getStringList("List");
        HashMap<String, Location> locationList = new HashMap<String, Location>();
        for (String s : list) {
            Location location = new Location(null, 0.0, 0.0, 0.0);
            location.setX(dataConfig.getDouble("Location." + s + ".x"));
            location.setY(dataConfig.getDouble("Location." + s + ".y"));
            location.setZ(dataConfig.getDouble("Location." + s + ".z"));
            location.setWorld(Bukkit.getWorld((String)dataConfig.getString("Location." + s + ".world")));
            locationList.put(s, location);
        }
        return locationList;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Location playerLocation = e.getPlayer().getLocation();
        int lv = e.getPlayer().getLevel();
        double radius = lv * 20 + 200;
        for (String s : SurvivalRadius.getViTri().keySet()) {
            double distance;
            if (playerLocation.getWorld() != SurvivalRadius.getViTri().get(s).getWorld() || (distance = playerLocation.distance(SurvivalRadius.getViTri().get(s))) < radius) continue;
            if (e.getPlayer().hasPermission("ml.*")) {
                return;
            }
            e.getPlayer().sendMessage("\u00a7aB\u1ea1n kh\u00f4ng th\u1ec3 \u0111i ti\u1ebfp, h\u00e3y luy\u1ec7n Level \u0111\u1ec3 c\u00f3 th\u1ec3 \u0111i xa h\u01a1n!");
            e.getPlayer().setVelocity(SurvivalRadius.getViTri().get(s).subtract(playerLocation).toVector().multiply(1.0 / distance));
            return;
        }
    }
}

