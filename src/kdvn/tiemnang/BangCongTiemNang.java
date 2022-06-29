/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Listener
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.tiemnang;

import java.util.List;
import kdvn.classes.ClassSetting;
import kdvn.settings.SettingMethod;
import kdvn.tiemnang.ItemSelect;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BangCongTiemNang
implements Listener {
    public static final String TITLE = ChatColor.translateAlternateColorCodes((char)'&', (String)"&2&lC\u1ed8NG TI\u1ec0M N\u0102NG");

    public static Inventory getBang(Player player) {
        Inventory inventory = Bukkit.createInventory((InventoryHolder)null, (int)9, (String)TITLE);
        inventory.setItem(0, ItemSelect.tanCong());
        inventory.setItem(1, ItemSelect.triTue());
        inventory.setItem(2, ItemSelect.theLuc());
        inventory.setItem(3, ItemSelect.phepThuat());
        inventory.setItem(8, ItemSelect.showInfo());
        if (ClassSetting.getClass(player).equalsIgnoreCase("archer")) {
            ItemStack archerItem = ItemSelect.triTue();
            List lore = archerItem.getItemMeta().getLore();
            lore.add((Object)ChatColor.DARK_AQUA + "[ARCHER] T\u0103ng th\u00eam " + 0.1f + " S\u00c1T TH\u01af\u01a0NG");
            lore.add((Object)ChatColor.DARK_AQUA + "[ARCHER] T\u0103ng th\u00eam 0.025 T\u1ed0C \u0110\u1ed8 B\u1eaeN CUNG");
            ItemMeta meta = archerItem.getItemMeta();
            meta.setLore(lore);
            archerItem.setItemMeta(meta);
            inventory.setItem(1, archerItem);
        } else if (ClassSetting.getClass(player).equalsIgnoreCase("knight")) {
            ItemStack knightItem = ItemSelect.theLuc();
            List lore = knightItem.getItemMeta().getLore();
            lore.add((Object)ChatColor.DARK_AQUA + "[KNIGHT] T\u0103ng th\u00eam " + SettingMethod.lamTron(0.4000000059604645) + " M\u00c1U");
            ItemMeta meta = knightItem.getItemMeta();
            meta.setLore(lore);
            knightItem.setItemMeta(meta);
            inventory.setItem(2, knightItem);
        } else if (ClassSetting.getClass(player).equalsIgnoreCase("mage")) {
            ItemStack mageItem = ItemSelect.phepThuat();
            List lore = mageItem.getItemMeta().getLore();
            lore.add((Object)ChatColor.DARK_AQUA + "[MAGE] T\u0103ng th\u00eam " + 5 + " N\u0102NG L\u01af\u1ee2NG");
            lore.add((Object)ChatColor.DARK_AQUA + "[MAGE] T\u0103ng th\u00eam " + 0.18 + " s\u00e1t th\u01b0\u01a1ng");
            ItemMeta meta = mageItem.getItemMeta();
            meta.setLore(lore);
            mageItem.setItemMeta(meta);
            inventory.setItem(3, mageItem);
        }
        return inventory;
    }

    public static void show(Player player) {
        player.openInventory(BangCongTiemNang.getBang(player));
    }
}

