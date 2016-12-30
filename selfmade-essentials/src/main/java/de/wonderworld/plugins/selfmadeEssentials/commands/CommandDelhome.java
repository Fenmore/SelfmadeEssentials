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
            showHomes(sender);
        }
        else {
            deleteHome(sender, args[0]);
        }

        return true;
    }

    private void deleteHome(Player sender, String arg) {

        String name = sender.getName();

        if (playerYMLManager.getHomeList(name).contains(arg)) {
            playerYMLManager.remHomeLocation(name, arg);
            LocaleHandler.sendLocalizedMessage(sender, "DELHOME_SUCCESS_FORMAT", arg);
        } else {
            LocaleHandler.sendLocalizedMessage(sender, "HOME_NOT_FOUND_FORMAT", arg);
        }
    }

    private void showHomes(Player sender) {

        String name = sender.getName();

        LocaleHandler.sendLocalizedMessage(sender, "DELHOME_INFO");
        sender.sendMessage(playerYMLManager.getHomeList(name).toString());
    }
}
