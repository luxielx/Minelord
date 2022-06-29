/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.projectiles.ProjectileSource
 */
package kdvn.event;

import kdvn.minelord.player.EnumClassMineLord;
import kdvn.minelord.player.MineLordPlayer;
import kdvn.noiluc.NoiLuc;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

public class PowerEvent
implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        try {
            if (e.getDamager() instanceof Player) {
                Player player = (Player)e.getDamager();
                if (new MineLordPlayer(player).getClassMineLord().equals(EnumClassMineLord.MAGE.toString())) {
                    return;
                }
                if (NoiLuc.getNoiLuc(player) == 0) {
                    player.sendMessage((Object)ChatColor.RED + "N\u1ed8I L\u1ef0C b\u1eb1ng 0, b\u1ea1n kh\u00f4ng th\u1ec3 g\u00e2y s\u00e1t th\u01b0\u01a1ng");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
                    return;
                }
                NoiLuc.setNoiLuc(player, NoiLuc.getNoiLuc(player) - 1);
            }
            if (e.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow)e.getDamager();
                Player player = null;
                if (arrow.getShooter() instanceof Player) {
                    player = (Player)arrow.getShooter();
                }
                if (NoiLuc.getNoiLuc(player) == 0) {
                    player.sendMessage((Object)ChatColor.RED + "N\u1ed8I L\u1ef0C b\u1eb1ng 0, b\u1ea1n kh\u00f4ng th\u1ec3 g\u00e2y s\u00e1t th\u01b0\u01a1ng");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 1));
                    return;
                }
                NoiLuc.setNoiLuc(player, NoiLuc.getNoiLuc(player) - 1);
            }
        }
        catch (NullPointerException arrow) {
            // empty catch block
        }
    }
}

