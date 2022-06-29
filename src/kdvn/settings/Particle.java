/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_11_R1.EntityPlayer
 *  net.minecraft.server.v1_11_R1.EnumParticle
 *  net.minecraft.server.v1_11_R1.Packet
 *  net.minecraft.server.v1_11_R1.PacketPlayOutWorldParticles
 *  net.minecraft.server.v1_11_R1.PlayerConnection
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer
 *  org.bukkit.entity.Player
 */
package kdvn.settings;

import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.EnumParticle;
import net.minecraft.server.v1_11_R1.Packet;
import net.minecraft.server.v1_11_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_11_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Particle {
    public static void sendParticle(EnumParticle type, Location loc, float xOffset, float yOffset, float zOffset, float speed, int count) {
        float x = (float)loc.getX();
        float y = (float)loc.getY();
        float z = (float)loc.getZ();
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type, true, x, y, z, xOffset, yOffset, zOffset, speed, count, null);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
        }
    }

    public static void sendParticle(EnumParticle type, double x, double y, double z, float xOffset, float yOffset, float zOffset, float speed, int count) {
        float xf = (float)x;
        float yf = (float)y;
        float zf = (float)y;
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type, true, xf, yf, zf, xOffset, yOffset, zOffset, speed, count, null);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
        }
    }

    public static void sendParticleTo(EnumParticle type, Player p, Location loc, float xOffset, float yOffset, float zOffset, float speed, int count) {
        float x = (float)loc.getX();
        float y = (float)loc.getY();
        float z = (float)loc.getZ();
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type, true, x, y, z, xOffset, yOffset, zOffset, speed, count, null);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
    }

    public static void sendParticleTo(EnumParticle type, Player p, double x, double y, double z, float xOffset, float yOffset, float zOffset, float speed, int count) {
        float xf = (float)x;
        float yf = (float)y;
        float zf = (float)y;
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(type, true, xf, yf, zf, xOffset, yOffset, zOffset, speed, count, null);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
    }
}

