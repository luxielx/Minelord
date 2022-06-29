/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.util.Vector
 */
package kdvn.skill.knight;

import java.util.ArrayList;
import java.util.List;
import kdvn.config.SkillKnightConfig;
import kdvn.main.Main;
import kdvn.settings.Particle;
import kdvn.settings.SettingMethod;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class BungNo {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    public static final String NAME = "BungNo";
    public static final int TIME_DELAY = 5;
    private static final int LEVEL = 200;

    public static int getMinLevelOfSkill(Player player) {
        return 200;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = BungNo.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 200 + 50 * point;
    }

    public static int getPoint(Player player) {
        return SkillKnightConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "BungNo");
    }

    public static void add1Point(Player player) {
        if (BungNo.getPoint(player) == 3) {
            return;
        }
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "BungNo", (Object)(BungNo.getPoint(player) + 1));
        SkillKnightConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!BungNo.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (BungNo.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void doIt(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player)e.getEntity();
            if (BungNo.getPoint(player) == 0) {
                return;
            }
            if (BungNo.isTriggered(player)) {
                return;
            }
            if (player.getHealth() > player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.20000000298023224) {
                return;
            }
            BungNo.triggerPlayer(player);
            double damage = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.05000000074505806;
            if (BungNo.getPoint(player) == 2) {
                damage = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.07000000029802322;
            } else if (BungNo.getPoint(player) == 3) {
                damage = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * 0.10000000149011612;
            }
            Particle.sendParticle(EnumParticle.EXPLOSION_LARGE, player.getLocation().add(0.0, 1.0, 0.0), 0.1f, 0.1f, 0.1f, 0.01f, 3);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
            List<Entity> listEn = SettingMethod.getEntitiesByLocation(player.getLocation(), 25.0f);
            for (Entity en : listEn) {
                if (en == player) continue;
                Location sub = player.getLocation().subtract(en.getLocation());
                en.setVelocity(sub.multiply(-1.0).add(0.0, 1.0, 0.0).toVector());
                if (!(en instanceof LivingEntity)) continue;
                e.setDamage(e.getDamage() + damage);
            }
            player.sendMessage(SettingMethod.colorDecomplier("&aTh\u1eddi gian h\u1ed3i &6B\u1ee7ng N\u1ed5: &c5"));
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

                @Override
                public void run() {
                    BungNo.remove(Player.this);
                    Player.this.sendMessage(SettingMethod.colorDecomplier("&3H\u1ed3i chi\u00eau &6B\u00f9ng N\u1ed5"));
                }
            }, 100);
        }
    }

}

