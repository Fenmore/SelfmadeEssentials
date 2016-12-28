package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localizationHandler.LocaleHandler;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandDelhome extends PlayerCommand {

    private PlayerYMLManager playerYMLManager;

    public CommandDelhome(PlayerYMLManager playerYMLManager) {
        this.playerYMLManager = playerYMLManager;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(EssentialCommands.message(LAN_EN.DELHOME_INFO));
            sender.sendMessage(playerYMLManager.getHomeList(sender.getName()).toString());
        }
        else {
            if(!playerYMLManager.getHomeList(sender.getName()).contains(args[0])) {
                LocaleHandler.sendLocalizedMessage(sender, "HOME_NOT_FOUND_FORMAT", args[0]);
            }
            else {
                playerYMLManager.remHomeLocation(sender.getName(), args[0]);
                LocaleHandler.sendLocalizedMessage(sender, "DELHOME_SUCCESS_FORMAT", args[0]);
            }
        }
        return true;
    }
}
