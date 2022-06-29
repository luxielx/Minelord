/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDeathEvent
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.player.PlayerLoginEvent
 *  org.bukkit.event.player.PlayerQuitEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.projectiles.ProjectileSource
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package kdvn.minelord.dosat;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import kdvn.main.Main;
import kdvn.minelord.dosat.DoSat;
import kdvn.point.Point;
import kdvn.tele.Warps;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class DoSatEvent
implements Listener {
    private Set<UUID> outPlayer = new HashSet<UUID>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        try {
            Arrow a;
            if (e.getDamager() instanceof Player) {
                if (e.getEntity() instanceof Player) {
                    Player target;
                    Player damager = (Player)e.getDamager();
                    if (!DoSat.canAttack(damager, target = (Player)e.getEntity())) {
                        damager.sendMessage("\u00a7aCh\u1ec9 khi b\u1eadt \u0110\u1ed3 S\u00e1t m\u1edbi c\u00f3 th\u1ec3 PvP");
                        e.setCancelled(true);
                        return;
                    }
                    double damage = e.getDamage();
                    if (DoSat.isTurnedOn(damager)) {
                        damage = e.getDamage() / 2.0;
                    }
                    e.setDamage(damage);
                }
            } else if (e.getDamager() instanceof Arrow && (a = (Arrow)e.getDamager()).getShooter() instanceof Player) {
                Player target;
                Player damager = (Player)a.getShooter();
                if (!DoSat.canAttack(damager, target = (Player)e.getEntity())) {
                    damager.sendMessage("\u00a7aCh\u1ec9 khi b\u1eadt \u0110\u1ed3 S\u00e1t m\u1edbi c\u00f3 th\u1ec3 PvP");
                    e.setCancelled(true);
                    return;
                }
                double damage = e.getDamage();
                if (DoSat.isTurnedOn(damager)) {
                    damage = e.getDamage() / 2.0;
                }
                e.setDamage(damage);
            }
        }
        catch (ClassCastException a) {
            // empty catch block
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player && e.getEntity().getKiller() instanceof Player) {
            Player killer = e.getEntity().getKiller();
            if (Warps.getWarps().containsKey("PvP") && killer.getLocation().distance(Warps.getLocation("PvP")) < 100.0) {
                return;
            }
            if (DoSat.isTurnedOn(killer)) {
                DoSat.addTime(killer, DoSat.TIME_PER_LIFE);
                killer.sendMessage("\u00a7cB\u1ea1n v\u1eeba gi\u1ebft m\u1ed9t m\u1ea1ng ng\u01b0\u1eddi, t\u0103ng th\u1eddi gian b\u1ecb t\u1ea5n c\u00f4ng th\u00eam \u00a7e" + DoSat.TIME_PER_LIFE);
            }
        }
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent e) {
        if (!e.getInventory().getTitle().equals(DoSat.TITLE)) {
            return;
        }
        if (e.getClickedInventory() != e.getWhoClicked().getOpenInventory().getTopInventory()) {
            return;
        }
        e.setCancelled(true);
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player)e.getWhoClicked();
        int slot = e.getSlot();
        if (slot == DoSat.CHANGE_SLOT) {
            DoSat.turnOnOrOff(player);
            player.closeInventory();
        }
        if (slot == DoSat.XOA_10_SLOT) {
            if (!Point.pointCost(player, DoSat.POINT_PER_SECOND * 10)) {
                player.closeInventory();
                player.sendMessage("\u00a7cB\u1ea1n ph\u1ea3i c\u00f3 " + DoSat.POINT_PER_SECOND * 10 + " v\u00e0ng");
                return;
            }
            DoSat.addTime(player, -10);
            player.sendMessage("\u00a7aX\u00f3a th\u00e0nh c\u00f4ng 10 gi\u00e2y");
        } else if (slot == DoSat.XOA_30_SLOT) {
            if (!Point.pointCost(player, DoSat.POINT_PER_SECOND * 30)) {
                player.closeInventory();
                player.sendMessage("\u00a7cB\u1ea1n ph\u1ea3i c\u00f3 " + DoSat.POINT_PER_SECOND * 30 + " v\u00e0ng");
                return;
            }
            DoSat.addTime(player, -30);
            player.sendMessage("\u00a7aX\u00f3a th\u00e0nh c\u00f4ng 30 gi\u00e2y");
        }
        if (slot == DoSat.XOA_50_SLOT) {
            if (!Point.pointCost(player, DoSat.POINT_PER_SECOND * 50)) {
                player.closeInventory();
                player.sendMessage("\u00a7cB\u1ea1n ph\u1ea3i c\u00f3 " + DoSat.POINT_PER_SECOND * 50 + " v\u00e0ng");
                return;
            }
            DoSat.addTime(player, -50);
            player.sendMessage("\u00a7aX\u00f3a th\u00e0nh c\u00f4ng 50 gi\u00e2y");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (DoSat.isWanted(e.getPlayer())) {
            this.outPlayer.add(e.getPlayer().getUniqueId());
            if (e.getPlayer().getLevel() < 20) {
                e.getPlayer().setLevel(0);
            } else {
                e.getPlayer().setLevel(e.getPlayer().getLevel() - 20);
            }
            DoSat.removeWanted(e.getPlayer());
            DoSat.turnOff(e.getPlayer());
        }
    }

    @EventHandler
    public void onJoin(final PlayerLoginEvent e) {
        if (this.outPlayer.contains(e.getPlayer().getUniqueId())) {
            new BukkitRunnable(){

                public void run() {
                    e.getPlayer().sendMessage("\u00a7cB\u1ea1n \u0111\u00e3 tho\u00e1t ra khi b\u1ecb TRUY N\u00c3 v\u00e0 b\u1ea1n b\u1ecb tr\u1eeb 20 level");
                }
            }.runTask((Plugin)Main.plugin);
            this.outPlayer.remove((Object)e.getPlayer());
        }
    }

}

