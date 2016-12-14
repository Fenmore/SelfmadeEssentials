package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEnderchest implements CommandExecutor {

    private Essentials plugin;

    public CommandEnderchest(Essentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }

        if (args.length == 0)
            return false;

        Player p = plugin.getServer().getPlayer(args[0]);
        if (p == null) {
            sender.sendMessage(EssentialCommands.message(Constants.PLAYER_NOT_FOUND_FORMAT, args[0]));
            return true;
        }

        ((Player) sender).openInventory(p.getEnderChest());


        return true;
    }
}
