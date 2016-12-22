package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandSethome extends PlayerCommand {

    private PlayerYMLManager playerYMLManager;

    public CommandSethome(PlayerYMLManager playerYMLManager) {
        this.playerYMLManager = playerYMLManager;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            playerYMLManager.setHomeLocation(sender.getName(), sender.getLocation(), "home");
            sender.sendMessage(EssentialCommands.message(LAN_EN.HOME_SET_FORMAT, "home"));
        }
        else {
            playerYMLManager.setHomeLocation(sender.getName(), sender.getLocation(), args[0].toLowerCase());
            sender.sendMessage(EssentialCommands.message(LAN_EN.HOME_SET_FORMAT, args[0].toLowerCase()));
        }
        return true;
    }
}
