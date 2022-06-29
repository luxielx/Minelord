/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package kdvn.minelord.player;

import kdvn.classes.Archer;
import kdvn.classes.ClassSetting;
import kdvn.classes.Knight;
import kdvn.classes.Mage;
import kdvn.magicwand.MagicWand;
import kdvn.minelord.dosat.DoSat;
import kdvn.minelord.player.DiemTiemNang;
import kdvn.minelord.player.EnumClassMineLord;
import kdvn.noiluc.NoiLuc;
import kdvn.phepthuat.NangLuong;
import kdvn.safezone.SafeZone;
import kdvn.tiemnang.DamageAll;
import kdvn.tiemnang.NhanhNhen;
import org.bukkit.entity.Player;

public class MineLordPlayer {
    private Player player;

    public MineLordPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setClass(String className) {
        if (className.equals(EnumClassMineLord.ARCHER.toString())) {
            Archer.chonClass(this.player);
        } else if (className.equals(EnumClassMineLord.KNIGHT.toString())) {
            Knight.chonClass(this.player);
        } else if (className.equals(EnumClassMineLord.MAGE.toString())) {
            Mage.chonClass(this.player);
        }
    }

    public String getClassMineLord() {
        return ClassSetting.getClass(this.player);
    }

    public DiemTiemNang getTiemNangManager() {
        return new DiemTiemNang(this.player);
    }

    public double getAddedDamage() {
        return DamageAll.getDamage(this.player);
    }

    public double getBowAttackSpeed() {
        return NhanhNhen.getTocDoBan(this.player);
    }

    public double getMagicWandAttackSpeed() {
        return MagicWand.getTocDoDanh(this.player);
    }

    public int getMaxNangLuong() {
        return NangLuong.getMaxNangLuong(this.player);
    }

    public double getTiLeNeDon() {
        return NhanhNhen.getNeDon(this.player);
    }

    public int getNoiLuc() {
        return NoiLuc.getNoiLuc(this.player);
    }

    public int getMaxNoiLuc() {
        return NoiLuc.getMaxNoiLuc(this.player);
    }

    public boolean canAttack(Player target) {
        if (SafeZone.isAllowedtoPVP(target)) {
            return true;
        }
        if (DoSat.canAttack(this.player, target)) {
            return true;
        }
        return false;
    }

    public int getNangLuong() {
        return NangLuong.getNangLuong(this.player);
    }
}

