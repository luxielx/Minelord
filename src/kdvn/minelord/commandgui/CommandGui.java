/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Material
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.InventoryHolder
 *  org.bukkit.inventory.InventoryView
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package kdvn.minelord.commandgui;

import java.util.ArrayList;
import java.util.List;
import kdvn.minelord.commandgui.WarpGui;
import kdvn.minelord.dosat.DoSat;
import kdvn.minelord.player.EnumClassMineLord;
import kdvn.minelord.player.MineLordPlayer;
import kdvn.skill.ArcherSkill;
import kdvn.skill.KnightSkill;
import kdvn.skill.MageSkill;
import kdvn.tiemnang.BangCongTiemNang;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandGui
implements Listener {
    private static String TITLE = "                \u00a73\u00a7l\u00a7nMINELORD";
    private static final int TIEMNANG_SLOT = 0;
    private static final int KYNANG_SLOT = 1;
    private static final int WARP_SLOT = 2;
    private static final int DOSAT_SLOT = 3;

    private static Inventory getInventory(Player player) {
        ItemStack tiemNang = new ItemStack(Material.APPLE, 1);
        ItemMeta tiemNangMeta = tiemNang.getItemMeta();
        tiemNangMeta.setDisplayName("\u00a7c\u00a7lTI\u1ec0M N\u0102NG");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("\u00a7eB\u1ea5m v\u1ea3o \u0111\u1ec3 m\u1edf b\u1ea3ng Ti\u1ec1m N\u0103ng");
        tiemNangMeta.setLore(lore);
        tiemNang.setItemMeta(tiemNangMeta);
        ItemStack kyNang = new ItemStack(Material.ENCHANTMENT_TABLE, 1);
        ItemMeta kyNangMeta = kyNang.getItemMeta();
        kyNangMeta.setDisplayName("\u00a7c\u00a7lK\u1ef8 N\u0102NG");
        ArrayList<String> loreKN = new ArrayList<String>();
        loreKN.add("\u00a7eB\u1ea5m v\u1ea3o \u0111\u1ec3 m\u1edf b\u1ea3ng K\u1ef9 N\u0103ng");
        kyNangMeta.setLore(loreKN);
        kyNang.setItemMeta(kyNangMeta);
        ItemStack warp = new ItemStack(Material.PAPER, 1);
        ItemMeta warpMeta = warp.getItemMeta();
        warpMeta.setDisplayName("\u00a7c\u00a7lD\u1ecaCH CHUY\u1ec2N");
        ArrayList<String> loreWarp = new ArrayList<String>();
        loreWarp.add("\u00a7eB\u1ea5m v\u1ea3o \u0111\u1ec3 m\u1edf b\u1ea3ng D\u1ecbch Chuy\u1ec3n");
        warpMeta.setLore(loreWarp);
        warp.setItemMeta(warpMeta);
        ItemStack doSat = new ItemStack(Material.REDSTONE, 1);
        ItemMeta doSatMeta = warp.getItemMeta();
        doSatMeta.setDisplayName("\u00a7c\u00a7l\u0110\u1ed2 S\u00c1T");
        ArrayList<String> loreDoSat = new ArrayList<String>();
        loreDoSat.add("\u00a7eB\u1ea5m v\u1ea3o \u0111\u1ec3 m\u1edf b\u1ea3ng \u0110\u1ed3 S\u00e1t");
        doSatMeta.setLore(loreDoSat);
        doSat.setItemMeta(doSatMeta);
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, (int)9, (String)TITLE);
        inv.setItem(0, tiemNang);
        inv.setItem(1, kyNang);
        inv.setItem(2, warp);
        inv.setItem(3, doSat);
        return inv;
    }

    public static void showInventory(Player player) {
        player.openInventory(CommandGui.getInventory(player));
    }

    @EventHandler
    public static void eventHandling(InventoryClickEvent e) {
        if (!e.getInventory().getTitle().equals(TITLE)) {
            return;
        }
        if (e.getClickedInventory() != e.getWhoClicked().getOpenInventory().getTopInventory()) {
            return;
        }
        e.setCancelled(true);
        int slot = e.getSlot();
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player)e.getWhoClicked();
        if (slot == 0) {
            BangCongTiemNang.show(player);
        } else if (slot == 1) {
            MineLordPlayer mlPlayer = new MineLordPlayer(player);
            if (mlPlayer.getClassMineLord().equals(EnumClassMineLord.ARCHER.toString())) {
                ArcherSkill.showBangChonSkill(player);
            } else if (mlPlayer.getClassMineLord().equals(EnumClassMineLord.KNIGHT.toString())) {
                KnightSkill.showBangChonSkill(player);
            } else if (mlPlayer.getClassMineLord().equals(EnumClassMineLord.MAGE.toString())) {
                MageSkill.showBangChonSkill(player);
            } else if (mlPlayer.getClassMineLord().equals(EnumClassMineLord.NULL.toString())) {
                player.sendMessage("\u00a76B\u1ea1n ch\u01b0a c\u00f3 \u00a7cCLASS");
            }
        } else if (slot == 2) {
            if (DoSat.isWanted(player)) {
                player.closeInventory();
                player.sendMessage("\u00a7cB\u1eadt \u0110\u1ed3 S\u00e1t th\u00ec kh\u00f4ng d\u1ecbch chuy\u1ec3n \u0111\u01b0\u1ee3c \u0111\u00e2u b\u1ea1n \u01a1i");
                return;
            }
            WarpGui.openGui(player);
        } else if (slot == 3) {
            player.openInventory(DoSat.getInventory(player));
        }
    }
}

