package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandMsg extends CustomCommand{

    private ModYMLManager modYMLManager;
    public CommandMsg(ModYMLManager modYMLManager) {
        this.modYMLManager = modYMLManager;
    }



    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws InvalidPlayerNameException, PlayerNotFoundException {

        if(args.length < 2 || args[0].length() < 4 || args[0].length() > 16) {
            return false;
        }

        String message = EssentialCommands.mergeArgs(args, 1);

        Player p = EssentialCommands.getPlayer(args[0]);

        p.sendMessage(EssentialCommands.message(LAN_EN.SOCIALSPY_MSG_SENDER_ME, sender.getName(), message));
        sender.sendMessage(EssentialCommands.message(LAN_EN.SOCIALSPY_MSG_ME_SENDER, p.getName(), message));

        List<String> socialSpyList = modYMLManager.getSocialSpyList();
        if(socialSpyList != null) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (socialSpyList.contains(player.getName()) && !player.getName().equalsIgnoreCase(sender.getName()) && !player.getName().equalsIgnoreCase(args[0]))
                    player.sendMessage(EssentialCommands.message(LAN_EN.SOCIALSPY_MSG_SENDER_RECIEVER, sender.getName(), p.getName(), message));
            }
        }



        return true;
    }
}
