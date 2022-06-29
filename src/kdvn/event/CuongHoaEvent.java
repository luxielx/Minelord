/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.inventory.InventoryCloseEvent
 *  org.bukkit.event.player.PlayerInteractEntityEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 */
package kdvn.event;

import kdvn.cuonghoa.BuaCuongHoa;
import kdvn.cuonghoa.ChuyenHoaItem;
import kdvn.cuonghoa.DaCuongHoa;
import kdvn.cuonghoa.InventoryCuongHoa;
import kdvn.cuonghoa.ItemCuongHoa;
import kdvn.main.Main;
import kdvn.settings.SettingMethod;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class CuongHoaEvent
implements Listener {
    public static final String NPC_NAME = "\u00a76C\u01b0\u1eddng H\u00f3a";

    private double getTile(ItemStack weapon, int daLevel) {
        if (!weapon.getItemMeta().hasLore()) {
            return 0.0;
        }
        double tiLe = 0.0;
        int weaponLevel = ItemCuongHoa.getCapDo(weapon);
        switch (weaponLevel) {
            case 0: {
                tiLe = 100.0;
                break;
            }
            case 1: {
                tiLe = 50.0;
                break;
            }
            case 2: {
                tiLe = 30.0;
                break;
            }
            case 3: {
                tiLe = 25.0;
                break;
            }
            case 4: {
                tiLe = 20.0;
                break;
            }
            case 5: {
                tiLe = 15.0;
                break;
            }
            case 6: {
                tiLe = 10.0;
                break;
            }
            case 7: {
                tiLe = 5.0;
                break;
            }
            case 8: {
                tiLe = 2.0;
                break;
            }
            case 9: {
                tiLe = 1.0;
                break;
            }
            case 10: {
                tiLe = 0.8;
                break;
            }
            case 11: {
                tiLe = 0.6;
                break;
            }
            case 12: {
                tiLe = 0.4;
                break;
            }
            case 13: {
                tiLe = 0.2;
                break;
            }
            case 14: {
                tiLe = 0.1;
            }
        }
        if (weaponLevel >= 15) {
            return 0.0;
        }
        if ((tiLe *= (double)daLevel) > 100.0) {
            tiLe = 100.0;
        }
        return tiLe;
    }

    private ItemStack cuongHoa(final Player player, final Inventory inv, ItemStack weapon, int daLevel, boolean bua) {
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, 1.0f);
        double tiLe = this.getTile(weapon, daLevel);
        int weaponLv = ItemCuongHoa.getCapDo(weapon);
        ItemStack item = weapon;
        if (weaponLv < 5) {
            if (SettingMethod.tiLe(tiLe)) {
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

                    @Override
                    public void run() {
                        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 1.0f, 1.0f);
                        CuongHoaEvent.this.thanhCong(inv, true);
                    }
                }, 10);
                item = ItemCuongHoa.cuongHoa1Cap(weapon);
            } else {
                this.thanhCong(inv, false);
            }
        } else if (SettingMethod.tiLe(tiLe)) {
            item = ItemCuongHoa.cuongHoa1Cap(weapon);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

                @Override
                public void run() {
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 1.0f, 1.0f);
                    CuongHoaEvent.this.thanhCong(inv, true);
                }
            }, 10);
        } else {
            this.thanhCong(inv, false);
            if (!bua) {
                item = ItemCuongHoa.giam1Cap(weapon);
            }
        }
        return item;
    }

    public void thanhCong(Inventory inv, boolean thanhCong) {
        if (thanhCong) {
            ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1);
            black.setDurability(5);
            ItemMeta meta = black.getItemMeta();
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
            meta.setDisplayName("\u00a7aTh\u00e0nh c\u00f4ng");
            black.setItemMeta(meta);
            int i = 38;
            while (i < 43) {
                inv.setItem(i, black);
                ++i;
            }
        } else {
            ItemStack black = new ItemStack(Material.STAINED_GLASS_PANE, 1);
            black.setDurability(15);
            ItemMeta meta = black.getItemMeta();
            meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
            meta.setDisplayName("\u00a78Th\u1ea5t b\u1ea1i");
            black.setItemMeta(meta);
            int i = 38;
            while (i < 43) {
                inv.setItem(i, black);
                ++i;
            }
        }
    }

    @EventHandler
    public void onCuongHoa(final InventoryClickEvent e) {
        if (e.getClickedInventory() != e.getWhoClicked().getOpenInventory().getTopInventory()) {
            return;
        }
        if (!e.getInventory().getTitle().equals("               \u00a72\u00a7l\u00a7nC\u01af\u1edcNG H\u00d3A")) {
            return;
        }
        if (e.getSlot() != 13 && e.getSlot() != 11 && e.getSlot() != 15 && e.getSlot() != 31) {
            e.setCancelled(true);
        }
        if (e.getSlot() == 44) {
            e.setCancelled(true);
        }
        if (e.getSlot() == 15) {
            if (e.getCursor().getType() == Material.AIR) {
                return;
            }
            if (!BuaCuongHoa.isThatItem(e.getCursor())) {
                e.setCancelled(true);
            }
        }
        if (e.getSlot() == 13) {
            if (e.getCursor().getType() == Material.AIR) {
                return;
            }
            if (!DaCuongHoa.isDaCuongHoa(e.getCursor())) {
                e.setCancelled(true);
            }
        }
        if (e.getSlot() == 31 && e.getCursor().getType() != Material.AIR) {
            e.setCancelled(true);
            return;
        }
        if (e.getSlot() == 44) {
            ItemStack newGems;
            if (e.getInventory().getItem(11) == null || e.getInventory().getItem(13) == null) {
                e.setCancelled(true);
                return;
            }
            boolean bua = true;
            if (e.getInventory().getItem(15) == null) {
                bua = false;
            }
            ItemStack daCuongHoa = e.getInventory().getItem(13);
            if (!ItemCuongHoa.hasMyItem(e.getInventory().getItem(11))) {
                return;
            }
            final ItemStack result = this.cuongHoa((Player)e.getWhoClicked(), e.getInventory(), e.getInventory().getItem(11), DaCuongHoa.getCapDoDaCuongHoa(daCuongHoa), bua);
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

                @Override
                public void run() {
                    e.getInventory().setItem(31, result);
                }
            }, 10);
            if (e.getInventory().getItem(13).getAmount() == 1) {
                e.getInventory().setItem(13, new ItemStack(Material.AIR));
            } else {
                newGems = e.getInventory().getItem(13);
                newGems.setAmount(newGems.getAmount() - 1);
                e.getInventory().setItem(13, newGems);
            }
            if (e.getInventory().getItem(15) != null) {
                if (e.getInventory().getItem(15).getAmount() == 1) {
                    e.getInventory().setItem(15, new ItemStack(Material.AIR));
                } else {
                    newGems = e.getInventory().getItem(15);
                    newGems.setAmount(newGems.getAmount() - 1);
                    e.getInventory().setItem(15, newGems);
                }
            }
            e.getInventory().setItem(11, new ItemStack(Material.AIR));
        }
    }

    @EventHandler
    public void onChuyenHoa(InventoryClickEvent e) {
        ChuyenHoaItem.eventHandlingClickInventory(e);
    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e) {
        Player player = (Player)e.getPlayer();
        if (e.getInventory().getTitle().equals("               \u00a72\u00a7l\u00a7nC\u01af\u1edcNG H\u00d3A")) {
            Inventory inv = e.getInventory();
            if (inv.getItem(13) != null) {
                player.getWorld().dropItem(player.getLocation(), inv.getItem(13));
            }
            if (inv.getItem(11) != null) {
                player.getWorld().dropItem(player.getLocation(), inv.getItem(11));
            }
            if (inv.getItem(15) != null) {
                player.getWorld().dropItem(player.getLocation(), inv.getItem(15));
            }
            if (inv.getItem(31) != null) {
                player.getWorld().dropItem(player.getLocation(), inv.getItem(31));
            }
        }
        ChuyenHoaItem.eventHandlingCloseInventory(e);
    }

    @EventHandler
    public void onNPC(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getName().equals("\u00a76C\u01b0\u1eddng H\u00f3a")) {
            e.getPlayer().openInventory(InventoryCuongHoa.getInventory());
        }
        if (e.getRightClicked().getName().equals("\u00a76Chuy\u1ec3n H\u00f3a")) {
            e.getPlayer().openInventory(ChuyenHoaItem.getInventory());
        }
    }

}

