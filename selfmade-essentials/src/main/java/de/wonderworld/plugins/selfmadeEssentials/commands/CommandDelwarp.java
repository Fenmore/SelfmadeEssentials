package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localizationHandler.LocaleHandler;
import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandDelwarp extends CustomCommand {

    private WarpYMLManager warpYMLManager;

    public CommandDelwarp(WarpYMLManager warpYMLManager) {
        this.warpYMLManager = warpYMLManager;
    }

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }

        String warpName = args[0];

        if (doesWarpExist(warpName)) {
            warpYMLManager.delWarp(warpName);
            LocaleHandler.sendLocalizedMessage(sender, "WARP_REMOVED_FORMAT", warpName);
        } else {
            LocaleHandler.sendLocalizedMessage(sender, "WARP_DOES_NOT_EXIST_FORMAT", warpName);
        }

        return true;
    }

    private boolean doesWarpExist(String warpName) {
        return warpYMLManager.getWarp(warpName) != null;
    }
}
