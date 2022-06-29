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
import kdvn.classes.BangChonClass;
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

public class ChuyenClassItem
implements Listener {
    public static final String NAME = "\u00a76[\u00a7cHI\u1ebeM\u00a76] \u00a73Th\u1ebb chuy\u1ec3n \u0111\u1ed5i CLASS";

    public static ItemStack getItem() {
        ItemStack item = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("\u00a76[\u00a7cHI\u1ebeM\u00a76] \u00a73Th\u1ebb chuy\u1ec3n \u0111\u1ed5i CLASS");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7f> \u00a7aItem d\u00f9ng \u0111\u1ec3 chuy\u1ec3n CLASS kh\u00e1c khi b\u1ea1n \u0111\u00e3 c\u00f3 CLASS r\u1ed3i");
        lore.add("\u00a7f> \u00a7aClick chu\u1ed9t ph\u1ea3i \u0111\u1ec3 d\u00f9ng");
        lore.add(" ");
        lore.add("\u00a7f> \u00a7aL\u01b0u \u00fd: Ch\u1ec9 d\u00f9ng \u0111\u01b0\u1ee3c m\u1ed9t l\u1ea7n duy nh\u1ea5t!");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public boolean isThatItem(ItemStack i) {
        if (i.getItemMeta().hasDisplayName()) {
            if (i.getItemMeta().getDisplayName().equals("\u00a76[\u00a7cHI\u1ebeM\u00a76] \u00a73Th\u1ebb chuy\u1ec3n \u0111\u1ed5i CLASS")) {
                if (i.getItemMeta().hasLore()) {
                    if (i.getItemMeta().getLore().contains("\u00a7f> \u00a7aItem d\u00f9ng \u0111\u1ec3 chuy\u1ec3n CLASS kh\u00e1c khi b\u1ea1n \u0111\u00e3 c\u00f3 CLASS r\u1ed3i")) {
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public void useItem(Player player) {
        if (!this.isThatItem(player.getInventory().getItemInMainHand())) {
            return;
        }
        ItemStack i = player.getInventory().getItemInMainHand();
        if (i.getAmount() == 1) {
            player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        } else {
            i.setAmount(i.getAmount() - 1);
            player.getInventory().setItemInMainHand(i);
        }
        BangChonClass.show(player);
    }

    @EventHandler
    public void useItemEvent(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) {
            return;
        }
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR && this.isThatItem(e.getPlayer().getInventory().getItemInMainHand())) {
            this.useItem(e.getPlayer());
        }
    }
}

