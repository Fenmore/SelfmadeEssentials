package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandHeal implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(EssentialCommands.message(LAN_EN.NOT_INSTANCEOF_PLAYER));
            } else {
                ((Player) sender).setHealth(((Player) sender).getMaxHealth());
            }
        } else {
            for (String name : args) {
                if (!EssentialCommands.validPlayerName(name)) {
                    sender.sendMessage(EssentialCommands.message(LAN_EN.NOT_VALID_PLAYER_NAME_FORMAT, name));
                    return true;
                }
            }
            List<Player> playerToHeal = new ArrayList<>();
            for (String name : args) {
                Player p = Bukkit.getPlayer(name);
                if (p == null) {
                    sender.sendMessage(EssentialCommands.message(LAN_EN.PLAYER_NOT_FOUND_FORMAT, name));
                    return true;
                }
                playerToHeal.add(p);
            }
            for (Player p : playerToHeal)
                p.setHealth(p.getMaxHealth());
        }
        return true;
    }
}
