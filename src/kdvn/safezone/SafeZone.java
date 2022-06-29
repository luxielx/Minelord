/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.sk89q.worldguard.protection.ApplicableRegionSet
 *  com.sk89q.worldguard.protection.flags.DefaultFlag
 *  com.sk89q.worldguard.protection.flags.StateFlag
 *  com.sk89q.worldguard.protection.managers.RegionManager
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 */
package kdvn.safezone;

import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import kdvn.main.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SafeZone {
    public static boolean inSafeZone(Player player) {
        RegionManager rm = Main.getWorldGuardPlugin().getRegionManager(player.getWorld());
        ApplicableRegionSet ar = rm.getApplicableRegions(player.getLocation());
        return !ar.allows(DefaultFlag.PVP);
    }

    public static boolean isAllowedtoPVP(Player player) {
        RegionManager rm = Main.getWorldGuardPlugin().getRegionManager(player.getWorld());
        ApplicableRegionSet ar = rm.getApplicableRegions(player.getLocation());
        return ar.allows(DefaultFlag.PVP);
    }
}

