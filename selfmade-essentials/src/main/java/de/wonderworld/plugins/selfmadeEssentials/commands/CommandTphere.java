package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayersNotTeleportedException;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandTphere extends PlayerCommand {

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) throws InvalidPlayerNameException, PlayerNotFoundException, PlayersNotTeleportedException {
        if (neededArgsLength(args)) {

            Player[] players = getPlayersToTeleport(args);

            EssentialCommands.teleportPlayers(players, sender.getLocation());
            return true;
        } else {
            return false;
        }
    }

    private Player[] getPlayersToTeleport(String[] args) throws InvalidPlayerNameException, PlayerNotFoundException {
        Player[] players = new Player[args.length];
        for (int i = 0; i < args.length; i++) {
            players[i] = EssentialCommands.getPlayer(args[i]);
        }
        return players;
    }

    private boolean neededArgsLength(String[] args) {
        return args.length > 0;
    }
}
