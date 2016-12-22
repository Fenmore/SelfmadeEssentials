package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class CommandTphere implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(LAN_EN.NOT_INSTANCEOF_PLAYER));
            return true;
        }

        if(args.length == 0) {
            return false;
        }

        Player p = Bukkit.getPlayer(args[0]);
        if(p == null) {
            sender.sendMessage(EssentialCommands.message(LAN_EN.PLAYER_NOT_FOUND_FORMAT, args[0]));
            return true;
        }

        new PlayerYMLManager().setBackLocation(p.getName(), p.getLocation());
        if(((Player) sender).isFlying()) {
            if(p.getAllowFlight()) {
                p.teleport((Entity) sender);
                p.setFlying(true);
            }
            else {
                p.teleport(EssentialCommands.getSafeLocation(((Player) sender).getLocation()));
            }
        }
        else {
            p.teleport((Entity) sender);
        }
        return true;
    }
}
