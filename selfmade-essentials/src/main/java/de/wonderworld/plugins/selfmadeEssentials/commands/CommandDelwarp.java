package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandDelwarp extends CustomCommand {

    private WarpYMLManager warpYMLManager;
    private LocalizedMessenger localizedMessenger;

    public CommandDelwarp(WarpYMLManager warpYMLManager, LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.warpYMLManager = warpYMLManager;
        this.localizedMessenger = localizedMessenger;
    }

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }

        String warpName = args[0];

        if (doesWarpExist(warpName)) {
            warpYMLManager.delWarp(warpName);
            localizedMessenger.sendLocalizedMessage(sender, "WARP_REMOVED_FORMAT", warpName);
        } else {
            localizedMessenger.sendLocalizedMessage(sender, "WARP_DOES_NOT_EXIST_FORMAT", warpName);
        }

        return true;
    }

    private boolean doesWarpExist(String warpName) {
        return warpYMLManager.getWarp(warpName) != null;
    }
}
