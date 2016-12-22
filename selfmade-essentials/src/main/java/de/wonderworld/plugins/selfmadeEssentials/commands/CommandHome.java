package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHome implements CommandExecutor{

    private PlayerYMLManager playerYMLManager;

    public CommandHome(PlayerYMLManager playerYMLManager) {
        this.playerYMLManager = playerYMLManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(LAN_EN.NOT_INSTANCEOF_PLAYER));
            return true;
        }

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
                playerYMLManager.setBackLocation(sender.getName(), ((Player) sender).getLocation());
                ((Player) sender).teleport(loc);
            }
            else
                sender.sendMessage(EssentialCommands.message(LAN_EN.HOME_WORLD_NOT_LOADED));
        }

        return true;
    }
}
