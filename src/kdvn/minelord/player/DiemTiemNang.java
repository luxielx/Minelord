/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package kdvn.minelord.player;

import kdvn.tiemnang.NhanhNhen;
import kdvn.tiemnang.PhepThuat;
import kdvn.tiemnang.SucManh;
import kdvn.tiemnang.TheLuc;
import kdvn.tiemnang.TongDiem;
import org.bukkit.entity.Player;

public class DiemTiemNang {
    private Player player;
    private int sucManh;
    private int nangLuong;
    private int theLuc;
    private int nhanhNhen;
    private int tongDiem;
    private int conLai;

    public DiemTiemNang(Player player) {
        this.player = player;
        this.sucManh = SucManh.getPoint(player);
        this.nangLuong = PhepThuat.getPoint(player);
        this.theLuc = TheLuc.getPoint(player);
        this.nhanhNhen = NhanhNhen.getPoint(player);
        this.tongDiem = TongDiem.getPoint(player);
        this.conLai = TongDiem.getRemain(player);
    }

    public int getTongDiem() {
        return this.tongDiem;
    }

    public void setTongDiem(int tongDiem) {
        this.tongDiem = tongDiem;
        TongDiem.setPoint(this.player, tongDiem);
    }

    public int getSucManh() {
        return this.sucManh;
    }

    public void setSucManh(int sucManh) {
        this.sucManh = sucManh;
        SucManh.setPoint(this.player, sucManh);
    }

    public int getNangLuong() {
        return this.nangLuong;
    }

    public void setNangLuong(int nangLuong) {
        this.nangLuong = nangLuong;
        PhepThuat.setPoint(this.player, nangLuong);
    }

    public int getTheLuc() {
        return this.theLuc;
    }

    public void setTheLuc(int theLuc) {
        this.theLuc = theLuc;
        TheLuc.setPoint(this.player, theLuc);
    }

    public int getNhanhNhen() {
        return this.nhanhNhen;
    }

    public void setNhanhNhen(int nhanhNhen) {
        this.nhanhNhen = nhanhNhen;
        NhanhNhen.setPoint(this.player, nhanhNhen);
    }

    public int getConLai() {
        return this.conLai;
    }
}

