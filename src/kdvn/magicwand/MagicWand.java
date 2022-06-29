/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.EquipmentSlot
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 *  org.bukkit.util.Vector
 */
package kdvn.magicwand;

import java.util.ArrayList;
import java.util.List;
import kdvn.classes.ClassSetting;
import kdvn.main.Main;
import kdvn.settings.ActionBarAPI;
import kdvn.settings.Particle;
import kdvn.settings.SettingMethod;
import kdvn.tiemnang.PhepThuat;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class MagicWand
implements Listener {
    public static final String DAMAGE_LORE = ChatColor.translateAlternateColorCodes((char)'&', (String)"\u00a7f[\u00a75G\u1eacY PH\u00c9P\u00a7f] &3S\u00e1t th\u01b0\u01a1ng:&c ");
    private static final String PARTICLE_LORE = ChatColor.translateAlternateColorCodes((char)'&', (String)"\u00a7f[\u00a75G\u1eacY PH\u00c9P\u00a7f] &3Ph\u00e9p thu\u1eadt:&c ");
    public static List<Player> delayed = new ArrayList<Player>();

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

    public static void addDamageLore(ItemStack item, double damage) {
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

    public static void addParticleLore(ItemStack item, String type) {
        ItemMeta meta = item.getItemMeta();
        if (item.getItemMeta().hasLore()) {
            List lore = item.getItemMeta().getLore();
            lore.add(String.valueOf(PARTICLE_LORE) + type.toUpperCase());
            meta.setLore(lore);
            item.setItemMeta(meta);
        } else {
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(String.valueOf(PARTICLE_LORE) + type.toUpperCase());
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
    }

    private static EnumParticle getEnumParticle(String type) {
        EnumParticle enumParticle = EnumParticle.FLAME;
        if (type.equalsIgnoreCase("flame")) {
            enumParticle = EnumParticle.FLAME;
        }
        if (type.equalsIgnoreCase("heart")) {
            enumParticle = EnumParticle.HEART;
        }
        if (type.equalsIgnoreCase("crit")) {
            enumParticle = EnumParticle.CRIT;
        }
        if (type.equalsIgnoreCase("dragonbreath")) {
            enumParticle = EnumParticle.DRAGON_BREATH;
        }
        if (type.equalsIgnoreCase("lava")) {
            enumParticle = EnumParticle.DRIP_LAVA;
        }
        if (type.equalsIgnoreCase("water")) {
            enumParticle = EnumParticle.DRIP_WATER;
        }
        if (type.equalsIgnoreCase("enchant")) {
            enumParticle = EnumParticle.ENCHANTMENT_TABLE;
        }
        if (type.equalsIgnoreCase("endrod")) {
            enumParticle = EnumParticle.END_ROD;
        }
        if (type.equalsIgnoreCase("mobdeath")) {
            enumParticle = EnumParticle.EXPLOSION_HUGE;
        }
        if (type.equalsIgnoreCase("firework")) {
            enumParticle = EnumParticle.FIREWORKS_SPARK;
        }
        if (type.equalsIgnoreCase("happyvillage")) {
            enumParticle = EnumParticle.VILLAGER_HAPPY;
        }
        if (type.equalsIgnoreCase("explosion")) {
            enumParticle = EnumParticle.EXPLOSION_NORMAL;
        }
        if (type.equalsIgnoreCase("smoke")) {
            enumParticle = EnumParticle.SMOKE_NORMAL;
        }
        if (type.equalsIgnoreCase("ender")) {
            enumParticle = EnumParticle.PORTAL;
        }
        if (type.equalsIgnoreCase("attack")) {
            enumParticle = EnumParticle.SWEEP_ATTACK;
        }
        if (type.equalsIgnoreCase("witch")) {
            enumParticle = EnumParticle.SPELL_WITCH;
        }
        return enumParticle;
    }

    public static double getDamageThroughItem(ItemStack item) {
        if (!item.getItemMeta().hasLore()) {
            return 0.0;
        }
        ItemMeta meta = item.getItemMeta();
        List lore = meta.getLore();
        int i = 0;
        while (i < lore.size()) {
            if (((String)lore.get(i)).contains(DAMAGE_LORE)) {
                String damageLore = (String)lore.get(i);
                double st = SettingMethod.getDoubleInString(damageLore);
                return st;
            }
            ++i;
        }
        return 0.0;
    }

    public static String getParticleType(ItemStack item) {
        String type = "flame";
        if (!item.getItemMeta().hasLore()) {
            return type;
        }
        ItemMeta meta = item.getItemMeta();
        List lore = meta.getLore();
        int i = 0;
        while (i < lore.size()) {
            if (((String)lore.get(i)).contains(PARTICLE_LORE)) {
                String damageLore = (String)lore.get(i);
                type = damageLore.substring(damageLore.lastIndexOf(" ") + 1, damageLore.length());
                break;
            }
            ++i;
        }
        return type;
    }

    public static void fireParticle(Player player, int length, EnumParticle type, double damage, float radius, int amount) {
        if (MagicWand.isDelayed(player)) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1.0f, 1.0f);
            ActionBarAPI.sendActionbar(player, "\u00a7cB\u1ea1n ch\u01b0a th\u1ec3 b\u1eafn. H\u00e3y n\u00e2ng \u0111i\u1ec3m \u00a73N\u0102NG L\u01af\u1ee2NG \u00a7ctrong Ti\u1ec1m N\u0103ng \u0111\u1ec3 c\u00f3 th\u1ec3 b\u1eafn nhanh h\u01a1n");
            new BukkitRunnable(){

                public void run() {
                    ActionBarAPI.sendActionbar(Player.this, " ");
                    Player.this.stopSound(Sound.ENTITY_ENDERDRAGON_GROWL);
                }
            }.runTaskLaterAsynchronously((Plugin)Main.plugin, 12);
            return;
        }
        MagicWand.delayPlayer(player);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                MagicWand.unDelayPlayer(Player.this);
            }
        }, (long)MagicWand.getTocDoDanh(player) * 20);
        if (!ClassSetting.manaCost(player, 10)) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1.0f, 1.0f);
            ActionBarAPI.sendActionbar(player, "\u00a76B\u1ea1n kh\u00f4ng \u0111\u1ee7 N\u0103ng L\u01b0\u1ee3ng");
            new BukkitRunnable(){

                public void run() {
                    ActionBarAPI.sendActionbar(Player.this, " ");
                    Player.this.stopSound(Sound.ENTITY_ENDERDRAGON_GROWL);
                }
            }.runTaskLaterAsynchronously((Plugin)Main.plugin, 12);
            return;
        }
        Location loc = player.getLocation();
        loc.add(0.0, 1.100000023841858, 0.0);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_BLAST, 1.0f, 1.0f);
        int i = 0;
        while (i <= length) {
            loc.add(loc.getDirection().getX(), loc.getDirection().getY(), loc.getDirection().getZ());
            Particle.sendParticle(type, loc, radius, radius, radius, 0.01f, amount);
            int count = 0;
            for (Entity e : MagicWand.getEntitiesByLocation(loc, radius + 1.0f)) {
                LivingEntity target;
                if (!(e instanceof LivingEntity) || (target = (LivingEntity)e) == player) continue;
                ++count;
                target.damage(damage, (Entity)player);
            }
            if (count > 0 || loc.getBlock().getType().isSolid()) break;
            ++i;
        }
    }

    private static List<Entity> getEntitiesByLocation(Location lo, float r) {
        ArrayList<Entity> listEn = new ArrayList<Entity>();
        for (Entity e : lo.getWorld().getEntities()) {
            Location locE = e.getLocation().add(0.0, 1.0, 0.0);
            if (locE.distanceSquared(lo) > (double)r) continue;
            listEn.add(e);
        }
        return listEn;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) {
            return;
        }
        if (e.getItem() == null) {
            return;
        }
        if (!ClassSetting.getClass(e.getPlayer()).equals("mage")) {
            return;
        }
        if (e.getPlayer().isSneaking()) {
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR) {
                return;
            }
            if (MagicWand.isThatItem(e.getPlayer().getInventory().getItemInMainHand())) {
                double damage = MagicWand.getDamageThroughItem(e.getItem()) + PhepThuat.getDamageOnlyMage(e.getPlayer());
                int tamXa = MagicWand.getLength(e.getPlayer());
                MagicWand.fireParticle(e.getPlayer(), tamXa, MagicWand.getEnumParticle(MagicWand.getParticleType(e.getItem())), damage, 0.1f, 3);
            }
        }
    }

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

    public static double getTocDoDanh(Player player) {
        double speed = 4.0 - (double)PhepThuat.getPoint(player) * 0.025;
        if (speed < 1.0) {
            speed = 1.0;
        }
        return speed;
    }

    public static int getLength(Player player) {
        int point = PhepThuat.getPoint(player);
        int doDai = 10 + point;
        if (doDai > 100) {
            doDai = 100;
        }
        return doDai;
    }

}

