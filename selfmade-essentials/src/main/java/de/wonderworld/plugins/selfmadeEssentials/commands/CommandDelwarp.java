package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
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
        if (args.length == 0)
            return false;

        if (warpYMLManager.getWarp(args[0]) == null) {
            sender.sendMessage(EssentialCommands.message(LAN_EN.WARP_DOES_NOT_EXIST_FORMAT, args[0]));
        }

        warpYMLManager.delWarp(args[0]);
        sender.sendMessage(EssentialCommands.message(LAN_EN.WARP_REMOVED_FORMAT, args[0]));

        return true;
    }
}
