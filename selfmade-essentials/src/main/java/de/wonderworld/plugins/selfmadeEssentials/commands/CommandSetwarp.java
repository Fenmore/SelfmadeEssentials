package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandSetwarp extends PlayerCommand {

    private WarpYMLManager warpYMLManager;
    private LocalizedMessenger localizedMessenger;
    public CommandSetwarp(WarpYMLManager warpYMLManager, LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.warpYMLManager = warpYMLManager;
        this.localizedMessenger = localizedMessenger;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        if(args.length == 0)
            return false;

        warpYMLManager.setWarp(sender.getLocation(), args[0]);
        localizedMessenger.sendLocalizedMessage(sender, "WARP_SET_FORMAT", args[0]);

        return true;
    }
}
