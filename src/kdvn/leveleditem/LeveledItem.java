/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.leveleditem;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class LeveledItem {
    private ItemStack item;
    private int lv;
    private final String LORE = "\u00a7f[\u00a76LV\u00a7f] \u00a7bC\u1ea5p \u0111\u1ed9 y\u00eau c\u1ea7u: \u00a7c@";
    private final String LORE_CONTAIN = "\u00a7f[\u00a76LV\u00a7f]";

    public LeveledItem(ItemStack item) {
        this.item = item;
        this.lv = this.getLevel(item);
    }

    public int getLevel() {
        return this.lv;
    }

    public ItemStack getItem() {
        return this.item;
    }

    public void setLevel(int lv) {
        this.lv = lv;
        List lore = new ArrayList();
        if (this.item.getItemMeta().hasLore()) {
            lore = this.item.getItemMeta().getLore();
        }
        String lvLore = "\u00a7f[\u00a76LV\u00a7f] \u00a7bC\u1ea5p \u0111\u1ed9 y\u00eau c\u1ea7u: \u00a7c@".replace("@", "" + lv);
        lore.add(lvLore);
        ItemMeta meta = this.item.getItemMeta();
        meta.setLore(lore);
        this.item.setItemMeta(meta);
    }

    private int getLevel(ItemStack item) {
        if (!item.getItemMeta().hasLore()) {
            return 0;
        }
        int lv = 0;
        for (String s : item.getItemMeta().getLore()) {
            if (!s.contains("\u00a7f[\u00a76LV\u00a7f]")) continue;
            String lvS = s.substring(s.lastIndexOf("c") + 1, s.length());
            lv = Integer.parseInt(lvS);
        }
        return lv;
    }

    public static void eventHandling(EntityDamageByEntityEvent e) {
        Player player;
        LeveledItem item;
        if (e.getDamager() instanceof Player && (player = (Player)e.getDamager()).getInventory().getItemInMainHand().getType() != Material.AIR && (item = new LeveledItem(player.getInventory().getItemInMainHand())).getLevel() > player.getLevel()) {
            player.sendMessage("\u00a7eB\u1ea1n ph\u1ea3i \u0111\u1ea1t c\u1ea5p \u0111\u1ed9 \u00a7f" + item.getLevel() + " \u00a7c\u0111\u1ec3 s\u1eed d\u1ee5ng v\u0169 kh\u00ed n\u00e0y");
            e.setCancelled(true);
        }
    }
}

