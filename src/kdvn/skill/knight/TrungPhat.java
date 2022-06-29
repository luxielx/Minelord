/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LightningStrike
 *  org.bukkit.entity.LivingEntity
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
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class TrungPhat {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static int[] combo = new int[4];
    public static final String NAME = "TrungPhat";
    public static final int MANA_COST = 30;
    public static final int TIME_DELAY = 30;
    private static final int LEVEL = 500;

    public static int getMinLevelOfSkill(Player player) {
        return 500;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = TrungPhat.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 500 + 50 * point;
    }

    public static int[] getCombo() {
        TrungPhat.combo[0] = 1;
        TrungPhat.combo[1] = 1;
        TrungPhat.combo[2] = 0;
        TrungPhat.combo[3] = 0;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillKnightConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "TrungPhat");
    }

    public static void add1Point(Player player) {
        if (TrungPhat.getPoint(player) == 3) {
            return;
        }
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "TrungPhat", (Object)(TrungPhat.getPoint(player) + 1));
        SkillKnightConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!TrungPhat.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (TrungPhat.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    private static void satThuongChuan(LivingEntity entity, double damage, Player player) {
        double health = entity.getHealth();
        entity.damage(0.0, (Entity)player);
        if (health <= damage) {
            entity.setHealth(1.0);
            return;
        }
        entity.setHealth(health - damage);
    }

    public static void doIt(Player player) {
        if (!ClassSetting.getClass(player).equals("knight")) {
            return;
        }
        if (TrungPhat.getPoint(player) == 0) {
            return;
        }
        if (TrungPhat.isTriggered(player)) {
            return;
        }
        if (!ClassSetting.manaCost(player, 30)) {
            return;
        }
        Location location = player.getLocation().clone().add(player.getLocation().getDirection().multiply(5.0f));
        int i = 0;
        while (i < 4) {
            player.getWorld().strikeLightning(location);
            ++i;
        }
        TrungPhat.triggerPlayer(player);
        double damage = 0.05000000074505806;
        if (TrungPhat.getPoint(player) == 2) {
            damage = 0.10000000149011612;
        } else if (TrungPhat.getPoint(player) == 3) {
            damage = 0.20000000298023224;
        }
        player.sendMessage("\u00a76Th\u1eddi gian h\u1ed3i chi\u00eau \u00a7fTr\u1eebng Ph\u1ea1t: \u00a7c30");
        List<Entity> listE = SettingMethod.getEntitiesByLocation(location, 16.0f);
        for (Entity e : listE) {
            if (!(e instanceof LivingEntity) || e == player) continue;
            LivingEntity ee = (LivingEntity)e;
            double maxHealth = ee.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
            TrungPhat.satThuongChuan((LivingEntity)e, maxHealth * damage, player);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                TrungPhat.remove(Player.this);
                Player.this.sendMessage(SettingMethod.colorDecomplier("&3H\u1ed3i chi\u00eau \u00a7fTr\u1eebng Ph\u1ea1t"));
            }
        }, 600);
    }

}

