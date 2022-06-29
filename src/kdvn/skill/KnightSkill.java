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
import kdvn.config.SkillKnightConfig;
import kdvn.main.Main;
import kdvn.skill.SkillSetting;
import kdvn.skill.knight.AnhDung;
import kdvn.skill.knight.BungNo;
import kdvn.skill.knight.HoaThan;
import kdvn.skill.knight.KhacMau;
import kdvn.skill.knight.KhieuKhich;
import kdvn.skill.knight.ManhMe;
import kdvn.skill.knight.PhanDon;
import kdvn.skill.knight.TraMau;
import kdvn.skill.knight.TrungPhat;
import kdvn.skill.knight.TuChoiTuThan;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class KnightSkill {
    public static final String INV_TITLE = "\u00a72\u00a7l\u00a7nK\u1ef8 N\u0102NG KNIGHT";
    public static final int SLOT_KHAC_MAU = 0;
    public static final int SLOT_MANH_ME = 2;
    public static final int SLOT_HOA_THAN = 4;
    public static final int SLOT_BUNG_NO = 6;
    public static final int SLOT_TU_CHOI_TU_THAN = 8;
    public static final int SLOT_TRA_MAU = 18;
    public static final int SLOT_ANH_DUNG = 20;
    public static final int SLOT_KHIEU_KHICH = 22;
    public static final int SLOT_PHAN_DON = 24;
    public static final int SLOT_TRUNG_PHAT = 26;

    public static int getRemainingPoint(Player player) {
        int tongDiem = SkillKnightConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + ".TongDiem");
        int result = tongDiem - (AnhDung.getPoint(player) + BungNo.getPoint(player) + HoaThan.getPoint(player) + KhacMau.getPoint(player) + KhieuKhich.getPoint(player) + ManhMe.getPoint(player) + PhanDon.getPoint(player) + TraMau.getPoint(player) + TrungPhat.getPoint(player) + TuChoiTuThan.getPoint(player));
        return result;
    }

    public static void showBangChonSkill(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, (int)27, (String)"\u00a72\u00a7l\u00a7nK\u1ef8 N\u0102NG KNIGHT");
        inv.setItem(20, KnightSkill.getAnhDungSkill(player));
        inv.setItem(6, KnightSkill.getBungNoSkill(player));
        inv.setItem(4, KnightSkill.getHoaThanSkill(player));
        inv.setItem(0, KnightSkill.getKhacMauSkill(player));
        inv.setItem(22, KnightSkill.getKhieuKhichSkill(player));
        inv.setItem(2, KnightSkill.getManhMeSkill(player));
        inv.setItem(24, KnightSkill.getPhanDonSkill(player));
        inv.setItem(18, KnightSkill.getTraMauSkill(player));
        inv.setItem(26, KnightSkill.getTrungPhatSkill(player));
        inv.setItem(8, KnightSkill.getTuChoiTuThanSkill(player));
        int i = 0;
        while (i < 27) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, SkillSetting.getBlackSlot(player));
            }
            ++i;
        }
        player.openInventory(inv);
    }

    private static ItemStack getAnhDungSkill(Player player) {
        int point = AnhDung.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 13, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73Anh D\u0169ng \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("1 1 1"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76Ti\u1ebfn v\u1ec1 ph\u00eda tr\u01b0\u1edbc, \u0111\u00e1nh g\u00e2y m\u00f9 ph\u00e1t ti\u1ebfp theo");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f40");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f20");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76G\u00e2y s\u00e1t th\u01b0\u01a1ng \u00a7c100%");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76G\u00e2y s\u00e1t th\u01b0\u01a1ng \u00a7c120%");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76G\u00e2y s\u00e1t th\u01b0\u01a1ng \u00a7c150%");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + AnhDung.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getBungNoSkill(Player player) {
        int point = BungNo.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 14, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73B\u00f9ng N\u1ed5 \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aN\u1ed8I T\u1ea0I ");
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76Khi d\u01b0\u1edbi 20% m\u00e1u, h\u1ea5t tung v\u00e0 s\u00e1t th\u01b0\u01a1ng k\u1ebb \u0111\u1ecbch xung quanh");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f0");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f5");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76G\u00e2y s\u00e1t th\u01b0\u01a1ng b\u1eb1ng \u00a7c5% m\u00e1u t\u1ed1i \u0111a c\u1ee7a b\u1ea3n th\u00e2n");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76G\u00e2y s\u00e1t th\u01b0\u01a1ng b\u1eb1ng \u00a7c7% m\u00e1u t\u1ed1i \u0111a c\u1ee7a b\u1ea3n th\u00e2n");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76G\u00e2y s\u00e1t th\u01b0\u01a1ng b\u1eb1ng \u00a7c10% m\u00e1u t\u1ed1i \u0111a c\u1ee7a b\u1ea3n th\u00e2n");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + BungNo.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getHoaThanSkill(Player player) {
        int point = HoaThan.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 15, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73H\u1ecfa Th\u00e2n \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("0 0"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76K\u00edch ho\u1ea1t t\u1ea1o ng\u1ecdn l\u1eeda xung quanh ng\u01b0\u1eddi s\u00e1t th\u01b0\u01a1ng k\u1ebb \u0111\u1ecbch \u1edf g\u1ea7n");
        lore.add("\u00a73M\u00c1U T\u1ed0N M\u1ed6I GI\u00c2Y: \u00a765% m\u00e1u t\u1ed1i \u0111a");
        lore.add("\u00a73Y\u00caU C\u1ea6U B\u1eacT/T\u1eaeT");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76G\u00e2y s\u00e1t th\u01b0\u01a1ng b\u1eb1ng \u00a7c5% m\u00e1u t\u1ed1i \u0111a");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76G\u00e2y s\u00e1t th\u01b0\u01a1ng b\u1eb1ng \u00a7c7% m\u00e1u t\u1ed1i \u0111a");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76G\u00e2y s\u00e1t th\u01b0\u01a1ng b\u1eb1ng \u00a7c10% m\u00e1u t\u1ed1i \u0111as");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + HoaThan.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getKhacMauSkill(Player player) {
        int point = KhacMau.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 16, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73Kh\u1eafc M\u00e1u \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("1 0 0"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76M\u1ed7i \u0111\u00f2n \u0111\u00e1nh th\u1ee9 3 s\u1ebd h\u00fat m\u00e1u t\u1eeb k\u1ebb \u0111\u1ecbch");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f10");
        lore.add("\u00a73Y\u00caU C\u1ea6U B\u1eacT/T\u1eaeT");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76H\u00fat \u00a7c2% \u00a76t\u1eeb k\u1ebb \u0111\u1ecbch");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76H\u00fat \u00a7c3% \u00a76t\u1eeb k\u1ebb \u0111\u1ecbch");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76H\u00fat \u00a7c5% \u00a76t\u1eeb k\u1ebb \u0111\u1ecbch");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + KhacMau.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getKhieuKhichSkill(Player player) {
        int point = KhieuKhich.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 17, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73Khi\u00eau Kh\u00edch \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("1 1"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76H\u00fat k\u1ebb \u0111\u1ecbch l\u1ea1i g\u1ea7n");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f10");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f5");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76H\u00fat trong b\u00e1n k\u00ednh \u00a7c5 \u00a76block");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76H\u00fat trong b\u00e1n k\u00ednh \u00a7c7 \u00a76block");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76H\u00fat trong b\u00e1n k\u00ednh \u00a7c10 \u00a76block");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + KhieuKhich.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getManhMeSkill(Player player) {
        int point = ManhMe.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 18, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73M\u1ea1nh M\u1ebd \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aN\u1ed8I T\u1ea0I ");
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76T\u0103ng gi\u00e1p - gi\u1ea3m s\u00e1t th\u01b0\u01a1ng khi b\u1ecb t\u1ea5n c\u00f4ng");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f0");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f0");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76Gi\u1ea3m \u00a7c10% \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76Gi\u1ea3m \u00a7c15% \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76Gi\u1ea3m \u00a7c20% \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + ManhMe.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getPhanDonSkill(Player player) {
        int point = PhanDon.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 19, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73Ph\u1ea3n \u0110\u00f2n \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aN\u1ed8I T\u1ea0I ");
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76M\u1ed7i khi b\u1ecb s\u00e1t th\u01b0\u01a1ng, ph\u1ea3n \u0111\u00f2n cho \u0111\u1ed1i ph\u01b0\u01a1ng");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f0");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f0");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76Ph\u1ea3n \u00a7c5% \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76Ph\u1ea3n \u00a7c7% \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76Ph\u1ea3n \u00a7c10% \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + PhanDon.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getTraMauSkill(Player player) {
        int point = TraMau.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 20, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73Tr\u1ea3 M\u00e1u \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("0 0 1"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76M\u1ed7i \u0111\u00f2n \u0111\u00e1nh th\u1ee9 3 g\u00e2y th\u00eam s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f10");
        lore.add("\u00a73Y\u00caU C\u1ea6U B\u1eacT/T\u1eaeT");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76G\u00e2y th\u00eam s\u00e1t th\u01b0\u01a1ng b\u0103ng \u00a7c5% \u00a76m\u00e1u t\u1ed1i \u0111a c\u1ee7a b\u1ea3n th\u00e2n");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76G\u00e2y th\u00eam s\u00e1t th\u01b0\u01a1ng b\u0103ng \u00a7c6% \u00a76m\u00e1u t\u1ed1i \u0111a c\u1ee7a b\u1ea3n th\u00e2n");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76G\u00e2y th\u00eam s\u00e1t th\u01b0\u01a1ng b\u0103ng \u00a7c8% \u00a76m\u00e1u t\u1ed1i \u0111a c\u1ee7a b\u1ea3n th\u00e2n");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + TraMau.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getTrungPhatSkill(Player player) {
        int point = TrungPhat.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 21, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73Tr\u1eebng Ph\u1ea1t \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("1 1 0 0"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76Tri\u1ec7u h\u1ed3i s\u1ea5m s\u00e9t \u1edf tr\u01b0\u1edbc m\u1eb7t kho\u1ea3ng c\u00e1ch ng\u1eafn");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f30");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f30");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76G\u00e2y \u00a7c5% \u00a76m\u00e1u t\u1ed1i \u0111a \u0111\u1ed1i ph\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76G\u00e2y \u00a7c10% \u00a76m\u00e1u t\u1ed1i \u0111a \u0111\u1ed1i ph\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76G\u00e2y \u00a7c20% \u00a76m\u00e1u t\u1ed1i \u0111a \u0111\u1ed1i ph\u01b0\u01a1ng");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + TrungPhat.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getTuChoiTuThanSkill(Player player) {
        int point = TuChoiTuThan.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 22, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73T\u1eeb Ch\u1ed1i T\u1eed Th\u1ea7n \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aN\u1ed8I T\u1ea0I ");
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76Khi d\u01b0\u1edbi 15% m\u00e1u m\u00e0 b\u1ecb s\u00e1t th\u01b0\u01a1ng s\u1ebd b\u1ea5t t\u1eed 1 th\u1eddi gian");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f0");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f60");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76B\u1ea5t t\u1eed trong \u00a7c5s");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76B\u1ea5t t\u1eed trong \u00a7c7s");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76B\u1ea5t t\u1eed trong \u00a7c10s");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + TuChoiTuThan.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }
}

