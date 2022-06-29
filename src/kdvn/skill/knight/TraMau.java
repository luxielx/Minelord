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
import kdvn.safezone.SafeZone;
import kdvn.settings.SettingMethod;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;

public class TraMau {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static HashMap<Player, Integer> playerClick = new HashMap();
    private static int[] combo = new int[3];
    public static final String NAME = "TraMau";
    public static final int MANA_COST = 10;
    private static final int LEVEL = 50;

    public static int getMinLevelOfSkill(Player player) {
        return 50;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = TraMau.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 50 + 50 * point;
    }

    public static int[] getCombo() {
        TraMau.combo[0] = 0;
        TraMau.combo[1] = 0;
        TraMau.combo[2] = 1;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillKnightConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "TraMau");
    }

    public static void add1Point(Player player) {
        if (TraMau.getPoint(player) == 3) {
            return;
        }
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "TraMau", (Object)(TraMau.getPoint(player) + 1));
        SkillKnightConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!TraMau.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (TraMau.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static boolean hasClick(Player player) {
        return playerClick.containsKey((Object)player);
    }

    public static void add1Click(Player player) {
        if (!TraMau.hasClick(player)) {
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
        if (TraMau.getPoint(player) == 0) {
            return;
        }
        if (TraMau.isTriggered(player)) {
            player.sendMessage("\u00a7aT\u1eaft \u00a7cTr\u1ea3 M\u00e1u");
            TraMau.remove(player);
        } else {
            TraMau.triggerPlayer(player);
            player.sendMessage("\u00a76B\u1eadt \u00a7cTr\u1ea3 M\u00e1u");
        }
    }

    public static void doIt(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player)e.getDamager();
        if (!SafeZone.isAllowedtoPVP(player)) {
            return;
        }
        if (TraMau.getPoint(player) == 0) {
            return;
        }
        if (!(e.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity target = (LivingEntity)e.getEntity();
        if (!TraMau.isTriggered(player)) {
            return;
        }
        TraMau.add1Click(player);
        if (TraMau.getClick(player) == 3) {
            TraMau.removeClick(player);
            if (!ClassSetting.manaCost(player, 10)) {
                return;
            }
            float tiLe = 0.05f;
            if (TraMau.getPoint(player) == 2) {
                tiLe = 0.06f;
            } else if (TraMau.getPoint(player) == 3) {
                tiLe = 0.08f;
            }
            double satThuong = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * (double)tiLe;
            e.setDamage(e.getDamage() + satThuong);
            player.sendMessage("\u00a7aG\u00e2y th\u00eam \u00a7c" + SettingMethod.lamTron(satThuong) + " \u00a7as\u00e1t th\u01b0\u01a1ng cho \u00a76" + target.getName());
        }
    }
}

