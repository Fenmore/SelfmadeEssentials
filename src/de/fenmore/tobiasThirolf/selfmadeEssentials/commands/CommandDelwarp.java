package de.fenmore.tobiasThirolf.selfmadeEssentials.commands;

import de.fenmore.tobiasThirolf.selfmadeEssentials.essentials.Constants;
import de.fenmore.tobiasThirolf.selfmadeEssentials.files.WarpYMLManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandDelwarp implements CommandExecutor {

    private WarpYMLManager warpYMLManager;
    public CommandDelwarp(WarpYMLManager warpYMLManager) {
        this.warpYMLManager = warpYMLManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length == 0)
            return false;

        if(warpYMLManager.getWarp(args[0]) == null) {
            sender.sendMessage(EssentialCommands.message(Constants.WARP_DOES_NOT_EXIST_FORMAT, args[0]));
        }

        warpYMLManager.delWarp(args[0]);
        sender.sendMessage(EssentialCommands.message(Constants.WARP_REMOVED_FORMAT, args[0]));

        return true;
    }
}
