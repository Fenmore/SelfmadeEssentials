package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandWorkbench extends PlayerCommand {


    public CommandWorkbench(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        sender.openWorkbench(null, true);
        return true;
    }
}
