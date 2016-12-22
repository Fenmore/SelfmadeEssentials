package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.WarpYMLManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandSpawn extends PlayerCommand {

    private WarpYMLManager warpYMLManager;

    public CommandSpawn(WarpYMLManager warpYMLManager) {
        this.warpYMLManager = warpYMLManager;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        Location loc = warpYMLManager.getWarp("spawn");
        if(loc.getWorld() != null) {
            new PlayerYMLManager().setBackLocation(sender.getName(), sender.getLocation());
            sender.teleport(loc);
        }

        return true;
    }
}
