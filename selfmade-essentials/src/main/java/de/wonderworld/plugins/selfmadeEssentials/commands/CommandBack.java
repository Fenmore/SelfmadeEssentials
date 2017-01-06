package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandBack extends PlayerCommand {

    private PlayerYMLManager playerYMLManager;

    public CommandBack(LocalizedMessenger localizedMessenger, PlayerYMLManager playerYMLManager) {
        super(localizedMessenger);
        this.playerYMLManager = playerYMLManager;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {

        Location loc = playerYMLManager.getBackLocation(sender.getName());

        if (loc.getWorld() != null) {
            sender.teleport(loc);
            playerYMLManager.remBackLocation(sender.getName());
        }

        return true;
    }
}
