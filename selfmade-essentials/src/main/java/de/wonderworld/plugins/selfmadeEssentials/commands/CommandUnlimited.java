package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.YMLVariable;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandUnlimited extends PlayerCommand {

    private PlayerYMLManager playerYMLManager;
    private LocalizedMessenger localizedMessenger;

    public CommandUnlimited(LocalizedMessenger localizedMessenger, PlayerYMLManager playerYMLManager) {
        super(localizedMessenger);
        this.playerYMLManager = playerYMLManager;
        this.localizedMessenger = localizedMessenger;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command command, String label, String[] args) throws PlayerNotFoundException, InvalidPlayerNameException, NotInstanceOfPlayerException {

        toggleUnlimited(sender);

        return true;
    }
    private void toggleUnlimited(Player targetPlayer) {
        playerYMLManager.toggle(targetPlayer.getName(), YMLVariable.UNLIMITED);

        if (playerYMLManager.isActive(targetPlayer.getName(), YMLVariable.UNLIMITED)) {
            localizedMessenger.sendLocalizedMessage(targetPlayer, "UNLIMITED_IS_NOW_ON");
        } else {
            localizedMessenger.sendLocalizedMessage(targetPlayer, "UNLIMITED_IS_NOW_OFF");
        }
    }
}
