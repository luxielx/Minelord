/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.util.Vector
 */
package kdvn.skill.knight;

import java.util.ArrayList;
import java.util.List;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillKnightConfig;
import kdvn.main.Main;
import kdvn.settings.Particle;
import kdvn.settings.SettingMethod;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class AnhDung {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static List<Player> chem = new ArrayList<Player>();
    private static int[] combo = new int[3];
    public static final String NAME = "AnhDung";
    public static final int MANA_COST = 40;
    public static final int TIME_DELAY = 20;
    private static final int LEVEL = 100;

    public static int getMinLevelOfSkill(Player player) {
        return 100;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = AnhDung.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 100 + 50 * point;
    }

    public static int[] getCombo() {
        AnhDung.combo[0] = 1;
        AnhDung.combo[1] = 1;
        AnhDung.combo[2] = 1;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillKnightConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "AnhDung");
    }

    public static void add1Point(Player player) {
        if (AnhDung.getPoint(player) == 3) {
            return;
        }
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "AnhDung", (Object)(AnhDung.getPoint(player) + 1));
        SkillKnightConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!AnhDung.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (AnhDung.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static double xuLySatThuong(double damage, Player player) {
        if (!ClassSetting.getClass(player).equals("knight")) {
            return damage;
        }
        if (AnhDung.getPoint(player) == 0) {
            return damage;
        }
        double satThuong = damage;
        if (AnhDung.getPoint(player) == 1) {
            satThuong = damage * 1.0;
        } else if (AnhDung.getPoint(player) == 2) {
            satThuong = damage * 1.2000000476837158;
        } else if (AnhDung.getPoint(player) == 3) {
            satThuong = damage * 1.5;
        }
        return satThuong;
    }

    public static boolean isChemed(Player player) {
        return chem.contains((Object)player);
    }

    public static void removeChem(Player player) {
        if (chem.contains((Object)player)) {
            chem.remove((Object)player);
        }
    }

    public static void doIt(Player player) {
        if (AnhDung.getPoint(player) == 0) {
            return;
        }
        if (AnhDung.isTriggered(player)) {
            return;
        }
        if (!ClassSetting.manaCost(player, 40)) {
            return;
        }
        AnhDung.triggerPlayer(player);
        player.sendMessage("\u00a76Th\u1eddi gian h\u1ed3i chi\u00eau \u00a78Anh D\u0169ng: \u00a7c20");
        Particle.sendParticle(EnumParticle.SMOKE_NORMAL, player.getLocation().clone().add(0.0, 1.0, 0.0), 0.1f, 0.1f, 0.1f, 0.001f, 5);
        player.setVelocity(player.getLocation().getDirection().multiply(2));
        player.sendMessage("\u00a7aPh\u00e1t ch\u00e9m ti\u1ebfp theo s\u1ebd g\u00e2y M\u00f9 v\u00e0 H\u1ea5t Tung k\u1ebb \u0111\u1ecbch");
        if (!chem.contains((Object)player)) {
            chem.add(player);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                AnhDung.remove(Player.this);
                Player.this.sendMessage(SettingMethod.colorDecomplier("&3H\u1ed3i chi\u00eau &6Anh D\u0169ng"));
            }
        }, 400);
    }

}

