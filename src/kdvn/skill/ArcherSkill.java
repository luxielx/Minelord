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
import kdvn.config.SkillArcherConfig;
import kdvn.main.Main;
import kdvn.skill.SkillSetting;
import kdvn.skill.archer.BangKich;
import kdvn.skill.archer.BangTien;
import kdvn.skill.archer.CungCap;
import kdvn.skill.archer.CuongSat;
import kdvn.skill.archer.DocKich;
import kdvn.skill.archer.HoaKich;
import kdvn.skill.archer.HoiMau;
import kdvn.skill.archer.LienHoanTen;
import kdvn.skill.archer.MuaTen;
import kdvn.skill.archer.NguoiBaoVe;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ArcherSkill {
    public static final String INV_TITLE = "\u00a72\u00a7l\u00a7nK\u1ef8 N\u0102NG ARCHER";
    public static final int SLOT_BANG_TIEN = 0;
    public static final int SLOT_HOA_KICH = 2;
    public static final int SLOT_LIEN_HOAN_TEN = 4;
    public static final int SLOT_NGUOI_BAO_VE = 6;
    public static final int SLOT_MUA_TEN = 8;
    public static final int SLOT_DOC_KICH = 18;
    public static final int SLOT_BANG_KICH = 20;
    public static final int SLOT_HOI_MAU = 22;
    public static final int SLOT_CUNG_CAP = 24;
    public static final int SLOT_CUONG_SAT = 26;

    public static int getRemainingPoint(Player player) {
        int tongDiem = SkillArcherConfig.getConfig((Plugin)Main.plugin).getInt("Player." + player.getName() + ".TongDiem");
        int result = tongDiem - (BangKich.getPoint(player) + BangTien.getPoint(player) + CungCap.getPoint(player) + DocKich.getPoint(player) + HoaKich.getPoint(player) + HoiMau.getPoint(player) + LienHoanTen.getPoint(player) + MuaTen.getPoint(player) + NguoiBaoVe.getPoint(player) + CuongSat.getPoint(player));
        return result;
    }

    public static void showBangChonSkill(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, (int)27, (String)"\u00a72\u00a7l\u00a7nK\u1ef8 N\u0102NG ARCHER");
        inv.setItem(20, ArcherSkill.getBangKichSkill(player));
        inv.setItem(0, ArcherSkill.getBangTienSkill(player));
        inv.setItem(24, ArcherSkill.getCungCapSkill(player));
        inv.setItem(18, ArcherSkill.getDocKichSkill(player));
        inv.setItem(2, ArcherSkill.getHoaKichSkill(player));
        inv.setItem(22, ArcherSkill.getHoiMauSkill(player));
        inv.setItem(4, ArcherSkill.getLienHoanTenSkill(player));
        inv.setItem(8, ArcherSkill.getMuaTenSkill(player));
        inv.setItem(6, ArcherSkill.getNguoiBaoVeSkill(player));
        inv.setItem(26, ArcherSkill.getCuongSatSkill(player));
        int i = 0;
        while (i < 27) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, SkillSetting.getBlackSlot(player));
            }
            ++i;
        }
        player.openInventory(inv);
    }

    private static ItemStack getBangKichSkill(Player player) {
        int point = BangKich.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 1, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73B\u0103ng K\u00edch \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("1 1"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76B\u1eafn ra m\u1ed9t tia b\u0103ng, \u0111\u00f3ng b\u0103ng k\u1ebb \u0111\u1ecbch tr\u00fang ph\u1ea3i");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f20");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f15");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76\u0110\u00f3ng b\u0103ng trong \u00a7c5s");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76\u0110\u00f3ng b\u0103ng trong \u00a7c7s");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76\u0110\u00f3ng b\u0103ng trong \u00a7c10s");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + BangKich.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getBangTienSkill(Player player) {
        int point = BangTien.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 2, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73B\u0103ng Ti\u1ec5n \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aN\u1ed8I T\u1ea0I ");
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76L\u00e0m ch\u1eadm k\u1ebb \u0111\u1ecbch khi b\u1ecb tr\u00fang t\u00ean");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f0");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f0");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76L\u00e0m ch\u1eadm trong \u00a7c0.5s");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76L\u00e0m ch\u1eadm trong \u00a7c1s");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76L\u00e0m ch\u1eadm trong \u00a7c2s");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + BangTien.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getCungCapSkill(Player player) {
        int point = CungCap.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 3, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73C\u1ee9ng C\u00e1p \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("0 0 1"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76Gi\u1ea3m 50% s\u00e1t th\u01b0\u01a1ng cho b\u1ea3n th\u00e2n v\u00e0 \u0111\u1ed3ng \u0111\u1ed9i trong Party");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f30");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f30");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76B\u1ea3o V\u1ec7 \u00a7c10s");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76B\u1ea3o V\u1ec7 trong \u00a7c20s");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76B\u1ea3o V\u1ec7 trong \u00a7c30s");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + CungCap.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getDocKichSkill(Player player) {
        int point = DocKich.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 4, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73\u0110\u1ed9c K\u00edch \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aN\u1ed8I T\u1ea0I ");
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76M\u0169i t\u00ean \u0111\u01b0\u1ee3c t\u1ea9m thu\u1ed1c \u0111\u1ed9c");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f0");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f0");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76\u0110\u1ed9c \u00a7c5s");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76\u0110\u1ed9c \u00a7c10s");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76\u0110\u1ed9c \u00a7c20s");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + DocKich.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getHoaKichSkill(Player player) {
        int point = HoaKich.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 5, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73H\u1ecfa K\u00edch \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("0 0"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76B\u1eafn ra 1 tia l\u1eeda g\u00e2y s\u00e1t th\u01b0\u01a1ng v\u00e0 ch\u00e1y k\u1ebb \u0111\u1ecbch");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f10");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f5");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76G\u00e2y \u00a7c100% \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76G\u00e2y \u00a7c120% \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76G\u00e2y \u00a7c150% \u00a76s\u00e1t th\u01b0\u01a1ng");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + HoaKich.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getHoiMauSkill(Player player) {
        int point = HoiMau.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 6, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73H\u1ed3i M\u00e1u \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("0 0 0"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76H\u1ed3i m\u00e1u cho to\u00e0n b\u1ed9 th\u00e0nh vi\u00ean trong party xung quanh");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f20");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f15");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76H\u1ed3i \u00a7c20 \u00a76m\u00e1u v\u00e0 trong b\u00e1n k\u00ednh \u00a7c5 block");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76H\u1ed3i \u00a7c30 \u00a76m\u00e1u v\u00e0 trong b\u00e1n k\u00ednh \u00a7c10 block");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76H\u1ed3i \u00a7c40 \u00a76m\u00e1u v\u00e0 trong b\u00e1n k\u00ednh \u00a7c20 block");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + HoiMau.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getLienHoanTenSkill(Player player) {
        int point = LienHoanTen.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 7, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73Li\u00ean Ho\u00e0n T\u00ean \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aN\u1ed8I T\u1ea0I ");
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76M\u1ed7i ph\u00e1t b\u1eafn th\u1ee9 3 s\u1ebd b\u1eafn ra nhi\u1ec1u h\u01a1n 1 m\u0169i t\u00ean");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f0");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f0");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76B\u1eafn ra \u00a7c3 \u00a76m\u0169i t\u00ean");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76B\u1eafn ra \u00a7c5 \u00a76m\u0169i t\u00ean");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76B\u1eafn ra \u00a7c9 \u00a76m\u0169i t\u00ean");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + LienHoanTen.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getMuaTenSkill(Player player) {
        int point = MuaTen.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 8, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73M\u01b0a T\u00ean \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("1 1 0 0"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76G\u00e2y m\u01b0a t\u00ean t\u1eeb tr\u00ean tr\u1eddi xu\u1ed1ng");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f20");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f60");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76B\u1eafn nhi\u1ec1u t\u00ean");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76B\u1eafn nhi\u1ec1u t\u00ean h\u01a1n");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76B\u1eafn nhi\u1ec1u t\u00ean h\u01a1n n\u1eefa");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + MuaTen.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getNguoiBaoVeSkill(Player player) {
        int point = NguoiBaoVe.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 9, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73Ng\u01b0\u1eddi B\u1ea3o V\u1ec7 \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("1 1 1"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76Tri\u1ec7u h\u1ed3i Ng\u01b0\u1eddi B\u1ea3o V\u1ec7");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f40");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f60");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76Ng\u01b0\u1eddi B\u1ea3o V\u1ec7 c\u00f3 \u00a7c200 \u00a76m\u00e1u");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76Ng\u01b0\u1eddi B\u1ea3o V\u1ec7 c\u00f3 \u00a7c300 \u00a76m\u00e1u");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76Ng\u01b0\u1eddi B\u1ea3o V\u1ec7 c\u00f3 \u00a7c500 \u00a76m\u00e1u");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + NguoiBaoVe.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    private static ItemStack getCuongSatSkill(Player player) {
        int point = CuongSat.getPoint(player);
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
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 10, amount);
        ItemMeta meta = itemSkill.getItemMeta();
        meta.setDisplayName("\u00a73Cu\u1ed3ng S\u00e1t \u00a76[\u00a7aC\u1ea5p \u0111\u1ed9: \u00a7c" + point + "\u00a76]");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(" ");
        lore.add("\u00a7aCOMBO: \u00a7c" + SkillSetting.toCombo("0 0 1 1"));
        lore.add(" ");
        lore.add("\u00a7aK\u1ef8 N\u0102NG: \u00a76T\u0103ng 100% s\u00e1t th\u01b0\u01a1ng ti\u1ec1m n\u0103ng cho \u0111\u1ed3ng \u0111\u1ed9i trong party");
        lore.add("\u00a73N\u0102NG L\u01af\u1ee2NG: \u00a7f20");
        lore.add("\u00a73TH\u1edcI GIAN H\u1ed2I CHI\u00caU: \u00a7f30");
        lore.add(" ");
        lore.add("\u00a7aC\u1ea4P 1: \u00a76Hi\u1ec7u \u1ee9ng trong \u00a7c10s, \u00a76b\u00e1n k\u00ednh \u00a7c5 \u00a76block");
        lore.add("\u00a7aC\u1ea4P 2: \u00a76Hi\u1ec7u \u1ee9ng trong \u00a7c20s, \u00a76b\u00e1n k\u00ednh \u00a7c10 \u00a76block");
        lore.add("\u00a7aC\u1ea4P 3: \u00a76Hi\u1ec7u \u1ee9ng trong \u00a7c30s, \u00a76b\u00e1n k\u00ednh \u00a7c20 \u00a76block");
        lore.add(" ");
        lore.add("\u00a75C\u1ea5p \u0111\u1ed9 \u0111\u1ec3 n\u00e2ng l\u00ean c\u1ea5p ti\u1ebfp theo: \u00a7f" + CuongSat.getNexLevelRequirement(player));
        meta.setLore(lore);
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }
}

