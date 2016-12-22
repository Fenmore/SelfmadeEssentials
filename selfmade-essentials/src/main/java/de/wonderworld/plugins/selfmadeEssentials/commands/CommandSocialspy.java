package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandSocialspy extends PlayerCommand {

    private ModYMLManager modYMLManager;

    public CommandSocialspy(ModYMLManager modYMLManager) {
        this.modYMLManager = modYMLManager;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        modYMLManager.toggleSocialSpy(sender.getName());

        if(modYMLManager.socialSpyIsActive(sender.getName()))
            sender.sendMessage(EssentialCommands.message(LAN_EN.SOCIAL_SPY_IS_NOW_ON));
        else
            sender.sendMessage(EssentialCommands.message(LAN_EN.SOCIAL_SPY_IS_NOW_OFF));


        return true;
    }
}
