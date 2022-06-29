/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 */
package kdvn.runnable;

import kdvn.skill.knight.HoaThan;
import kdvn.skill.mage.KhienNangLuong;
import kdvn.skill.mage.VongTronNangLuong;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SkillRunnable
implements Runnable {
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (HoaThan.isTriggered(player)) {
                HoaThan.doIt(player);
            }
            if (VongTronNangLuong.isTriggered(player)) {
                VongTronNangLuong.doIt(player);
            }
            if (!KhienNangLuong.isTriggered(player)) continue;
            KhienNangLuong.vongTron(player);
        }
    }
}

