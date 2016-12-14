package de.fenmore.tobiasThirolf.selfmadeEssentials.commands;

import de.fenmore.tobiasThirolf.selfmadeEssentials.essentials.Constants;
import de.fenmore.tobiasThirolf.selfmadeEssentials.essentials.Essentials;
import de.fenmore.tobiasThirolf.selfmadeEssentials.files.ModYMLManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandMsg implements CommandExecutor{

    private Essentials plugin;
    private ModYMLManager modYMLManager;
    public CommandMsg(Essentials plugin, ModYMLManager modYMLManager) {
        this.plugin = plugin;
        this.modYMLManager = modYMLManager;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length < 2 || !EssentialCommands.validPlayerName(args[0]) || args[0].length() < 4 || args[0].length() > 16) {
            return false;
        }



        String message = EssentialCommands.mergeArgs(args, 1);

        Player p = plugin.getServer().getPlayer(args[0]);
        if(p == null) {
            p.sendMessage(EssentialCommands.message(Constants.PLAYER_NOT_FOUND_FORMAT, args[0]));
            return true;
        }

        p.sendMessage(EssentialCommands.message(Constants.SOCIALSPY_MSG_SENDER_ME, sender.getName(), message));
        sender.sendMessage(EssentialCommands.message(Constants.SOCIALSPY_MSG_ME_SENDER, p.getName(), message));

        List<String> socialSpyList = modYMLManager.getSocialSpyList();
        if(socialSpyList != null) {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (socialSpyList.contains(player.getName()) && !player.getName().equalsIgnoreCase(sender.getName()) && !player.getName().equalsIgnoreCase(args[0]))
                    player.sendMessage(EssentialCommands.message(Constants.SOCIALSPY_MSG_SENDER_RECIEVER, sender.getName(), p.getName(), message));
            }
        }



        return true;
    }
}
