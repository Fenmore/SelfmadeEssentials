package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandR extends CustomCommand {

    private PlayerYMLManager playerYMLManager;

    public CommandR(LocalizedMessenger localizedMessenger, PlayerYMLManager playerYMLManager) {
        super(localizedMessenger);
        this.playerYMLManager = playerYMLManager;
    }

    @Override
    protected boolean onCustomCommand(CommandSender sender, Command command, String label, String[] args) {
    /*    String quickResponseName = playerYMLManager.getQuickResponseName(sender.getName());
        if(quickResponseName != null) {
            if(instantMessaging(args)) {

            }
            else {
                suggestMsgCommand(quickResponseName);
            }
        }
        else {
            sender.sendMessage(EssentialCommands.message(LAN_EN.QUICK_RESPONSE_NOT_AVAILABLE));
        }

        return true;
        */
        return false;
    }

    private void suggestMsgCommand(String quickResponseName) {

    }

    private boolean instantMessaging(String[] args) {
        return args.length >= 1;
    }
}
