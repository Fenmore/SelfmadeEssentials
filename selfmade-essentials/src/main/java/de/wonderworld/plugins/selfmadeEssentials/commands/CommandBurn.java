package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandBurn extends CustomCommand {

    public CommandBurn(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
    }

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException, InvalidPlayerNameException, PlayerNotFoundException {

        List<Player> playerToBurn = getPlayerToBurn(sender, args);

        for (Player p : playerToBurn) {
            p.setFireTicks(Integer.MAX_VALUE);
        }

        return true;
    }

    private List<Player> getPlayerToBurn(CommandSender sender, String[] args) throws InvalidPlayerNameException, PlayerNotFoundException, NotInstanceOfPlayerException {

        List<Player> playerToBurn = new ArrayList<>();
        if (isBurningSender(args)) {
            playerToBurn.add(getSenderAsPlayer(sender));
        } else {
            playerToBurn.addAll(getPlayersFromArgs(args));
        }

        return playerToBurn;
    }

    private Player getSenderAsPlayer(CommandSender sender) throws NotInstanceOfPlayerException {

        if (sender instanceof Player) {

            return ((Player) sender);
        } else {
            throw new NotInstanceOfPlayerException();
        }
    }

    private List<Player> getPlayersFromArgs(String[] args) throws PlayerNotFoundException, InvalidPlayerNameException {

        List<Player> players = new ArrayList<>();

        for (String name : args) {
            Player p = EssentialCommands.getPlayer(name);
            players.add(p);
        }

        return players;
    }

    private boolean isBurningSender(String[] args) {
        return args.length == 0;
    }
}

