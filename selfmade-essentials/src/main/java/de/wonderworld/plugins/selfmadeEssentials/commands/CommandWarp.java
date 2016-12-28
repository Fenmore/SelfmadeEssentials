package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localizationHandler.LocaleHandler;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandWarp extends PlayerCommand {

    private WarpYMLManager warpYMLManager;
    public CommandWarp(WarpYMLManager warpYMLManager) {
        this.warpYMLManager = warpYMLManager;
    }


    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        if(args.length == 0)
            return false;

        Location warp = warpYMLManager.getWarp(args[0]);
        if(warp == null) {
            LocaleHandler.sendLocalizedMessage(sender, "WARP_DOES_NOT_EXIST_FORMAT", args[0]);
            return true;
        }

        if(warp.getWorld() != null) {
            new PlayerYMLManager().setBackLocation(sender.getName(), sender.getLocation());
            sender.teleport(warp);
        }
        else
            LocaleHandler.sendLocalizedMessage(sender, "WARP_WORLD_NOT_LOADED");


        return true;
    }
}
