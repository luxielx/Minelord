/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.inventory.InventoryCloseEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package kdvn.cuonghoa;

import java.util.ArrayList;
import java.util.List;
import kdvn.cuonghoa.ItemCuongHoa;
import kdvn.main.Main;
import kdvn.point.Money;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class ChuyenHoaItem {
    public static final int WEAPON_1 = 11;
    public static final int WEAPON_2 = 15;
    public static final int BUTTON = 31;
    public static final int MONEY_COST = 50000;
    public static final String TITLE = "\u00a72\u00a7l\u00a7nCHUY\u1ec2N H\u00d3A";
    public static final String NPC_NAME = "\u00a76Chuy\u1ec3n H\u00f3a";

    public static Inventory getInventory() {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, (int)54, (String)"\u00a72\u00a7l\u00a7nCHUY\u1ec2N H\u00d3A");
        int i = 0;
        while (i < 54) {
            if (i != 11 && i != 15 && i != 31) {
                inv.setItem(i, ChuyenHoaItem.getBlackSlot());
            }
            ++i;
        }
        inv.setItem(31, ChuyenHoaItem.getChuyenHoaButton());
        return inv;
    }

    public static ItemStack getBlackSlot() {
        ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1);
        black.setDurability(11);
        ItemMeta meta = black.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setDisplayName("\u00a75*");
        black.setItemMeta(meta);
        return black;
    }

    public static ItemStack getChuyenHoaButton() {
        ItemStack item = new ItemStack(Material.ANVIL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
        meta.setUnbreakable(true);
        meta.setDisplayName("\u00a7cB\u1ea5m \u0111\u1ec3 chuy\u1ec3n h\u00f3a");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aS\u1ed1 ti\u1ec1n m\u1ea5t khi chuy\u1ec3n h\u00f3a: \u00a7f50000 b\u1ea1c");
        lore.add("\u00a7aH\u01b0\u1edbng d\u1eabn: \u00a7e\u0110\u1eb7t 2 v\u0169 kh\u00ed v\u00e0o 2 \u00f4 v\u00e0 b\u1ea5m chuy\u1ec3n h\u00f3a");
        lore.add("\u00a7c           \u00a7eSau \u0111\u00f3 c\u1ea5p \u0111\u1ed9 2 v\u0169 kh\u00ed s\u1ebd chuy\u1ec3n cho nhau");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static void eventHandlingClickInventory(InventoryClickEvent e) {
        if (e.getClickedInventory() != e.getWhoClicked().getOpenInventory().getTopInventory()) {
            return;
        }
        if (!e.getInventory().getTitle().equals("\u00a72\u00a7l\u00a7nCHUY\u1ec2N H\u00d3A")) {
            return;
        }
        int slot = e.getSlot();
        if (slot != 11 && slot != 15) {
            e.setCancelled(true);
        }
        ItemStack weapon1 = e.getInventory().getItem(11);
        ItemStack weapon2 = e.getInventory().getItem(15);
        if (slot == 31) {
            if (!Money.moneyCost((Player)e.getWhoClicked(), 50000.0)) {
                e.getWhoClicked().sendMessage("\u00a7cB\u1ea1n kh\u00f4ng \u0111\u1ee7 ti\u1ec1n");
                e.getWhoClicked().closeInventory();
                return;
            }
            if (weapon1 == null || weapon2 == null) {
                return;
            }
            int weapon1Lv = ItemCuongHoa.getCapDo(weapon1);
            int weapon2Lv = ItemCuongHoa.getCapDo(weapon2);
            final ItemStack newItem1 = ItemCuongHoa.cuongHoa(weapon1, weapon2Lv);
            final ItemStack newItem2 = ItemCuongHoa.cuongHoa(weapon2, weapon1Lv);
            e.getInventory().setItem(11, new ItemStack(Material.AIR));
            e.getInventory().setItem(15, new ItemStack(Material.AIR));
            e.getWhoClicked().getWorld().playSound(e.getWhoClicked().getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, 1.0f);
            new BukkitRunnable(){

                public void run() {
                    InventoryClickEvent.this.getWhoClicked().getWorld().playSound(InventoryClickEvent.this.getWhoClicked().getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 1.0f, 1.0f);
                    InventoryClickEvent.this.getInventory().setItem(11, newItem1);
                    InventoryClickEvent.this.getInventory().setItem(15, newItem2);
                }
            }.runTaskLater((Plugin)Main.plugin, 10);
        }
    }

    public static void eventHandlingCloseInventory(InventoryCloseEvent e) {
        Player player = (Player)e.getPlayer();
        if (e.getInventory().getTitle().equals("\u00a72\u00a7l\u00a7nCHUY\u1ec2N H\u00d3A")) {
            Inventory inv = e.getInventory();
            if (inv.getItem(11) != null) {
                player.getWorld().dropItem(player.getLocation(), inv.getItem(11));
            }
            if (inv.getItem(15) != null) {
                player.getWorld().dropItem(player.getLocation(), inv.getItem(15));
            }
        }
    }

}

