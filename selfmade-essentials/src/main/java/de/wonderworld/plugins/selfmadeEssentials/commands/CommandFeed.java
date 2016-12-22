package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandFeed extends CustomCommand {

    private Essentials plugin;

    public CommandFeed(Essentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException, InvalidPlayerNameException, PlayerNotFoundException {


        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                throw new NotInstanceOfPlayerException();
            }
            else {
                ((Player) sender).setFoodLevel(20);
                ((Player) sender).setSaturation(20);
                ((Player) sender).setExhaustion(0);
            }
        }
        else {

            List<Player> playerToFeed = new ArrayList<>();
            for (String name : args) {
                Player p = plugin.getServer().getPlayer(name);
                if (p == null) {
                    throw new PlayerNotFoundException(name);
                }
                playerToFeed.add(p);
            }
            for (Player p : playerToFeed) {
                p.setFoodLevel(20);
                p.setSaturation(20);
                p.setExhaustion(0);
            }
        }
        return true;

    }


}




