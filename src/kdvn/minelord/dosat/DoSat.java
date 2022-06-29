/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.inventivetalent.glow.GlowAPI
 *  org.inventivetalent.glow.GlowAPI$Color
 */
package kdvn.minelord.dosat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kdvn.classes.ClassSetting;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.inventivetalent.glow.GlowAPI;

public class DoSat {
    public static int TIME_PER_LIFE = 30;
    public static int TIME_RUNNABLE = 1;
    public static int POINT_PER_SECOND = 2;
    public static int CHANGE_SLOT = 0;
    public static int XOA_10_SLOT = 1;
    public static int XOA_30_SLOT = 2;
    public static int XOA_50_SLOT = 3;
    public static String TITLE = "\u00a7c\u00a7l\u0110\u1ed2 S\u00c1T";
    public static int MONEY = 500;
    private static Set<Player> turnedOn = new HashSet<Player>();
    private static Map<Player, Integer> timeCounting = new HashMap<Player, Integer>();

    public static boolean isTurnedOn(Player player) {
        if (turnedOn.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static boolean canAttack(Player player, Player target) {
        if (!DoSat.isTurnedOn(player) && !DoSat.isWanted(target)) {
            return false;
        }
        return true;
    }

    public static boolean canAttack(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return true;
        }
        if (!(e.getEntity() instanceof Player)) {
            return true;
        }
        Player damager = (Player)e.getDamager();
        Player target = (Player)e.getEntity();
        return DoSat.canAttack(damager, target);
    }

    public static boolean isWanted(Player player) {
        boolean check = timeCounting.containsKey((Object)player);
        return check;
    }

    public static void turnOnOrOff(Player player) {
        if (DoSat.isTurnedOn(player)) {
            DoSat.turnOff(player);
            ClassSetting.setPlayerNameTag(player);
        } else {
            DoSat.turnOn(player);
        }
    }

    public static void removeWanted(Player player) {
        if (timeCounting.containsKey((Object)player)) {
            timeCounting.remove((Object)player);
        }
    }

    public static int getTimeRemaining(Player player) {
        if (!timeCounting.containsKey((Object)player)) {
            return 0;
        }
        return timeCounting.get((Object)player);
    }

    public static void addTime(Player player, int time) {
        if (timeCounting.containsKey((Object)player)) {
            if (time < 0 && (float)DoSat.getTimeRemaining(player) < (float)time * -1.0f) {
                timeCounting.put(player, 1);
            } else {
                timeCounting.put(player, timeCounting.get((Object)player) + time);
            }
            return;
        }
        timeCounting.put(player, time);
    }

    public static void addGlow(Player player, int time) {
        GlowAPI.setGlowing((Entity)player, (GlowAPI.Color)GlowAPI.Color.RED, (Collection)Bukkit.getOnlinePlayers());
    }

    public static void addGlow(Player player) {
        GlowAPI.setGlowing((Entity)player, (GlowAPI.Color)GlowAPI.Color.RED, (Collection)Bukkit.getOnlinePlayers());
    }

    public static void removeGlow(Player player) {
        GlowAPI.setGlowing((Entity)player, (boolean)false, (Collection)Bukkit.getOnlinePlayers());
    }

    public static void turnOn(Player player) {
        turnedOn.add(player);
        DoSat.addTime(player, 1);
        player.sendMessage("\u00a7aB\u1eadt \u00a7c\u00a7l\u0110\u1ed2 S\u00c1T");
    }

    public static void turnOff(Player player) {
        if (turnedOn.contains((Object)player)) {
            turnedOn.remove((Object)player);
        }
        player.sendMessage("\u00a7aT\u1eaft \u00a7c\u00a7l\u0110\u1ed2 S\u00c1T");
    }

    public static Inventory getInventory(Player player) {
        ItemStack item = new ItemStack(Material.REDSTONE, 1);
        ItemMeta meta = item.getItemMeta();
        String name = "\u00a7c\u00a7lB\u1eacT \u0110\u1ed2 S\u00c1T";
        if (DoSat.isTurnedOn(player)) {
            name = "\u00a7c\u00a7lT\u1eaeT \u0110\u1ed2 S\u00c1T";
        }
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        ItemStack item1 = new ItemStack(Material.PAPER, 1);
        ItemMeta meta1 = item.getItemMeta();
        String name1 = "\u00a76Gi\u1ea3m 10s truy n\u00e3";
        meta1.setDisplayName(name1);
        ArrayList<String> lore1 = new ArrayList<String>();
        lore1.add("\u00a7cC\u1ea7n " + POINT_PER_SECOND * 10 + " v\u00e0ng");
        meta1.setLore(lore1);
        item1.setItemMeta(meta1);
        ItemStack item2 = new ItemStack(Material.PAPER, 1);
        ItemMeta meta2 = item.getItemMeta();
        String name2 = "\u00a76Gi\u1ea3m 30s truy n\u00e3";
        meta2.setDisplayName(name2);
        ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add("\u00a7cC\u1ea7n " + POINT_PER_SECOND * 30 + " v\u00e0ng");
        meta2.setLore(lore2);
        item2.setItemMeta(meta2);
        ItemStack item3 = new ItemStack(Material.PAPER, 1);
        ItemMeta meta3 = item.getItemMeta();
        String name3 = "\u00a76Gi\u1ea3m 50s truy n\u00e3";
        meta3.setDisplayName(name3);
        ArrayList<String> lore3 = new ArrayList<String>();
        lore3.add("\u00a7cC\u1ea7n " + POINT_PER_SECOND * 50 + " v\u00e0ng");
        meta3.setLore(lore3);
        item3.setItemMeta(meta3);
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, (int)9, (String)TITLE);
        inv.setItem(CHANGE_SLOT, item);
        inv.setItem(XOA_10_SLOT, item1);
        inv.setItem(XOA_30_SLOT, item2);
        inv.setItem(XOA_50_SLOT, item3);
        return inv;
    }
}

