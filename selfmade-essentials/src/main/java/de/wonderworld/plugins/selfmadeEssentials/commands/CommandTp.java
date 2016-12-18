package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTp implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            return false;
        }

        if(args.length == 3) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
                return true;
            }
            try {
                ((Player) sender).teleport(new Location(((Player) sender).getWorld(), Double.valueOf(args[0]), Double.valueOf(args[1]), Double.valueOf(args[2])));
            } catch (NumberFormatException e) {
                sender.sendMessage(EssentialCommands.message(Constants.TP_NUMBERS_EXPECTED));
            }
            return true;
        }

        Player p1 = Bukkit.getPlayer(args[0]);
        if(p1 == null) {
            sender.sendMessage(EssentialCommands.message(Constants.PLAYER_NOT_FOUND_FORMAT, args[0]));
            return true;
        }

        if(args.length == 1) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
                return true;
            }
            if(p1.isFlying()) {
                if (((Player) sender).getAllowFlight()) {
                    ((Player) sender).setFlying(true);
                }
                new PlayerYMLManager().setBackLocation(sender.getName(), ((Player) sender).getLocation());
                ((Player) sender).teleport(EssentialCommands.getSafeLocation(p1.getLocation()));
            }
            else {
                new PlayerYMLManager().setBackLocation(sender.getName(), ((Player) sender).getLocation());
                ((Player) sender).teleport(p1);
            }
            return true;
        }

        Player p2 = Bukkit.getPlayer(args[1]);
        if(p2 == null) {
            sender.sendMessage(EssentialCommands.message(Constants.PLAYER_NOT_FOUND_FORMAT, args[0]));
            return true;
        }

        new PlayerYMLManager().setBackLocation(p1.getName(), p1.getLocation());
        if (p2.isFlying()) {
            if(p1.getAllowFlight()) {
                p1.teleport(p2.getLocation());
                p1.setFlying(true);
            }
            else {
                p1.teleport(EssentialCommands.getSafeLocation(p2.getLocation()));
            }
        }
        else {
            p1.teleport(p2);
        }

        return true;
    }
}
