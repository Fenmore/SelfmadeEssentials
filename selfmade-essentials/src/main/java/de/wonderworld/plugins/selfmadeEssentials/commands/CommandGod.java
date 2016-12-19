package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGod implements CommandExecutor {

    private ModYMLManager modYMLManager;

    public CommandGod() {
        this.modYMLManager = new ModYMLManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }
        modYMLManager.toggleGodmode(sender.getName());

        Player p;
        if(args.length == 0){
            p = (Player) sender;
        }
        else{
            p = Bukkit.getServer().getPlayer(args[0]);
            if(p == null ){
                sender.sendMessage(EssentialCommands.message(Constants.PLAYER_NOT_FOUND_FORMAT, args[0]));
                return true;
            }
        }
        if (modYMLManager.isGodmodeActive(p.getName())) {
            p.sendMessage(EssentialCommands.message(Constants.GODMODE_IS_NOW_ON));
        } else {
            p.sendMessage(EssentialCommands.message(Constants.GODMODE_IS_NOW_OFF));
        }
        return true;
    }
}
