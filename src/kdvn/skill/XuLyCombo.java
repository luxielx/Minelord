/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.connorlinfoot.titleapi.TitleAPI
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.entity.Player
 */
package kdvn.skill;

import com.connorlinfoot.titleapi.TitleAPI;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kdvn.classes.ClassSetting;
import kdvn.settings.SettingMethod;
import kdvn.skill.archer.BangKich;
import kdvn.skill.archer.CungCap;
import kdvn.skill.archer.CuongSat;
import kdvn.skill.archer.HoaKich;
import kdvn.skill.archer.HoiMau;
import kdvn.skill.archer.MuaTen;
import kdvn.skill.archer.NguoiBaoVe;
import kdvn.skill.knight.AnhDung;
import kdvn.skill.knight.HoaThan;
import kdvn.skill.knight.KhacMau;
import kdvn.skill.knight.KhieuKhich;
import kdvn.skill.knight.TraMau;
import kdvn.skill.knight.TrungPhat;
import kdvn.skill.mage.BaoTuyet;
import kdvn.skill.mage.BocPhaThien;
import kdvn.skill.mage.KhienNangLuong;
import kdvn.skill.mage.MuaSaoBang;
import kdvn.skill.mage.QuanDoanDiaNguc;
import kdvn.skill.mage.VongTronNangLuong;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class XuLyCombo {
    public static HashMap<Player, List<Integer>> combo = new HashMap();

    public static int getTimeOfClick(Player player) {
        if (!combo.containsKey((Object)player)) {
            return 0;
        }
        return combo.get((Object)player).size();
    }

    public static void addOne(Player player, int i) {
        if (combo.containsKey((Object)player)) {
            List<Integer> list = combo.get((Object)player);
            list.add(i);
            combo.put(player, list);
        } else {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(i);
            combo.put(player, list);
        }
    }

    public static void finishCombo(Player player) {
        combo.remove((Object)player);
        TitleAPI.sendSubtitle((Player)player, (Integer)0, (Integer)20, (Integer)0, (String)SettingMethod.colorDecomplier(" "));
    }

    public static boolean balancingCombo(Player player, int[] i) {
        if (!combo.containsKey((Object)player)) {
            return false;
        }
        if (combo.get((Object)player).size() != i.length) {
            return false;
        }
        boolean check = true;
        int i2 = 0;
        while (i2 < combo.get((Object)player).size()) {
            if (i[i2] != combo.get((Object)player).get(i2)) {
                check = false;
                break;
            }
            ++i2;
        }
        return check;
    }

    public static void sendTitleCombo(Player player) {
        ArrayList<String> comboList = new ArrayList<String>();
        Iterator<Integer> iterator = combo.get((Object)player).iterator();
        while (iterator.hasNext()) {
            int i = iterator.next();
            if (i == 0) {
                comboList.add("&6TR\u00c1I" + (Object)ChatColor.GREEN);
                continue;
            }
            comboList.add("&6PH\u1ea2I" + (Object)ChatColor.GREEN);
        }
        String mess = "";
        for (String s : comboList) {
            mess = String.valueOf(mess) + " - " + s;
        }
        mess = String.valueOf(mess) + " - ";
        mess = "&a" + mess;
        TitleAPI.sendFullTitle((Player)player, (Integer)0, (Integer)20, (Integer)0, (String)"", (String)SettingMethod.colorDecomplier(mess));
    }

    public static void doIt(Player player) {
        XuLyCombo.comboArcher(player);
        XuLyCombo.comboKnight(player);
        XuLyCombo.comboMage(player);
    }

    public static void comboArcher(Player player) {
        if (!ClassSetting.getClass(player).equals("archer")) {
            return;
        }
        if (XuLyCombo.balancingCombo(player, HoaKich.getCombo())) {
            if (!HoaKich.isTriggered(player)) {
                HoaKich.fire(player);
                XuLyCombo.finishCombo(player);
                System.out.println("Hoa Kich - " + player.getName());
            }
        } else if (XuLyCombo.balancingCombo(player, BangKich.getCombo())) {
            if (!BangKich.isTriggered(player)) {
                BangKich.fire(player);
                XuLyCombo.finishCombo(player);
                System.out.println("Bang Kich - " + player.getName());
            }
        } else if (XuLyCombo.balancingCombo(player, HoiMau.getCombo())) {
            if (!HoiMau.isTriggered(player)) {
                HoiMau.doIt(player);
                XuLyCombo.finishCombo(player);
                System.out.println("Hoi Mau - " + player.getName());
            }
        } else if (XuLyCombo.balancingCombo(player, CungCap.getCombo())) {
            if (!CungCap.isTriggered(player)) {
                CungCap.doIt(player);
                XuLyCombo.finishCombo(player);
                System.out.println("Cung Cap - " + player.getName());
            }
        } else if (XuLyCombo.balancingCombo(player, CuongSat.getCombo())) {
            if (!CuongSat.isTriggered(player)) {
                CuongSat.doIt(player);
                XuLyCombo.finishCombo(player);
            }
        } else if (XuLyCombo.balancingCombo(player, MuaTen.getCombo())) {
            if (!MuaTen.isTriggered(player)) {
                MuaTen.doIt(player);
                XuLyCombo.finishCombo(player);
                System.out.println("Mua Ten - " + player.getName());
            }
        } else if (XuLyCombo.balancingCombo(player, NguoiBaoVe.getCombo()) && !NguoiBaoVe.isTriggered(player)) {
            NguoiBaoVe.doIt(player);
            XuLyCombo.finishCombo(player);
            System.out.println("Nguoi Bao Ve - " + player.getName());
        }
    }

    public static void comboKnight(Player player) {
        if (!ClassSetting.getClass(player).equals("knight")) {
            return;
        }
        if (XuLyCombo.balancingCombo(player, HoaThan.getCombo())) {
            HoaThan.changeTriggered(player);
            System.out.println("Hoa Than - " + player.getName());
        } else if (XuLyCombo.balancingCombo(player, KhieuKhich.getCombo())) {
            KhieuKhich.doIt(player);
            System.out.println("Khieu Khich - " + player.getName());
        } else if (XuLyCombo.balancingCombo(player, KhacMau.getCombo())) {
            KhacMau.changeTriggered(player);
            System.out.println("Khac Mau - " + player.getName());
        } else if (XuLyCombo.balancingCombo(player, TraMau.getCombo())) {
            TraMau.changeTriggered(player);
            System.out.println("Tra Mau - " + player.getName());
        } else if (XuLyCombo.balancingCombo(player, AnhDung.getCombo())) {
            AnhDung.doIt(player);
            System.out.println("Anh Dung - " + player.getName());
        } else if (XuLyCombo.balancingCombo(player, TrungPhat.getCombo())) {
            TrungPhat.doIt(player);
            System.out.println("Trung Phat - " + player.getName());
        }
    }

    public static void comboMage(Player player) {
        if (!ClassSetting.getClass(player).equals("mage")) {
            return;
        }
        if (XuLyCombo.balancingCombo(player, VongTronNangLuong.getCombo())) {
            VongTronNangLuong.changeTriggered(player);
            System.out.println("Vong tron nang luong - " + player.getName());
        } else if (XuLyCombo.balancingCombo(player, KhienNangLuong.getCombo())) {
            KhienNangLuong.changeTriggered(player);
            System.out.println("Khien Nang Luong - " + player.getName());
        } else if (XuLyCombo.balancingCombo(player, BocPhaThien.getCombo())) {
            BocPhaThien.doIt(player);
            System.out.println("Boc Pha Thien - " + player.getName());
        } else if (XuLyCombo.balancingCombo(player, MuaSaoBang.getCombo())) {
            MuaSaoBang.doIt(player);
            System.out.println("Mua Sao Bang - " + player.getName());
        } else if (XuLyCombo.balancingCombo(player, BaoTuyet.getCombo())) {
            BaoTuyet.fire(player);
            System.out.println("Bao Tuyet - " + player.getName());
        } else if (XuLyCombo.balancingCombo(player, QuanDoanDiaNguc.getCombo())) {
            QuanDoanDiaNguc.doIt(player);
            System.out.println("Quan Doan Dia Nguc - " + player.getName());
        }
    }
}

