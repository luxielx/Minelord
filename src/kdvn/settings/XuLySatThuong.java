/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.gmail.filoghost.holographicdisplays.api.Hologram
 *  com.gmail.filoghost.holographicdisplays.api.HologramsAPI
 *  com.gmail.filoghost.holographicdisplays.api.line.TextLine
 *  org.bukkit.Location
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 *  org.bukkit.util.Vector
 */
package kdvn.settings;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;
import kdvn.main.Main;
import kdvn.settings.SettingMethod;
import kdvn.tiemnang.NhanhNhen;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class XuLySatThuong {
    public static double xuLyDamager(double damage, Player player) {
        return damage;
    }

    public static boolean neDon(Player player, LivingEntity target) {
        if (XuLySatThuong.neDon(player)) {
            Location location = player.getLocation().clone().add(0.0, 1.1, 0.0).add(player.getLocation().getDirection().multiply(1.2f));
            Hologram holo = HologramsAPI.createHologram((Plugin)Main.plugin, (Location)location);
            holo.appendTextLine("\u00a76N\u00e9 \u0111\u00f2n");
            new BukkitRunnable(){
                int i;

                public void run() {
                    ++this.i;
                    if (this.i >= 5) {
                        this.cancel();
                        return;
                    }
                    if (!Hologram.this.isDeleted()) {
                        Hologram.this.teleport(Hologram.this.getLocation().add(0.0, 0.10000000149011612, 0.0));
                    }
                }
            }.runTaskTimerAsynchronously((Plugin)Main.plugin, 0, 1);
            new BukkitRunnable(){

                public void run() {
                    if (!Hologram.this.isDeleted()) {
                        Hologram.this.delete();
                    }
                }
            }.runTaskLater((Plugin)Main.plugin, 7);
            return true;
        }
        return false;
    }

    private static boolean neDon(Player player) {
        float tiLe = NhanhNhen.getNeDon(player);
        if (tiLe > 50.0f) {
            tiLe = 50.0f;
        }
        if (SettingMethod.tiLe(tiLe)) {
            return true;
        }
        return false;
    }

}

