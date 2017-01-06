package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandClearInventory extends PlayerCommand {

    public CommandClearInventory(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {

        sender.getInventory().clear();

        return true;
    }
}
