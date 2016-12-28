package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localizationHandler.LocaleHandler;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly extends CustomCommand {


    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException, InvalidPlayerNameException, PlayerNotFoundException {
        Player p;
        if(args.length == 0){
            if (!(sender instanceof Player)) {
                throw new NotInstanceOfPlayerException();
            }
            else {
                p = (Player) sender;
            }
        }
        else{
            p = EssentialCommands.getPlayer(args[0]);
        }

        if (!p.getAllowFlight()) {
            p.setAllowFlight(true);
            LocaleHandler.sendLocalizedMessage(p, "FLY_MODE_ON");
        } else {
            p.setAllowFlight(false);
            LocaleHandler.sendLocalizedMessage(p, "FLY_MODE_OFF");
        }
        return true;
    }
}