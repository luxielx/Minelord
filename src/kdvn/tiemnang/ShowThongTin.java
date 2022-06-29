/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Player
 */
package kdvn.tiemnang;

import kdvn.minelord.player.DiemTiemNang;
import kdvn.minelord.player.EnumClassMineLord;
import kdvn.minelord.player.MineLordPlayer;
import kdvn.noiluc.NoiLuc;
import kdvn.settings.SettingMethod;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class ShowThongTin {
    public static void show(Player player) {
        MineLordPlayer mlPlayer = new MineLordPlayer(player);
        player.sendMessage("==============================");
        player.sendMessage((Object)ChatColor.GREEN + "      > " + player.getName() + " <      ");
        player.sendMessage((Object)ChatColor.YELLOW + "T\u1ed4NG \u0110I\u1ec2M: " + (Object)ChatColor.RED + mlPlayer.getTiemNangManager().getTongDiem());
        player.sendMessage((Object)ChatColor.YELLOW + "\u0110I\u1ec2M C\u00d2N L\u1ea0I: " + (Object)ChatColor.RED + mlPlayer.getTiemNangManager().getConLai());
        player.sendMessage((Object)ChatColor.YELLOW + "\u0110I\u1ec2M S\u1ee8C M\u1ea0NH: " + (Object)ChatColor.RED + mlPlayer.getTiemNangManager().getSucManh());
        player.sendMessage((Object)ChatColor.YELLOW + "\u0110I\u1ec2M TH\u1ec2 L\u1ef0C: " + (Object)ChatColor.RED + mlPlayer.getTiemNangManager().getTheLuc());
        player.sendMessage((Object)ChatColor.YELLOW + "\u0110I\u1ec2M PH\u00c9P THU\u1eacT: " + (Object)ChatColor.RED + mlPlayer.getTiemNangManager().getNangLuong());
        player.sendMessage((Object)ChatColor.YELLOW + "\u0110I\u1ec2M NHANH NH\u1eb8N: " + (Object)ChatColor.RED + mlPlayer.getTiemNangManager().getNhanhNhen());
        player.sendMessage("------------------------------");
        player.sendMessage((Object)ChatColor.BLUE + "S\u00c1T TH\u01af\u01a0NG: " + (Object)ChatColor.RED + SettingMethod.lamTron(mlPlayer.getAddedDamage()));
        player.sendMessage((Object)ChatColor.BLUE + "M\u00c1U T\u1ed0I \u0110A: " + (Object)ChatColor.RED + SettingMethod.lamTron(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()));
        player.sendMessage((Object)ChatColor.BLUE + "N\u1ed8I L\u1ef0C: \u00a7c" + NoiLuc.getMaxNoiLuc(player));
        player.sendMessage((Object)ChatColor.BLUE + "N\u0102NG L\u01af\u1ee2NG: " + (Object)ChatColor.RED + mlPlayer.getMaxNangLuong());
        player.sendMessage((Object)ChatColor.BLUE + "T\u1ed0C \u0110\u1ed8 CH\u1ea0Y: " + (Object)ChatColor.RED + SettingMethod.lamTron(player.getWalkSpeed()));
        player.sendMessage((Object)ChatColor.BLUE + "T\u1ec8 L\u1ec6 N\u00c9 \u0110\u00d2N: " + (Object)ChatColor.RED + SettingMethod.lamTron(mlPlayer.getTiLeNeDon()) + "%");
        if (mlPlayer.getClassMineLord().equals(EnumClassMineLord.ARCHER.toString())) {
            player.sendMessage((Object)ChatColor.BLUE + "T\u1ed0C \u0110\u1ed8 B\u1eaeN CUNG: " + (Object)ChatColor.RED + SettingMethod.lamTron(mlPlayer.getBowAttackSpeed()));
        } else if (mlPlayer.getClassMineLord().equals(EnumClassMineLord.MAGE.toString())) {
            player.sendMessage((Object)ChatColor.BLUE + "T\u1ed0C \u0110\u1ed8 B\u1eaeN PH\u00c9P: " + (Object)ChatColor.RED + SettingMethod.lamTron(mlPlayer.getMagicWandAttackSpeed()));
        }
        player.sendMessage("==============================");
    }
}

