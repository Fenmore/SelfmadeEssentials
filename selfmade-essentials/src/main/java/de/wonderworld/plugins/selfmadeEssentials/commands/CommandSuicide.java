package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandSuicide implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(LAN_EN.NOT_INSTANCEOF_PLAYER));
            return true;
        }
        ((Player) sender).setHealth(0);

        for(Player player: Bukkit.getOnlinePlayers()){
            player.sendMessage(EssentialCommands.message(LAN_EN.PLAYER_KILLED_HIMSELF_FORMAT, sender.getName()));
        }
        return true;
    }
}
