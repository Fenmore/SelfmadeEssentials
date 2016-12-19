package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetwarp implements CommandExecutor{

    private WarpYMLManager warpYMLManager;
    public CommandSetwarp() {
        this.warpYMLManager = new WarpYMLManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }

        if(args.length == 0)
            return false;

        warpYMLManager.setWarp(((Player) sender).getLocation(), args[0]);
        sender.sendMessage(EssentialCommands.message(Constants.WARP_SET_FORMAT, args[0]));

        return true;
    }
}
