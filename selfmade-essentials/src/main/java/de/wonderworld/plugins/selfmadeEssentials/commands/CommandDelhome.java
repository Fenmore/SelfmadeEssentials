package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDelhome implements CommandExecutor{

    private PlayerYMLManager playerYMLManager;

    public CommandDelhome() {
        this.playerYMLManager = new PlayerYMLManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }

        if(args.length == 0) {
            sender.sendMessage(EssentialCommands.message(Constants.DELHOME_INFO));
            sender.sendMessage(playerYMLManager.getHomeList(sender.getName()).toString());
        }
        else {
            if(!playerYMLManager.getHomeList(sender.getName()).contains(args[0])) {
                sender.sendMessage(EssentialCommands.message(Constants.HOME_NOT_FOUND_FORMAT, args[0]));
            }
            else {
                playerYMLManager.remHomeLocation(sender.getName(), args[0]);
                sender.sendMessage(EssentialCommands.message(Constants.DELHOME_SUCCESS_FORMAT, args[0]));
            }
        }
        return true;
    }
}
