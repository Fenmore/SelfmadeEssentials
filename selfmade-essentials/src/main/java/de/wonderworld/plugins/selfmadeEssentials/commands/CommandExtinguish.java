package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public class CommandExtinguish extends CustomCommand {

    public CommandExtinguish(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
    }

    @Override
    public boolean onCustomCommand(CommandSender sender, Command command, String label, String[] args) throws NotInstanceOfPlayerException, InvalidPlayerNameException, PlayerNotFoundException {

        List<Player> playersToExtinguish = EssentialCommands.getPlayersFromArgumets(args, sender);
        extinguishPlayers(playersToExtinguish);

        return true;
    }

    private void extinguishPlayers(List<Player> playersToExtinguish) {
        for (Player p : playersToExtinguish) {
            p.setFireTicks(0);
        }
    }

}
