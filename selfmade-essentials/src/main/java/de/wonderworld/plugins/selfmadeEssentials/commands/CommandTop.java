package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandTop extends PlayerCommand {


    public CommandTop(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        Location loc = sender.getLocation();
        World world = loc.getWorld();
        for (int i = 0; i <= 256 - loc.getBlockY(); i++) {
            if (world.getBlockAt(loc.getBlockX(), loc.getBlockY() + i, loc.getBlockZ()).getType().isSolid() &&
                    world.getBlockAt(loc.getBlockX(), loc.getBlockY() + i + 1, loc.getBlockZ()).getType().isTransparent() &&
                    world.getBlockAt(loc.getBlockX(), loc.getBlockY() + i + 2, loc.getBlockZ()).getType().isTransparent()) {
                sender.teleport(new Location(world, loc.getX(), loc.getY() + i + 1, loc.getZ()));
                break;
            }
        }
        return true;
    }
}
