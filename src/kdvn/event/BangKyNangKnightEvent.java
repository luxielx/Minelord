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

import kdvn.skill.KnightSkill;
import kdvn.skill.knight.AnhDung;
import kdvn.skill.knight.BungNo;
import kdvn.skill.knight.HoaThan;
import kdvn.skill.knight.KhacMau;
import kdvn.skill.knight.KhieuKhich;
import kdvn.skill.knight.ManhMe;
import kdvn.skill.knight.PhanDon;
import kdvn.skill.knight.TraMau;
import kdvn.skill.knight.TrungPhat;
import kdvn.skill.knight.TuChoiTuThan;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class BangKyNangKnightEvent
implements Listener {
    @EventHandler
    public void onConCac(InventoryClickEvent e) {
        Player player;
        if (!e.getInventory().getTitle().equals("\u00a72\u00a7l\u00a7nK\u1ef8 N\u0102NG KNIGHT")) {
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
        if (KnightSkill.getRemainingPoint(player = (Player)e.getWhoClicked()) <= 0) {
            player.sendMessage("\u00a7cB\u1ea1n kh\u00f4ng c\u00f3 \u0111i\u1ec3m c\u1ed9ng K\u1ef9 N\u0103ng!");
            player.closeInventory();
            return;
        }
        if (e.getSlot() == 20) {
            if (AnhDung.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            AnhDung.add1Point(player);
            player.closeInventory();
            KnightSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 6) {
            if (BungNo.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            BungNo.add1Point(player);
            player.closeInventory();
            KnightSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 4) {
            if (HoaThan.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            HoaThan.add1Point(player);
            player.closeInventory();
            KnightSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 0) {
            if (KhacMau.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            KhacMau.add1Point(player);
            player.closeInventory();
            KnightSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 22) {
            if (KhieuKhich.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            KhieuKhich.add1Point(player);
            player.closeInventory();
            KnightSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 2) {
            if (ManhMe.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                return;
            }
            ManhMe.add1Point(player);
            player.closeInventory();
            KnightSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 24) {
            if (PhanDon.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            PhanDon.add1Point(player);
            player.closeInventory();
            KnightSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 18) {
            if (TraMau.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            TraMau.add1Point(player);
            player.closeInventory();
            KnightSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 26) {
            if (TrungPhat.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            TrungPhat.add1Point(player);
            player.closeInventory();
            KnightSkill.showBangChonSkill(player);
        } else if (e.getSlot() == 8) {
            if (TuChoiTuThan.getNexLevelRequirement(player) > player.getLevel()) {
                player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a \u0111\u1ee7 c\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng K\u1ef9 N\u0103ng n\u00e0y!");
                player.closeInventory();
                return;
            }
            TuChoiTuThan.add1Point(player);
            player.closeInventory();
            KnightSkill.showBangChonSkill(player);
        }
    }
}

