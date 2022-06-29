/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.event.player.PlayerToggleSneakEvent
 *  org.bukkit.inventory.EquipmentSlot
 */
package kdvn.event;

import java.util.ArrayList;
import java.util.List;
import kdvn.safezone.SafeZone;
import kdvn.skill.XuLyCombo;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;

public class ComboEvent
implements Listener {
    public static List<Player> isCombo = new ArrayList<Player>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!ComboEvent.isPlayerComboed(e.getPlayer())) {
            return;
        }
        if (e.getHand() != EquipmentSlot.HAND) {
            return;
        }
        if (SafeZone.inSafeZone(e.getPlayer())) {
            return;
        }
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 0.5f, 0.5f);
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            XuLyCombo.addOne(e.getPlayer(), 0);
            XuLyCombo.sendTitleCombo(e.getPlayer());
        } else if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            XuLyCombo.addOne(e.getPlayer(), 1);
            XuLyCombo.sendTitleCombo(e.getPlayer());
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        if (e.isSneaking()) {
            ComboEvent.addCombo(e.getPlayer());
        } else {
            XuLyCombo.doIt(e.getPlayer());
            ComboEvent.huyCombo(e.getPlayer());
        }
    }

    public static boolean isPlayerComboed(Player player) {
        if (isCombo.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void addCombo(Player player) {
        if (!ComboEvent.isPlayerComboed(player)) {
            isCombo.add(player);
        }
    }

    public static void huyCombo(Player player) {
        if (ComboEvent.isPlayerComboed(player)) {
            isCombo.remove((Object)player);
        }
        XuLyCombo.finishCombo(player);
    }
}

