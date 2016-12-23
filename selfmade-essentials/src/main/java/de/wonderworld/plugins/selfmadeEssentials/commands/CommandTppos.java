package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.ArgumentNumberExpectedException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayersNotTeleportedException;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandTppos extends PlayerCommand {

    @Override
    public boolean onPlayerCommand(Player sender, Command command, String label, String[] args) throws PlayerNotFoundException, InvalidPlayerNameException, ArgumentNumberExpectedException, PlayersNotTeleportedException {
        if(neededArgsLength(args)) {
            Player[] playersToTeleport = getPlayersToTeleport(sender, args);
            Location destination = getDestination(sender.getLocation().getWorld(), args);

            EssentialCommands.teleportPlayers(playersToTeleport, destination);
            return true;
        }
        else {
            return false;
        }
    }

    private boolean neededArgsLength(String[] args) {
        return args.length >= 3;
    }

    private Location getDestination(World playerWorld, String[] args) throws ArgumentNumberExpectedException {
        Location loc = getDestinationWithoutWorld(args);
        loc.setWorld(playerWorld);
        return loc;
    }

    private Location getDestinationWithoutWorld(String[] args) throws ArgumentNumberExpectedException {
        Double x;
        Double y;
        Double z;
        try {
            x = Double.valueOf(args[args.length - 3]);
        } catch (NumberFormatException e) {
            throw new ArgumentNumberExpectedException(args[args.length - 3]);
        }
        try {
            y = Double.valueOf(args[args.length - 2]);
        } catch (NumberFormatException e) {
            throw new ArgumentNumberExpectedException(args[args.length - 2]);
        }
        try {
            z = Double.valueOf(args[args.length - 1]);
        } catch (NumberFormatException e) {
            throw new ArgumentNumberExpectedException(args[args.length - 1]);
        }
        return new Location(null, x, y, z);
    }

    private Player[] getPlayersToTeleport(Player sender, String[] args) throws InvalidPlayerNameException, PlayerNotFoundException {
        if(args.length == 3) {
            return new Player[]{sender};
        }
        else {
            Player[] players = new Player[args.length - 3];
            for(int i = 0; i <= args.length - 4; i++) {
                players[i] = EssentialCommands.getPlayer(args[i]);
            }
            return players;
        }
    }
}
