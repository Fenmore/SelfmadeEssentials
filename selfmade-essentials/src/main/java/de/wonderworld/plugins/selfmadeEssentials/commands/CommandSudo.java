package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSudo implements CommandExecutor{

    private Essentials plugin;
    public CommandSudo(Essentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length <= 1) {
            return false;
        }

        Player p = plugin.getServer().getPlayer(args[0]);
        if(p == null) {
            sender.sendMessage(EssentialCommands.message(Constants.PLAYER_NOT_FOUND_FORMAT));
            return true;
        }

        String command = EssentialCommands.mergeArgs(args, 1);
        p.performCommand(command);

        return true;
    }
}
