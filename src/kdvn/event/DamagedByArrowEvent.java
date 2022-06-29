/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.projectiles.ProjectileSource
 */
package kdvn.event;

import kdvn.bow.BowArcher;
import kdvn.classes.ClassSetting;
import kdvn.minelord.player.MineLordPlayer;
import kdvn.noiluc.NoiLuc;
import kdvn.settings.SettingMethod;
import kdvn.skill.archer.BangTien;
import kdvn.skill.archer.CungCap;
import kdvn.skill.archer.CuongSat;
import kdvn.skill.archer.DocKich;
import kdvn.skill.archer.NguoiBaoVe;
import kdvn.skill.mage.VongTronNangLuong;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.projectiles.ProjectileSource;

public class DamagedByArrowEvent
implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Arrow arrow;
        if (e.getDamager() instanceof Arrow && (arrow = (Arrow)e.getDamager()).getShooter() instanceof Player) {
            Player player = (Player)arrow.getShooter();
            if (NoiLuc.getNoiLuc(player) == 0) {
                e.setCancelled(true);
                return;
            }
            if (!ClassSetting.getClass(player).equals("archer")) {
                player.sendMessage(SettingMethod.colorDecomplier("&cCUNG ch\u1ec9 d\u00f9ng cho ARCHER"));
                e.setDamage(0.0);
                e.setCancelled(true);
                return;
            }
            double damage = new MineLordPlayer(player).getAddedDamage();
            damage += DamagedByArrowEvent.getDamage(player);
            DocKich.doIt(e);
            BangTien.doIt(e);
            if (CuongSat.duocTangSatThuong(player)) {
                damage *= 1.5;
            }
            if (e.getEntity() instanceof Player) {
                if (CungCap.duocGiamSatThuong((Player)e.getEntity())) {
                    damage *= 0.5;
                }
                if (VongTronNangLuong.isTriggered((Player)e.getEntity())) {
                    damage *= 0.800000011920929;
                }
            }
            e.setDamage(damage);
            NguoiBaoVe.onDamage(e);
        }
    }

    public static double getDamage(Player player) {
        double damage = 0.0;
        if (player.getInventory().getItemInMainHand().getType() != Material.AIR && BowArcher.isThatItem(player.getInventory().getItemInMainHand())) {
            return BowArcher.getDamageThroughItem(player.getInventory().getItemInMainHand());
        }
        return damage;
    }
}

