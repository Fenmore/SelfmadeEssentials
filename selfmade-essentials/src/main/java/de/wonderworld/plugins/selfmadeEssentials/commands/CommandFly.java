package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }

        if (!((Player) sender).getAllowFlight()) {
            ((Player) sender).setAllowFlight(true);
            /*Location loc = ((Player) sender).getLocation();
            loc.setY(loc.getY() + 10);
            ((Player) sender).teleport(loc);*/
            sender.sendMessage(EssentialCommands.message(Constants.FLY_MODE_ON));
        } else {
            ((Player) sender).setAllowFlight(false);
            sender.sendMessage(EssentialCommands.message(Constants.FLY_MODE_OFF));
        }


        return true;
    }
}
