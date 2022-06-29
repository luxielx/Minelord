/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.WitherSkeleton
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityTargetLivingEntityEvent
 *  org.bukkit.inventory.EntityEquipment
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.util.Vector
 */
package kdvn.skill.mage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import kdvn.minelord.dosat.DoSat;
import kdvn.settings.Particle;
import kdvn.settings.SettingMethod;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class QuanDoanDiaNguc
implements Listener {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    public static HashMap<Player, String> owningMob = new HashMap();
    public static final String NAME = "QuanDoanDiaNguc";
    private static int[] combo = new int[3];
    public static final int TIME_DELAY = 60;
    public static final int MANA_COST = 500;
    private static final int LEVEL = 200;

    public static int getMinLevelOfSkill(Player player) {
        return 200;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = QuanDoanDiaNguc.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 200 + 50 * point;
    }

    public static int[] getCombo() {
        QuanDoanDiaNguc.combo[0] = 1;
        QuanDoanDiaNguc.combo[1] = 1;
        QuanDoanDiaNguc.combo[2] = 1;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillMageConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "QuanDoanDiaNguc");
    }

    public static void add1Point(Player player) {
        if (QuanDoanDiaNguc.getPoint(player) == 3) {
            return;
        }
        SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "QuanDoanDiaNguc", (Object)(QuanDoanDiaNguc.getPoint(player) + 1));
        SkillMageConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!QuanDoanDiaNguc.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (QuanDoanDiaNguc.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static boolean hasMob(Player player) {
        return owningMob.containsKey((Object)player);
    }

    public static String getMobName(Player player) {
        String name = "zz";
        if (QuanDoanDiaNguc.hasMob(player)) {
            name = owningMob.get((Object)player);
        }
        return name;
    }

    public static void doIt(final Player player) {
        if (!ClassSetting.getClass(player).equals("mage")) {
            return;
        }
        if (QuanDoanDiaNguc.getPoint(player) == 0) {
            return;
        }
        if (QuanDoanDiaNguc.isTriggered(player)) {
            return;
        }
        if (!ClassSetting.manaCost(player, 500)) {
            return;
        }
        Block block = player.getTargetBlock(null, 30);
        if (block.getType() == Material.AIR) {
            player.sendMessage("\u00a7cB\u1ea1n ch\u1ec9 v\u00e0o kh\u00f4ng \u0111\u00fang block ho\u1eb7c qu\u00e1 xa");
            return;
        }
        QuanDoanDiaNguc.triggerPlayer(player);
        if (QuanDoanDiaNguc.getPoint(player) == 1) {
            int i = 0;
            while (i < 2) {
                QuanDoanDiaNguc.spawnMob(player, block.getLocation().clone().add(0.0, 3.0, 0.0));
                ++i;
            }
        } else if (QuanDoanDiaNguc.getPoint(player) == 2) {
            int i = 0;
            while (i < 3) {
                QuanDoanDiaNguc.spawnMob(player, block.getLocation().clone().add(0.0, 3.0, 0.0));
                ++i;
            }
        } else if (QuanDoanDiaNguc.getPoint(player) == 3) {
            int i = 0;
            while (i < 4) {
                QuanDoanDiaNguc.spawnMob(player, block.getLocation().clone().add(0.0, 3.0, 0.0));
                ++i;
            }
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                QuanDoanDiaNguc.hatTung(Block.this.getLocation(), player);
            }
        }, 10);
        player.sendMessage("\u00a76Th\u1eddi gian h\u1ed3i chi\u00eau \u00a78Qu\u00e2n \u0110o\u00e0n \u0110\u1ecba Ng\u1ee5c: \u00a7c60s");
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                QuanDoanDiaNguc.remove(Player.this);
                Player.this.sendMessage("\u00a76H\u1ed3i chi\u00eau \u00a78Qu\u00e2n \u0110o\u00e0n \u0110\u1ecba Ng\u1ee5c");
            }
        }, 1200);
    }

    public static void spawnMob(Player player, Location location) {
        WitherSkeleton pigZombie = (WitherSkeleton)player.getWorld().spawnEntity(location, EntityType.WITHER_SKELETON);
        pigZombie.setCustomName("   \u00a78\u00a7lL\u00ednh \u0110\u1ecba Ng\u1ee5c \u00a76c\u1ee7a \u00a7a\u00a7l" + player.getName());
        pigZombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
        pigZombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
        pigZombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
        pigZombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1));
        pigZombie.getEquipment().setItemInMainHand(new ItemStack(Material.GOLD_SWORD, 1));
        pigZombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 1));
        pigZombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100.0);
        pigZombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue((double)(QuanDoanDiaNguc.getPoint(player) * 10));
        pigZombie.setHealth(100.0);
        owningMob.put(player, pigZombie.getCustomName());
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                if (!WitherSkeleton.this.isDead()) {
                    WitherSkeleton.this.remove();
                }
            }
        }, 1200);
    }

    public static void hatTung(Location location, Player player) {
        Particle.sendParticle(EnumParticle.EXPLOSION_LARGE, location, 3.0f, 3.0f, 3.0f, 0.01f, 5);
        for (Entity e : SettingMethod.getEntitiesByLocation(location, 25.0f)) {
            if (e.getName().equals(QuanDoanDiaNguc.getMobName(player)) || e == player) continue;
            e.setVelocity(e.getLocation().subtract(location).clone().multiply(0.30000001192092896).add(0.0, 0.699999988079071, 0.0).toVector());
        }
    }

    @EventHandler
    public static void onDamage(EntityDamageByEntityEvent e) {
        Player player;
        if (!DoSat.canAttack(e)) {
            return;
        }
        if (e.getEntity() instanceof Player && QuanDoanDiaNguc.hasMob(player = (Player)e.getEntity()) && e.getDamager() instanceof LivingEntity) {
            if (!(e.getEntity() instanceof LivingEntity)) {
                return;
            }
            LivingEntity en = (LivingEntity)e.getDamager();
            for (Entity entity : player.getWorld().getEntities()) {
                WitherSkeleton enn;
                if (!(entity instanceof WitherSkeleton) || !(enn = (WitherSkeleton)entity).getName().equals(QuanDoanDiaNguc.getMobName(player))) continue;
                enn.setTarget(en);
            }
        }
        if (e.getDamager() instanceof Player && QuanDoanDiaNguc.hasMob(player = (Player)e.getDamager()) && e.getEntity() instanceof LivingEntity) {
            for (Entity entity : player.getWorld().getEntities()) {
                WitherSkeleton enn;
                if (!(entity instanceof WitherSkeleton) || !(enn = (WitherSkeleton)entity).getName().equals(QuanDoanDiaNguc.getMobName(player))) continue;
                enn.setTarget((LivingEntity)e.getEntity());
            }
        }
    }

    @EventHandler
    public static void onTargetEvent(EntityTargetLivingEntityEvent e) {
        if (!(e.getTarget() instanceof Player)) {
            return;
        }
        if (e.getEntity() instanceof WitherSkeleton) {
            WitherSkeleton em = (WitherSkeleton)e.getEntity();
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!QuanDoanDiaNguc.hasMob(player) || !QuanDoanDiaNguc.getMobName(player).equals(em.getName())) continue;
                if (e.getTarget() instanceof Player && !DoSat.canAttack(player, (Player)e.getTarget())) {
                    e.setCancelled(true);
                    return;
                }
                if (e.getTarget() != player) continue;
                e.setCancelled(true);
                return;
            }
        }
    }

}

