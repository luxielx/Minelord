/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Location
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 */
package kdvn.skill.knight;

import java.util.ArrayList;
import java.util.List;
import kdvn.config.SkillKnightConfig;
import kdvn.main.Main;
import kdvn.safezone.SafeZone;
import kdvn.settings.Particle;
import kdvn.settings.SettingMethod;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class HoaThan {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static int[] combo = new int[2];
    public static final String NAME = "HoaThan";
    private static final int LEVEL = 150;

    public static int getMinLevelOfSkill(Player player) {
        return 150;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = HoaThan.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 150 + 50 * point;
    }

    public static int[] getCombo() {
        HoaThan.combo[0] = 0;
        HoaThan.combo[1] = 0;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillKnightConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "HoaThan");
    }

    public static void add1Point(Player player) {
        if (HoaThan.getPoint(player) == 3) {
            return;
        }
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "HoaThan", (Object)(HoaThan.getPoint(player) + 1));
        SkillKnightConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!HoaThan.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (HoaThan.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void doIt(Player player) {
        if (HoaThan.getPoint(player) == 0) {
            return;
        }
        if (HoaThan.isTriggered(player)) {
            if (player.getHealth() <= player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.05000000074505806) {
                HoaThan.remove(player);
                player.sendMessage("\u00a76B\u1ea1n s\u1ebd ch\u1ebft n\u1ebfu d\u00f9ng Skill");
                player.sendMessage("\u00a7aT\u1eaft \u00a7cH\u1ecfa Th\u00e2n");
                return;
            }
            player.setHealth(player.getHealth() - player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.05000000074505806);
            int i = 0;
            while (i < 10) {
                Particle.sendParticle(EnumParticle.FLAME, player.getLocation(), 1.0f, 1.0f, 1.0f, 0.001f, 5);
                ++i;
            }
            List<Entity> entities = SettingMethod.getEntitiesByLocation(player.getLocation(), 16.0f);
            for (Entity e : entities) {
                Player p;
                if (e == player) continue;
                double damage = 0.004999999888241291;
                if (HoaThan.getPoint(player) == 2) {
                    damage = 0.007;
                } else if (HoaThan.getPoint(player) == 3) {
                    damage = 0.10000000149011612;
                }
                if (!(e instanceof LivingEntity)) continue;
                if (e instanceof Player && !SafeZone.isAllowedtoPVP(p = (Player)e)) {
                    return;
                }
                ((LivingEntity)e).damage(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * damage, (Entity)player);
                e.setFireTicks(5);
            }
        }
    }

    public static void changeTriggered(Player player) {
        if (HoaThan.getPoint(player) == 0) {
            return;
        }
        if (HoaThan.isTriggered(player)) {
            player.sendMessage("\u00a7aT\u1eaft \u00a7cH\u1ecfa Th\u00e2n");
            HoaThan.remove(player);
        } else {
            HoaThan.triggerPlayer(player);
            player.sendMessage("\u00a7cB\u1eadt \u00a7cH\u1ecfa Th\u00e2n");
        }
    }
}

