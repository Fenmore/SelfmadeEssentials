package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandSethome extends PlayerCommand {

    private PlayerYMLManager playerYMLManager;
    private LocalizedMessenger localizedMessenger;

    public CommandSethome(PlayerYMLManager playerYMLManager, LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.playerYMLManager = playerYMLManager;
        this.localizedMessenger = localizedMessenger;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            playerYMLManager.setHomeLocation(sender.getName(), sender.getLocation(), "home");
            localizedMessenger.sendLocalizedMessage(sender, "HOME_SET_FORMAT", "home");
        }
        else {
            playerYMLManager.setHomeLocation(sender.getName(), sender.getLocation(), args[0].toLowerCase());
            localizedMessenger.sendLocalizedMessage(sender, "HOME_SET_FORMAT", args[0].toLowerCase());
        }
        return true;
    }
}
