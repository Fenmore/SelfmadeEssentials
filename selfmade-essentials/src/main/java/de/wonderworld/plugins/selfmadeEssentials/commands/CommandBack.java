package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBack extends PlayerCommand {

    private PlayerYMLManager playerYMLManager;

    public CommandBack(PlayerYMLManager playerYMLManager) {
        this.playerYMLManager = playerYMLManager;
    }

    @Override
    protected boolean onPlayerCommand(Player player, Command cmd, String label, String[] args) {
        Location loc = playerYMLManager.getBackLocation(player.getName());
        if (loc.getWorld() != null) {
            player.teleport(loc);
            playerYMLManager.remBackLocation(player.getName());
        }

        return true;
    }
}
