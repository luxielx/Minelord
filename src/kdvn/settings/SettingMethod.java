/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.gmail.filoghost.holographicdisplays.api.Hologram
 *  com.gmail.filoghost.holographicdisplays.api.HologramsAPI
 *  com.gmail.filoghost.holographicdisplays.api.line.TextLine
 *  net.md_5.bungee.api.ChatColor
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package kdvn.settings;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kdvn.main.Main;
import kdvn.settings.Particle;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class SettingMethod {
    public static void moveUpHolograms(Location location, String mess) {
        Hologram holo = HologramsAPI.createHologram((Plugin)Main.plugin, (Location)location);
        holo.appendTextLine(mess);
        new BukkitRunnable(){
            int i;

            public void run() {
                ++this.i;
                if (this.i >= 9) {
                    this.cancel();
                    return;
                }
                if (!Hologram.this.isDeleted()) {
                    Hologram.this.teleport(Hologram.this.getLocation().add(0.0, 0.05000000074505806, 0.0));
                }
            }
        }.runTaskTimerAsynchronously((Plugin)Main.plugin, 0, 1);
        new BukkitRunnable(){

            public void run() {
                if (!Hologram.this.isDeleted()) {
                    Hologram.this.delete();
                }
            }
        }.runTaskLater((Plugin)Main.plugin, 12);
    }

    public static int getMobLevel(LivingEntity mob) {
        String name = mob.getName();
        if (!name.contains("Lv")) {
            return 0;
        }
        int lv = SettingMethod.getIntInString(name);
        if (lv == 0) {
            Double d = new Double(mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            lv = d.intValue() / 20;
        }
        return lv;
    }

    public static double getDoubleInString(String s) {
        Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");
        Matcher m = p.matcher(s);
        if (m.find()) {
            double d = Double.parseDouble(m.group(1));
            d = SettingMethod.lamTron(d);
            return d;
        }
        return 0.0;
    }

    public static double lamTron(double i) {
        String s = SettingMethod.lamTronString(i).replace(",", ".");
        double newDouble = Double.valueOf(s);
        return newDouble;
    }

    public static String lamTronString(double i) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(i).replace(",", ".");
    }

    public static int getIntInString(String s) {
        String intValue = s.replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(intValue);
        }
        catch (Exception e) {
            return 0;
        }
    }

    public static boolean tiLe(double tiLe) {
        double rate = tiLe * 100.0;
        int random = new Random().nextInt(10000);
        if ((double)random < rate) {
            return true;
        }
        return false;
    }

    public static String colorDecomplier(String s) {
        return ChatColor.translateAlternateColorCodes((char)'&', (String)s);
    }

    public static List<Entity> getEntitiesByLocation(Location lo, float r) {
        ArrayList<Entity> listEn = new ArrayList<Entity>();
        for (Entity e : lo.getWorld().getEntities()) {
            Location locE = e.getLocation().add(0.0, 0.75, 0.0);
            if (locE.distanceSquared(lo) > (double)r) continue;
            listEn.add(e);
        }
        return listEn;
    }

    public static String hoiKyNangMess(String kyNang) {
        return (Object)ChatColor.DARK_AQUA + "H\u1ed3i k\u1ef9 n\u0103ng: " + (Object)ChatColor.YELLOW + kyNang;
    }

    public static void createCircle(EnumParticle e, Location location, int radius) {
        int amount = radius * 10;
        double increment = 6.283185307179586 / (double)amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        int i = 0;
        while (i < amount) {
            double angle = (double)i * increment;
            double x = location.getX() + (double)radius * Math.cos(angle);
            double z = location.getZ() + (double)radius * Math.sin(angle);
            locations.add(new Location(location.getWorld(), x, location.getY(), z));
            ++i;
        }
        for (Location l : locations) {
            Particle.sendParticle(e, l, 0.0f, 0.0f, 0.0f, 0.0f, 1);
        }
    }

    public static String getItemName(ItemStack i) {
        String name = "";
        name = !i.getItemMeta().hasDisplayName() ? i.getType().name().replaceAll("_", " ") : i.getItemMeta().getDisplayName();
        return name;
    }

    public static List<Double> getListDoubleInString(String s) {
        Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");
        Matcher m = p.matcher(s);
        ArrayList<Double> number = new ArrayList<Double>();
        while (m.find()) {
            double d = Double.parseDouble(m.group(1));
            d = SettingMethod.lamTron(d);
            number.add(d);
        }
        return number;
    }

}

