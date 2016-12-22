package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSocialspy implements CommandExecutor {

    private ModYMLManager modYMLManager;

    public CommandSocialspy(ModYMLManager modYMLManager) {
        this.modYMLManager = modYMLManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(LAN_EN.NOT_INSTANCEOF_PLAYER));
            return true;
        }


        modYMLManager.toggleSocialSpy(sender.getName());

        if(modYMLManager.socialSpyIsActive(sender.getName()))
            sender.sendMessage(EssentialCommands.message(LAN_EN.SOCIAL_SPY_IS_NOW_ON));
        else
            sender.sendMessage(EssentialCommands.message(LAN_EN.SOCIAL_SPY_IS_NOW_OFF));


        return true;
    }
}
