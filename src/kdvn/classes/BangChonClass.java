/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.classes;

import java.util.List;
import kdvn.classes.Archer;
import kdvn.classes.Knight;
import kdvn.classes.Mage;
import kdvn.settings.SettingMethod;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BangChonClass {
    public static final String TITLE = SettingMethod.colorDecomplier("&2&l&nCH\u1eccN CLASS");

    public static Inventory get() {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, (int)9, (String)TITLE);
        inv.setItem(0, BangChonClass.getArcher());
        inv.setItem(1, BangChonClass.getKnight());
        inv.setItem(2, BangChonClass.getMage());
        inv.setItem(8, BangChonClass.getRemoveClass());
        return inv;
    }

    public static void show(Player player) {
        player.openInventory(BangChonClass.get());
    }

    private static ItemStack getArcher() {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(SettingMethod.colorDecomplier("&a&l" + "archer".toUpperCase()));
        meta.setLore(Archer.getThongTin());
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack getKnight() {
        ItemStack item = new ItemStack(Material.SHIELD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(SettingMethod.colorDecomplier("&8&l" + "knight".toUpperCase()));
        meta.setLore(Knight.getThongTin());
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack getMage() {
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(SettingMethod.colorDecomplier("&5&l" + "mage".toUpperCase()));
        meta.setLore(Mage.getThongTin());
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack getRemoveClass() {
        ItemStack item = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(SettingMethod.colorDecomplier("&f&lX\u00f3a Class"));
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        item.setItemMeta(meta);
        return item;
    }
}

