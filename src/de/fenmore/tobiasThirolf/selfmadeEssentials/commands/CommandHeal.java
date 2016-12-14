package de.fenmore.tobiasThirolf.selfmadeEssentials.commands;

import de.fenmore.tobiasThirolf.selfmadeEssentials.essentials.Constants;
import de.fenmore.tobiasThirolf.selfmadeEssentials.essentials.Essentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandHeal implements CommandExecutor{

    private Essentials plugin;
    public CommandHeal(Essentials plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
            return true;
        }
        if(args.length == 0) {
            ((Player) sender).setHealth(((Player) sender).getMaxHealth());
            return true;
        }

        for(String name : args) {
            if (!EssentialCommands.validPlayerName(name)) {
                sender.sendMessage(EssentialCommands.message(Constants.NOT_VALID_PLAYER_NAME_FORMAT, name));
                return true;
            }
        }

        List<Player> playerToHeal = new ArrayList<>();
        for(String name : args) {
            Player p = plugin.getServer().getPlayer(name);
            if(p == null) {
                sender.sendMessage(EssentialCommands.message(Constants.PLAYER_NOT_FOUND_FORMAT, name));
                return true;
            }
            playerToHeal.add(p);
        }


        for(Player p : playerToHeal)
            p.setHealth(p.getMaxHealth());


        return true;
    }
}
