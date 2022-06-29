/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.tiemnang;

import java.util.ArrayList;
import java.util.List;
import kdvn.settings.SettingMethod;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemSelect {
    public static ItemStack tanCong() {
        ItemStack item = new ItemStack(Material.WOOD_HOE, 1);
        item.setDurability(33);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setDisplayName((Object)ChatColor.RED + "+1 S\u1ee8C M\u1ea0NH");
        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add((Object)ChatColor.WHITE + "====================");
        lore.add((Object)ChatColor.GREEN + "T\u0103ng " + 0.2f + " S\u00c1T TH\u01af\u01a0NG");
        lore.add((Object)ChatColor.RED + "> Click \u0111\u1ec3 c\u1ed9ng \u0111i\u1ec3m");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack theLuc() {
        ItemStack item = new ItemStack(Material.WOOD_HOE, 1);
        item.setDurability(35);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setDisplayName((Object)ChatColor.RED + "+1 TH\u1ec2 L\u1ef0C");
        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add((Object)ChatColor.WHITE + "====================");
        lore.add((Object)ChatColor.GREEN + "T\u0103ng " + SettingMethod.lamTronString(0.4000000059604645) + " M\u00c1U");
        lore.add((Object)ChatColor.RED + "> Click \u0111\u1ec3 c\u1ed9ng \u0111i\u1ec3m");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack phepThuat() {
        ItemStack item = new ItemStack(Material.WOOD_HOE, 1);
        item.setDurability(36);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setDisplayName((Object)ChatColor.RED + "+1 N\u0102NG L\u01af\u1ee2NG");
        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add((Object)ChatColor.WHITE + "====================");
        lore.add((Object)ChatColor.GREEN + "T\u0103ng " + 5 + " N\u0102NG L\u01af\u1ee2NG");
        lore.add((Object)ChatColor.RED + "> Click \u0111\u1ec3 c\u1ed9ng \u0111i\u1ec3m");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack triTue() {
        ItemStack item = new ItemStack(Material.WOOD_HOE, 1);
        item.setDurability(34);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.setDisplayName((Object)ChatColor.RED + "+1 NHANH NH\u1eb8N");
        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add((Object)ChatColor.WHITE + "====================");
        lore.add((Object)ChatColor.GREEN + "T\u0103ng " + 0.1f + "% N\u00c9 \u0110\u00d2N");
        lore.add((Object)ChatColor.RED + "> Click \u0111\u1ec3 c\u1ed9ng \u0111i\u1ec3m");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack showInfo() {
        ItemStack item = new ItemStack(Material.PAPER, 1);
        item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
        meta.setDisplayName((Object)ChatColor.RED + "HI\u1ec6N TH\u00d4NG TIN");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add((Object)ChatColor.GREEN + "> Click \u0111\u1ec3 xem");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}

