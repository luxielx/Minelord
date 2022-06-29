/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.nametagedit.plugin.NametagEdit
 *  org.bukkit.Material
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.plugin.Plugin
 */
package kdvn.classes;

import com.nametagedit.plugin.NametagEdit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kdvn.bow.BowArcher;
import kdvn.classes.Archer;
import kdvn.classes.Knight;
import kdvn.classes.Mage;
import kdvn.config.ClassConfig;
import kdvn.config.SkillArcherConfig;
import kdvn.config.SkillKnightConfig;
import kdvn.config.SkillMageConfig;
import kdvn.magicwand.MagicWand;
import kdvn.main.Main;
import kdvn.phepthuat.NangLuong;
import kdvn.tiemnang.NhanhNhen;
import kdvn.tiemnang.PhepThuat;
import kdvn.tiemnang.SucManh;
import kdvn.tiemnang.TheLuc;
import kdvn.tiemnang.TongDiem;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ClassSetting {
    public static String chiSoTren10(int chiSo) {
        String s = "  ";
        int i = 1;
        while (i <= chiSo) {
            s = String.valueOf(s) + "\u00a7d\u25cf";
            ++i;
        }
        i = chiSo;
        while (i < 10) {
            s = String.valueOf(s) + "\u00a78\u25cf";
            ++i;
        }
        return s;
    }

    public static void setDefault(Player player) {
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
        player.setWalkSpeed(0.2f);
        player.setLevel(0);
        player.setExp(0.0f);
        SucManh.setPoint(player, 0);
        NangLuong.setNangLuong(player, 0);
        NhanhNhen.setPoint(player, 0);
        TheLuc.setPoint(player, 0);
        PhepThuat.setPoint(player, 0);
        TongDiem.setPoint(player, 0);
        ClassSetting.removeAllSkill(player);
    }

    public static void removeAllSkill(Player player) {
        SkillKnightConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName(), (Object)null);
        SkillKnightConfig.saveConfig();
        SkillArcherConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName(), (Object)null);
        SkillArcherConfig.saveConfig();
        SkillMageConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName(), (Object)null);
        SkillMageConfig.saveConfig();
    }

    public static String getClass(Player player) {
        if (ClassConfig.getConfig((Plugin)Main.plugin).getString("Player." + player.getName()) != null) {
            return ClassConfig.getConfig((Plugin)Main.plugin).getString("Player." + player.getName());
        }
        return "null";
    }

    public static boolean hasClass(Player player) {
        if (ClassSetting.getClass(player).equals("null")) {
            return false;
        }
        return true;
    }

    public static String getItemClass(ItemStack item) {
        List lore;
        block8 : {
            block7 : {
                block6 : {
                    try {
                        if (item.getItemMeta().hasLore()) break block6;
                        return "null";
                    }
                    catch (NullPointerException e) {
                        return "null";
                    }
                }
                lore = item.getItemMeta().getLore();
                if (!lore.contains(Archer.ITEM_LORE)) break block7;
                return "archer";
            }
            if (!lore.contains(Knight.ITEM_LORE)) break block8;
            return "knight";
        }
        if (lore.contains(Mage.ITEM_LORE)) {
            return "mage";
        }
        return "null";
    }

    public static ItemStack addClassLore(ItemStack item, String className) {
        List lore = new ArrayList();
        ItemStack itemm = item;
        ItemMeta meta = itemm.getItemMeta();
        if (item.getItemMeta().hasLore()) {
            lore = item.getItemMeta().getLore();
        }
        if (className.equalsIgnoreCase("archer")) {
            lore.add(Archer.ITEM_LORE);
        } else if (className.equalsIgnoreCase("knight")) {
            lore.add(Knight.ITEM_LORE);
        } else if (className.equalsIgnoreCase("mage")) {
            lore.add(Mage.ITEM_LORE);
        }
        meta.setLore(lore);
        itemm.setItemMeta(meta);
        return itemm;
    }

    public static boolean checkItem(ItemStack item, Player player) {
        if (!ClassSetting.getClass(player).equalsIgnoreCase(ClassSetting.getItemClass(item))) {
            return false;
        }
        return true;
    }

    public static void setPlayerNameTag(Player player) {
        String className = ClassSetting.getClass(player);
        String prefix = "";
        if (className.equals("archer")) {
            prefix = "\u00a76(" + Archer.PREFIX + "\u00a76)\u00a7f.";
        } else if (className.equals("knight")) {
            prefix = "\u00a76(" + Knight.PREFIX + "\u00a76)\u00a7f.";
        } else if (className.equals("mage")) {
            prefix = "\u00a76(" + Mage.PREFIX + "\u00a76)\u00a7f.";
        }
        NametagEdit.getApi().setPrefix(player, prefix);
    }

    public static boolean manaCost(Player player, int mana) {
        int manaOfPlayer = NangLuong.getNangLuong(player);
        if (manaOfPlayer < mana) {
            player.sendMessage("\u00a73B\u1ea1n \u00a7cKH\u00d4NG \u00a73\u0111\u1ee7 N\u0103ng L\u01b0\u1ee3ng \u0111\u1ec3 d\u00f9ng k\u1ef9 n\u0103ng!");
            return false;
        }
        NangLuong.setNangLuong(player, manaOfPlayer - mana);
        return true;
    }

    public static void removeClass(Player player) {
        if (ClassSetting.hasClass(player)) {
            ClassConfig.getConfig((Plugin)Main.plugin).set("Player." + player.getName(), (Object)null);
            ClassConfig.saveConfig();
        }
    }

    private static ItemStack getItemWithDurabity(ItemStack item, short durability) {
        ItemStack newItem = item;
        newItem.setDurability(durability);
        ItemMeta meta = newItem.getItemMeta();
        meta.setUnbreakable(true);
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
        meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_UNBREAKABLE});
        newItem.setItemMeta(meta);
        return newItem;
    }

    public static void giveFirstWeapon(Player player, String className) {
        if (className.equals("archer")) {
            ItemStack newItem = new ItemStack(Material.WOOD_SWORD);
            newItem = ClassSetting.getItemWithDurabity(newItem, 7);
            BowArcher.addDamageToItem(newItem, 2.0);
            newItem = ClassSetting.addClassLore(newItem, "archer");
            ItemMeta meta = newItem.getItemMeta();
            meta.setDisplayName("\u00a7aCung \u00a76Kh\u1edfi \u0110\u1ea7u");
            newItem.setItemMeta(meta);
            player.getInventory().addItem(new ItemStack[]{newItem});
            player.getInventory().addItem(new ItemStack[]{new ItemStack(Material.ARROW, 64)});
        } else if (className.equals("knight")) {
            ItemStack newItem = new ItemStack(Material.WOOD_SWORD);
            newItem = ClassSetting.getItemWithDurabity(newItem, 3);
            newItem = ClassSetting.addClassLore(newItem, "knight");
            ItemMeta meta = newItem.getItemMeta();
            meta.setDisplayName("\u00a7aKi\u1ebfm \u00a76Kh\u1edfi \u0110\u1ea7u");
            newItem.setItemMeta(meta);
            player.getInventory().addItem(new ItemStack[]{newItem});
        } else if (className.equals("mage")) {
            ItemStack newItem = new ItemStack(Material.WOOD_SWORD);
            newItem = ClassSetting.getItemWithDurabity(newItem, 5);
            ItemMeta meta = newItem.getItemMeta();
            meta.setDisplayName("\u00a7aG\u1eady ph\u00e9p \u00a76Kh\u1edfi \u0110\u1ea7u");
            newItem.setItemMeta(meta);
            MagicWand.addDamageLore(newItem, 2.0);
            MagicWand.addParticleLore(newItem, "flame");
            newItem = ClassSetting.addClassLore(newItem, "mage");
            player.getInventory().addItem(new ItemStack[]{newItem});
        }
    }
}

