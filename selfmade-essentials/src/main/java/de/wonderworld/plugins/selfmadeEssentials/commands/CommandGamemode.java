package de.wonderworld.plugins.selfmadeEssentials.commands;


import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by David on 16.12.2016.
 */
public class CommandGamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p;
        GameMode mode = null;

        if (args.length <= 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(EssentialCommands.message(Constants.NOT_INSTANCEOF_PLAYER));
                return true;
            }
            p = (Player) sender;
            if(args.length == 0){
                String gamemode = p.getGameMode().toString();
                switch (gamemode) {
                    case "SURVIVAL":
                        mode = GameMode.CREATIVE;
                        break;
                    case "CREATIVE":
                        mode = GameMode.SURVIVAL;
                        break;
                    case "ADVENTURE":
                        mode = GameMode.CREATIVE;
                        break;
                    case "SPECTATOR":
                        mode = GameMode.CREATIVE;
                        break;
                }
            }
        }
        else {
            int modeInt;
            try{
                p = Bukkit.getServer().getPlayer(args[1]);
                if(p == null ){
                    sender.sendMessage(EssentialCommands.message(Constants.PLAYER_NOT_FOUND_FORMAT, args[1]));
                    return true;
                }
                modeInt = Integer.valueOf(args[0]);
            }
            catch (NumberFormatException e) {
                p = Bukkit.getServer().getPlayer(args[0]);
                if(p == null ){
                    sender.sendMessage(EssentialCommands.message(Constants.PLAYER_NOT_FOUND_FORMAT, args[0]));
                    return true;
                }
                modeInt = Integer.valueOf(args[1]);
            }
            switch (modeInt) {
                case 0:
                    mode = GameMode.SURVIVAL;
                    break;
                case 1:
                    mode = GameMode.CREATIVE;
                    break;
                case 2:
                    mode = GameMode.ADVENTURE;
                    break;
                case 3:
                    mode = GameMode.SPECTATOR;
                    break;
            }
        }

        p.setGameMode(mode);

        return true;
    }
}
