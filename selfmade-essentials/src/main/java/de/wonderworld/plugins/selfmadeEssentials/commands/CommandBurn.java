package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandBurn extends CustomCommand {

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException, InvalidPlayerNameException, PlayerNotFoundException {
        for (Player p : getPlayerToBurn(sender, args)) {
            p.setFireTicks(Integer.MAX_VALUE);
        }
        return true;
    }

    private List<Player> getPlayerToBurn(CommandSender sender, String[] args) throws InvalidPlayerNameException, PlayerNotFoundException, NotInstanceOfPlayerException {
        List<Player> playerToBurn = new ArrayList<>();
        if (playerToBurnIsSender(args)) {
            if (sender instanceof Player) {
                playerToBurn.add((Player) sender);
            } else {
                throw new NotInstanceOfPlayerException();
            }
        } else {
            addPlayers(args, playerToBurn);
        }
        return playerToBurn;
    }

    private void addPlayers(String[] args, List<Player> playerToBurn) throws PlayerNotFoundException, InvalidPlayerNameException {
        for (String name : args) {
            Player p = EssentialCommands.getPlayer(name);
            playerToBurn.add(p);
        }
    }

    private boolean playerToBurnIsSender(String[] args) {
        if (args.length == 0)
            return true;
        else
            return false;
    }
}

