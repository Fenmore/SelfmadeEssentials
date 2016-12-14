package de.fenmore.tobiasThirolf.selfmadeEssentials.commands;

import de.fenmore.tobiasThirolf.selfmadeEssentials.essentials.Constants;
import de.fenmore.tobiasThirolf.selfmadeEssentials.essentials.Essentials;
import de.fenmore.tobiasThirolf.selfmadeEssentials.files.ModYMLManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fenmore on 24.11.2016.
 */
public class CommandVanish implements CommandExecutor {

    private ModYMLManager modYMLManager;
    private Essentials plugin;

    public CommandVanish(ModYMLManager modYMLManager, Essentials plugin) {
        this.modYMLManager = modYMLManager;
        this.plugin = plugin;
    }

    //Test

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(Constants.NOT_INSTANCEOF_PLAYER);
            return true;
        }

        List<String> vanishActiveList = modYMLManager.getVanishActiveList();
        //List<String> vanishSeeList = modYMLManager.getVanishVisibleList();
        if(args.length == 0) {
            System.out.println(Essentials.board.getTeam("vanishVisible").getEntries());
            if(Essentials.board.getTeam("vanishVisible").getEntries().contains(sender.getName())) {
                if (!vanishActiveList.contains(sender.getName())) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (!Essentials.board.getTeam("vanishVisible").getEntries().contains(player.getName())) {
                            player.hidePlayer((Player) sender);
                            player.sendMessage(EssentialCommands.message(Constants.PLAYER_FAKE_QUIT_FORMAT, sender.getName()));
                        } else {
                            player.sendMessage(EssentialCommands.message(Constants.PLAYER_ENTERED_VANISH, sender.getName()));
                        }
                    }
                    modYMLManager.setVanish(sender.getName());
                    Player pSender = (Player) sender;
                    pSender.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
                    sender.sendMessage(EssentialCommands.message(Constants.VANISH_ACTIVE));
                } else {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (!Essentials.board.getTeam("vanishVisible").getEntries().contains(player.getName())) {
                            player.showPlayer((Player) sender);
                            player.sendMessage(EssentialCommands.message(Constants.PLAYER_FAKE_JOIN_FORMAT, sender.getName()));
                        } else {
                            player.sendMessage(EssentialCommands.message(Constants.PLAYER_LEFT_VANISH_FORMAT, sender.getName()));
                        }
                    }
                    modYMLManager.remVanish(sender.getName());
                    Player pSender = (Player) sender;
                    pSender.removePotionEffect(PotionEffectType.INVISIBILITY);
                    sender.sendMessage(EssentialCommands.message(Constants.VANISH_INACTIVE));
                }
            }
            else {
                sender.sendMessage(EssentialCommands.message(Constants.VANISH_NOT_PERMITTED));
            }
        }
        else if(args[0].equalsIgnoreCase("see")) {
            if(!sender.hasPermission("selfmadeEssentials.vanish")) {
                sender.addAttachment(plugin, "selfmadeEssentials.vanish", true);
                for(Player player : Bukkit.getOnlinePlayers()) {
                    if(vanishActiveList.contains(player.getName())) {
                        ((Player) sender).showPlayer(player);
                    }
                }
                addPlayerToBoard(sender.getName());
                sender.sendMessage(EssentialCommands.message(Constants.ENTERED_VANISH_VISIBLE));
            }
            else {
                for(PermissionAttachmentInfo info : sender.getEffectivePermissions()) {
                    if (info.getPermission().equals("selfmadeEssentials.vanish"))
                        sender.removeAttachment(info.getAttachment());
                }
                if(vanishActiveList.contains(sender.getName())) {
                    modYMLManager.remVanish(sender.getName());
                    Player pSender = (Player) sender;
                    pSender.removePotionEffect(PotionEffectType.INVISIBILITY);
                    sender.sendMessage(EssentialCommands.message(Constants.VANISH_INACTIVE));
                }
                for(Player player : Bukkit.getOnlinePlayers()) {
                    if(vanishActiveList.contains(player.getName())) {
                        ((Player) sender).hidePlayer(player);
                    }
                }
                removePlayerFromBoard(sender.getName());
                sender.sendMessage(EssentialCommands.message(Constants.LEFT_VANISH_VISIBLE));
            }
        }
        else if(args[0].equalsIgnoreCase("list")) {
            List<String> list = new ArrayList<>();
            for(Player player : Bukkit.getOnlinePlayers()) {
                if(player.hasPermission("selfmadeEssentials.vanish"))
                    list.add(player.getName());
            }
            sender.sendMessage(EssentialCommands.message(Constants.VANISH_LIST_INTRO));
            sender.sendMessage(list.toString());
            //sender.sendMessage(vanishActiveList.toString() + "       " + vanishSeeList.toString() + "         " + Essentials.board.getTeam("vanishVisible").getEntries().toString());
        }

        return true;
    }

    public void removePlayerFromBoard(String entry) {
        Essentials.board.getTeam("vanishVisible").removeEntry(entry);
        Bukkit.getPlayer(entry).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    public void addPlayerToBoard(String entry) {
        Essentials.board.getTeam("vanishVisible").addEntry(entry);
        Bukkit.getPlayer(entry).setScoreboard(Essentials.board);
    }

}
