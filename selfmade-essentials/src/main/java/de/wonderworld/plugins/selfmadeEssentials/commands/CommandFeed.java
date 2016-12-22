package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandFeed implements CommandExecutor {

    private Essentials plugin;

    public CommandFeed(Essentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(EssentialCommands.message(LAN_EN.NOT_INSTANCEOF_PLAYER));
            }
            else {
                ((Player) sender).setFoodLevel(20);
                ((Player) sender).setSaturation(20);
                ((Player) sender).setExhaustion(0);
            }
        }
        else {
            for (String name : args) {
                if (!EssentialCommands.validPlayerName(name)) {
                    sender.sendMessage(EssentialCommands.message(LAN_EN.NOT_VALID_PLAYER_NAME_FORMAT, name));
                    return true;
                }
            }
            List<Player> playerToFeed = new ArrayList<>();
            for (String name : args) {
                Player p = plugin.getServer().getPlayer(name);
                if (p == null) {
                    sender.sendMessage(EssentialCommands.message(LAN_EN.PLAYER_NOT_FOUND_FORMAT, name));
                    return true;
                }
                playerToFeed.add(p);
            }
            for (Player p : playerToFeed) {
                p.setFoodLevel(20);
                p.setSaturation(20);
                p.setExhaustion(0);
            }
        }
        return true;

    }


}




