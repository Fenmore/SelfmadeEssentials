package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSethome implements CommandExecutor{

    private PlayerYMLManager playerYMLManager;

    public CommandSethome() {
        this.playerYMLManager = new PlayerYMLManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }

        if(args.length == 0) {
            playerYMLManager.setHomeLocation(sender.getName(), ((Player) sender).getLocation(), "home");
            sender.sendMessage(EssentialCommands.message(Constants.HOME_SET_FORMAT, "home"));
        }
        else {
            playerYMLManager.setHomeLocation(sender.getName(), ((Player) sender).getLocation(), args[0].toLowerCase());
            sender.sendMessage(EssentialCommands.message(Constants.HOME_SET_FORMAT, args[0].toLowerCase()));
        }
        return true;
    }
}
