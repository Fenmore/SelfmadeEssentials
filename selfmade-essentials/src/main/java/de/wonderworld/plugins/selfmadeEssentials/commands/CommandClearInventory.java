package de.wonderworld.plugins.selfmadeEssentials.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandClearInventory extends PlayerCommand {

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        sender.getInventory().clear();
        return true;
    }
}
