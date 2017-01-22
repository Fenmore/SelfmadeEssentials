package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Utilities;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandFeed extends CustomCommand {

    private Essentials plugin;

    public CommandFeed(LocalizedMessenger localizedMessenger, Essentials plugin) {
        super(localizedMessenger);
        this.plugin = plugin;
    }

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException, InvalidPlayerNameException, PlayerNotFoundException {

        List<Player> playersFromArgumets = EssentialCommands.getPlayersFromArgumets(args, sender);

        feedPlayers(playersFromArgumets);

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