/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.alessiodp.parties.Parties
 *  com.alessiodp.parties.handlers.PlayerHandler
 *  com.alessiodp.parties.objects.Party
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 */
package kdvn.skill.archer;

import com.alessiodp.parties.Parties;
import com.alessiodp.parties.handlers.PlayerHandler;
import com.alessiodp.parties.objects.Party;
import java.util.ArrayList;
import java.util.List;
import kdvn.classes.ClassSetting;
import kdvn.config.SkillArcherConfig;
import kdvn.main.Main;
import kdvn.settings.Particle;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class HoiMau {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    public static final int TIME_DELAY = 15;
    public static final int MANA_COST = 20;
    private static int[] combo = new int[3];
    public static final String NAME = "HoiMau";
    private static final int LEVEL = 150;

    public static int getMinLevelOfSkill(Player player) {
        return 150;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = HoiMau.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 150 + 50 * point;
    }

    public static int[] getCombo() {
        HoiMau.combo[0] = 0;
        HoiMau.combo[1] = 0;
        HoiMau.combo[2] = 0;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillArcherConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "HoiMau");
    }

    public static void add1Point(Player player) {
        if (HoiMau.getPoint(player) == 3) {
            return;
        }
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "HoiMau", (Object)(HoiMau.getPoint(player) + 1));
        SkillArcherConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!HoiMau.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (HoiMau.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void hoiMau(Player player, double health) {
        if (player.getHealth() + health > player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
        } else {
            player.setHealth(player.getHealth() + health);
        }
    }

    public static void doIt(Player player) {
        if (HoiMau.getPoint(player) == 0) {
            return;
        }
        if (HoiMau.isTriggered(player)) {
            player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a h\u1ed3i chi\u00eau xong");
            return;
        }
        if (!ClassSetting.manaCost(player, 20)) {
            return;
        }
        HoiMau.triggerPlayer(player);
        player.sendMessage("\u00a73Th\u1eddi gian h\u1ed3i \u00a7aH\u1ed3i M\u00e1u: \u00a7c15");
        double health = 20.0;
        if (HoiMau.getPoint(player) == 2) {
            health = 30.0;
        } else if (HoiMau.getPoint(player) == 3) {
            health = 40.0;
        }
        double radius = health;
        HoiMau.hoiMau(player, health);
        player.sendMessage("\u00a7aB\u1ea1n \u0111\u01b0\u1ee3c h\u1ed3i \u00a7c" + health + "\u00a7a m\u00e1u t\u1eeb " + player.getName());
        try {
            Party p = Parties.getInstance().getPlayerHandler().getPartyFromPlayer(player);
            ArrayList memList = p.getOnlinePlayers();
            for (Player gamer : memList) {
                if (gamer.equals((Object)player) || player.getLocation().distance(gamer.getLocation()) > radius) continue;
                HoiMau.hoiMau(gamer, health);
                gamer.sendMessage("\u00a7aB\u1ea1n \u0111\u01b0\u1ee3c h\u1ed3i \u00a7c" + health + "\u00a7a m\u00e1u t\u1eeb " + player.getName());
                int i = 0;
                while (i < 10) {
                    Particle.sendParticle(EnumParticle.VILLAGER_HAPPY, gamer.getLocation(), 2.0f, 2.0f, 2.0f, 0.01f, 5);
                    ++i;
                }
            }
        }
        catch (NullPointerException p) {
            // empty catch block
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                HoiMau.remove(Player.this);
                Player.this.sendMessage("\u00a76H\u1ed3i chi\u00eau \u00a76H\u1ed3i M\u00e1u");
            }
        }, 300);
    }

}

