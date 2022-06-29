/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.EquipmentSlot
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.items;

import java.util.ArrayList;
import java.util.List;
import kdvn.phepthuat.NangLuong;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class ThuocHoiNangLuong
implements Listener {
    public static final String LORE_NAME = "\u00a7aN\u0103ng l\u01b0\u1ee3ng ph\u1ee5c h\u1ed3i:\u00a7f ";

    public static boolean isThatItem(ItemStack item) {
        if (item.getType() == Material.AIR) {
            return false;
        }
        if (!item.getItemMeta().hasLore()) {
            return false;
        }
        for (String s : item.getItemMeta().getLore()) {
            if (!s.contains("\u00a7aN\u0103ng l\u01b0\u1ee3ng ph\u1ee5c h\u1ed3i:\u00a7f ")) continue;
            return true;
        }
        return false;
    }

    public static void addLore(ItemStack item, int mana) {
        ItemMeta meta = item.getItemMeta();
        if (item.getItemMeta().hasLore()) {
            List lore = item.getItemMeta().getLore();
            lore.add("\u00a7aN\u0103ng l\u01b0\u1ee3ng ph\u1ee5c h\u1ed3i:\u00a7f " + mana);
            meta.setLore(lore);
            item.setItemMeta(meta);
        } else {
            ArrayList<String> lore = new ArrayList<String>();
            lore.add("\u00a7aN\u0103ng l\u01b0\u1ee3ng ph\u1ee5c h\u1ed3i:\u00a7f " + mana);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }

    public static int getManaFromItem(ItemStack item) {
        int damage = 0;
        if (!item.getItemMeta().hasLore()) {
            return 0;
        }
        ItemMeta meta = item.getItemMeta();
        List lore = meta.getLore();
        int i = 0;
        while (i < lore.size()) {
            if (((String)lore.get(i)).contains("\u00a7aN\u0103ng l\u01b0\u1ee3ng ph\u1ee5c h\u1ed3i:\u00a7f ")) {
                String damageLore = (String)lore.get(i);
                String satThuongChar = damageLore.substring(damageLore.lastIndexOf(" ") + 1, damageLore.length());
                try {
                    int satThuong;
                    damage = satThuong = Integer.parseInt(satThuongChar);
                    break;
                }
                catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }
            ++i;
        }
        return damage;
    }

    public static void setItem(ItemStack item, int mana) {
        ThuocHoiNangLuong.addLore(item, mana);
    }

    @EventHandler
    public void onUseItem(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) {
            return;
        }
        if (e.getPlayer().getInventory().getItemInMainHand().getType() == null) {
            return;
        }
        if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (ThuocHoiNangLuong.isThatItem(e.getItem())) {
                ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
                NangLuong.addNangLuong(e.getPlayer(), ThuocHoiNangLuong.getManaFromItem(i));
                e.getPlayer().sendMessage("\u00a7aB\u1ea1n \u0111\u01b0\u1ee3c h\u1ed3i " + ThuocHoiNangLuong.getManaFromItem(i) + " n\u0103ng l\u01b0\u1ee3ng");
                if (i.getAmount() == 1) {
                    e.getPlayer().getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                } else {
                    i.setAmount(i.getAmount() - 1);
                    e.getPlayer().getInventory().setItemInMainHand(i);
                }
            }
            return;
        }
    }
}

