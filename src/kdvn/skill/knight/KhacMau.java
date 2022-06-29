/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.plugin.Plugin
 */
package kdvn.skill.knight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillKnightConfig;
import kdvn.main.Main;
import kdvn.settings.SettingMethod;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class KhacMau {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static HashMap<Player, Integer> playerClick = new HashMap();
    private static int[] combo = new int[3];
    public static final String NAME = "KhacMau";
    public static final int MANA_COST = 10;
    private static final int LEVEL = 50;

    public static int getMinLevelOfSkill(Player player) {
        return 50;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = KhacMau.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 50 + 50 * point;
    }

    public static int[] getCombo() {
        KhacMau.combo[0] = 1;
        KhacMau.combo[1] = 0;
        KhacMau.combo[2] = 0;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillKnightConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "KhacMau");
    }

    public static void add1Point(Player player) {
        if (KhacMau.getPoint(player) == 3) {
            return;
        }
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "KhacMau", (Object)(KhacMau.getPoint(player) + 1));
        SkillKnightConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!KhacMau.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (KhacMau.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static boolean hasClick(Player player) {
        return playerClick.containsKey((Object)player);
    }

    public static void add1Click(Player player) {
        if (!KhacMau.hasClick(player)) {
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

    public static void changeTriggered(Player player) {
        if (KhacMau.getPoint(player) == 0) {
            return;
        }
        if (KhacMau.isTriggered(player)) {
            player.sendMessage("\u00a7aT\u1eaft \u00a7cKh\u1eafc M\u00e1u");
            KhacMau.remove(player);
        } else {
            KhacMau.triggerPlayer(player);
            player.sendMessage("\u00a76B\u1eadt \u00a7cKh\u1eafc M\u00e1u");
        }
    }

    public static void doIt(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player)e.getDamager();
        if (KhacMau.getPoint(player) == 0) {
            return;
        }
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity target = (LivingEntity)e.getEntity();
        if (!KhacMau.isTriggered(player)) {
            return;
        }
        KhacMau.add1Click(player);
        if (KhacMau.getClick(player) == 3) {
            KhacMau.removeClick(player);
            if (!ClassSetting.manaCost(player, 10)) {
                return;
            }
            float tiLe = 0.02f;
            if (KhacMau.getPoint(player) == 2) {
                tiLe = 0.03f;
            } else if (KhacMau.getPoint(player) == 3) {
                tiLe = 0.05f;
            }
            double hp = target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * (double)tiLe;
            KhacMau.hoiMau(player, hp);
            player.sendMessage("\u00a7aH\u00fat \u00a7c" + SettingMethod.lamTron(hp) + " \u00a7am\u00e1u t\u1eeb \u00a76" + target.getName());
        }
    }

    public static void hoiMau(Player player, double health) {
        if (player.getHealth() + health > player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        } else {
            player.setHealth(player.getHealth() + health);
        }
    }
}

