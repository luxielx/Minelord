/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.Inventory
 */
package kdvn.event;

import kdvn.minelord.player.EnumClassMineLord;
import kdvn.minelord.player.MineLordPlayer;
import kdvn.tiemnang.BangCongTiemNang;
import kdvn.tiemnang.NhanhNhen;
import kdvn.tiemnang.PhepThuat;
import kdvn.tiemnang.ShowThongTin;
import kdvn.tiemnang.SucManh;
import kdvn.tiemnang.TheLuc;
import kdvn.tiemnang.TongDiem;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class CongTiemNang
implements Listener {
    @EventHandler
    public void onClickInventory(InventoryClickEvent e) {
        if (!e.getInventory().getTitle().equals(BangCongTiemNang.TITLE)) {
            return;
        }
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player)e.getWhoClicked();
        if (e.getSlot() == 0) {
            if (TongDiem.getRemain(player) <= 0) {
                player.sendMessage((Object)ChatColor.RED + "B\u1ea1n kh\u00f4ng c\u00f3 \u0111i\u1ec3m TI\u1ec0M N\u0102NG \u0111\u1ec3 c\u1ed9ng!");
                e.setCancelled(true);
                player.closeInventory();
                return;
            }
            e.setCancelled(true);
            SucManh.addOnePoint(player);
        } else if (e.getSlot() == 2) {
            if (TongDiem.getRemain(player) <= 0) {
                player.sendMessage((Object)ChatColor.RED + "B\u1ea1n kh\u00f4ng c\u00f3 \u0111i\u1ec3m TI\u1ec0M N\u0102NG \u0111\u1ec3 c\u1ed9ng!");
                e.setCancelled(true);
                player.closeInventory();
                return;
            }
            e.setCancelled(true);
            TheLuc.addOnePoint(player);
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 0.4000000059604645);
            if (new MineLordPlayer(player).getClassMineLord().equals(EnumClassMineLord.KNIGHT.toString())) {
                player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() + 0.4000000059604645);
            }
        } else if (e.getSlot() == 3) {
            if (TongDiem.getRemain(player) <= 0) {
                player.sendMessage((Object)ChatColor.RED + "B\u1ea1n kh\u00f4ng c\u00f3 \u0111i\u1ec3m TI\u1ec0M N\u0102NG \u0111\u1ec3 c\u1ed9ng!");
                e.setCancelled(true);
                player.closeInventory();
                return;
            }
            e.setCancelled(true);
            PhepThuat.addOnePoint(player);
        } else if (e.getSlot() == 1) {
            if (TongDiem.getRemain(player) <= 0) {
                player.sendMessage((Object)ChatColor.RED + "B\u1ea1n kh\u00f4ng c\u00f3 \u0111i\u1ec3m TI\u1ec0M N\u0102NG \u0111\u1ec3 c\u1ed9ng!");
                e.setCancelled(true);
                player.closeInventory();
                return;
            }
            e.setCancelled(true);
            NhanhNhen.addOnePoint(player);
            player.setWalkSpeed(NhanhNhen.getTocDo(player));
        } else if (e.getSlot() == 8) {
            e.setCancelled(true);
            player.closeInventory();
            ShowThongTin.show(player);
        }
    }
}

