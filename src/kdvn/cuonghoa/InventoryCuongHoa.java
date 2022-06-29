/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.cuonghoa;

import java.util.ArrayList;
import java.util.List;
import kdvn.cuonghoa.BuaCuongHoa;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryCuongHoa {
    public static final int WEAPON_SLOT = 11;
    public static final int INCANTATION_SLOT = 15;
    public static final int RESULT_SLOT = 31;
    public static final int BUTTON_SLOT = 44;
    public static final int GEM_SLOT = 13;
    public static final int TUTORIAL_SLOT = 36;
    public static final String TITLE = "               \u00a72\u00a7l\u00a7nC\u01af\u1edcNG H\u00d3A";

    public static Inventory getInventory() {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, (int)45, (String)"               \u00a72\u00a7l\u00a7nC\u01af\u1edcNG H\u00d3A");
        int i = 0;
        while (i < 45) {
            if (i != 11 && i != 15 && i != 31 && i != 44 && i != 13) {
                inv.setItem(i, InventoryCuongHoa.getBlackSlot());
            }
            ++i;
        }
        inv.setItem(44, InventoryCuongHoa.getCuongHoaButton());
        inv.setItem(36, InventoryCuongHoa.getHuongDan());
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

    public static ItemStack getCuongHoaButton() {
        ItemStack item = new ItemStack(Material.ANVIL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
        meta.setUnbreakable(true);
        meta.setDisplayName("\u00a7cB\u1ea5m \u0111\u1ec3 c\u01b0\u1eddng h\u00f3a");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a76> \u00a7aC\u1ea5p 0 -> 1: 100% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 1 -> 2: 50% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 2 -> 3: 30% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 3 -> 4: 25% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 4 -> 5: 20% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 5 -> 6: 15% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 6 -> 7: 10% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 7 -> 8: 5% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 8 -> 9: 2% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 9 -> 10: 1% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 10 -> 11: 0.8% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 11 -> 12: 0.6% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 12 -> 13: 0.4% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 13 -> 14: 0.2% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 14 -> 15: 0.1% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        lore.add("\u00a76> \u00a7aC\u1ea5p 15 -> ?: 0% x C\u1ea5p \u0111\u1ed9 \u0111\u00e1");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack getHuongDan() {
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("\u00a7aH\u01b0\u1edbng D\u1eabn");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7f\u00a7l      1      2      3      ");
        lore.add(" ");
        lore.add("\u00a7f\u00a7l             4");
        lore.add(" ");
        lore.add("\u00a75\u00d4 s\u1ed1 1: \u00a73V\u0169 kh\u00ed (Ph\u1ea3i c\u00f3 ch\u1ec9 s\u1ed1)");
        lore.add("\u00a75\u00d4 s\u1ed1 2: \u00a73\u0110\u00e1 C\u01b0\u1eddng H\u00f3a");
        lore.add("\u00a75\u00d4 s\u1ed1 3: \u00a73B\u00f9a C\u01b0\u1eddng H\u00f3a");
        lore.add("\u00a75\u00d4 s\u1ed1 4: \u00a73V\u0169 kh\u00ed sau khi C\u01b0\u1eddng H\u00f3a");
        lore.add(" ");
        lore.add("\u00a7f>>\u00a7cCh\u00fa \u00fd: \u00a76\u0110\u1ed3 +5 tr\u1edf l\u00ean c\u01b0\u1eddng h\u00f3a th\u1ea5t b\u1ea1i s\u1ebd b\u1ecb gi\u1ea3m c\u1ea5p");
        lore.add("\u00a7f>>\u00a7cCh\u00fa \u00fd: \u00a76C\u01b0\u1eddng h\u00f3a th\u1ea5t b\u1ea1i c\u00f3 " + BuaCuongHoa.getItem().getItemMeta().getDisplayName() + " \u00a76kh\u00f4ng l\u00e0m gi\u1ea3m c\u1ea5p");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}

