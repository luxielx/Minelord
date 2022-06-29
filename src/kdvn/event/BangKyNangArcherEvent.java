/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 */
package kdvn.event;

import kdvn.skill.ArcherSkill;
import kdvn.skill.archer.BangKich;
import kdvn.skill.archer.BangTien;
import kdvn.skill.archer.CungCap;
import kdvn.skill.archer.CuongSat;
import kdvn.skill.archer.DocKich;
import kdvn.skill.archer.HoaKich;
import kdvn.skill.archer.HoiMau;
import kdvn.skill.archer.LienHoanTen;
import kdvn.skill.archer.MuaTen;
import kdvn.skill.archer.NguoiBaoVe;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class BangKyNangArcherEvent
implements Listener {
    @EventHandler
    public void onConCac(InventoryClickEvent e) {
        Player player;
        if (!e.getInventory().getTitle().equals("\u00a72\u00a7l\u00a7nK\u1ef8 N\u0102NG ARCHER")) {
            return;
        }
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        if (e.getClickedInventory() != e.getWhoClicked().getOpenInventory().getTopInventory()) {
            return;
        }
        e.setCancelled(true);
        int slot = e.getSlot();
        if (slot > 18) {
            if (slot % 2 == 0 && e.getInventory().getItem(slot - 2).getAmount() == 1) {
                e.getWhoClicked().sendMessage("\u00a7aB\u1ea1n ph\u1ea3i c\u1ed9ng \u00a7cL\u1ea6N L\u01af\u1ee2T \u00a7at\u1eebng k\u1ef9 n\u0103ng!");
                e.getWhoClicked().closeInventory();
                return;
            }
        } else if (slot <= 8 && slot % 2 == 0 && slot != 0 && e.getInventory().getItem(slot - 2).getAmount() == 1) {
            e.getWhoClicked().sendMessage("\u00a7aB\u1ea1n ph\u1ea3i c\u1ed9ng \u00a7cL\u1ea6N L\u01af\u1ee2T \u00a7at\u1eebng k\u1ef9 n\u0103ng!");
            e.getWhoClicked().closeInventory();
            return;
        }
        if (ArcherSkill.getRemainingPoint(player = (Player)e.getWhoClicked()) <= 0) {
            player.sendMessage("\u00a7cB\u1ea1n kh\u00f4ng c\u00f3 \u0111i\u1ec3m c\u1ed9ng K\u1ef9 N\u0103ng!");
            player.closeInventory();
            return;
        }
        if (e.getSlot() == 20) {
            if (BangKich.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            BangKich.add1Point(player);
            player.closeInventory();
            ArcherSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 0) {
            if (BangTien.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            BangTien.add1Point(player);
            player.closeInventory();
            ArcherSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 24) {
            if (CungCap.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            CungCap.add1Point(player);
            player.closeInventory();
            ArcherSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 26) {
            if (CuongSat.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            CuongSat.add1Point(player);
            player.closeInventory();
            ArcherSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 18) {
            if (DocKich.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            DocKich.add1Point(player);
            player.closeInventory();
            ArcherSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 2) {
            if (HoaKich.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                return;
            }
            HoaKich.add1Point(player);
            player.closeInventory();
            ArcherSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 22) {
            if (HoiMau.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            HoiMau.add1Point(player);
            player.closeInventory();
            ArcherSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 4) {
            if (LienHoanTen.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            LienHoanTen.add1Point(player);
            player.closeInventory();
            ArcherSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 8) {
            if (MuaTen.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            MuaTen.add1Point(player);
            player.closeInventory();
            ArcherSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 6) {
            if (NguoiBaoVe.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            NguoiBaoVe.add1Point(player);
            player.closeInventory();
            ArcherSkill.showBangChonSkill(player);
        }
    }
}

