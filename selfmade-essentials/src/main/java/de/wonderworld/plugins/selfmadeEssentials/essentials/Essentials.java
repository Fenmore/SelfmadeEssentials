package de.wonderworld.plugins.selfmadeEssentials.essentials;

import de.fenmore.localization.LocalizedMessenger;
import de.fenmore.localization.Translations;
import de.wonderworld.plugins.selfmadeEssentials.commands.*;
import de.wonderworld.plugins.selfmadeEssentials.events.*;
import de.wonderworld.plugins.selfmadeEssentials.files.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;

public class Essentials extends JavaPlugin {

    public static File dirPlayer;
    public static Scoreboard board;
    public static File dirStats;
    public static File dir;
    public static String vanishPermission;
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
    private CommandUnlimited commandUnlimited;
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
    private EventPlayerItemConsume eventPlayerItemConsume;
    private EventWeatherChange eventWeatherChange;
    private EventPlayerJoin eventPlayerJoin;
    private EventPlayerQuit eventPlayerQuit;
    private EventAsyncPlayerPreLogin eventAsyncPlayerPreLogin;
    private EventEntityDamage eventEntityDamage;
    private EventBlockPlace eventBlockPlace;


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
        PlayerYMLManager playerYMLManager = new PlayerYMLManager();
        WarpYMLManager warpYMLManager = new WarpYMLManager();
        ModYMLManager modYMLManager = new ModYMLManager();
        ListYMLManager listYMLManager = new ListYMLManager(this);
        LocalizedMessenger localizedMessenger = Translations.getLocalizedMessenger(this);
        commandMsg = new CommandMsg(modYMLManager, localizedMessenger);
        commandHeal = new CommandHeal(localizedMessenger);
        commandFeed = new CommandFeed(localizedMessenger, this);
        commandSocialspy = new CommandSocialspy(modYMLManager, localizedMessenger);
        commandKillmob = new CommandKillmob(localizedMessenger);
        commandSpawnmob = new CommandSpawnmob(localizedMessenger);
        commandClearinventory = new CommandClearInventory(localizedMessenger);
        commandFly = new CommandFly(localizedMessenger);
        commandInvsee = new CommandInvsee(localizedMessenger);
        commandWarp = new CommandWarp(warpYMLManager, localizedMessenger);
        commandSetwarp = new CommandSetwarp(warpYMLManager, localizedMessenger);
        commandDelwarp = new CommandDelwarp(warpYMLManager, localizedMessenger);
        commandWarplist = new CommandWarplist(warpYMLManager);
        commandRepair = new CommandRepair(localizedMessenger);
        commandSudo = new CommandSudo(localizedMessenger);
        commandEnderchest = new CommandEnderchest(localizedMessenger);
        commandList = new CommandList(this, listYMLManager, localizedMessenger);
        commandTree = new CommandTree(localizedMessenger);
        commandVanish = new CommandVanish(modYMLManager, localizedMessenger);
        commandBurn = new CommandBurn(localizedMessenger);
        commandPtime = new CommandPtime(localizedMessenger);
        commandWorkbench = new CommandWorkbench(localizedMessenger);
        commandTp = new CommandTp();
        commandSuicide = new CommandSuicide(localizedMessenger);
        commandFb = new CommandFb(this);
        commandEffect = new CommandEffect(localizedMessenger);
        commandGamemode = new CommandGamemode(localizedMessenger);
        commandExtinguish = new CommandExtinguish(localizedMessenger);
        commandSpeed = new CommandSpeed(localizedMessenger);
        commandGod = new CommandGod(modYMLManager, localizedMessenger);
        commandTop = new CommandTop(localizedMessenger);
        commandBack = new CommandBack(localizedMessenger, playerYMLManager);
        commandTphere = new CommandTphere(localizedMessenger);
        commandSpawn = new CommandSpawn(localizedMessenger, warpYMLManager);
        commandHome = new CommandHome(playerYMLManager, localizedMessenger);
        commandSethome = new CommandSethome(playerYMLManager, localizedMessenger);
        commandDelhome = new CommandDelhome(playerYMLManager, localizedMessenger);
        commandTppos = new CommandTppos(localizedMessenger);
        commandUnlimited = new CommandUnlimited(localizedMessenger, playerYMLManager);
        eventWeatherChange = new EventWeatherChange();
        eventPlayerJoin = new EventPlayerJoin(modYMLManager, localizedMessenger, playerYMLManager);
        eventPlayerQuit = new EventPlayerQuit(localizedMessenger);
        eventAsyncPlayerPreLogin = new EventAsyncPlayerPreLogin();
        eventEntityDamage = new EventEntityDamage();
        eventPlayerItemConsume = new EventPlayerItemConsume();
        eventBlockPlace = new EventBlockPlace();
    }

    @Override
    public void onEnable() {
        vanishPermission = this.getCommand("vanish").getPermission();

        board = this.getServer().getScoreboardManager().getNewScoreboard();

        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(eventWeatherChange, this);
        pluginManager.registerEvents(eventPlayerJoin, this);
        pluginManager.registerEvents(eventPlayerItemConsume, this);
        pluginManager.registerEvents(eventPlayerQuit, this);
        pluginManager.registerEvents(eventAsyncPlayerPreLogin, this);
        pluginManager.registerEvents(eventEntityDamage, this);
        pluginManager.registerEvents(eventBlockPlace, this);

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
        this.getCommand("unlimited").setExecutor(commandUnlimited);
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

        Translations.clear();
    }

    public void vanishPermissionSet(String playerName) {

        Utilities.vanishPermissionSet(playerName);
    }

    public void vanishPermissionRemoved(String playerName) {

        Utilities.vanishPermissionRemoved(playerName);
    }


    private void fillUuid() {

        Bukkit.getOnlinePlayers().forEach(UuidYMLManager::setUuid);
    }

    private void fillBoard() {

        Team team = getTeam();

        Bukkit.getOnlinePlayers()
                .stream()
                .filter(p -> p.hasPermission(vanishPermission))
                .forEach(p -> {
                    p.setScoreboard(board);
                    team.addEntry(p.getName());
                });
    }

    private Team getTeam() {

        Team team = Essentials.board.getTeam("vanishVisible");

        if (team == null) {
            team = Essentials.board.registerNewTeam("vanishVisible");
            team.setCanSeeFriendlyInvisibles(true);
        }
        return team;
    }

}
