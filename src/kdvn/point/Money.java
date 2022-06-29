/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.milkbowl.vault.economy.Economy
 *  net.milkbowl.vault.economy.EconomyResponse
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.entity.Player
 */
package kdvn.point;

import kdvn.main.Main;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Money {
    public static double getMoney(Player player) {
        Economy eco = Main.getEcononomy();
        return eco.getBalance((OfflinePlayer)player);
    }

    public static boolean moneyCost(Player player, double money) {
        Economy eco = Main.getEcononomy();
        double moneyOfPlayer = eco.getBalance((OfflinePlayer)player);
        if (moneyOfPlayer < money) {
            return false;
        }
        eco.withdrawPlayer((OfflinePlayer)player, money);
        return true;
    }

    public static void giveMoney(Player player, double money) {
        Economy eco = Main.getEcononomy();
        eco.depositPlayer((OfflinePlayer)player, money);
    }
}

