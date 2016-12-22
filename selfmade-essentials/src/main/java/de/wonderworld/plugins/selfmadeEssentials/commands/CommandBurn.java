package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandBurn extends CustomCommand {

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = ((Player) sender);
                player.setFireTicks(Integer.MAX_VALUE);
            }
            else {
                throw new NotInstanceOfPlayerException();
            }
            return true;
        }


        List<Player> playerToBurn = new ArrayList<>();
        for (String name : args) {
            Player p = Bukkit.getPlayer(name);
            if (p == null) {
                sender.sendMessage(EssentialCommands.message(LAN_EN.PLAYER_NOT_FOUND_FORMAT, name));
                return true;
            }
            playerToBurn.add(p);
        }
        for (Player p : playerToBurn) {
            p.setFireTicks(Integer.MAX_VALUE);
        }
        return true;

    }
}

