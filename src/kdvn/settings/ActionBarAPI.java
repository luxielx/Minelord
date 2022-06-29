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
 *  org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer
 *  org.bukkit.entity.Player
 */
package kdvn.settings;

import net.minecraft.server.v1_11_R1.EntityPlayer;
import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.Packet;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import net.minecraft.server.v1_11_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBarAPI {
    public static void sendActionbar(Player player, String message) {
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a((String)("{\"text\":\"" + message + "\"}"));
        PacketPlayOutChat packet = new PacketPlayOutChat(icbc, 2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)packet);
    }
}

