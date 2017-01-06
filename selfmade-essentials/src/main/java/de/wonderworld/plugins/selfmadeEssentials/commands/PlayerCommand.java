package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand extends CustomCommand {

    private LocalizedMessenger localizedMessengerPlayerCommand;

    public PlayerCommand(LocalizedMessenger localizedMessenger) {
        this.localizedMessengerPlayerCommand = localizedMessenger;
    }

    @Override
    public boolean onCustomCommand(CommandSender commandSender, Command command, String label, String[] args) throws ArgumentNumberExpectedException, InvalidPlayerNameException, PlayersNotTeleportedException, PlayerNotFoundException, NotInstanceOfPlayerException {
        if (commandSender instanceof Player) {
            return onPlayerCommand((Player) commandSender, command, label, args);
        } else {
            localizedMessengerPlayerCommand.sendLocalizedMessage(commandSender, "NOT_INSTANCEOF_PLAYER");
            return true;
        }
    }

    protected abstract boolean onPlayerCommand(Player sender, Command command, String label, String[] args) throws PlayerNotFoundException, InvalidPlayerNameException, ArgumentNumberExpectedException, PlayersNotTeleportedException, NotInstanceOfPlayerException;
}
