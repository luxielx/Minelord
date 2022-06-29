/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.cuonghoa;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuaCuongHoa {
    private static final String NAME = "\u00a7f\u00a7lB\u00d9A C\u01af\u1edcNG H\u00d3A";

    public static ItemStack getItem() {
        ItemStack item = new ItemStack(Material.CARPET, 1);
        item.setDurability(10);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7c> \u00a73D\u00f9ng khi c\u01b0\u1eddng h\u00f3a");
        lore.add("\u00a7c> \u00a73C\u01b0\u1eddng h\u00f3a th\u1ea5t b\u1ea1i kh\u00f4ng b\u1ecb gi\u1ea3m c\u1ea5p!");
        meta.setLore(lore);
        meta.setDisplayName("\u00a7f\u00a7lB\u00d9A C\u01af\u1edcNG H\u00d3A");
        item.setItemMeta(meta);
        return item;
    }

    public static boolean isThatItem(ItemStack item) {
        if (item.getType() == Material.AIR) {
            return false;
        }
        if (!item.getItemMeta().hasDisplayName()) {
            return false;
        }
        if (!item.getItemMeta().hasLore()) {
            return false;
        }
        if (item.getItemMeta().getDisplayName().equals("\u00a7f\u00a7lB\u00d9A C\u01af\u1edcNG H\u00d3A")) {
            return true;
        }
        return false;
    }
}

