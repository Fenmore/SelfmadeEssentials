package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandEnderchest extends PlayerCommand {

    public CommandEnderchest(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) throws InvalidPlayerNameException, PlayerNotFoundException {

        if (args.length == 0) {

            return false;
        } else {
            Player p = EssentialCommands.getPlayer(args[0]);
            sender.openInventory(p.getEnderChest());

            return true;
        }
    }
}
