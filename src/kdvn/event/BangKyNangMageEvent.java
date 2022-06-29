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

import kdvn.skill.MageSkill;
import kdvn.skill.mage.BaoTuyet;
import kdvn.skill.mage.BocPhaThien;
import kdvn.skill.mage.DanhCapNangLuong;
import kdvn.skill.mage.HoiSuc;
import kdvn.skill.mage.KhienNangLuong;
import kdvn.skill.mage.LoDien;
import kdvn.skill.mage.MuaSaoBang;
import kdvn.skill.mage.NoLe;
import kdvn.skill.mage.QuanDoanDiaNguc;
import kdvn.skill.mage.VongTronNangLuong;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class BangKyNangMageEvent
implements Listener {
    @EventHandler
    public void onConCac(InventoryClickEvent e) {
        Player player;
        if (!e.getInventory().getTitle().equals("\u00a72\u00a7l\u00a7nK\u1ef8 N\u0102NG MAGE")) {
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
            if (slot % 2 == 0 && slot != 0 && e.getInventory().getItem(slot - 2).getAmount() == 1) {
                e.getWhoClicked().sendMessage("\u00a7aB\u1ea1n ph\u1ea3i c\u1ed9ng \u00a7cL\u1ea6N L\u01af\u1ee2T \u00a7at\u1eebng k\u1ef9 n\u0103ng!");
                e.getWhoClicked().closeInventory();
                return;
            }
        } else if (slot <= 8 && slot % 2 == 0 && slot != 0 && e.getInventory().getItem(slot - 2).getAmount() == 1) {
            e.getWhoClicked().sendMessage("\u00a7aB\u1ea1n ph\u1ea3i c\u1ed9ng \u00a7cL\u1ea6N L\u01af\u1ee2T \u00a7at\u1eebng k\u1ef9 n\u0103ng!");
            e.getWhoClicked().closeInventory();
            return;
        }
        if (MageSkill.getRemainingPoint(player = (Player)e.getWhoClicked()) <= 0) {
            player.sendMessage("\u00a7cB\u1ea1n kh\u00f4ng c\u00f3 \u0111i\u1ec3m c\u1ed9ng K\u1ef9 N\u0103ng!");
            player.closeInventory();
            return;
        }
        if (e.getSlot() == 6) {
            if (BaoTuyet.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            BaoTuyet.add1Point(player);
            player.closeInventory();
            MageSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 8) {
            if (BocPhaThien.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            BocPhaThien.add1Point(player);
            player.closeInventory();
            MageSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 20) {
            if (DanhCapNangLuong.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            DanhCapNangLuong.add1Point(player);
            player.closeInventory();
            MageSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 18) {
            if (HoiSuc.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            HoiSuc.add1Point(player);
            player.closeInventory();
            MageSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 4) {
            if (KhienNangLuong.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            KhienNangLuong.add1Point(player);
            player.closeInventory();
            MageSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 0) {
            if (LoDien.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                return;
            }
            LoDien.add1Point(player);
            player.closeInventory();
            MageSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 26) {
            if (MuaSaoBang.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            MuaSaoBang.add1Point(player);
            player.closeInventory();
            MageSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 2) {
            if (NoLe.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            NoLe.add1Point(player);
            player.closeInventory();
            MageSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 24) {
            if (QuanDoanDiaNguc.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            QuanDoanDiaNguc.add1Point(player);
            player.closeInventory();
            MageSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 22) {
            if (VongTronNangLuong.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            VongTronNangLuong.add1Point(player);
            player.closeInventory();
            MageSkill.showBangChonSkill(player);
        }
    }
}

