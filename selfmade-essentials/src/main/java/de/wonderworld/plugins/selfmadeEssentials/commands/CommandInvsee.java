package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandInvsee extends PlayerCommand {

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) throws InvalidPlayerNameException, PlayerNotFoundException {
        if (args.length == 0)
            return false;

        Player p = EssentialCommands.getPlayer(args[0]);

        sender.openInventory(p.getInventory());
        return true;
    }
}
