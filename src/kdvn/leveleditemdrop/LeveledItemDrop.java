/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.leveleditemdrop;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LeveledItemDrop {
    public static ItemStack getItemLv1() {
        ItemStack item = new ItemStack(Material.CARPET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("\u00a78\u0110\u00e1 linh h\u1ed3n \u00a7a[\u00a7cC\u1ea5p \u0111\u1ed9: 1\u00a7a]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a73> \u00a7fNh\u1eb7t \u0111\u01b0\u1ee3c khi gi\u1ebft qu\u00e1i");
        lore.add("\u00a73> \u00a7fD\u00f9ng \u0111\u1ec3 trao \u0111\u1ed5i");
        lore.add(" ");
        lore.add("\u00a73> \u00a7cQu\u00e1i m\u1ea1nh th\u00ec t\u1ec9 l\u1ec7 r\u1edbt v\u00e0 \u0111\u1ed3 hi\u1ebfm cao");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getItemLv2() {
        ItemStack item = new ItemStack(Material.CARPET, 1);
        item.setDurability(1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("\u00a76\u0110\u00e1 linh h\u1ed3n \u00a7a[\u00a7cC\u1ea5p \u0111\u1ed9: 2\u00a7a]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a73> \u00a7fNh\u1eb7t \u0111\u01b0\u1ee3c khi gi\u1ebft qu\u00e1i");
        lore.add("\u00a73> \u00a7fD\u00f9ng \u0111\u1ec3 trao \u0111\u1ed5i");
        lore.add(" ");
        lore.add("\u00a73> \u00a7cQu\u00e1i m\u1ea1nh th\u00ec t\u1ec9 l\u1ec7 r\u1edbt v\u00e0 \u0111\u1ed3 hi\u1ebfm cao");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getItemLv3() {
        ItemStack item = new ItemStack(Material.CARPET, 1);
        item.setDurability(2);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("\u00a72\u0110\u00e1 linh h\u1ed3n \u00a7a[\u00a7cC\u1ea5p \u0111\u1ed9: 3\u00a7a]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a73> \u00a7fNh\u1eb7t \u0111\u01b0\u1ee3c khi gi\u1ebft qu\u00e1i");
        lore.add("\u00a73> \u00a7fD\u00f9ng \u0111\u1ec3 trao \u0111\u1ed5i");
        lore.add(" ");
        lore.add("\u00a73> \u00a7cQu\u00e1i m\u1ea1nh th\u00ec t\u1ec9 l\u1ec7 r\u1edbt v\u00e0 \u0111\u1ed3 hi\u1ebfm cao");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getItemLv4() {
        ItemStack item = new ItemStack(Material.CARPET, 1);
        item.setDurability(3);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("\u00a74\u0110\u00e1 linh h\u1ed3n \u00a7a[\u00a7cC\u1ea5p \u0111\u1ed9: 4\u00a7a]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a73> \u00a7fNh\u1eb7t \u0111\u01b0\u1ee3c khi gi\u1ebft qu\u00e1i");
        lore.add("\u00a73> \u00a7fD\u00f9ng \u0111\u1ec3 trao \u0111\u1ed5i");
        lore.add(" ");
        lore.add("\u00a73> \u00a7cQu\u00e1i m\u1ea1nh th\u00ec t\u1ec9 l\u1ec7 r\u1edbt v\u00e0 \u0111\u1ed3 hi\u1ebfm cao");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}

