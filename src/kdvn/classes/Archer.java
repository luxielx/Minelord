/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.connorlinfoot.titleapi.TitleAPI
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 */
package kdvn.classes;

import com.connorlinfoot.titleapi.TitleAPI;
import java.util.ArrayList;
import java.util.List;
import kdvn.classes.ClassSetting;
import kdvn.config.ClassConfig;
import kdvn.main.Main;
import kdvn.noiluc.NoiLuc;
import kdvn.settings.SettingMethod;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Archer {
    public static final String BASIC_NAME = "archer";
    public static final String PREFIX = SettingMethod.colorDecomplier("&aARCHER");
    public static final String ITEM_LORE = SettingMethod.colorDecomplier("&fV\u0169 kh\u00ed c\u1ee7a &a" + "archer".toUpperCase());

    public static List<String> getThongTin() {
        ArrayList<String> thongTin = new ArrayList<String>();
        thongTin.add("\u00a7aV\u0169 kh\u00ed: \u00a7cCung, Dao");
        thongTin.add("\u00a7aTh\u00f4ng tin:");
        thongTin.add(String.valueOf(ClassSetting.chiSoTren10(5)) + " \u00a76S\u00e1t th\u01b0\u01a1ng");
        thongTin.add(String.valueOf(ClassSetting.chiSoTren10(8)) + " \u00a76Nhanh nh\u1eb9n");
        thongTin.add(String.valueOf(ClassSetting.chiSoTren10(5)) + " \u00a76Ph\u00e9p thu\u1eadt");
        thongTin.add(String.valueOf(ClassSetting.chiSoTren10(2)) + " \u00a76Ph\u00f2ng th\u1ee7");
        thongTin.add(" ");
        thongTin.add("\u00a7cCh\u1ecdn Class vui l\u00f2ng th\u00e1o h\u1ebft trang b\u1ecb ra!");
        return thongTin;
    }

    public static void chonClass(Player player) {
        ClassSetting.setDefault(player);
        TitleAPI.sendTitle((Player)player, (Integer)20, (Integer)60, (Integer)20, (String)SettingMethod.colorDecomplier("&a&l" + "archer".toUpperCase()), (String)"");
        ClassConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName(), (Object)"archer");
        ClassConfig.saveConfig();
        NoiLuc.setMaxNoiLuc(player, 10);
    }
}

