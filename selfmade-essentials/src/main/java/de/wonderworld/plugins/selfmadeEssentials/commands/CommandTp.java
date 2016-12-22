package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTp extends CustomCommand {

    private PlayerYMLManager playerYMLManager;

    public CommandTp(PlayerYMLManager playerYMLManager) {
        this.playerYMLManager = playerYMLManager;
    }

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException, PlayerNotFoundException, InvalidPlayerNameException {
        if (requiredArgsLength(args)) {

            Player[] playersToTeleport = getPlayersToTeleport(args, sender);
            Player playerDestination = getDestination(args);

            teleportPlayers(playersToTeleport, playerDestination);

            return true;
        } else {
            return false;
        }
    }

    private void teleportPlayers(Player[] playersToTeleport, Player playerDestination) {
        for (Player p : playersToTeleport) {
            playerYMLManager.setBackLocation(p.getName(), p.getLocation());
            if (playerDestination.isFlying()) {
                if (p.getAllowFlight()) {
                    p.teleport(playerDestination.getLocation());
                    p.setFlying(true);
                } else {
                    p.teleport(EssentialCommands.getSafeLocation(playerDestination.getLocation()));
                }
            } else {
                p.teleport(playerDestination.getLocation());
            }
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
                Player[] players = {(Player) sender};
                return players;
            } else {
                throw new NotInstanceOfPlayerException();
            }
        } else {
            Player[] players = new Player[args.length - 1];
            for (int i = 0; i <= args.length - 2; i++) {
                Player p = Bukkit.getPlayer(args[i]);
                if (p == null)
                    throw new PlayerNotFoundException(args[i]);
                else
                    players[i] = p;
            }
            return players;
        }
    }

    private boolean requiredArgsLength(String[] args) {
        if (args.length >= 1)
            return true;
        else
            return false;
    }
}
