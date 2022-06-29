/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.gmail.filoghost.holographicdisplays.api.Hologram
 *  com.gmail.filoghost.holographicdisplays.api.HologramsAPI
 *  com.gmail.filoghost.holographicdisplays.api.line.TextLine
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDeathEvent
 *  org.bukkit.event.player.PlayerMoveEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.projectiles.ProjectileSource
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 *  org.bukkit.util.Vector
 */
package kdvn.event;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import java.util.ArrayList;
import java.util.List;
import kdvn.main.Main;
import kdvn.point.Money;
import kdvn.settings.SettingMethod;
import kdvn.takemoneywhenkilledmonster.ItemMoneyDrop;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class MobDeathDropMoney
implements Listener {
    private List<Item> checked = new ArrayList<Item>();
    private final int TIME_DELAY = 10;

    private String getPlayerOwnedItem(Item item) {
        String customName = item.getCustomName();
        if (!customName.contains("\u00a7c")) {
            return null;
        }
        String playerName = customName.substring(2, customName.indexOf(" "));
        return playerName;
    }

    @EventHandler
    public void onDamge(final EntityDamageByEntityEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        if (!(e.getDamager() instanceof Player) && !(e.getDamager() instanceof Arrow)) {
            return;
        }
        if (e.getDamager() instanceof Arrow && !(((Arrow)e.getDamager()).getShooter() instanceof Player)) {
            return;
        }
        final Player player = e.getDamager() instanceof Arrow ? (Player)((Arrow)e.getDamager()).getShooter() : (Player)e.getDamager();
        final LivingEntity target = (LivingEntity)e.getEntity();
        final double healthAfter = target.getHealth();
        new BukkitRunnable(){

            public void run() {
                double healthBefore = target.getHealth();
                double damage = healthAfter - healthBefore;
                Double exp = new Double(MobDeathDropMoney.this.expByDamage(player, damage, target));
                int expInt = exp.intValue();
                if (exp == 0.0) {
                    return;
                }
                if (expInt < 1) {
                    expInt = 1;
                }
                player.giveExp(expInt);
                Location location = player.getLocation().clone().add(0.0, 2.0, 0.7).add(player.getLocation().getDirection().multiply(1.2f));
                final Hologram holo = HologramsAPI.createHologram((Plugin)Main.plugin, (Location)location);
                holo.appendTextLine("\u00a7a+" + expInt + " exp");
                Location location2 = e.getEntity().getLocation().clone().add(0.0, 1.5, 0.0).add(e.getEntity().getLocation().getDirection());
                final Hologram holo2 = HologramsAPI.createHologram((Plugin)Main.plugin, (Location)location2);
                holo2.appendTextLine("\u00a7c\u00a7l" + SettingMethod.lamTron(damage) + "\u2665");
                new BukkitRunnable(){
                    int i;

                    public void run() {
                        ++this.i;
                        if (this.i >= 9) {
                            this.cancel();
                            return;
                        }
                        if (!holo.isDeleted()) {
                            holo.teleport(holo.getLocation().add(0.0, 0.05000000074505806, 0.0));
                        }
                        if (!holo2.isDeleted()) {
                            holo2.teleport(holo2.getLocation().add(0.0, 0.10000000149011612, 0.0));
                        }
                    }
                }.runTaskTimerAsynchronously((Plugin)Main.plugin, 0, 1);
                new BukkitRunnable(){

                    public void run() {
                        if (!holo.isDeleted()) {
                            holo.delete();
                        }
                        if (!holo2.isDeleted()) {
                            holo2.delete();
                        }
                    }
                }.runTaskLater((Plugin)Main.plugin, 12);
            }

        }.runTaskLater((Plugin)Main.plugin, 1);
    }

    private double expByDamage(Player player, double damage, LivingEntity entity) {
        int lv = SettingMethod.getMobLevel(entity);
        if (lv == 0) {
            return 0.0;
        }
        double expBonus = 0.0;
        double trueExp = (5.0 + (double)lv * 0.5) / entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * damage;
        if (player.hasPermission("rankVip.1")) {
            expBonus = trueExp * 0.20000000298023224;
        }
        if (player.hasPermission("rankVip.2")) {
            expBonus = trueExp * 0.4000000059604645;
        }
        if (player.hasPermission("rankVip.3")) {
            expBonus = trueExp * 0.6000000238418579;
        }
        if (player.hasPermission("rankVip.4")) {
            expBonus = trueExp * 0.800000011920929;
        }
        if (player.hasPermission("rankVip.5")) {
            expBonus = trueExp * 1.0;
        }
        if (player.hasPermission("rankVip.6")) {
            expBonus = trueExp * 1.2000000476837158;
        }
        if (player.hasPermission("rankVip.7")) {
            expBonus = trueExp * 1.399999976158142;
        }
        if (player.hasPermission("rankVip.8")) {
            expBonus = trueExp * 1.600000023841858;
        }
        if (player.hasPermission("rankVip.9")) {
            expBonus = trueExp * 1.7999999523162842;
        }
        if (player.hasPermission("rankVip.10")) {
            expBonus = trueExp * 2.0;
        }
        return trueExp + expBonus;
    }

    @EventHandler
    public void onMonsterDeath(EntityDeathEvent e) {
        try {
            if (e.getEntity() instanceof LivingEntity && e.getEntity().getKiller() instanceof Player) {
                LivingEntity entity = e.getEntity();
                Player player = entity.getKiller();
                int lv = SettingMethod.getMobLevel(entity);
                if (lv == 0) {
                    return;
                }
                ItemMoneyDrop money = new ItemMoneyDrop(lv * 10);
                final Item item = entity.getWorld().dropItem(entity.getLocation(), money.getItem());
                item.setPickupDelay(10000);
                new BukkitRunnable(){

                    public void run() {
                        item.setCustomName(item.getItemStack().getItemMeta().getDisplayName());
                    }
                }.runTaskLaterAsynchronously((Plugin)Main.plugin, 200);
                item.setCustomName("\u00a7c" + player.getName() + " " + item.getItemStack().getItemMeta().getDisplayName());
                item.setCustomNameVisible(true);
            }
        }
        catch (NullPointerException ee) {
            return;
        }
    }

    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
        List<Entity> list = SettingMethod.getEntitiesByLocation(e.getPlayer().getLocation(), 2.0f);
        for (Entity en : list) {
            if (!(en instanceof Item)) continue;
            final Item i = (Item)en;
            if (!ItemMoneyDrop.isThatItem(i.getItemStack())) {
                return;
            }
            if (this.getPlayerOwnedItem(i) != null && !this.getPlayerOwnedItem(i).equals(e.getPlayer().getName())) {
                return;
            }
            if (this.checked.contains((Object)i)) {
                return;
            }
            this.checked.add(i);
            i.setVelocity(e.getPlayer().getLocation().subtract(i.getLocation()).toVector());
            new BukkitRunnable(){

                public void run() {
                    i.remove();
                    double money = ItemMoneyDrop.getMoneyThroughItemName(i.getItemStack());
                    double bonusMoney = 0.0;
                    if (e.getPlayer().hasPermission("rankVip.1")) {
                        bonusMoney = money * 0.20000000298023224;
                    }
                    if (e.getPlayer().hasPermission("rankVip.2")) {
                        bonusMoney = money * 0.4000000059604645;
                    }
                    if (e.getPlayer().hasPermission("rankVip.3")) {
                        bonusMoney = money * 0.6000000238418579;
                    }
                    if (e.getPlayer().hasPermission("rankVip.4")) {
                        bonusMoney = money * 0.800000011920929;
                    }
                    if (e.getPlayer().hasPermission("rankVip.5")) {
                        bonusMoney = money * 1.0;
                    }
                    if (e.getPlayer().hasPermission("rankVip.6")) {
                        bonusMoney = money * 1.2000000476837158;
                    }
                    if (e.getPlayer().hasPermission("rankVip.7")) {
                        bonusMoney = money * 1.399999976158142;
                    }
                    if (e.getPlayer().hasPermission("rankVip.8")) {
                        bonusMoney = money * 1.600000023841858;
                    }
                    if (e.getPlayer().hasPermission("rankVip.9")) {
                        bonusMoney = money * 1.7999999523162842;
                    }
                    if (e.getPlayer().hasPermission("rankVip.10")) {
                        bonusMoney = money * 2.0;
                    }
                    Money.giveMoney(e.getPlayer(), money);
                    Money.giveMoney(e.getPlayer(), bonusMoney);
                    e.getPlayer().sendMessage("\u00a7eB\u1ea1n nh\u1eadn \u0111\u01b0\u1ee3c " + money + " b\u1ea1c");
                    e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                    if (bonusMoney != 0.0) {
                        e.getPlayer().sendMessage("\u00a76[VIP] \u00a7aB\u1ea1n nh\u1eadn \u0111\u01b0\u1ee3c th\u00eam " + SettingMethod.lamTron(bonusMoney) + " b\u1ea1c");
                    }
                    if (MobDeathDropMoney.this.checked.contains((Object)i)) {
                        MobDeathDropMoney.this.checked.remove((Object)i);
                    }
                }
            }.runTaskLater((Plugin)Main.plugin, 1);
        }
    }

}

