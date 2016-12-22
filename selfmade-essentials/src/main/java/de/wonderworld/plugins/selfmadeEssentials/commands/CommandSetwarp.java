package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandSetwarp extends PlayerCommand {

    private WarpYMLManager warpYMLManager;
    public CommandSetwarp(WarpYMLManager warpYMLManager) {
        this.warpYMLManager = warpYMLManager;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        if(args.length == 0)
            return false;

        warpYMLManager.setWarp(sender.getLocation(), args[0]);
        sender.sendMessage(EssentialCommands.message(LAN_EN.WARP_SET_FORMAT, args[0]));

        return true;
    }
}
