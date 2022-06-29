/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  com.alessiodp.parties.utils.api.PartiesAPI
 *  com.sk89q.worldguard.bukkit.WorldGuardPlugin
 *  net.milkbowl.vault.chat.Chat
 *  net.milkbowl.vault.economy.Economy
 *  org.black_ixx.playerpoints.PlayerPoints
 *  org.bukkit.Bukkit
 *  org.bukkit.Server
 *  org.bukkit.World
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Listener
 *  org.bukkit.permissions.Permission
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.PluginDescriptionFile
 *  org.bukkit.plugin.PluginManager
 *  org.bukkit.plugin.RegisteredServiceProvider
 *  org.bukkit.plugin.ServicesManager
 *  org.bukkit.plugin.java.JavaPlugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package kdvn.main;

import com.alessiodp.parties.utils.api.PartiesAPI;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import java.io.File;
import java.util.logging.Logger;
import kdvn.config.ClassConfig;
import kdvn.config.Config;
import kdvn.config.SatThuongConfig;
import kdvn.config.SkillArcherConfig;
import kdvn.config.SkillKnightConfig;
import kdvn.config.SkillMageConfig;
import kdvn.config.SurvivalRadiusConfig;
import kdvn.config.TiemNangConfig;
import kdvn.config.WarpConfig;
import kdvn.event.BangKyNangArcherEvent;
import kdvn.event.BangKyNangKnightEvent;
import kdvn.event.BangKyNangMageEvent;
import kdvn.event.ChonClassEvent;
import kdvn.event.ComboEvent;
import kdvn.event.CongTiemNang;
import kdvn.event.CuongHoaEvent;
import kdvn.event.DamageEvent;
import kdvn.event.DamagedByArrowEvent;
import kdvn.event.MobDeathDropMoney;
import kdvn.event.NameTagEvent;
import kdvn.event.OtherEvents;
import kdvn.event.PowerEvent;
import kdvn.items.ChuyenClassItem;
import kdvn.items.ThuocHoiMau;
import kdvn.items.ThuocHoiNangLuong;
import kdvn.items.ThuocHoiNoiLuc;
import kdvn.leveleditemdrop.DropGems;
import kdvn.magicwand.MagicWand;
import kdvn.main.Commands;
import kdvn.minelord.commandgui.CommandGui;
import kdvn.minelord.commandgui.WarpGui;
import kdvn.minelord.dosat.DoSat;
import kdvn.minelord.dosat.DoSatEvent;
import kdvn.minelord.dosat.DoSatRunnable;
import kdvn.minelord.player.MineLordPlayer;
import kdvn.minelord.survivalradius.SurvivalRadius;
import kdvn.minlord.fix.FixHPRespawn;
import kdvn.runnable.SkillRunnable;
import kdvn.runnable.TangPowerRunnable;
import kdvn.skill.archer.BangKich;
import kdvn.skill.archer.NguoiBaoVe;
import kdvn.skill.mage.NoLe;
import kdvn.skill.mage.QuanDoanDiaNguc;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Main
extends JavaPlugin {
    public static Main plugin;
    private static Economy econ;
    private static Permission perms;
    private static Chat chat;
    private static final Logger log;
    private static PlayerPoints playerPoints;
    public static PartiesAPI partyAPI;

    static {
        econ = null;
        perms = null;
        chat = null;
        log = Logger.getLogger("Minecraft");
    }

    public void onEnable() {
        plugin = this;
        this.hookPlayerPoint();
        this.getLogger().info("[MINELORD] Mine Lord has been Enabled..........");
        this.getLogger().info("[MINELORD]  :>");
        Config.loadConfig((Plugin)this);
        TiemNangConfig.setUpConfig((Plugin)this);
        ClassConfig.setUpConfig((Plugin)this);
        SatThuongConfig.setUpConfig((Plugin)this);
        SkillArcherConfig.setUpConfig((Plugin)this);
        SkillKnightConfig.setUpConfig((Plugin)this);
        SkillMageConfig.setUpConfig((Plugin)this);
        SurvivalRadiusConfig.setUpConfig((Plugin)this);
        WarpConfig.setUpConfig();
        Main.createFolder((Plugin)plugin);
        this.getCommand("ml").setExecutor((CommandExecutor)new Commands());
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents((Listener)new CongTiemNang(), (Plugin)this);
        pm.registerEvents((Listener)new DamageEvent(), (Plugin)this);
        pm.registerEvents((Listener)new PowerEvent(), (Plugin)this);
        pm.registerEvents((Listener)new OtherEvents(), (Plugin)this);
        pm.registerEvents((Listener)new ChonClassEvent(), (Plugin)this);
        pm.registerEvents((Listener)new DamagedByArrowEvent(), (Plugin)this);
        pm.registerEvents((Listener)new ComboEvent(), (Plugin)this);
        pm.registerEvents((Listener)new BangKich(), (Plugin)this);
        pm.registerEvents((Listener)new NguoiBaoVe(), (Plugin)this);
        pm.registerEvents((Listener)new MagicWand(), (Plugin)this);
        pm.registerEvents((Listener)new NoLe(), (Plugin)this);
        pm.registerEvents((Listener)new QuanDoanDiaNguc(), (Plugin)this);
        pm.registerEvents((Listener)new BangKyNangArcherEvent(), (Plugin)this);
        pm.registerEvents((Listener)new BangKyNangKnightEvent(), (Plugin)this);
        pm.registerEvents((Listener)new BangKyNangMageEvent(), (Plugin)this);
        pm.registerEvents((Listener)new ChuyenClassItem(), (Plugin)this);
        pm.registerEvents((Listener)new ThuocHoiNangLuong(), (Plugin)this);
        pm.registerEvents((Listener)new ThuocHoiNoiLuc(), (Plugin)this);
        pm.registerEvents((Listener)new DropGems(), (Plugin)this);
        pm.registerEvents((Listener)new CuongHoaEvent(), (Plugin)this);
        pm.registerEvents((Listener)new NameTagEvent(), (Plugin)this);
        pm.registerEvents((Listener)new FixHPRespawn(), (Plugin)this);
        pm.registerEvents((Listener)new MobDeathDropMoney(), (Plugin)this);
        pm.registerEvents((Listener)new ThuocHoiMau(), (Plugin)this);
        pm.registerEvents((Listener)new WarpGui(), (Plugin)this);
        pm.registerEvents((Listener)new CommandGui(), (Plugin)this);
        pm.registerEvents((Listener)new DoSatEvent(), (Plugin)this);
        pm.registerEvents((Listener)new SurvivalRadius(), (Plugin)this);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, (Runnable)new TangPowerRunnable(), 0, 40);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, (Runnable)new SkillRunnable(), 0, 20);
        new DoSatRunnable().runTaskTimer((Plugin)this, 0, (long)(DoSat.TIME_RUNNABLE * 20));
        new BukkitRunnable(){

            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    world.setTime(18000);
                }
            }
        }.runTaskTimer((Plugin)this, 0, 500);
        if (this.getServer().getPluginManager().getPlugin("Parties") != null && this.getServer().getPluginManager().getPlugin("Parties").isEnabled()) {
            partyAPI = new PartiesAPI();
        }
        if (!this.setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", this.getDescription().getName()));
            this.getServer().getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        this.setupChat();
    }

    public void onDisable() {
    }

    public static MineLordPlayer getMineLordPlayer(Player player) {
        return new MineLordPlayer(player);
    }

    public static PartiesAPI getPartyAPI() {
        return partyAPI;
    }

    public static WorldGuardPlugin getWorldGuardPlugin() {
        Plugin pl = Bukkit.getPluginManager().getPlugin("WorldGuard");
        if (pl == null) {
            return null;
        }
        return (WorldGuardPlugin)pl;
    }

    public static void createFolder(Plugin plugin) {
        File file1 = new File(plugin.getDataFolder(), "/Skills");
        file1.mkdir();
        File file2 = new File(plugin.getDataFolder(), "/Points");
        file2.mkdir();
        File file3 = new File(plugin.getDataFolder(), "/Others");
        file3.mkdir();
        File file4 = new File(plugin.getDataFolder(), "/Warps");
        file4.mkdir();
    }

    private boolean setupEconomy() {
        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider rsp = this.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = (Economy)rsp.getProvider();
        if (econ != null) {
            return true;
        }
        return false;
    }

    private boolean setupChat() {
        RegisteredServiceProvider rsp = this.getServer().getServicesManager().getRegistration(Chat.class);
        chat = (Chat)rsp.getProvider();
        if (chat != null) {
            return true;
        }
        return false;
    }

    public static Economy getEcononomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

    public static PlayerPoints getPP() {
        return playerPoints;
    }

    private boolean hookPlayerPoint() {
        Plugin pl = this.getServer().getPluginManager().getPlugin("PlayerPoints");
        playerPoints = PlayerPoints.class.cast((Object)pl);
        if (playerPoints != null) {
            return true;
        }
        return false;
    }

}

