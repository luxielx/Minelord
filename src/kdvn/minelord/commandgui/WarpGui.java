/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.minelord.commandgui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kdvn.config.WarpConfig;
import kdvn.tele.Warps;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WarpGui
implements Listener {
    public static final String TITLE = "\u00a72\u00a7l\u00a7nD\u1ecaCH CHUY\u1ec2N";
    private static HashMap<Integer, String> warpsSlot = new HashMap();

    private static Inventory getInventory() {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, (int)36, (String)"\u00a72\u00a7l\u00a7nD\u1ecaCH CHUY\u1ec2N");
        int count = 0;
        int i = 0;
        while (i < WarpConfig.getConfig().getStringList("List").size()) {
            String s = (String)WarpConfig.getConfig().getStringList("List").get(i);
            ItemStack item = new ItemStack(Warps.getMaterial(s), 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("\u00a7e\u00a7l" + s);
            ArrayList<String> lore = new ArrayList<String>();
            lore.add("\u00a76C\u1ea5p \u0111\u1ed9 y\u00eau c\u1ea7u: \u00a7f" + Warps.getLevelRequirement(s));
            lore.add("\u00a76Ph\u00ed d\u1ecbch chuy\u1ec3n: \u00a7f" + Warps.getMoneyRequirement(s));
            lore.add(" ");
            lore.add("\u00a7cClick \u0111\u1ec3 d\u1ecbch chuy\u1ec3n");
            meta.setLore(lore);
            item.setItemMeta(meta);
            inv.setItem(count, item);
            warpsSlot.put(count, s);
            ++count;
            ++i;
        }
        return inv;
    }

    public static void openGui(Player player) {
        player.openInventory(WarpGui.getInventory());
    }

    @EventHandler
    public static void eventHandling(InventoryClickEvent e) {
        if (!e.getInventory().getTitle().equals("\u00a72\u00a7l\u00a7nD\u1ecaCH CHUY\u1ec2N")) {
            return;
        }
        if (e.getClickedInventory() != e.getWhoClicked().getOpenInventory().getTopInventory()) {
            return;
        }
        e.setCancelled(true);
        int slot = e.getSlot();
        if (warpsSlot.keySet().contains(slot)) {
            if (!(e.getWhoClicked() instanceof Player)) {
                return;
            }
            Player player = (Player)e.getWhoClicked();
            Warps.teleport(player, warpsSlot.get(slot));
            player.closeInventory();
        }
    }
}

