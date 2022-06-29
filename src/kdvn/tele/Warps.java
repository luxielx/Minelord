/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package kdvn.tele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kdvn.config.WarpConfig;
import kdvn.main.Main;
import kdvn.point.Money;
import kdvn.settings.Particle;
import kdvn.skill.archer.BangKich;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Warps {
    private static FileConfiguration dataConfig = WarpConfig.getConfig();
    private static final int TIME_DELAY = 5;

    public static void setLocation(Player player, Location location, String name, int lv, double money, Material material) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        dataConfig.set("Location." + name + ".x", (Object)x);
        dataConfig.set("Location." + name + ".y", (Object)y);
        dataConfig.set("Location." + name + ".z", (Object)z);
        dataConfig.set("Location." + name + ".world", (Object)location.getWorld().getName());
        dataConfig.set("Location." + name + ".pitch", (Object)Float.valueOf(location.getPitch()));
        dataConfig.set("Location." + name + ".yaw", (Object)Float.valueOf(location.getYaw()));
        dataConfig.set("Location." + name + ".level", (Object)lv);
        dataConfig.set("Location." + name + ".money", (Object)money);
        dataConfig.set("Location." + name + ".material", (Object)material.toString());
        List list = dataConfig.getStringList("List");
        if (!list.contains(name)) {
            list.add(name);
        }
        dataConfig.set("List", (Object)list);
        WarpConfig.saveConfig();
        player.sendMessage((Object)ChatColor.GREEN + "Set warp th\u00e0nh c\u00f4ng!");
    }

    public static void deleteWarp(String warp) {
        List list = dataConfig.getStringList("List");
        if (list.contains(warp)) {
            list.remove(warp);
            dataConfig.set("Location." + warp, (Object)null);
        }
        dataConfig.set("List", (Object)list);
    }

    public static HashMap<String, Location> getWarps() {
        List list = dataConfig.getStringList("List");
        HashMap<String, Location> locationList = new HashMap<String, Location>();
        for (String s : list) {
            Location location = new Location(null, 0.0, 0.0, 0.0);
            location.setX(dataConfig.getDouble("Location." + s + ".x"));
            location.setY(dataConfig.getDouble("Location." + s + ".y"));
            location.setZ(dataConfig.getDouble("Location." + s + ".z"));
            location.setPitch((float)dataConfig.getDouble("Location." + s + ".pitch"));
            location.setYaw((float)dataConfig.getDouble("Location." + s + ".yaw"));
            location.setWorld(Bukkit.getWorld((String)dataConfig.getString("Location." + s + ".world")));
            locationList.put(s, location);
        }
        return locationList;
    }

    public static int getLevelRequirement(String warp) {
        return dataConfig.getInt("Location." + warp + ".level");
    }

    public static double getMoneyRequirement(String warp) {
        return dataConfig.getDouble("Location." + warp + ".money");
    }

    public static Location getLocation(String warp) {
        return Warps.getWarps().get(warp);
    }

    public static Material getMaterial(String warp) {
        return Material.valueOf((String)dataConfig.getString("Location." + warp + ".material"));
    }

    public static void teleport(Player player, String warp) {
        if (!dataConfig.contains("List")) {
            return;
        }
        List listWarps = dataConfig.getStringList("List");
        if (!listWarps.contains(warp)) {
            player.sendMessage("\u00a76\u0110\u1ecba \u0111i\u1ec3m d\u1ecbch chuy\u1ec3n kh\u00f4ng t\u1ed3n t\u1ea1i");
            return;
        }
        int lv = Warps.getLevelRequirement(warp);
        if (player.getLevel() < lv) {
            player.sendMessage("\u00a7cC\u1ea5p \u0111\u1ed9 c\u1ee7a b\u1ea1n kh\u00f4ng \u0111\u1ee7 \u0111\u1ec3 d\u1ecbch chuy\u1ec3n");
            return;
        }
        if (!Money.moneyCost(player, Warps.getMoneyRequirement(warp))) {
            player.sendMessage("\u00a7cB\u1ea1n kh\u00f4ng \u0111\u1ee7 \u00a76Ti\u1ec1n \u00a7c\u0111\u1ec3 d\u1ecbch chuy\u1ec3n");
            return;
        }
        Location location = Warps.getLocation(warp);
        Warps.dichChuyen(player, location, 5);
    }

    private static void dichChuyen(final Player player, final Location toLocation, int timeDelay) {
        Location location = player.getLocation();
        player.sendMessage("\u00a7aD\u1ecbch chuy\u1ec3n trong \u00a7c5s");
        BangKich.dongBang((LivingEntity)player);
        new BukkitRunnable(){
            int i;

            public void run() {
                ++this.i;
                if (this.i == 5) {
                    this.cancel();
                    return;
                }
                Warps.createCircle(EnumParticle.FLAME, Location.this.clone().add(0.0, 0.20000000298023224, 0.0), (float)(this.i + 1) / 2.0f);
                Warps.createCircle(EnumParticle.END_ROD, toLocation.clone().add(0.0, 0.20000000298023224, 0.0), (float)(5 - this.i) / 2.0f);
            }
        }.runTaskTimerAsynchronously((Plugin)Main.plugin, 0, 20);
        new BukkitRunnable(){
            int i;

            public void run() {
                ++this.i;
                Location.this.getWorld().playSound(Location.this, Sound.BLOCK_ANVIL_PLACE, 1.0f, 1.0f);
                if (this.i == 5) {
                    player.teleport(toLocation);
                    player.sendMessage("\u00a7aD\u1ecbch chuy\u1ec3n th\u00e0nh c\u00f4ng");
                    BangKich.phaBang((LivingEntity)player);
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)Main.plugin, 0, 20);
    }

    public static void createCircle(EnumParticle particle, Location location, double radius) {
        Double d = new Double(radius);
        int amount = (d.intValue() + 1) * 4;
        double increment = 6.283185307179586 / (double)amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        int i = 0;
        while (i < amount) {
            double angle = (double)i * increment;
            double x = location.getX() + radius * Math.cos(angle);
            double z = location.getZ() + radius * Math.sin(angle);
            locations.add(new Location(location.getWorld(), x, location.getY(), z));
            ++i;
        }
        for (Location l : locations) {
            Particle.sendParticle(particle, l, 0.0f, 0.0f, 0.0f, 0.0f, 1);
        }
    }

    public static void sendWarpList(Player player) {
        List listWarps = dataConfig.getStringList("List");
        for (String s : listWarps) {
            int lv = Warps.getLevelRequirement(s);
            double money = Warps.getLevelRequirement(s);
            player.sendMessage("\u00a73\u0110\u1ecba \u0111i\u1ec3m: \u00a7c" + s + " \u00a76| \u00a73C\u1ea5p \u0111\u1ed9: \u00a7c" + lv + " \u00a76| \u00a73Ti\u1ec1n: \u00a7c" + money);
        }
    }

}

