package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localizationHandler.LocaleHandler;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.ArgumentNumberExpectedException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayersNotTeleportedException;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand extends CustomCommand {

    @Override
    public boolean onCustomCommand(CommandSender commandSender, Command command, String label, String[] args) throws ArgumentNumberExpectedException, InvalidPlayerNameException, PlayersNotTeleportedException, PlayerNotFoundException {
        if (commandSender instanceof Player) {
            return onPlayerCommand((Player) commandSender, command, label, args);
        } else {
            LocaleHandler.sendLocalizedMessage(commandSender, "NOT_INSTANCEOF_PLAYER");
            return true;
        }
    }

    protected abstract boolean onPlayerCommand(Player sender, Command command, String label, String[] args) throws PlayerNotFoundException, InvalidPlayerNameException, ArgumentNumberExpectedException, PlayersNotTeleportedException;
}
