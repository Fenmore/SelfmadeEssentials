package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class CommandExtinguish extends CustomCommand {

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
