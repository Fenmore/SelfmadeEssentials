package de.wonderworld.plugins.selfmadeEssentials.essentials;

import de.wonderworld.plugins.selfmadeEssentials.commands.*;
import de.wonderworld.plugins.selfmadeEssentials.events.EventAsyncPlayerPreLogin;
import de.wonderworld.plugins.selfmadeEssentials.events.EventPlayerJoin;
import de.wonderworld.plugins.selfmadeEssentials.events.EventPlayerQuit;
import de.wonderworld.plugins.selfmadeEssentials.events.EventWeatherChange;
import de.wonderworld.plugins.selfmadeEssentials.files.ListYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.UuidYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
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
    private CommandClearinventory commandClearinventory;
    private CommandFly commandFly;
    private CommandInvsee commandInvsee;
    private CommandWarp commandWarp;
    private CommandSetwarp commandSetwarp;
    private CommandDelwarp commandDelwarp;
    private CommandWarplist commandWarplist;
    private CommandSudo commandSudo;
    private CommandEnderchest commandEnderchest;
    private CommandList commandList;
    private CommandTree commandTree;
    private CommandVanish commandVanish;
    private CommandBurn commandBurn;
    private CommandPtime commandPtime;
    private CommandWorkbench commandWorkbench;
    private CommandTp commandTp;
    private CommandFb commandFb;
    private CommandEffect commandEffect;
    private EventWeatherChange eventWeatherChange;
    private EventPlayerJoin eventPlayerJoin;
    private EventPlayerQuit eventPlayerQuit;
    private EventAsyncPlayerPreLogin eventAsyncPlayerPreLogin;
    private ModYMLManager modYMLManager;
    private WarpYMLManager warpYMLManager;
    private ListYMLManager listYMLManager;
    private MessageListener messageListener;

    public Essentials() {
        messageListener = new MessageListener();
        dir = new File(getDataFolder().toString());
        dirStats = new File(getDataFolder(), "Stats");
        dirPlayer = new File(dirStats, "Player");
        if (!(dirStats.exists())) {
            dirStats.mkdir();
        }
        if (!(dirPlayer.exists())) {
            dirPlayer.mkdir();
        }
        modYMLManager = new ModYMLManager();
        warpYMLManager = new WarpYMLManager(this);
        listYMLManager = new ListYMLManager(getDataFolder(), this);
        commandMsg = new CommandMsg(this, modYMLManager);
        commandHeal = new CommandHeal(this);
        commandFeed = new CommandFeed(this);
        commandSocialspy = new CommandSocialspy(modYMLManager);
        commandKillmob = new CommandKillmob();
        commandSpawnmob = new CommandSpawnmob(this);
        commandClearinventory = new CommandClearinventory();
        commandFly = new CommandFly();
        commandInvsee = new CommandInvsee(this);
        commandWarp = new CommandWarp(warpYMLManager);
        commandSetwarp = new CommandSetwarp(warpYMLManager);
        commandDelwarp = new CommandDelwarp(warpYMLManager);
        commandWarplist = new CommandWarplist(warpYMLManager);
        commandSudo = new CommandSudo(this);
        commandEnderchest = new CommandEnderchest(this);
        commandList = new CommandList(this, listYMLManager);
        commandTree = new CommandTree();
        commandVanish = new CommandVanish(modYMLManager, this);
        commandBurn = new CommandBurn(this);
        commandPtime = new CommandPtime();
        commandWorkbench = new CommandWorkbench();
        commandTp = new CommandTp();
        commandFb = new CommandFb(this);
        commandEffect = new CommandEffect();
        eventWeatherChange = new EventWeatherChange();
        eventPlayerJoin = new EventPlayerJoin(modYMLManager);
        eventPlayerQuit = new EventPlayerQuit(modYMLManager);
        eventAsyncPlayerPreLogin = new EventAsyncPlayerPreLogin();


    }

    @Override
    public void onEnable() {
        board = this.getServer().getScoreboardManager().getNewScoreboard();
        this.getServer().getPluginManager().registerEvents(eventWeatherChange, this);
        this.getServer().getPluginManager().registerEvents(eventPlayerJoin, this);
        this.getServer().getPluginManager().registerEvents(eventPlayerQuit, this);
        this.getServer().getPluginManager().registerEvents(eventAsyncPlayerPreLogin, this);
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
        this.getCommand("tp").setExecutor(commandTp);
        this.getCommand("fb").setExecutor(commandFb);
        this.getCommand("effect").setExecutor(commandEffect);
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "SimplePermission", messageListener);
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