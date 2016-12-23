package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayersNotTeleportedException;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTp extends CustomCommand {

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException, PlayerNotFoundException, InvalidPlayerNameException, PlayersNotTeleportedException {
        if (requiredArgsLength(args)) {

            Player[] playersToTeleport = getPlayersToTeleport(args, sender);
            Player playerDestination = getDestination(args);

            EssentialCommands.teleportPlayers(playersToTeleport, playerDestination.getLocation());
            return true;
        } else {
            return false;
        }
    }

    private Player getDestination(String[] args) throws PlayerNotFoundException, InvalidPlayerNameException {
        Player destination = Bukkit.getPlayer(args[args.length - 1]);
        if (destination == null)
            throw new PlayerNotFoundException(args[args.length - 1]);
        else
            return destination;
    }

    private Player[] getPlayersToTeleport(String[] args, CommandSender sender) throws PlayerNotFoundException, NotInstanceOfPlayerException, InvalidPlayerNameException {
        if (args.length == 1) {
            if (sender instanceof Player) {
                return new Player[]{(Player) sender};
            } else {
                throw new NotInstanceOfPlayerException();
            }
        } else {
            Player[] players = new Player[args.length - 1];
            for (int i = 0; i <= args.length - 2; i++) {
                players[i] = EssentialCommands.getPlayer(args[i]);
            }
            return players;
        }
    }

    private boolean requiredArgsLength(String[] args) {
        return args.length >= 1;
    }
}
