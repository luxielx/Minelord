/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.takemoneywhenkilledmonster;

import kdvn.settings.SettingMethod;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemMoneyDrop {
    private double money;
    private ItemStack item;

    public ItemMoneyDrop(double money) {
        this.money = money;
        this.setUpItem(money);
    }

    public double getMoney() {
        return this.money;
    }

    public void setMoney(double money) {
        this.money = money;
        this.setUpItem(money);
    }

    public ItemStack getItem() {
        return this.item;
    }

    private void setUpItem(double money) {
        ItemStack item = new ItemStack(Material.CARPET, 1);
        item.setDurability(11);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("\u00a7f\u00a7l" + money + " b\u1ea1c");
        item.setItemMeta(meta);
        this.item = item;
    }

    public static double getMoneyThroughItemName(ItemStack item) {
        double money = SettingMethod.lamTron(SettingMethod.getDoubleInString(item.getItemMeta().getDisplayName()));
        return money *= (double)item.getAmount();
    }

    public static boolean isThatItem(ItemStack item) {
        if (!item.getItemMeta().hasDisplayName()) {
            return false;
        }
        if (item.getType() == Material.CARPET && item.getDurability() == 11 && item.getItemMeta().getDisplayName().contains("b\u1ea1c")) {
            return true;
        }
        return false;
    }
}

