package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandMsg extends CustomCommand{

    private ModYMLManager modYMLManager;
    private LocalizedMessenger localizedMessenger;
    public CommandMsg(ModYMLManager modYMLManager, LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.modYMLManager = modYMLManager;
        this.localizedMessenger = localizedMessenger;
    }



    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws InvalidPlayerNameException, PlayerNotFoundException {
        if(neededArgs(args)) {
            String message = EssentialCommands.mergeArgs(args, 1);
            CommandSender reciever = getReciever(args);
            sendMsg(sender, reciever, message);
            sendSocialSpyMsg(sender, reciever, args, message);
            return true;
        }
        else
            return false;
    }

    private boolean neededArgs(String[] args) {
        return args.length >= 2 || args[0].length() >= 4 || args[0].length() <= 16;
    }

    private void sendSocialSpyMsg(CommandSender sender, CommandSender reciever, String[] args, String message) {
        List<String> socialSpyList = modYMLManager.getSocialSpyList();
        if(socialSpyList != null) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (socialSpyList.contains(player.getName()) && !player.getName().equalsIgnoreCase(sender.getName()) && !player.getName().equalsIgnoreCase(args[0]))
                    localizedMessenger.sendLocalizedMessage(player, "SOCIALSPY_MSG_SENDER_RECIEVER", sender.getName(), reciever.getName(), message);
            }
        }
    }

    private CommandSender getReciever(String[] args) throws InvalidPlayerNameException, PlayerNotFoundException {
        CommandSender reciever;
        if(args[0].equalsIgnoreCase("console"))
            reciever = Bukkit.getConsoleSender();
        else
            reciever = EssentialCommands.getPlayer(args[0]);
        return reciever;
    }

    private CommandSender sendMsg(CommandSender sender, CommandSender reciever, String message) throws InvalidPlayerNameException, PlayerNotFoundException {
        localizedMessenger.sendLocalizedMessage(reciever, "SOCIALSPY_MSG_SENDER_ME", sender.getName(), message);
        localizedMessenger.sendLocalizedMessage(sender, "SOCIALSPY_MSG_ME_SENDER", reciever.getName(), message);

        return reciever;
    }
}
