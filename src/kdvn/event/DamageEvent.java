/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Location
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDeathEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package kdvn.event;

import kdvn.classes.ClassSetting;
import kdvn.leveleditem.LeveledItem;
import kdvn.minelord.player.EnumClassMineLord;
import kdvn.minelord.player.MineLordPlayer;
import kdvn.noiluc.NoiLuc;
import kdvn.safezone.SafeZone;
import kdvn.settings.Particle;
import kdvn.settings.SettingMethod;
import kdvn.settings.XuLySatThuong;
import kdvn.skill.archer.CungCap;
import kdvn.skill.archer.CuongSat;
import kdvn.skill.knight.AnhDung;
import kdvn.skill.knight.BungNo;
import kdvn.skill.knight.KhacMau;
import kdvn.skill.knight.ManhMe;
import kdvn.skill.knight.PhanDon;
import kdvn.skill.knight.TraMau;
import kdvn.skill.knight.TuChoiTuThan;
import kdvn.skill.mage.DanhCapNangLuong;
import kdvn.skill.mage.HoiSuc;
import kdvn.skill.mage.KhienNangLuong;
import kdvn.skill.mage.LoDien;
import kdvn.skill.mage.MuaSaoBang;
import kdvn.skill.mage.VongTronNangLuong;
import kdvn.tiemnang.DamageAll;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DamageEvent
implements Listener {
    @EventHandler
    public void xuLySatThuong(EntityDamageByEntityEvent e) {
        Player player;
        double damage;
        LeveledItem.eventHandling(e);
        BungNo.doIt(e);
        KhacMau.doIt(e);
        TraMau.doIt(e);
        MuaSaoBang.xuLyEvent(e);
        HoiSuc.doEvent(e);
        if (e.getDamager() instanceof Arrow) {
            return;
        }
        if (e.getDamager() instanceof Player) {
            player = (Player)e.getDamager();
            if (!ClassSetting.getItemClass(player.getInventory().getItemInMainHand()).equals("null") && !ClassSetting.checkItem(player.getInventory().getItemInMainHand(), player)) {
                player.sendMessage(SettingMethod.colorDecomplier("&cB\u1ea1n d\u00f9ng \u0111\u1ed3 kh\u00f4ng \u0111\u00fang Class"));
                e.setCancelled(true);
                return;
            }
            if (NoiLuc.getNoiLuc(player) == 0) {
                e.setCancelled(true);
                return;
            }
            damage = e.getDamage();
            if (!new MineLordPlayer(player).getClassMineLord().equals(EnumClassMineLord.MAGE.toString())) {
                damage += DamageAll.getDamage(player);
            }
            if (CuongSat.duocTangSatThuong(player)) {
                damage += new MineLordPlayer(player).getAddedDamage();
            }
            if (AnhDung.isChemed(player)) {
                Player playerr;
                damage = AnhDung.xuLySatThuong(damage, player);
                AnhDung.removeChem(player);
                if (e.getEntity() instanceof LivingEntity && e.getEntity() instanceof Player && SafeZone.isAllowedtoPVP(playerr = (Player)e.getEntity())) {
                    ((Player)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 1));
                    Particle.sendParticle(EnumParticle.REDSTONE, playerr.getLocation(), 0.01f, 0.01f, 0.01f, 1.0f, 10);
                }
            }
            e.setDamage(damage);
        }
        if (e.getEntity() instanceof Player) {
            player = (Player)e.getEntity();
            if (e.getDamager() instanceof LivingEntity) {
                damage = e.getDamage();
                if (XuLySatThuong.neDon(player, (LivingEntity)e.getDamager())) {
                    e.setCancelled(true);
                    return;
                }
                if (CungCap.duocGiamSatThuong(player)) {
                    damage *= 0.5;
                }
                if (VongTronNangLuong.isTriggered(player)) {
                    damage *= 0.800000011920929;
                }
                e.setDamage(damage);
            }
        }
        TuChoiTuThan.doIt(e);
        PhanDon.doIt(e);
        ManhMe.doIt(e);
        LoDien.doIt(e);
        KhienNangLuong.doIt(e);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        Player player;
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        if (e.getEntity().getKiller() instanceof Player && ClassSetting.getClass(player = e.getEntity().getKiller()).equals("mage")) {
            DanhCapNangLuong.doIt(e);
        }
    }
}

