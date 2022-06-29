/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.AsyncPlayerChatEvent
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.event.player.PlayerLevelChangeEvent
 */
package kdvn.event;

import kdvn.classes.Archer;
import kdvn.classes.ClassSetting;
import kdvn.classes.Knight;
import kdvn.classes.Mage;
import kdvn.minelord.player.MineLordPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class NameTagEvent
implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ClassSetting.setPlayerNameTag(e.getPlayer());
    }

    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent e) {
        ClassSetting.setPlayerNameTag(e.getPlayer());
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        MineLordPlayer player = new MineLordPlayer(e.getPlayer());
        String className = player.getClassMineLord();
        if (className.equals("archer")) {
            e.setFormat("\u00a76[" + Archer.PREFIX + " \u00a7c" + e.getPlayer().getLevel() + "\u00a76]\u00a7f>" + e.getFormat());
        } else if (className.equals("knight")) {
            e.setFormat("\u00a76[" + Knight.PREFIX + " \u00a7c" + e.getPlayer().getLevel() + "\u00a76]\u00a7f>" + e.getFormat());
        } else if (className.equals("mage")) {
            e.setFormat("\u00a76[" + Mage.PREFIX + " \u00a7c" + e.getPlayer().getLevel() + "\u00a76]\u00a7f>" + e.getFormat());
        }
    }
}

