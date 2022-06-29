/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package kdvn.skill.mage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import kdvn.settings.SettingMethod;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VongTronNangLuong {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static HashMap<Player, Integer> playerClick = new HashMap();
    private static int[] combo = new int[2];
    public static final String NAME = "VongTronNangLuong";
    private static final int LEVEL = 150;

    public static int getMinLevelOfSkill(Player player) {
        return 150;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = VongTronNangLuong.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 150 + 50 * point;
    }

    public static int[] getCombo() {
        VongTronNangLuong.combo[0] = 1;
        VongTronNangLuong.combo[1] = 1;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillMageConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "VongTronNangLuong");
    }

    public static void add1Point(Player player) {
        if (VongTronNangLuong.getPoint(player) == 3) {
            return;
        }
        SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "VongTronNangLuong", (Object)(VongTronNangLuong.getPoint(player) + 1));
        SkillMageConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!VongTronNangLuong.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (VongTronNangLuong.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static boolean hasClick(Player player) {
        return playerClick.containsKey((Object)player);
    }

    public static void add1Click(Player player) {
        if (!VongTronNangLuong.hasClick(player)) {
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
        SettingMethod.createCircle(EnumParticle.REDSTONE, player.getLocation().clone().add(0.0, 0.3, 0.0), 1);
        SettingMethod.createCircle(EnumParticle.REDSTONE, player.getLocation().clone().add(0.0, 1.0, 0.0), 1);
    }

    public static void changeTriggered(Player player) {
        if (VongTronNangLuong.getPoint(player) == 0) {
            return;
        }
        if (VongTronNangLuong.isTriggered(player)) {
            player.sendMessage("\u00a7aT\u1eaft \u00a7cV\u00f2ng Tr\u00f2n N\u0103ng L\u01b0\u1ee3ng");
            VongTronNangLuong.remove(player);
        } else {
            VongTronNangLuong.triggerPlayer(player);
            player.sendMessage("\u00a76B\u1eadt \u00a7cV\u00f2ng Tr\u00f2n N\u0103ng L\u01b0\u1ee3ng");
        }
    }

    public static void doIt(Player player) {
        if (VongTronNangLuong.getPoint(player) == 0) {
            return;
        }
        int manaCost = 20;
        if (VongTronNangLuong.getPoint(player) == 2) {
            manaCost = 15;
        } else if (VongTronNangLuong.getPoint(player) == 3) {
            manaCost = 10;
        }
        if (!ClassSetting.manaCost(player, manaCost)) {
            player.sendMessage("\u00a7aT\u1eaft \u00a7cV\u00f2ng Tr\u00f2n N\u0103ng L\u01b0\u1ee3ng");
            VongTronNangLuong.remove(player);
            return;
        }
        VongTronNangLuong.vongTron(player);
        player.removePotionEffect(PotionEffectType.REGENERATION);
        player.removePotionEffect(PotionEffectType.SPEED);
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 1));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 1));
    }
}

