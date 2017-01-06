package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.YMLVariable;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandUnlimited extends PlayerCommand {

    private PlayerYMLManager playerYMLManager;

    public CommandUnlimited(PlayerYMLManager playerYMLManager) {

        this.playerYMLManager = playerYMLManager;
    }


    public CommandUnlimited(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command command, String label, String[] args) throws PlayerNotFoundException, InvalidPlayerNameException, NotInstanceOfPlayerException {

        toggleUnlimited(sender);

        return true;
    }





    private void toggleUnlimited(Player targetPlayer) {
        playerYMLManager.toggle(targetPlayer.getName(), YMLVariable.UNLIMITED);

        if (playerYMLManager.isActive(targetPlayer.getName(), YMLVariable.UNLIMITED)) {
            LocaleHandler.sendLocalizedMessage(targetPlayer, "UNLIMITED_IS_NOW_ON");
        } else {
            LocaleHandler.sendLocalizedMessage(targetPlayer, "UNLIMITED_IS_NOW_OFF");
        }
    }
}
//playerinventory
//playeritemconsumeevent