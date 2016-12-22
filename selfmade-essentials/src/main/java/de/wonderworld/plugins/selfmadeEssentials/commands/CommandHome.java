package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandHome extends PlayerCommand{

    private PlayerYMLManager playerYMLManager;

    public CommandHome(PlayerYMLManager playerYMLManager) {
        this.playerYMLManager = playerYMLManager;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        String home = "home";
        if(args.length != 0) {
            home = args[0];
        }

        if(!playerYMLManager.getHomeList(sender.getName()).contains(home)) {
            sender.sendMessage(EssentialCommands.message(LAN_EN.HOME_NOT_FOUND_FORMAT, home));
        }
        else {
            Location loc = playerYMLManager.getHome(sender.getName(), home);
            if(loc.getWorld() != null) {
                playerYMLManager.setBackLocation(sender.getName(), sender.getLocation());
                sender.teleport(loc);
            }
            else
                sender.sendMessage(EssentialCommands.message(LAN_EN.HOME_WORLD_NOT_LOADED));
        }

        return true;
    }
}
