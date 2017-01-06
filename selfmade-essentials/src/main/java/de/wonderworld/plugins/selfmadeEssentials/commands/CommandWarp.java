package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandWarp extends PlayerCommand {

    private WarpYMLManager warpYMLManager;
    private LocalizedMessenger localizedMessenger;
    public CommandWarp(WarpYMLManager warpYMLManager, LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.warpYMLManager = warpYMLManager;
        this.localizedMessenger = localizedMessenger;
    }


    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        if(args.length == 0)
            return false;

        Location warp = warpYMLManager.getWarp(args[0]);
        if(warp == null) {
            localizedMessenger.sendLocalizedMessage(sender, "WARP_DOES_NOT_EXIST_FORMAT", args[0]);
            return true;
        }

        if(warp.getWorld() != null) {
            new PlayerYMLManager().setBackLocation(sender.getName(), sender.getLocation());
            sender.teleport(warp);
        }
        else
            localizedMessenger.sendLocalizedMessage(sender, "WARP_WORLD_NOT_LOADED");


        return true;
    }
}
