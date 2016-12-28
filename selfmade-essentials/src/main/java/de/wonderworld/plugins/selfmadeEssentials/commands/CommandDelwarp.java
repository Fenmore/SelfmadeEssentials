package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localizationHandler.LocaleHandler;
import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandDelwarp extends CustomCommand {

    private WarpYMLManager warpYMLManager;

    public CommandDelwarp(WarpYMLManager warpYMLManager) {
        this.warpYMLManager = warpYMLManager;
    }

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0)
            return false;

        if (warpYMLManager.getWarp(args[0]) == null) {
            LocaleHandler.sendLocalizedMessage(sender, "WARP_DOES_NOT_EXIST_FORMAT", args[0]);
        }

        warpYMLManager.delWarp(args[0]);
        LocaleHandler.sendLocalizedMessage(sender, "WARP_REMOVED_FORMAT", args[0]);

        return true;
    }
}
