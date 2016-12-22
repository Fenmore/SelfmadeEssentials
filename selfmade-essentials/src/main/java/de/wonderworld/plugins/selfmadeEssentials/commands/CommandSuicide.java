package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandSuicide extends PlayerCommand {

    @Override
    public boolean onPlayerCommand(Player sender, Command command, String label, String[] args) {
        sender.setHealth(0);

        for(Player player: Bukkit.getOnlinePlayers()){
            player.sendMessage(EssentialCommands.message(LAN_EN.PLAYER_KILLED_HIMSELF_FORMAT, sender.getName()));
        }
        return true;
    }
}
