/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 */
package kdvn.runnable;

import kdvn.noiluc.NoiLuc;
import kdvn.phepthuat.NangLuong;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TangPowerRunnable
implements Runnable {
    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (NoiLuc.getNoiLuc(player) < NoiLuc.getMaxNoiLuc(player)) {
                NoiLuc.setNoiLuc(player, NoiLuc.getNoiLuc(player) + 1);
            }
            if (NangLuong.getNangLuong(player) >= NangLuong.getMaxNangLuong(player)) continue;
            NangLuong.setNangLuong(player, NangLuong.getNangLuong(player) + 5);
        }
    }
}

