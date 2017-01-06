package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandSocialspy extends PlayerCommand {

    private ModYMLManager modYMLManager;
    private LocalizedMessenger localizedMessenger;

    public CommandSocialspy(ModYMLManager modYMLManager, LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.modYMLManager = modYMLManager;
        this.localizedMessenger = localizedMessenger;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        modYMLManager.toggleSocialSpy(sender.getName());

        if(modYMLManager.socialSpyIsActive(sender.getName()))
            localizedMessenger.sendLocalizedMessage(sender, "SOCIAL_SPY_IS_NOW_ON");
        else
            localizedMessenger.sendLocalizedMessage(sender, "SOCIAL_SPY_IS_NOW_OFF");


        return true;
    }
}
