package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandFeed extends CustomCommand {

    private Essentials plugin;

    public CommandFeed(Essentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException, InvalidPlayerNameException, PlayerNotFoundException {

        List<Player> playerToFeed = EssentialCommands.getPlayersFromArgumets(args, sender);
        feedPlayers(playerToFeed);

        return true;
    }

    private void feedPlayers(List<Player> playerToFeed) {
        for (Player p : playerToFeed) {
            p.setFoodLevel(20);
            p.setSaturation(20);
            p.setExhaustion(0);
        }
    }
}