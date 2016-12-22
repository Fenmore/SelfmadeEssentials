package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSudo extends CustomCommand{

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length <= 1) {
            return false;
        }

        Player p = Bukkit.getPlayer(args[0]);
        if(p == null) {
            sender.sendMessage(EssentialCommands.message(LAN_EN.PLAYER_NOT_FOUND_FORMAT));
            return true;
        }

        if(args[1].equalsIgnoreCase("msg") || args[1].equalsIgnoreCase("r")) {
            sender.sendMessage(EssentialCommands.message(LAN_EN.SUDO_COMMAND_NOT_ALLOWED));
            return true;
        }

        String command = EssentialCommands.mergeArgs(args, 1);
        p.performCommand(command);

        return true;
    }
}
