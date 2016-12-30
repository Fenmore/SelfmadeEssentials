package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localizationHandler.LocaleHandler;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.*;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class CustomCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            return onCustomCommand(sender, command, label, args);
        } catch (PlayerNotFoundException e) {
            LocaleHandler.sendLocalizedMessage(sender, "PLAYER_NOT_FOUND_FORMAT", e.getPlayerName());
        } catch (NotInstanceOfPlayerException e) {
            LocaleHandler.sendLocalizedMessage(sender, "NOT_INSTANCEOF_PLAYER");
        } catch (InvalidPlayerNameException e) {
            LocaleHandler.sendLocalizedMessage(sender, "NOT_VALID_PLAYER_NAME_FORMAT", e.getPlayerName());
        } catch (PlayersNotTeleportedException e) {
            LocaleHandler.sendLocalizedMessage(sender, "PLAYERS_NOT_TELEPORTED_FORMAT", e.getPlayers().toString());
        } catch (ArgumentNumberExpectedException e) {
            LocaleHandler.sendLocalizedMessage(sender, "NUMBER_EXPECTED_FORMAT", e.getArgument());
        }
        return true;
    }

    protected abstract boolean onCustomCommand(CommandSender sender, Command command, String label, String[] args) throws PlayerNotFoundException, NotInstanceOfPlayerException, InvalidPlayerNameException, PlayersNotTeleportedException, ArgumentNumberExpectedException;

}
