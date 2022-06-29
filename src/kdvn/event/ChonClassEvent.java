/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.inventory.InventoryCloseEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package kdvn.event;

import kdvn.classes.Archer;
import kdvn.classes.BangChonClass;
import kdvn.classes.ClassSetting;
import kdvn.classes.Knight;
import kdvn.classes.Mage;
import kdvn.main.Main;
import kdvn.minelord.player.EnumClassMineLord;
import kdvn.minelord.player.MineLordPlayer;
import kdvn.skill.SkillSetting;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class ChonClassEvent
implements Listener {
    @EventHandler
    public void onInventory(InventoryClickEvent e) {
        if (!e.getInventory().getTitle().equalsIgnoreCase(BangChonClass.TITLE)) {
            return;
        }
        Player player = (Player)e.getWhoClicked();
        if (e.getSlot() != 0 && e.getSlot() != 1 && e.getSlot() != 2) {
            e.getSlot();
        }
        if (e.getSlot() == 0) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 2.0f, 1.0f);
            ClassSetting.giveFirstWeapon(player, "archer");
            Archer.chonClass(player);
            e.setCancelled(true);
            player.closeInventory();
            ClassSetting.setPlayerNameTag(player);
            SkillSetting.removeAllSkill(player);
            SkillSetting.addDefaultSkillARCHER(player, 0);
        } else if (e.getSlot() == 1) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 2.0f, 1.0f);
            ClassSetting.giveFirstWeapon(player, "knight");
            Knight.chonClass(player);
            e.setCancelled(true);
            player.closeInventory();
            ClassSetting.setPlayerNameTag(player);
            SkillSetting.removeAllSkill(player);
            SkillSetting.addDefaultSkillKNIGHT(player, 0);
        } else if (e.getSlot() == 2) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 2.0f, 1.0f);
            ClassSetting.giveFirstWeapon(player, "mage");
            Mage.chonClass(player);
            e.setCancelled(true);
            player.closeInventory();
            ClassSetting.setPlayerNameTag(player);
            SkillSetting.removeAllSkill(player);
            SkillSetting.addDefaultSkillMAGE(player, 0);
        } else if (e.getSlot() == 8) {
            if (!player.hasPermission("ml.*")) {
                e.setCancelled(true);
                return;
            }
            ClassSetting.removeClass(player);
            e.setCancelled(true);
            player.closeInventory();
            ClassSetting.setDefault(player);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player;
        if (!e.getInventory().getTitle().equalsIgnoreCase(BangChonClass.TITLE)) {
            return;
        }
        if (e.getPlayer() instanceof Player && new MineLordPlayer(player = (Player)e.getPlayer()).getClassMineLord().equals(EnumClassMineLord.NULL.toString())) {
            new BukkitRunnable(){

                public void run() {
                    BangChonClass.show(player);
                }
            }.runTaskLater((Plugin)Main.plugin, 10);
        }
    }

}

