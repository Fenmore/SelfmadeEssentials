package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PlayerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            try {
                return onPlayerCommand((Player) commandSender, command, label, args);
            } catch (InvalidPlayerNameException e) {
                commandSender.sendMessage(EssentialCommands.message(LAN_EN.NOT_VALID_PLAYER_NAME_FORMAT, e.getPlayerName()));
            } catch (PlayerNotFoundException e) {
                commandSender.sendMessage(EssentialCommands.message(LAN_EN.PLAYER_NOT_FOUND_FORMAT, e.getPlayerName()));
            }
            return true;
        } else {
            commandSender.sendMessage(EssentialCommands.message(LAN_EN.NOT_INSTANCEOF_PLAYER));
            return true;
        }
    }

    protected abstract boolean onPlayerCommand(Player player, Command command, String label, String[] args) throws PlayerNotFoundException, InvalidPlayerNameException;
}
