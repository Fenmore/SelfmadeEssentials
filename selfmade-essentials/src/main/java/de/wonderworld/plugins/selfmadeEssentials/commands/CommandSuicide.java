package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandSuicide extends PlayerCommand {

    @Override
    protected boolean onPlayerCommand(Player player, Command command, String label, String[] args) {
        player.setHealth(0);

        for(Player p: Bukkit.getOnlinePlayers()){
            p.sendMessage(EssentialCommands.message(LAN_EN.PLAYER_KILLED_HIMSELF_FORMAT, player.getName()));
        }
        return true;
    }
}
