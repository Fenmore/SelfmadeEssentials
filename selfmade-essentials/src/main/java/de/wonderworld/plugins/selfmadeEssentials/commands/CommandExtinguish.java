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


public class CommandExtinguish extends CustomCommand {

    @Override
    public boolean onCustomCommand(CommandSender sender, Command command, String label, String[] args) throws NotInstanceOfPlayerException, InvalidPlayerNameException, PlayerNotFoundException {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                throw new NotInstanceOfPlayerException();
            }
            else {
                ((Player) sender).setFireTicks(0);
            }
        }
        else {


            List<Player> playerToBurn = new ArrayList<>();
            for (String name : args) {
                Player p = Bukkit.getPlayer(name);
                if (p == null) {
                    throw new PlayerNotFoundException(name);
                }
                playerToBurn.add(p);
            }
            for (Player p : playerToBurn) {
                p.setFireTicks(0);
            }
        }
        return true;
    }
}
