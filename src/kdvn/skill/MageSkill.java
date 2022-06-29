/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 */
package kdvn.skill;

import java.util.ArrayList;
import java.util.List;
import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import kdvn.skill.SkillSetting;
import kdvn.skill.mage.BaoTuyet;
import kdvn.skill.mage.BocPhaThien;
import kdvn.skill.mage.DanhCapNangLuong;
import kdvn.skill.mage.HoiSuc;
import kdvn.skill.mage.KhienNangLuong;
import kdvn.skill.mage.LoDien;
import kdvn.skill.mage.MuaSaoBang;
import kdvn.skill.mage.NoLe;
import kdvn.skill.mage.QuanDoanDiaNguc;
import kdvn.skill.mage.VongTronNangLuong;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class MageSkill {
    public static final String INV_TITLE = "\u00a72\u00a7l\u00a7nK\u1ef8 N\u0102NG MAGE";
    public static final int SLOT_LO_DIEN = 0;
    public static final int SLOT_NO_LE = 2;
    public static final int SLOT_KHIEN_NANG_LUONG = 4;
    public static final int SLOT_BAO_TUYET = 6;
    public static final int SLOT_BOC_PHA_THIEN = 8;
    public static final int SLOT_HOI_SUC = 18;
    public static final int SLOT_DANH_CAP_NANG_LUONG = 20;
    public static final int SLOT_VONG_TRON_NANG_LUONG = 22;
    public static final int SLOT_QUAN_DOAN_DIA_NGUC = 24;
    public static final int SLOT_MUA_SAO_BANG = 26;

    public static int getRemainingPoint(Player player) {
        int tongDiem = SkillMageConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + ".TongDiem");
        int result = tongDiem - (BaoTuyet.getPoint(player) + BocPhaThien.getPoint(player) + DanhCapNangLuong.getPoint(player) + HoiSuc.getPoint(player) + KhienNangLuong.getPoint(player) + LoDien.getPoint(player) + MuaSaoBang.getPoint(player) + NoLe.getPoint(player) + QuanDoanDiaNguc.getPoint(player) + VongTronNangLuong.getPoint(player));
        return result;
    }

    public static void showBangChonSkill(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, (int)27, (String)"\u00a72\u00a7l\u00a7nK\u1ef8 N\u0102NG MAGE");
        inv.setItem(6, MageSkill.getBaoTuyetSkill(player));
        inv.setItem(8, MageSkill.getBocPhaThienSkill(player));
        inv.setItem(20, MageSkill.getDanhCapNangLuongSkill(player));
        inv.setItem(0, MageSkill.getLoDienSkill(player));
        inv.setItem(26, MageSkill.getMuaSaoBangSkill(player));
        inv.setItem(2, MageSkill.getNoLeSkill(player));
        inv.setItem(18, MageSkill.getHoiSucSkill(player));
        inv.setItem(4, MageSkill.getKhienNangLuongSkill(player));
        inv.setItem(24, MageSkill.getQuanDoanDiaNgucSkill(player));
        inv.setItem(22, MageSkill.getVongTronNangLuongSkill(player));
        int i = 0;
        while (i < 27) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, SkillSetting.getBlackSlot(player));
            }
            ++i;
        }
        player.openInventory(inv);
    }

    private static ItemStack getBaoTuyetSkill(Player player) {
        int point = BaoTuyet.getPoint(player);
        int amount = 1;
        if (point == 0) {
            amount = 1;
        } else if (point == 1) {
            amount = 10;
        } else if (point == 2) {
            amount = 20;
        } else if (point == 3) {
            amount = 30;
        }
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 23, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73Gi\u00f3 Tuy\u1ebft \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("0 0 0"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76B\u1eafn ra m\u1ed9t lu\u1ed3ng gi\u00f3, g\u00e2y s\u00e1t th\u01b0\u01a1ng v\u00e0 l\u00e0m ch\u1eadm k\u1ebb \u0111\u1ecbch");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f50");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f3");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76G\u00e2y s\u00e1t th\u01b0\u01a1ng b\u1eb1ng \u00a7c5% \u00a76n\u0103ng l\u01b0\u1ee3ng t\u1ed1i \u0111a");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76G\u00e2y s\u00e1t th\u01b0\u01a1ng b\u1eb1ng \u00a7c7% \u00a76n\u0103ng l\u01b0\u1ee3ng t\u1ed1i \u0111a");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76G\u00e2y s\u00e1t th\u01b0\u01a1ng b\u1eb1ng \u00a7c10% \u00a76n\u0103ng l\u01b0\u1ee3ng t\u1ed1i \u0111a");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + BaoTuyet.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getBocPhaThienSkill(Player player) {
        int point = BocPhaThien.getPoint(player);
        int amount = 1;
        if (point == 0) {
            amount = 1;
        } else if (point == 1) {
            amount = 10;
        } else if (point == 2) {
            amount = 20;
        } else if (point == 3) {
            amount = 30;
        }
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 24, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73B\u1ed9c Ph\u00e1 Thi\u00ean \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("1 0 1 0"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76G\u00e2y m\u1ed9t v\u1ee5 n\u1ed5 c\u1ef1c l\u1edbn t\u1ea1i \u0111\u1ecba \u0111i\u1ec3m \u0111\u00e3 ch\u1ecdn");
        lore.add("\u00a7cS\u00c1T TH\u01af\u01a0NG: \u00a7650% n\u0103ng l\u01b0\u1ee3ng t\u1ed1i \u0111a");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f120");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76Ti\u00eau T\u1ed1n \u00a7c99% \u00a76n\u0103ng l\u01b0\u1ee3ng t\u1ed1i \u0111a");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76Ti\u00eau T\u1ed1n \u00a7c98% \u00a76n\u0103ng l\u01b0\u1ee3ng t\u1ed1i \u0111a");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76Ti\u00eau T\u1ed1n \u00a7c95% \u00a76n\u0103ng l\u01b0\u1ee3ng t\u1ed1i \u0111a");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + BocPhaThien.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getDanhCapNangLuongSkill(Player player) {
        int point = DanhCapNangLuong.getPoint(player);
        int amount = 1;
        if (point == 0) {
            amount = 1;
        } else if (point == 1) {
            amount = 10;
        } else if (point == 2) {
            amount = 20;
        } else if (point == 3) {
            amount = 30;
        }
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 25, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73\u0110\u00e1nh C\u1eafp N\u0103ng L\u01b0\u1ee3ng \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aN\u1ed8I T\u1ea0I ");
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76Khi gi\u1ebft ch\u1ebft, \u0111\u00e1nh c\u1eafp n\u0103ng l\u01b0\u1ee3ng t\u1eeb n\u1ea1n nh\u00e2n");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f0");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f0");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76\u0110\u00e1nh c\u1eafp \u00a7c5 \u00a76n\u0103ng l\u01b0\u1ee3ng");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76\u0110\u00e1nh c\u1eafp \u00a7c7 \u00a76n\u0103ng l\u01b0\u1ee3ng");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76\u0110\u00e1nh c\u1eafp \u00a7c10 \u00a76n\u0103ng l\u01b0\u1ee3ng");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + DanhCapNangLuong.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getHoiSucSkill(Player player) {
        int point = HoiSuc.getPoint(player);
        int amount = 1;
        if (point == 0) {
            amount = 1;
        } else if (point == 1) {
            amount = 10;
        } else if (point == 2) {
            amount = 20;
        } else if (point == 3) {
            amount = 30;
        }
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 26, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73H\u1ed3i S\u1ee9c \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aN\u1ed8I T\u1ea0I ");
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76M\u1ed7i 20s khi kh\u00f4ng b\u1ecb t\u1ea5n c\u00f4ng \u0111\u01b0\u1ee3c hi\u1ec7u \u1ee9ng h\u1ed3i m\u00e1u");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f0");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f0");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76H\u1ed3i m\u00e1u trong \u00a7c10s");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76H\u1ed3i m\u00e1u trong \u00a7c12s");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76H\u1ed3i m\u00e1u trong \u00a7c15s");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + HoiSuc.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getKhienNangLuongSkill(Player player) {
        int point = KhienNangLuong.getPoint(player);
        int amount = 1;
        if (point == 0) {
            amount = 1;
        } else if (point == 1) {
            amount = 10;
        } else if (point == 2) {
            amount = 20;
        } else if (point == 3) {
            amount = 30;
        }
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 27, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73Khi\u00ean N\u0103ng L\u01b0\u1ee3ng \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("0 0"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76Khi b\u1ecb t\u1ea5n c\u00f4ng, d\u00f9ng n\u0103ng l\u01b0\u1ee3ng l\u00e0m l\u00e0 ch\u1eafn v\u00e0 kh\u00f4ng b\u1ecb s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f?");
        lore.add("\u00a73Y\u00caU C\u1ea6U B\u1eacT/T\u1eaeT");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76N\u0103ng l\u01b0\u1ee3ng m\u1ea5t \u0111i b\u0103ng \u00a7c12 l\u1ea7n \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76N\u0103ng l\u01b0\u1ee3ng m\u1ea5t \u0111i b\u0103ng \u00a7c10 l\u1ea7n \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76N\u0103ng l\u01b0\u1ee3ng m\u1ea5t \u0111i b\u0103ng \u00a7c7 l\u1ea7n \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + KhienNangLuong.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getLoDienSkill(Player player) {
        int point = LoDien.getPoint(player);
        int amount = 1;
        if (point == 0) {
            amount = 1;
        } else if (point == 1) {
            amount = 10;
        } else if (point == 2) {
            amount = 20;
        } else if (point == 3) {
            amount = 30;
        }
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 28, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73L\u1ed9 Di\u1ec7n \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aN\u1ed8I T\u1ea0I ");
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76M\u1ed7i \u0111\u00f2n \u0111\u00e1nh l\u00e0m l\u1ed9 di\u1ec7n k\u1ebb \u0111\u1ecbch v\u00e0 g\u00e2y th\u00eam s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f0");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f0");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76L\u1ed9 di\u1ec7n trong \u00a7c2s \u00a76v\u00e0 g\u00e2y th\u00eam \u00a7c10% \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76L\u1ed9 di\u1ec7n trong \u00a7c3s \u00a76v\u00e0 g\u00e2y th\u00eam \u00a7c10% \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76L\u1ed9 di\u1ec7n trong \u00a7c5s \u00a76v\u00e0 g\u00e2y th\u00eam \u00a7c20% \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + LoDien.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getMuaSaoBangSkill(Player player) {
        int point = MuaSaoBang.getPoint(player);
        int amount = 1;
        if (point == 0) {
            amount = 1;
        } else if (point == 1) {
            amount = 10;
        } else if (point == 2) {
            amount = 20;
        } else if (point == 3) {
            amount = 30;
        }
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 29, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73M\u01b0a Thi\u00ean Th\u1ea1ch \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("0 1 0 1"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76G\u00e2y ra m\u01b0a sao b\u0103ng t\u1ea1i \u0111\u1ecba \u0111i\u1ec3m ch\u1ec9 \u0111\u1ecbnh");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f800");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f120");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76S\u00e1t th\u01b0\u01a1ng m\u1ed7i vi\u00ean \u00a7c5% \u00a76n\u0103ng l\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76S\u00e1t th\u01b0\u01a1ng m\u1ed7i vi\u00ean \u00a7c7% \u00a76n\u0103ng l\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76S\u00e1t th\u01b0\u01a1ng m\u1ed7i vi\u00ean \u00a7c10% \u00a76n\u0103ng l\u01b0\u01a1ng");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + MuaSaoBang.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getNoLeSkill(Player player) {
        int point = NoLe.getPoint(player);
        int amount = 1;
        if (point == 0) {
            amount = 1;
        } else if (point == 1) {
            amount = 10;
        } else if (point == 2) {
            amount = 20;
        } else if (point == 3) {
            amount = 30;
        }
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 30, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73N\u00f4 L\u1ec7 \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aN\u1ed8I T\u1ea0I ");
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76Khi b\u1ecb t\u1ea5n c\u00f4ng, tri\u1ec7u h\u1ed3i 1 n\u00f4 l\u1ec7 t\u1ea5n c\u00f4ng k\u1ebb \u0111\u1ecbch");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f0");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f5");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76N\u00f4 L\u1ec7 c\u00f3 \u00a7c50 \u00a76m\u00e1u");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76N\u00f4 L\u1ec7 c\u00f3 \u00a7c70 \u00a76m\u00e1u");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76N\u00f4 L\u1ec7 c\u00f3 \u00a7c100 \u00a76m\u00e1u");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + NoLe.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getQuanDoanDiaNgucSkill(Player player) {
        int point = QuanDoanDiaNguc.getPoint(player);
        int amount = 1;
        if (point == 0) {
            amount = 1;
        } else if (point == 1) {
            amount = 10;
        } else if (point == 2) {
            amount = 20;
        } else if (point == 3) {
            amount = 30;
        }
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 31, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73Qu\u00e2n \u0110o\u00e0n \u0110\u1ecba Ng\u1ee5c \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("1 1 1"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76Tri\u1ec7u h\u1ed3i m\u1ed9t qu\u00e2n \u0111o\u00e0n t\u1eeb \u0111\u1ecba ng\u1ee5c");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f500");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f60");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76Tri\u1ec7u h\u1ed3i \u00a7c2 \u00a76qu\u00e1i v\u1eadt");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76Tri\u1ec7u h\u1ed3i \u00a7c3 \u00a76qu\u00e1i v\u1eadt");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76Tri\u1ec7u h\u1ed3i \u00a7c4 \u00a76qu\u00e1i v\u1eadt");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + QuanDoanDiaNguc.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getVongTronNangLuongSkill(Player player) {
        int point = VongTronNangLuong.getPoint(player);
        int amount = 1;
        if (point == 0) {
            amount = 1;
        } else if (point == 1) {
            amount = 10;
        } else if (point == 2) {
            amount = 20;
        } else if (point == 3) {
            amount = 30;
        }
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 32, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73V\u00f2ng Tr\u00f2n N\u0103ng L\u01b0\u1ee3ng \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("1 1"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76T\u0103ng t\u1ed1c \u0111\u1ed9 h\u1ed3i m\u00e1u v\u00e0 t\u1ed1c \u0111\u1ed9 ch\u1ea1y");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f?");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f0");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76M\u1ed7i gi\u00e2y m\u1ea5t \u00a7c20 \u00a76n\u0103ng l\u01b0\u1ee3ng");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76M\u1ed7i gi\u00e2y m\u1ea5t \u00a7c15 \u00a76n\u0103ng l\u01b0\u1ee3ng");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76M\u1ed7i gi\u00e2y m\u1ea5t \u00a7c10 \u00a76n\u0103ng l\u01b0\u1ee3ng");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + VongTronNangLuong.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }
}

