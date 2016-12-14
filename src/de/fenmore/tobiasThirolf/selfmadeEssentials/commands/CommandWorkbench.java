package de.fenmore.tobiasThirolf.selfmadeEssentials.commands;

import de.fenmore.tobiasThirolf.selfmadeEssentials.essentials.Constants;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

/**
 * Created by Fenmore on 29.11.2016.
 */
public class CommandWorkbench implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }



        ((Player) sender).openWorkbench(null, true);

        return true;
    }
}
