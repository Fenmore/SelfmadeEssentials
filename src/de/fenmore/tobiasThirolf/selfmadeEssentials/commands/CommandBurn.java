package de.fenmore.tobiasThirolf.selfmadeEssentials.commands;

import de.fenmore.tobiasThirolf.selfmadeEssentials.essentials.Constants;
import de.fenmore.tobiasThirolf.selfmadeEssentials.essentials.Essentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * https://hub.spigotmc.org/javadocs/bukkit/     setFireTicks
 */
public class CommandBurn implements CommandExecutor {

    private Essentials plugin;
    public CommandBurn(Essentials essentials) {
        this.plugin = essentials;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
        }
        if (args.length == 0) {
            ((Player) sender).setFireTicks(Integer.MAX_VALUE);
            return true;
        }

        for (String name : args) {
            if (!EssentialCommands.validPlayerName(name)) {
                sender.sendMessage(EssentialCommands.message(Constants.NOT_VALID_PLAYER_NAME_FORMAT, name));
                return true;
            }
        }

        List<Player> playerToBurn = new ArrayList<>();
        for (String name : args) {
            Player p = plugin.getServer().getPlayer(name);
            if (p == null) {
                sender.sendMessage(EssentialCommands.message(Constants.PLAYER_NOT_FOUND_FORMAT, name));
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

