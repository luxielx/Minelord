/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Projectile
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package kdvn.bow;

import java.util.ArrayList;
import java.util.List;
import kdvn.main.Main;
import kdvn.safezone.SafeZone;
import kdvn.settings.SettingMethod;
import kdvn.tiemnang.NhanhNhen;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class BowArcher {
    public static final String DAMAGE_LORE = SettingMethod.colorDecomplier("&f[&aCUNG&f] &eS\u00e1t th\u01b0\u01a1ng:&c ");
    public static List<Player> delayed = new ArrayList<Player>();

    public static boolean isThatItem(ItemStack i) {
        if (i == null) {
            return false;
        }
        if (!i.getItemMeta().hasLore()) {
            return false;
        }
        for (String s : i.getItemMeta().getLore()) {
            if (!s.contains(DAMAGE_LORE)) continue;
            return true;
        }
        return false;
    }

    public static void addDamageToItem(ItemStack item, double damage) {
        ItemMeta meta = item.getItemMeta();
        if (item.getItemMeta().hasLore()) {
            List lore = item.getItemMeta().getLore();
            lore.add(String.valueOf(DAMAGE_LORE) + damage);
            meta.setLore(lore);
            item.setItemMeta(meta);
        } else {
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(String.valueOf(DAMAGE_LORE) + damage);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }

    public static double getAttackSpeed(Player player) {
        return NhanhNhen.getTocDoBan(player);
    }

    public static void delayPlayer(Player player) {
        if (!delayed.contains((Object)player)) {
            delayed.add(player);
        }
    }

    public static void unDelayPlayer(Player player) {
        if (delayed.contains((Object)player)) {
            delayed.remove((Object)player);
        }
    }

    public static boolean isDelayed(Player player) {
        return delayed.contains((Object)player);
    }

    public static double getDamageThroughItem(ItemStack item) {
        float damage = 0.0f;
        if (!item.getItemMeta().hasLore()) {
            return 0.0;
        }
        ItemMeta meta = item.getItemMeta();
        List lore = meta.getLore();
        int i = 0;
        while (i < lore.size()) {
            if (((String)lore.get(i)).contains(DAMAGE_LORE)) {
                String damageLore = (String)lore.get(i);
                String satThuongChar = damageLore.substring(damageLore.lastIndexOf(" ") + 1, damageLore.length());
                try {
                    float satThuong;
                    damage = satThuong = Float.parseFloat(satThuongChar);
                    break;
                }
                catch (Exception e) {
                    return 0.0;
                }
            }
            ++i;
        }
        return damage;
    }

    public static boolean subtractAmountOfArrowInInventory(Player player) {
        try {
            boolean check = false;
            int i = 0;
            while (i < 44) {
                if (player.getInventory().getItem(i) != null && player.getInventory().getItem(i).getType() == Material.ARROW) {
                    check = true;
                    ItemStack arrow = player.getInventory().getItem(i);
                    if (arrow.getAmount() == 1) {
                        player.getInventory().setItem(i, null);
                        break;
                    }
                    arrow.setAmount(arrow.getAmount() - 1);
                    player.getInventory().setItem(i, arrow);
                    break;
                }
                ++i;
            }
            return check;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void lauchArrow(Player player) {
        double delay = BowArcher.getAttackSpeed(player);
        Arrow arrow = (Arrow)player.launchProjectile(Arrow.class);
        new BukkitRunnable(){

            public void run() {
                Arrow.this.remove();
            }
        }.runTaskLaterAsynchronously((Plugin)Main.plugin, 20);
        BowArcher.delayPlayer(player);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                BowArcher.unDelayPlayer(Player.this);
            }
        }, (long)delay * 20);
    }

    public static void onFire(PlayerInteractEvent e) {
        if (!BowArcher.isThatItem(e.getItem())) {
            return;
        }
        SafeZone.inSafeZone(e.getPlayer());
        if (SafeZone.inSafeZone(e.getPlayer())) {
            return;
        }
        BowArcher.lauchArrow(e.getPlayer());
    }

}

