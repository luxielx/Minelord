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
 *  org.bukkit.Sound
 *  org.bukkit.World
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
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CungCap {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static List<Player> giamST = new ArrayList<Player>();
    public static final int TIME_DELAY = 30;
    public static final int MANA_COST = 30;
    private static int[] combo = new int[3];
    public static final String NAME = "CungCap";
    private static final int LEVEL = 200;

    public static int getMinLevelOfSkill(Player player) {
        return 200;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = CungCap.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 200 + 50 * point;
    }

    public static int[] getCombo() {
        CungCap.combo[0] = 0;
        CungCap.combo[1] = 0;
        CungCap.combo[2] = 1;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillArcherConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "CungCap");
    }

    public static void add1Point(Player player) {
        if (CungCap.getPoint(player) == 3) {
            return;
        }
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "CungCap", (Object)(CungCap.getPoint(player) + 1));
        SkillArcherConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!CungCap.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (CungCap.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void addGiamSatThuong(Player player) {
        if (!giamST.contains((Object)player)) {
            giamST.add(player);
        }
    }

    public static void removeGiamSatThuong(Player player) {
        if (giamST.contains((Object)player)) {
            giamST.remove((Object)player);
        }
    }

    public static boolean duocGiamSatThuong(Player player) {
        return giamST.contains((Object)player);
    }

    public static int giamST(Player causer, Player target) {
        if (CungCap.getPoint(causer) == 0) {
            return 0;
        }
        int time = 10;
        if (CungCap.getPoint(causer) == 2) {
            time = 20;
        } else if (CungCap.getPoint(causer) == 3) {
            time = 30;
        }
        CungCap.addGiamSatThuong(target);
        int i = 0;
        while (i < 10) {
            Particle.sendParticle(EnumParticle.CRIT, target.getLocation(), 0.7f, 0.7f, 0.7f, 0.01f, 5);
            ++i;
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                CungCap.removeGiamSatThuong(Player.this);
                Player.this.sendMessage("\u00a76H\u1ebft hi\u1ec7u \u1ee9ng t\u0103ng gi\u00e1p");
            }
        }, (long)(time * 20));
        return time;
    }

    public static void doIt(Player player) {
        if (CungCap.isTriggered(player)) {
            player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a h\u1ed3i chi\u00eau xong");
            return;
        }
        if (!ClassSetting.manaCost(player, 30)) {
            return;
        }
        if (CungCap.getPoint(player) == 0) {
            return;
        }
        CungCap.triggerPlayer(player);
        player.sendMessage("\u00a73Th\u1eddi gian h\u1ed3i \u00a78C\u1ee9ng C\u00e1p: \u00a7c30");
        double radius = 5.0;
        if (CungCap.getPoint(player) == 2) {
            radius = 10.0;
        } else if (CungCap.getPoint(player) == 3) {
            radius = 20.0;
        }
        int time = CungCap.giamST(player, player);
        player.sendMessage("\u00a7aB\u1ea1n \u0111\u01b0\u1ee3c gi\u1ea3m 50% s\u00e1t th\u01b0\u01a1ng t\u1eeb " + player.getName() + " trong \u00a7c" + time + "s");
        try {
            Party p = Parties.getInstance().getPlayerHandler().getPartyFromPlayer(player);
            ArrayList memList = p.getOnlinePlayers();
            for (Player gamer : memList) {
                if (gamer.equals((Object)player) || player.getLocation().distance(gamer.getLocation()) > radius || gamer == player) continue;
                CungCap.giamST(player, gamer);
                player.sendMessage("\u00a7aB\u1ea1n \u0111\u01b0\u1ee3c gi\u1ea3m 50% s\u00e1t th\u01b0\u01a1ng t\u1eeb " + player.getName() + " trong \u00a7c" + time + "s");
                player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1.0f, 1.0f);
                int i = 0;
                while (i < 10) {
                    Particle.sendParticle(EnumParticle.CRIT, gamer.getLocation(), 1.0f, 1.0f, 1.0f, 0.01f, 5);
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
                CungCap.remove(Player.this);
            }
        }, 600);
    }

}

