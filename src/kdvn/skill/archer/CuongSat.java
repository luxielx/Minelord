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
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CuongSat {
    private static List<Player> triggeredPlayer = new ArrayList<Player>();
    private static List<Player> tangST = new ArrayList<Player>();
    public static final int TIME_DELAY = 30;
    public static final int MANA_COST = 20;
    private static int[] combo = new int[4];
    public static final String NAME = "CuongSat";
    private static final int LEVEL = 500;

    public static int getMinLevelOfSkill(Player player) {
        return 500;
    }

    public static int getNexLevelRequirement(Player player) {
        int point = CuongSat.getPoint(player);
        if (point == 3) {
            return 10000;
        }
        return 500 + 50 * point;
    }

    public static int[] getCombo() {
        CuongSat.combo[0] = 0;
        CuongSat.combo[1] = 0;
        CuongSat.combo[2] = 1;
        CuongSat.combo[3] = 1;
        return combo;
    }

    public static int getPoint(Player player) {
        return SkillArcherConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + "." + "CuongSat");
    }

    public static void add1Point(Player player) {
        if (CuongSat.getPoint(player) == 3) {
            return;
        }
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName() + "." + "CuongSat", (Object)(CuongSat.getPoint(player) + 1));
        SkillArcherConfig.saveConfig();
    }

    public static boolean isTriggered(Player player) {
        if (triggeredPlayer.contains((Object)player)) {
            return true;
        }
        return false;
    }

    public static void triggerPlayer(Player player) {
        if (!CuongSat.isTriggered(player)) {
            triggeredPlayer.add(player);
        }
    }

    public static void remove(Player player) {
        if (CuongSat.isTriggered(player)) {
            triggeredPlayer.remove((Object)player);
        }
    }

    public static void addTangSatThuong(Player player) {
        if (!tangST.contains((Object)player)) {
            tangST.add(player);
        }
    }

    public static void removeTangSatThuong(Player player) {
        if (tangST.contains((Object)player)) {
            tangST.remove((Object)player);
        }
    }

    public static boolean duocTangSatThuong(Player player) {
        return tangST.contains((Object)player);
    }

    public static int tangST(Player causer, Player target) {
        if (CuongSat.getPoint(causer) == 0) {
            return 0;
        }
        int time = 10;
        if (CuongSat.getPoint(causer) == 2) {
            time = 20;
        } else if (CuongSat.getPoint(causer) == 3) {
            time = 30;
        }
        CuongSat.addTangSatThuong(target);
        int i = 0;
        while (i < 10) {
            Particle.sendParticle(EnumParticle.CRIT, target.getLocation(), 0.7f, 0.7f, 0.7f, 0.01f, 5);
            ++i;
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.plugin, new Runnable(){

            @Override
            public void run() {
                CuongSat.removeTangSatThuong(Player.this);
                Player.this.sendMessage("\u00a76H\u1ebft hi\u1ec7u \u1ee9ng t\u0103ng s\u00e1t th\u01b0\u01a1ng");
            }
        }, (long)(time * 20));
        return time;
    }

    public static void doIt(Player player) {
        if (CuongSat.getPoint(player) == 0) {
            return;
        }
        if (!ClassSetting.manaCost(player, 20)) {
            return;
        }
        if (CuongSat.isTriggered(player)) {
            player.sendMessage("\u00a7cB\u1ea1n ch\u01b0a h\u1ed3i chi\u00eau xong");
            return;
        }
        CuongSat.triggerPlayer(player);
        player.sendMessage("\u00a73Th\u1eddi gian h\u1ed3i \u00a7cCu\u1ed3ng S\u00e1t: \u00a7630");
        double radius = 5.0;
        if (CuongSat.getPoint(player) == 2) {
            radius = 10.0;
        } else if (CuongSat.getPoint(player) == 3) {
            radius = 20.0;
        }
        int time = CuongSat.tangST(player, player);
        player.sendMessage("\u00a7aB\u1ea1n \u0111\u01b0\u1ee3c t\u0103ng 100% s\u00e1t th\u01b0\u01a1ng ti\u1ec1m n\u0103ng t\u1eeb " + player.getName() + " trong \u00a7c" + time + "s");
        try {
            Party p = Parties.getInstance().getPlayerHandler().getPartyFromPlayer(player);
            ArrayList memList = p.getOnlinePlayers();
            for (Player gamer : memList) {
                if (gamer.equals((Object)player) || player.getLocation().distance(gamer.getLocation()) > radius || player == gamer) continue;
                CuongSat.tangST(player, gamer);
                player.sendMessage("\u00a7aB\u1ea1n \u0111\u01b0\u1ee3c t\u0103ng 100% s\u00e1t th\u01b0\u01a1ng ti\u1ec1m n\u0103ng t\u1eeb " + player.getName() + " trong \u00a7c" + time + "s");
                int i = 0;
                while (i < 10) {
                    Particle.sendParticle(EnumParticle.SWEEP_ATTACK, gamer.getLocation(), 1.0f, 1.0f, 1.0f, 0.01f, 5);
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
                CuongSat.remove(Player.this);
            }
        }, 600);
    }

}

