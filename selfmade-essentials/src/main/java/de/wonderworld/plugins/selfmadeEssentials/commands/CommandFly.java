package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p;
        if(args.length == 0){
            if (!(sender instanceof Player)) {
                sender.sendMessage(EssentialCommands.message(LAN_EN.NOT_INSTANCEOF_PLAYER));
                return true;
            }
            else {
                p = (Player) sender;
            }
        }
        else{
            p = Bukkit.getServer().getPlayer(args[0]);
            if(p == null ){
                sender.sendMessage(EssentialCommands.message(LAN_EN.PLAYER_NOT_FOUND_FORMAT, args[0]));
                return true;
            }
        }

        if (!p.getAllowFlight()) {
            p.setAllowFlight(true);
            p.sendMessage(EssentialCommands.message(LAN_EN.FLY_MODE_ON));
        } else {
            p.setAllowFlight(false);
            p.sendMessage(EssentialCommands.message(LAN_EN.FLY_MODE_OFF));
        }
        return true;
    }
}