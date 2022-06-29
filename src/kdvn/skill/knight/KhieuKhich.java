/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Monster
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
import kdvn.settings.SettingMethod;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class KhieuKhich {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static int[] combo = new int[2];
    public static final String NAME = "KhieuKhich";
    public static final int MANA_COST = 10;
    public static final int TIME_DELAY = 5;
    private static final int LEVEL = 150;

    public static int getMinLevelOfSkill(Player player) {
        return 150;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = KhieuKhich.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 150 + 50 * point;
    }

    public static int[] getCombo() {
        KhieuKhich.combo[0] = 1;
        KhieuKhich.combo[1] = 1;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillKnightConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "KhieuKhich");
    }

    public static void add1Point(Player player) {
        if (KhieuKhich.getPoint(player) == 3) {
            return;
        }
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "KhieuKhich", (Object)(KhieuKhich.getPoint(player) + 1));
        SkillKnightConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!KhieuKhich.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (KhieuKhich.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void doIt(Player player) {
        if (KhieuKhich.getPoint(player) == 0) {
            return;
        }
        if (!ClassSetting.manaCost(player, 10)) {
            return;
        }
        if (KhieuKhich.isTriggered(player)) {
            player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a h\u1ed3i chi\u00eau xong!");
            return;
        }
        KhieuKhich.triggerPlayer(player);
        int radius = 5;
        if (KhieuKhich.getPoint(player) == 2) {
            radius = 7;
        } else if (KhieuKhich.getPoint(player) == 3) {
            radius = 10;
        }
        List<Entity> listEn = SettingMethod.getEntitiesByLocation(player.getLocation(), radius * radius);
        for (Entity e : listEn) {
            Location sub = player.getLocation().subtract(e.getLocation());
            e.setVelocity(sub.toVector().multiply(0.3f));
            if (!(e instanceof Monster)) continue;
            ((Monster)e).setTarget((LivingEntity)player);
        }
        player.sendMessage(SettingMethod.colorDecomplier("&aTh\u1eddi gian h\u1ed3i &6Khi\u00eau Kh\u00edch: &c5"));
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                KhieuKhich.remove(Player.this);
                Player.this.sendMessage(SettingMethod.colorDecomplier("&3H\u1ed3i chi\u00eau &6Khi\u00eau Kh\u00edch"));
            }
        }, 100);
    }

}

