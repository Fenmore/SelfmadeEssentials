package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTop implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }

        Location loc = ((Player) sender).getLocation();
        World world = loc.getWorld();
        for (int i = 0; i <= 256 - loc.getBlockY(); i++) {
            if(world.getBlockAt(loc.getBlockX(), loc.getBlockY() + i, loc.getBlockZ()).getType().isSolid() &&
                    world.getBlockAt(loc.getBlockX(), loc.getBlockY() + i + 1, loc.getBlockZ()).getType().isTransparent() &&
                    world.getBlockAt(loc.getBlockX(), loc.getBlockY() + i + 2, loc.getBlockZ()).getType().isTransparent()) {
                ((Player) sender).teleport(new Location(world, loc.getX(), loc.getY() + i + 1, loc.getZ()));
                break;
            }
        }
        return true;
    }
}
