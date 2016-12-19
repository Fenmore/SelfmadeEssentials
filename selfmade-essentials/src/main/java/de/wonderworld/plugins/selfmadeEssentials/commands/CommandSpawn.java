package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

    private WarpYMLManager warpYMLManager;

    public CommandSpawn() {
        this.warpYMLManager = new WarpYMLManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }

        Location loc = warpYMLManager.getWarp("spawn");
        if(loc.getWorld() != null) {
            new PlayerYMLManager().setBackLocation(sender.getName(), ((Player) sender).getLocation());
            ((Player) sender).teleport(loc);
        }

        return true;
    }
}
