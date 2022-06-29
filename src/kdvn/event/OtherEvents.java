/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.block.CreatureSpawner
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Fireball
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.block.BlockRedstoneEvent
 *  org.bukkit.event.entity.EntityDeathEvent
 *  org.bukkit.event.entity.ExplosionPrimeEvent
 *  org.bukkit.event.entity.SpawnerSpawnEvent
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.inventory.InventoryType
 *  org.bukkit.event.player.PlayerCommandPreprocessEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.event.player.PlayerLevelChangeEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 *  org.bukkit.util.Vector
 *  whosafk.events.AFKStatusOnEvent
 */
package kdvn.event;

import kdvn.bow.BowArcher;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillArcherConfig;
import kdvn.config.SkillKnightConfig;
import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import kdvn.minelord.player.MineLordPlayer;
import kdvn.noiluc.NoiLuc;
import kdvn.point.Money;
import kdvn.settings.ActionBarAPI;
import kdvn.settings.Particle;
import kdvn.settings.SettingMethod;
import kdvn.skill.archer.LienHoanTen;
import kdvn.tiemnang.TongDiem;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import whosafk.events.AFKStatusOnEvent;

public class OtherEvents
implements Listener {
    private final int MAX_LEVEL = 1000;

    @EventHandler
    public void cmdAFK(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().contains("/afk")) {
            e.getPlayer().sendMessage("\u00a76B\u1ea1n kh\u00f4ng th\u1ec3 d\u00f9ng l\u1ec7nh n\u00e0y");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onAFK(AFKStatusOnEvent e) {
        if (e.getPlayer().hasPermission("ml.*")) {
            return;
        }
        e.getPlayer().kickPlayer("\u00a7cB\u1ea1n b\u1ecb kick v\u00ec AFK");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
    }

    @EventHandler
    public void onLevelUp(PlayerLevelChangeEvent e) {
        int newLevel = e.getNewLevel();
        if (newLevel > 1000) {
            e.getPlayer().setLevel(1000);
            newLevel = 1000;
        }
        if (newLevel > e.getOldLevel()) {
            SettingMethod.moveUpHolograms(e.getPlayer().getLocation(), "\u00a72\u00a7lL\u00caN C\u1ea4P");
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 2.0f, 1.0f);
            Particle.sendParticle(EnumParticle.FIREWORKS_SPARK, e.getPlayer().getLocation().add(e.getPlayer().getLocation().getDirection()), 1.0f, 1.0f, 1.0f, 0.1f, 20);
            e.getPlayer().sendTitle("\u00a7a\u00a7lL\u00ean c\u1ea5p \u00a7c\u00a7l" + newLevel, "", 10, 20, 10);
        }
        TongDiem.setPoint(e.getPlayer(), newLevel);
        NoiLuc.setMaxNoiLuc(e.getPlayer(), newLevel + 1);
        if (newLevel / 50 != e.getOldLevel() / 50) {
            if (ClassSetting.getClass(e.getPlayer()).equals("archer")) {
                SkillArcherConfig.getConfig((Plugin)Main.plugin).set("Player." + e.getPlayer().getName() + ".TongDiem", (Object)(newLevel / 50));
                SkillArcherConfig.saveConfig();
            } else if (ClassSetting.getClass(e.getPlayer()).equals("knight")) {
                SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + e.getPlayer().getName() + ".TongDiem", (Object)(newLevel / 50));
                SkillKnightConfig.saveConfig();
            } else if (ClassSetting.getClass(e.getPlayer()).equals("mage")) {
                SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + e.getPlayer().getName() + ".TongDiem", (Object)(newLevel / 50));
                SkillMageConfig.saveConfig();
            }
        }
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        try {
            if (e.getItem().getType() == Material.CARPET) {
                e.setCancelled(true);
            }
        }
        catch (NullPointerException ee) {
            return;
        }
        if (e.getPlayer().isSneaking()) {
            return;
        }
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && BowArcher.isThatItem(e.getItem())) {
            if (BowArcher.isDelayed(e.getPlayer())) {
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1.0f, 1.0f);
                ActionBarAPI.sendActionbar(e.getPlayer(), "\u00a7cB\u1ea1n ch\u01b0a th\u1ec3 b\u1eafn. H\u00e3y t\u0103ng \u0111i\u1ec3m \u00a7aNHANH NH\u1eb8N \u00a7c\u0111\u1ec3 b\u1eafn nhanh h\u01a1n!");
                new BukkitRunnable(){

                    public void run() {
                        ActionBarAPI.sendActionbar(e.getPlayer(), " ");
                        e.getPlayer().stopSound(Sound.ENTITY_ENDERDRAGON_GROWL);
                    }
                }.runTaskLaterAsynchronously((Plugin)Main.plugin, 12);
                return;
            }
            if (e.getPlayer().getInventory().contains(Material.ARROW)) {
                LienHoanTen.add1Click(e.getPlayer());
                if (LienHoanTen.getClick(e.getPlayer()) == 3) {
                    LienHoanTen.xuLySatThuong(e.getPlayer(), new MineLordPlayer(e.getPlayer()).getAddedDamage() * 1.5);
                } else {
                    BowArcher.lauchArrow(e.getPlayer());
                }
            } else {
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1.0f, 1.0f);
                ActionBarAPI.sendActionbar(e.getPlayer(), "\u00a76B\u1ea1n kh\u00f4ng c\u00f3 \u0111\u1ee7 t\u00ean");
                new BukkitRunnable(){

                    public void run() {
                        ActionBarAPI.sendActionbar(e.getPlayer(), " ");
                        e.getPlayer().stopSound(Sound.ENTITY_ENDERDRAGON_GROWL);
                    }
                }.runTaskLaterAsynchronously((Plugin)Main.plugin, 10);
            }
        }
    }

    @EventHandler
    public void onExplosionPrime(ExplosionPrimeEvent e) {
        if (e.getEntity() instanceof Fireball) {
            e.setFire(false);
        }
    }

    @EventHandler
    public void onSpawn(SpawnerSpawnEvent e) {
        try {
            e.setCancelled(true);
            e.getSpawner().getBlock().setType(Material.AIR);
        }
        catch (NullPointerException ee) {
            return;
        }
    }

    @EventHandler
    public void onDeath(final EntityDeathEvent e) {
        new BukkitRunnable(){

            public void run() {
                for (Entity entity : SettingMethod.getEntitiesByLocation(e.getEntity().getLocation(), 4.0f)) {
                    if (!(entity instanceof Item) || ((Item)entity).getItemStack().getType() != Material.GOLD_NUGGET) continue;
                    entity.remove();
                }
            }
        }.runTaskLater((Plugin)Main.plugin, 1);
    }

    @EventHandler
    public void onRename(InventoryClickEvent e) {
        if (e.getInventory().getType() == InventoryType.ANVIL) {
            e.setCancelled(true);
            e.getWhoClicked().sendMessage("\u00a7c\u00a7lN\u1ebfu b\u1ea1n \u0111\u1ecbnh bug th\u00ec t\u1eeb b\u1ecf \u00fd \u0111\u1ecbnh \u0111i");
        }
    }

    @EventHandler
    public void onCmdBack(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().equalsIgnoreCase("/back")) {
            int moneyPerLevel = 20;
            double money = moneyPerLevel * e.getPlayer().getLevel();
            if (!Money.moneyCost(e.getPlayer(), money)) {
                e.getPlayer().sendMessage("\u00a7cB\u1ea1n ph\u1ea3i c\u00f3 " + money + " b\u1ea1c th\u00ec m\u1edbi c\u00f3 th\u1ec3 /back");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onRedstone(BlockRedstoneEvent e) {
        try {
            e.getBlock().setType(Material.AIR);
        }
        catch (Exception ee) {
            return;
        }
    }

}

