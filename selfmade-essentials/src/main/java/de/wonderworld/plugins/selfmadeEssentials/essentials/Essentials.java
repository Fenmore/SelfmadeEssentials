package de.wonderworld.plugins.selfmadeEssentials.essentials;

import de.wonderworld.plugins.selfmadeEssentials.commands.*;
import de.wonderworld.plugins.selfmadeEssentials.events.*;
import de.wonderworld.plugins.selfmadeEssentials.files.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;

public class Essentials extends JavaPlugin {

    public static File dirPlayer;
    public static Scoreboard board;
    public static File dirStats;
    public static File dir;
    private CommandMsg commandMsg;
    private CommandHeal commandHeal;
    private CommandFeed commandFeed;
    private CommandSocialspy commandSocialspy;
    private CommandKillmob commandKillmob;
    private CommandSpawnmob commandSpawnmob;
    private CommandClearInventory commandClearinventory;
    private CommandFly commandFly;
    private CommandInvsee commandInvsee;
    private CommandWarp commandWarp;
    private CommandSetwarp commandSetwarp;
    private CommandDelwarp commandDelwarp;
    private CommandWarplist commandWarplist;
    private CommandSudo commandSudo;
    private CommandEnderchest commandEnderchest;
    private CommandRepair commandRepair;
    private CommandList commandList;
    private CommandTree commandTree;
    private CommandVanish commandVanish;
    private CommandBurn commandBurn;
    private CommandPtime commandPtime;
    private CommandWorkbench commandWorkbench;
    private CommandTp commandTp;
    private CommandExtinguish commandExtinguish;
    private CommandFb commandFb;
    private CommandEffect commandEffect;
    private CommandGamemode commandGamemode;
    private CommandSpeed commandSpeed;
    private CommandSuicide commandSuicide;
    private CommandGod commandGod;
    private CommandTop commandTop;
    private CommandBack commandBack;
    private CommandTphere commandTphere;
    private CommandSpawn commandSpawn;
    private CommandHome commandHome;
    private CommandSethome commandSethome;
    private CommandDelhome commandDelhome;
    private CommandTppos commandTppos;
    private EventWeatherChange eventWeatherChange;
    private EventPlayerJoin eventPlayerJoin;
    private EventPlayerQuit eventPlayerQuit;
    private EventAsyncPlayerPreLogin eventAsyncPlayerPreLogin;
    private ListYMLManager listYMLManager;
    private ModYMLManager modYMLManager;
    private WarpYMLManager warpYMLManager;
    private PlayerYMLManager playerYMLManager;
    private EventEntityDamage eventEntityDamage;


    public Essentials() {
        dir = new File(getDataFolder().toString());
        dirStats = new File(getDataFolder(), "Stats");
        dirPlayer = new File(dirStats, "Player");
        if (!(dirStats.exists())) {
            dirStats.mkdir();
        }
        if (!(dirPlayer.exists())) {
            dirPlayer.mkdir();
        }
        playerYMLManager = new PlayerYMLManager();
        warpYMLManager = new WarpYMLManager();
        modYMLManager = new ModYMLManager();
        listYMLManager = new ListYMLManager(this);
        commandMsg = new CommandMsg(modYMLManager);
        commandHeal = new CommandHeal();
        commandFeed = new CommandFeed(this);
        commandSocialspy = new CommandSocialspy(modYMLManager);
        commandKillmob = new CommandKillmob();
        commandSpawnmob = new CommandSpawnmob();
        commandClearinventory = new CommandClearInventory();
        commandFly = new CommandFly();
        commandInvsee = new CommandInvsee();
        commandWarp = new CommandWarp(warpYMLManager);
        commandSetwarp = new CommandSetwarp(warpYMLManager);
        commandDelwarp = new CommandDelwarp(warpYMLManager);
        commandWarplist = new CommandWarplist(warpYMLManager);
        commandRepair = new CommandRepair();
        commandSudo = new CommandSudo();
        commandEnderchest = new CommandEnderchest();
        commandList = new CommandList(this, listYMLManager);
        commandTree = new CommandTree();
        commandVanish = new CommandVanish(modYMLManager, this);
        commandBurn = new CommandBurn();
        commandPtime = new CommandPtime();
        commandWorkbench = new CommandWorkbench();
        commandTp = new CommandTp();
        commandSuicide = new CommandSuicide();
        commandFb = new CommandFb(this);
        commandEffect = new CommandEffect();
        commandGamemode = new CommandGamemode();
        commandExtinguish = new CommandExtinguish();
        commandSpeed = new CommandSpeed();
        commandGod = new CommandGod(modYMLManager);
        commandTop = new CommandTop();
        commandBack = new CommandBack(playerYMLManager);
        commandTphere = new CommandTphere();
        commandSpawn = new CommandSpawn(warpYMLManager);
        commandHome = new CommandHome(playerYMLManager);
        commandSethome = new CommandSethome(playerYMLManager);
        commandDelhome = new CommandDelhome(playerYMLManager);
        commandTppos = new CommandTppos();
        eventWeatherChange = new EventWeatherChange();
        eventPlayerJoin = new EventPlayerJoin();
        eventPlayerQuit = new EventPlayerQuit();
        eventAsyncPlayerPreLogin = new EventAsyncPlayerPreLogin();
        eventEntityDamage = new EventEntityDamage();
    }

    @Override
    public void onEnable() {
        board = this.getServer().getScoreboardManager().getNewScoreboard();
        this.getServer().getPluginManager().registerEvents(eventWeatherChange, this);
        this.getServer().getPluginManager().registerEvents(eventPlayerJoin, this);
        this.getServer().getPluginManager().registerEvents(eventPlayerQuit, this);
        this.getServer().getPluginManager().registerEvents(eventAsyncPlayerPreLogin, this);
        this.getServer().getPluginManager().registerEvents(eventEntityDamage, this);
        this.getCommand("msg").setExecutor(commandMsg);
        this.getCommand("heal").setExecutor(commandHeal);
        this.getCommand("feed").setExecutor(commandFeed);
        this.getCommand("socialspy").setExecutor(commandSocialspy);
        this.getCommand("killmob").setExecutor(commandKillmob);
        this.getCommand("spawnmob").setExecutor(commandSpawnmob);
        this.getCommand("clearinventory").setExecutor(commandClearinventory);
        this.getCommand("fly").setExecutor(commandFly);
        this.getCommand("invsee").setExecutor(commandInvsee);
        this.getCommand("warp").setExecutor(commandWarp);
        this.getCommand("setwarp").setExecutor(commandSetwarp);
        this.getCommand("delwarp").setExecutor(commandDelwarp);
        this.getCommand("warplist").setExecutor(commandWarplist);
        this.getCommand("sudo").setExecutor(commandSudo);
        this.getCommand("enderchest").setExecutor(commandEnderchest);
        this.getCommand("list").setExecutor(commandList);
        this.getCommand("bigtree").setExecutor(commandTree);
        this.getCommand("vanish").setExecutor(commandVanish);
        this.getCommand("burn").setExecutor(commandBurn);
        this.getCommand("ptime").setExecutor(commandPtime);
        this.getCommand("workbench").setExecutor(commandWorkbench);
        this.getCommand("suicide").setExecutor(commandSuicide);
        this.getCommand("extinguish").setExecutor(commandExtinguish);
        this.getCommand("tp").setExecutor(commandTp);
        this.getCommand("fb").setExecutor(commandFb);
        this.getCommand("effect").setExecutor(commandEffect);
        this.getCommand("repair").setExecutor(commandRepair);
        this.getCommand("gamemode").setExecutor(commandGamemode);
        this.getCommand("speed").setExecutor(commandSpeed);
        this.getCommand("god").setExecutor(commandGod);
        this.getCommand("top").setExecutor(commandTop);
        this.getCommand("back").setExecutor(commandBack);
        this.getCommand("tphere").setExecutor(commandTphere);
        this.getCommand("spawn").setExecutor(commandSpawn);
        this.getCommand("home").setExecutor(commandHome);
        this.getCommand("sethome").setExecutor(commandSethome);
        this.getCommand("delhome").setExecutor(commandDelhome);
        this.getCommand("tppos").setExecutor(commandTppos);
        fillBoard();
        fillUuid();
    }


    @Override
    public void onDisable() {

    }


    private void fillUuid() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            UuidYMLManager.setUuid(p);
        }
    }

    private void fillBoard() {
        ModYMLManager modYMLManager = new ModYMLManager();
        Team team = Essentials.board.getTeam("vanishVisible");
        if (team == null) {
            team = Essentials.board.registerNewTeam("vanishVisible");
            team.setCanSeeFriendlyInvisibles(true);
        }
        for (String vanishSee : modYMLManager.getVanishVisibleList()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (modYMLManager.getVanishVisibleList().contains(p.getName())) {
                    p.setScoreboard(board);
                }
            }
            team.addEntry(vanishSee);
        }
    }

}
