package de.wonderworld.plugins.selfmadeEssentials.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandWorkbench extends PlayerCommand {
    
    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        sender.openWorkbench(null, true);
        return true;
    }
}
