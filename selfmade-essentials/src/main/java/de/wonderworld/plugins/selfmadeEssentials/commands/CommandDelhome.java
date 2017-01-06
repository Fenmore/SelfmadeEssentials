package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandDelhome extends PlayerCommand {

    private PlayerYMLManager playerYMLManager;
    private LocalizedMessenger localizedMessenger;

    public CommandDelhome(PlayerYMLManager playerYMLManager, LocalizedMessenger localizedMessenger) {

        super(localizedMessenger);
        this.playerYMLManager = playerYMLManager;
        this.localizedMessenger = localizedMessenger;
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
            localizedMessenger.sendLocalizedMessage(sender, "DELHOME_SUCCESS_FORMAT", arg);
        } else {
            localizedMessenger.sendLocalizedMessage(sender, "HOME_NOT_FOUND_FORMAT", arg);
        }
    }

    private void showHomes(Player sender) {

        String name = sender.getName();

        localizedMessenger.sendLocalizedMessage(sender, "DELHOME_INFO");
        sender.sendMessage(playerYMLManager.getHomeList(name).toString());
    }
}
