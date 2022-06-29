/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EntityPlayer
 *  net.minecraft.server.v1_11_R1.IChatBaseComponent
 *  net.minecraft.server.v1_11_R1.IChatBaseComponent$ChatSerializer
 *  net.minecraft.server.v1_11_R1.Packet
 *  net.minecraft.server.v1_11_R1.PacketPlayOutChat
 *  net.minecraft.server.v1_11_R1.PlayerConnection
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer
 *  org.bukkit.entity.Player
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 */
package kdvn.minelord.dosat;

import kdvn.minelord.dosat.DoSat;
import kdvn.tele.Warps;
import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.Packet;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import net.minecraft.server.v1_11_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DoSatRunnable
extends BukkitRunnable {
    public void sendActionbar(Player player, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a((String)("{\"text\":\"" + message + "\"}"));
        PacketPlayOutChat packet = new PacketPlayOutChat(icbc, 2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)packet);
    }

    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PotionEffect p1;
            PotionEffect p3;
            if (!DoSat.isWanted(player)) continue;
            this.sendActionbar(player, "\u00a72Th\u1eddi gian b\u1ecb truy \u0111u\u1ed5i: \u00a7f" + DoSat.getTimeRemaining(player));
            if (!DoSat.isTurnedOn(player)) {
                DoSat.addTime(player, -1);
            } else if (Warps.getWarps().containsKey("PvP")) {
                if (player.getLocation().distance(Warps.getLocation("PvP")) >= 100.0) {
                    p1 = new PotionEffect(PotionEffectType.SLOW, 40, 4);
                    p3 = new PotionEffect(PotionEffectType.HUNGER, 40, 4);
                    player.addPotionEffect(p1);
                    player.addPotionEffect(p3);
                }
            } else {
                p1 = new PotionEffect(PotionEffectType.SLOW, 40, 4);
                p3 = new PotionEffect(PotionEffectType.HUNGER, 40, 4);
                player.addPotionEffect(p1);
                player.addPotionEffect(p3);
            }
            DoSat.addGlow(player);
            if (DoSat.getTimeRemaining(player) > 0) continue;
            DoSat.removeWanted(player);
            DoSat.removeGlow(player);
            this.sendActionbar(player, " ");
            player.sendMessage("\u00a7aB\u1ea1n \u0111\u00e3 h\u1ebft b\u1ecb truy n\u00e3!");
        }
    }
}

