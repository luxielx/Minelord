/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LightningStrike
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package kdvn.skill.mage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import kdvn.phepthuat.NangLuong;
import kdvn.settings.Particle;
import kdvn.settings.SettingMethod;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class BocPhaThien {
    public static HashMap<Location, Integer> timer = new HashMap();
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static int[] combo = new int[4];
    public static final String NAME = "BocPhaThien";
    public static final int TIME_DELAY = 120;
    private static final int LEVEL = 500;

    public static int getMinLevelOfSkill(Player player) {
        return 500;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = BocPhaThien.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 500 + 50 * point;
    }

    public static int[] getCombo() {
        BocPhaThien.combo[0] = 1;
        BocPhaThien.combo[1] = 0;
        BocPhaThien.combo[2] = 1;
        BocPhaThien.combo[3] = 0;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillMageConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "BocPhaThien");
    }

    public static void add1Point(Player player) {
        if (BocPhaThien.getPoint(player) == 3) {
            return;
        }
        SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "BocPhaThien", (Object)(BocPhaThien.getPoint(player) + 1));
        SkillMageConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        return triggeredPlayer.contains((Object)player);
    }

    public static void triggerPlayer(Player player) {
        if (!BocPhaThien.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (BocPhaThien.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void createCircle(Location location, int radius) {
        int amount = radius * 4;
        double increment = 6.283185307179586 / (double)amount;
        ArrayList<Location> locations = new ArrayList<Location>();
        int i = 0;
        while (i < amount) {
            double angle = (double)i * increment;
            double x = location.getX() + (double)radius * Math.cos(angle);
            double z = location.getZ() + (double)radius * Math.sin(angle);
            locations.add(new Location(location.getWorld(), x, location.getY(), z));
            ++i;
        }
        for (Location l : locations) {
            Particle.sendParticle(EnumParticle.FLAME, l, 0.0f, 0.0f, 0.0f, 0.0f, 1);
        }
    }

    public static void doIt(Player player) {
        if (BocPhaThien.getPoint(player) == 0) {
            return;
        }
        if (BocPhaThien.isTriggered(player)) {
            player.sendMessage("3");
            return;
        }
        int tiLe = 99;
        if (BocPhaThien.getPoint(player) == 2) {
            tiLe = 98;
        } else if (BocPhaThien.getPoint(player) == 3) {
            tiLe = 95;
        }
        if (NangLuong.getNangLuong(player) < NangLuong.getMaxNangLuong(player) / 100 * tiLe) {
            player.sendMessage("\u00a76B\u1ea1n kh\u00f4ng \u0111\u1ee7 \u00a73N\u0103ng L\u01b0\u1ee3ng \u00a76\u0111\u1ec3 d\u00f9ng \u00a7cB\u1ed9c Ph\u00e1 Thi\u00ean");
            return;
        }
        Block block = player.getTargetBlock(null, 100);
        if (block.getType() == Material.AIR) {
            player.sendMessage("\u00a7cB\u1ea1n ph\u1ea3i ch\u1ec9 v\u00e0o 1 Block");
            return;
        }
        Location location = block.getLocation();
        BocPhaThien.triggerPlayer(player);
        BocPhaThien.explosion(location, player);
        NangLuong.setNangLuong(player, NangLuong.getNangLuong(player) - NangLuong.getMaxNangLuong(player) / 100 * tiLe);
        player.sendMessage(SettingMethod.colorDecomplier("&aTh\u1eddi gian h\u1ed3i &6B\u1ed9c Ph\u00e1 Thi\u00ean &c120"));
        Bukkit.getScheduler().scheduleAsyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                BocPhaThien.remove(Player.this);
                Player.this.sendMessage(SettingMethod.colorDecomplier("&3H\u1ed3i chi\u00eau &6B\u1ed9c Ph\u00e1 Thi\u00ean"));
            }
        }, 2400);
    }

    public static void explosion(final Location location, Player player) {
        if (BocPhaThien.getPoint(player) == 0) {
            return;
        }
        timer.put(location, 0);
        new BukkitRunnable(){

            public void run() {
                BocPhaThien.timer.put(Location.this, BocPhaThien.timer.get((Object)Location.this) + 1);
                if (BocPhaThien.timer.get((Object)Location.this) >= 18) {
                    BocPhaThien.timer.remove((Object)Location.this);
                    this.cancel();
                    return;
                }
                int time = BocPhaThien.timer.get((Object)Location.this);
                if (time == 1) {
                    BocPhaThien.motTang(Location.this);
                    Location.this.getWorld().playSound(Location.this, Sound.ENTITY_FIREWORK_LARGE_BLAST, 3.0f, 3.0f);
                } else if (time == 3) {
                    BocPhaThien.haiTang(Location.this);
                    Location.this.getWorld().playSound(Location.this, Sound.ENTITY_FIREWORK_LARGE_BLAST, 3.0f, 3.0f);
                } else if (time == 5) {
                    BocPhaThien.baTang(Location.this);
                    Location.this.getWorld().playSound(Location.this, Sound.ENTITY_FIREWORK_LARGE_BLAST, 3.0f, 3.0f);
                } else if (time == 7) {
                    BocPhaThien.bonTang(Location.this);
                    Location.this.getWorld().playSound(Location.this, Sound.ENTITY_FIREWORK_LARGE_BLAST, 3.0f, 3.0f);
                } else if (time == 9) {
                    BocPhaThien.namTang(Location.this);
                    Location.this.getWorld().playSound(Location.this, Sound.ENTITY_FIREWORK_LARGE_BLAST, 3.0f, 3.0f);
                } else if (time == 11) {
                    BocPhaThien.sauTang(Location.this);
                    Location.this.getWorld().playSound(Location.this, Sound.ENTITY_FIREWORK_LARGE_BLAST, 3.0f, 3.0f);
                } else if (time == 12) {
                    BocPhaThien.bayTang(Location.this);
                    Location.this.getWorld().playSound(Location.this, Sound.ENTITY_FIREWORK_LARGE_BLAST, 3.0f, 3.0f);
                } else if (time == 13) {
                    BocPhaThien.tamTang(Location.this);
                    Location.this.getWorld().playSound(Location.this, Sound.ENTITY_FIREWORK_LARGE_BLAST, 3.0f, 3.0f);
                } else if (time == 14) {
                    BocPhaThien.tamTang(Location.this);
                    int temp = 0;
                    while (temp < 30) {
                        ++temp;
                    }
                } else if (time == 15) {
                    BocPhaThien.tamTang(Location.this);
                    int temp = 0;
                    while (temp < 30) {
                        Particle.sendParticle(EnumParticle.FLAME, Location.this.clone().add(0.0, (double)(++temp), 0.0), 0.1f, 0.1f, 0.1f, 0.0f, 2);
                    }
                } else if (time == 17) {
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimerAsynchronously((Plugin)Main.plugin, 0, 5);
        new BukkitRunnable(){

            public void run() {
                Bukkit.broadcastMessage((String)("\u00a76" + Player.this.getName() + " \u00a7a>> \u00a7cEXPLOSION"));
                location.getWorld().playSound(location, Sound.ENTITY_ENDERDRAGON_GROWL, 3.0f, 3.0f);
                Particle.sendParticle(EnumParticle.EXPLOSION_HUGE, location, 5.0f, 2.0f, 5.0f, 0.1f, 10);
                location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 3.0f, 3.0f);
                Player.this.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 1));
                Player.this.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 1));
                int i = 0;
                while (i < 2) {
                    location.getWorld().strikeLightning(location);
                    ++i;
                }
                List<Entity> list = SettingMethod.getEntitiesByLocation(location, 100.0f);
                for (Entity e : list) {
                    if (!(e instanceof LivingEntity)) continue;
                    ((LivingEntity)e).damage((double)((float)NangLuong.getMaxNangLuong(Player.this) * 0.5f), (Entity)Player.this);
                }
            }
        }.runTaskLater((Plugin)Main.plugin, 85);
    }

    private static void motTang(Location location) {
        BocPhaThien.createCircle(location.clone().add(0.0, 5.0, 0.0), 2);
        BocPhaThien.createCircle(location.clone().add(0.0, 5.0, 0.0), 5);
    }

    private static void haiTang(Location location) {
        BocPhaThien.motTang(location);
    }

    private static void baTang(Location location) {
        BocPhaThien.haiTang(location);
        BocPhaThien.createCircle(location.clone().add(0.0, 10.0, 0.0), 4);
        BocPhaThien.createCircle(location.clone().add(0.0, 15.0, 0.0), 9);
        BocPhaThien.createCircle(location.clone().add(0.0, 10.0, 0.0), 10);
    }

    private static void bonTang(Location location) {
        BocPhaThien.baTang(location);
    }

    private static void namTang(Location location) {
        BocPhaThien.bonTang(location);
        BocPhaThien.createCircle(location.clone().add(0.0, 15.0, 0.0), 7);
        BocPhaThien.createCircle(location.clone().add(0.0, 15.0, 0.0), 16);
    }

    private static void sauTang(Location location) {
        BocPhaThien.namTang(location);
        BocPhaThien.createCircle(location.clone().add(0.0, 20.0, 0.0), 5);
        BocPhaThien.createCircle(location.clone().add(0.0, 30.0, 0.0), 11);
        BocPhaThien.createCircle(location.clone().add(0.0, 20.0, 0.0), 12);
    }

    private static void bayTang(Location location) {
        BocPhaThien.sauTang(location);
        BocPhaThien.createCircle(location.clone().add(0.0, 25.0, 0.0), 3);
        BocPhaThien.createCircle(location.clone().add(0.0, 25.0, 0.0), 7);
    }

    private static void tamTang(Location location) {
        BocPhaThien.bayTang(location);
        BocPhaThien.createCircle(location.clone().add(0.0, 30.0, 0.0), 7);
        BocPhaThien.createCircle(location.clone().add(0.0, 30.0, 0.0), 16);
        BocPhaThien.createCircle(location.clone().add(0.0, 30.0, 0.0), 17);
    }

}

