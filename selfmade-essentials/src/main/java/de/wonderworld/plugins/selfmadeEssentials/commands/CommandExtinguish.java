package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class CommandExtinguish implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(EssentialCommands.message(LAN_EN.NOT_INSTANCEOF_PLAYER));
            }
            else {
                ((Player) sender).setFireTicks(0);
            }
        }
        else {


            List<Player> playerToBurn = new ArrayList<>();
            for (String name : args) {
                Player p = Bukkit.getPlayer(name);
                if (p == null) {
                    sender.sendMessage(EssentialCommands.message(LAN_EN.PLAYER_NOT_FOUND_FORMAT, name));
                    return true;
                }
                playerToBurn.add(p);
            }
            for (Player p : playerToBurn) {
                p.setFireTicks(0);
            }
        }
        return true;
    }
}
