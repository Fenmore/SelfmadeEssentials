package de.fenmore.tobiasThirolf.selfmadeEssentials.commands;

import de.fenmore.tobiasThirolf.selfmadeEssentials.files.WarpYMLManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandWarplist implements CommandExecutor{

    private WarpYMLManager warpYMLManager;
    public CommandWarplist(WarpYMLManager warpYMLManager) {
        this.warpYMLManager = warpYMLManager;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        sender.sendMessage(warpYMLManager.getWarpList().toString());

        return true;
    }
}
