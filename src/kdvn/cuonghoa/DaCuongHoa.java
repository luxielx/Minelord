/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.cuonghoa;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DaCuongHoa {
    public static boolean isDaCuongHoa(ItemStack i) {
        if (!i.getItemMeta().hasDisplayName()) {
            return false;
        }
        if (i.getItemMeta().getDisplayName().contains("\u00a73\u0110\u00c1 C\u01af\u1edcNG H\u00d3A")) {
            return true;
        }
        return false;
    }

    public static ItemStack getDaCuongHoa(int capDo) {
        ItemStack da = new ItemStack(Material.CARPET, 1);
        ItemMeta meta = da.getItemMeta();
        int data = 0;
        data = capDo == 5 ? 3 + capDo + 1 : 3 + capDo;
        da.setDurability((short)data);
        String name = "\u00a73\u0110\u00c1 C\u01af\u1edcNG H\u00d3A \u00a7a[\u00a76C\u1ea4P \u0110\u1ed8\u00a7c " + capDo + "\u00a7a]";
        meta.setDisplayName(name);
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7c>> \u00a7f\u0110\u00e1 c\u01b0\u1eddng h\u00f3a d\u00f9ng \u0111\u1ec3 n\u00e2ng c\u1ea5p v\u0169 kh\u00ed");
        lore.add("\u00a7c>> \u00a7f\u0110\u00e1 c\u00e0ng cao t\u1ec9 l\u1ec7 th\u00e0nh c\u00f4ng c\u00e0ng cao");
        lore.add(" ");
        lore.add("\u00a7c>> \u00a76C\u01b0\u1eddng h\u00f3a th\u1ea5t b\u1ea1i c\u00f3 kh\u1ea3 n\u0103ng b\u1ecb gi\u1ea3m c\u1ea5p!");
        meta.setLore(lore);
        da.setItemMeta(meta);
        return da;
    }

    public static int getCapDoDaCuongHoa(ItemStack item) {
        if (!DaCuongHoa.isDaCuongHoa(item)) {
            return 0;
        }
        if (!item.getItemMeta().hasDisplayName()) {
            return 0;
        }
        String name = item.getItemMeta().getDisplayName();
        String capDoChar = name.substring(name.lastIndexOf(" ") + 1, name.lastIndexOf(" ") + 2);
        int capDo = Integer.parseInt(capDoChar);
        return capDo;
    }
}

