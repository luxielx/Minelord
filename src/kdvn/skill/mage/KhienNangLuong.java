/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Location
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent
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
import kdvn.classes.ClassSetting;
import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import kdvn.minelord.dosat.DoSat;
import kdvn.phepthuat.NangLuong;
import kdvn.settings.SettingMethod;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class KhienNangLuong {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static HashMap<Player, Integer> playerClick = new HashMap();
    private static int[] combo = new int[2];
    public static final String NAME = "KhienNangLuong";
    private static final int LEVEL = 150;

    public static int getMinLevelOfSkill(Player player) {
        return 150;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = KhienNangLuong.getPoint(player);
        if (point == 3) {
            return 1000;
        }
        return 150 + 50 * point;
    }

    public static int[] getCombo() {
        KhienNangLuong.combo[0] = 0;
        KhienNangLuong.combo[1] = 0;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillMageConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "KhienNangLuong");
    }

    public static void add1Point(Player player) {
        if (KhienNangLuong.getPoint(player) == 3) {
            return;
        }
        SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "KhienNangLuong", (Object)(KhienNangLuong.getPoint(player) + 1));
        SkillMageConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!KhienNangLuong.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (KhienNangLuong.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static boolean hasClick(Player player) {
        return playerClick.containsKey((Object)player);
    }

    public static void add1Click(Player player) {
        if (!KhienNangLuong.hasClick(player)) {
            playerClick.put(player, 1);
        } else {
            playerClick.put(player, playerClick.get((Object)player) + 1);
        }
    }

    public static int getClick(Player player) {
        return playerClick.get((Object)player);
    }

    public static void removeClick(Player player) {
        if (playerClick.containsKey((Object)player)) {
            playerClick.remove((Object)player);
        }
    }

    public static void vongTron(Player player) {
        SettingMethod.createCircle(EnumParticle.VILLAGER_HAPPY, player.getLocation().clone().add(0.0, 0.7, 0.0), 1);
    }

    public static void changeTriggered(Player player) {
        if (KhienNangLuong.getPoint(player) == 0) {
            return;
        }
        if (KhienNangLuong.isTriggered(player)) {
            player.sendMessage("\u00a7aT\u1eaft \u00a7cKhi\u00ean N\u0103ng L\u01b0\u1ee3ng");
            KhienNangLuong.remove(player);
        } else {
            KhienNangLuong.triggerPlayer(player);
            player.sendMessage("\u00a76B\u1eadt \u00a7cKhi\u00ean N\u0103ng L\u01b0\u1ee3ng");
        }
    }

    public static void doIt(EntityDamageByEntityEvent e) {
        if (!DoSat.canAttack(e)) {
            return;
        }
        if (e.getEntity() instanceof Player) {
            final Player player = (Player)e.getEntity();
            if (KhienNangLuong.getPoint(player) == 0) {
                return;
            }
            if (!KhienNangLuong.isTriggered(player)) {
                return;
            }
            if ((float)NangLuong.getNangLuong(player) < (float)NangLuong.getMaxNangLuong(player) * 0.2f) {
                player.sendMessage("\u00a7aT\u1eaft \u00a7cKhi\u00ean N\u0103ng L\u01b0\u1ee3ng");
                KhienNangLuong.remove(player);
                return;
            }
            new BukkitRunnable(){

                public void run() {
                    if (EntityDamageByEntityEvent.this.getEntity().getLastDamageCause().getDamage() != EntityDamageByEntityEvent.this.getDamage()) {
                        Double finalDamage = EntityDamageByEntityEvent.this.getDamage() + EntityDamageByEntityEvent.this.getEntity().getLastDamageCause().getDamage();
                        int mul = 12;
                        if (KhienNangLuong.getPoint(player) == 2) {
                            mul = 10;
                        } else if (KhienNangLuong.getPoint(player) == 3) {
                            mul = 7;
                        }
                        int manaCost = finalDamage.intValue() * mul;
                        if (!ClassSetting.manaCost(player, manaCost)) {
                            player.sendMessage("\u00a7aT\u1eaft \u00a7cKhi\u00ean N\u0103ng L\u01b0\u1ee3ng");
                            KhienNangLuong.remove(player);
                            return;
                        }
                        NangLuong.setNangLuong(player, NangLuong.getNangLuong(player) - manaCost);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 1));
                        EntityDamageByEntityEvent.this.setCancelled(true);
                    } else {
                        Double finalDamage = EntityDamageByEntityEvent.this.getDamage();
                        int mul = 12;
                        if (KhienNangLuong.getPoint(player) == 2) {
                            mul = 10;
                        } else if (KhienNangLuong.getPoint(player) == 3) {
                            mul = 7;
                        }
                        int manaCost = finalDamage.intValue() * mul;
                        if (!ClassSetting.manaCost(player, manaCost)) {
                            player.sendMessage("\u00a7aT\u1eaft \u00a7cKhi\u00ean N\u0103ng L\u01b0\u1ee3ng");
                            KhienNangLuong.remove(player);
                            return;
                        }
                        NangLuong.setNangLuong(player, NangLuong.getNangLuong(player) - manaCost);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 1));
                        EntityDamageByEntityEvent.this.setCancelled(true);
                    }
                }
            }.runTask((Plugin)Main.plugin);
        }
    }

}

