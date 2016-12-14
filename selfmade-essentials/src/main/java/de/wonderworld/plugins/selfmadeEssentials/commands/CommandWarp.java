package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWarp implements CommandExecutor{

    private WarpYMLManager warpYMLManager;
    public CommandWarp(WarpYMLManager warpYMLManager) {
        this.warpYMLManager = warpYMLManager;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }

        if(args.length == 0)
            return false;

        Location warp = warpYMLManager.getWarp(args[0]);
        if(warp == null) {
            sender.sendMessage(EssentialCommands.message(Constants.WARP_DOES_NOT_EXIST_FORMAT, args[0]));
            return true;
        }

        if(warp.getWorld() != null)
            ((Player) sender).teleport(warp);
        else
            sender.sendMessage(EssentialCommands.message(Constants.WARP_WORLD_NOT_LOADED));


        return true;
    }
}
