/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 */
package kdvn.skill;

import kdvn.classes.ClassSetting;
import kdvn.config.SkillArcherConfig;
import kdvn.config.SkillKnightConfig;
import kdvn.config.SkillMageConfig;
import kdvn.main.Main;
import kdvn.skill.ArcherSkill;
import kdvn.skill.KnightSkill;
import kdvn.skill.MageSkill;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class SkillSetting {
    public static void removeAllSkill(Player player) {
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName(), (Object)null);
        SkillKnightConfig.saveConfig();
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName(), (Object)null);
        SkillArcherConfig.saveConfig();
        SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName(), (Object)null);
        SkillMageConfig.saveConfig();
    }

    public static void addDefaultSkillARCHER(Player player, int tongDiem) {
        String path = "Player." + player.getName();
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".TongDiem", (Object)tongDiem);
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".BangKich", (Object)0);
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".BangTien", (Object)0);
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".CungCap", (Object)0);
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".CuongSat", (Object)0);
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".DocKich", (Object)0);
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".HoaKich", (Object)0);
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".HoiMau", (Object)0);
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".LienHoanTen", (Object)0);
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".MuaTen", (Object)0);
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".NguoiBaoVe", (Object)0);
        SkillArcherConfig.saveConfig();
    }

    public static void addDefaultSkillKNIGHT(Player player, int tongDiem) {
        String path = "Player." + player.getName();
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".TongDiem", (Object)tongDiem);
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".AnhDung", (Object)0);
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".BungNo", (Object)0);
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".HoaThan", (Object)0);
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".KhacMau", (Object)0);
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".KhieuKhich", (Object)0);
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".ManhMe", (Object)0);
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".PhanDon", (Object)0);
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".TraMau", (Object)0);
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".TrungPhat", (Object)0);
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".TuChoiTuThan", (Object)0);
        SkillKnightConfig.saveConfig();
    }

    public static void addDefaultSkillMAGE(Player player, int tongDiem) {
        String path = "Player." + player.getName();
        SkillMageConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".TongDiem", (Object)tongDiem);
        SkillMageConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".BaoTuyet", (Object)0);
        SkillMageConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".BocPhaThien", (Object)0);
        SkillMageConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".DanhCapNangLuong", (Object)0);
        SkillMageConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".HoiSuc", (Object)0);
        SkillMageConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".KhienNangLuong", (Object)0);
        SkillMageConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".LoDien", (Object)0);
        SkillMageConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".MuaSaoBang", (Object)0);
        SkillMageConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".NoLe", (Object)0);
        SkillMageConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".QuanDoanDiaNguc", (Object)0);
        SkillMageConfig.getConfig((Plugin)Main.plugin).set(String.valueOf(path) + ".VongTronNangLuong", (Object)0);
        SkillMageConfig.saveConfig();
    }

    public static ItemStack getBlackSlot(Player player) {
        ItemStack itemSkill = SkillSetting.getItemSkill(Material.WOOD_HOE, 11, 1);
        ItemMeta meta = itemSkill.getItemMeta();
        if (ClassSetting.getClass(player).equals("archer")) {
            meta.setDisplayName("\u00a7aB\u1ea1n c\u00f2n \u00a7c" + ArcherSkill.getRemainingPoint(player) + " \u00a7a\u0111i\u1ec3m c\u1ed9ng");
        } else if (ClassSetting.getClass(player).equals("knight")) {
            meta.setDisplayName("\u00a7aB\u1ea1n c\u00f2n \u00a7c" + KnightSkill.getRemainingPoint(player) + " \u00a7a\u0111i\u1ec3m c\u1ed9ng");
        } else if (ClassSetting.getClass(player).equals("mage")) {
            meta.setDisplayName("\u00a7aB\u1ea1n c\u00f2n \u00a7c" + MageSkill.getRemainingPoint(player) + " \u00a7a\u0111i\u1ec3m c\u1ed9ng");
        }
        itemSkill.setItemMeta(meta);
        return itemSkill;
    }

    public static ItemStack getItemSkill(Material material, short durability, int amount) {
        ItemStack item = new ItemStack(material, amount);
        item.setDurability(durability);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
        item.setItemMeta(meta);
        return item;
    }

    public static String toCombo(String s) {
        String result = s.replace("0", "Tr\u00e1i").replace("1", "Ph\u1ea3i");
        return result;
    }
}

