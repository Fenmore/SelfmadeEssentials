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

public class CommandHeal extends CustomCommand {

    public CommandHeal(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
    }

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException, InvalidPlayerNameException, PlayerNotFoundException {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                throw new NotInstanceOfPlayerException();
            } else {
                ((Player) sender).setHealth(((Player) sender).getMaxHealth());
            }
        } else {

            List<Player> playerToHeal = new ArrayList<>();
            for (String name : args) {
                Player p = EssentialCommands.getPlayer(name);
                playerToHeal.add(p);
            }
            for (Player p : playerToHeal)
                p.setHealth(p.getMaxHealth());
        }
        return true;
    }
}
