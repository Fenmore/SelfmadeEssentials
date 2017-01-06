package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.fenmore.localization.Translations;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CommandFly extends CustomCommand {

    private LocalizedMessenger localizedMessenger;

    public CommandFly(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.localizedMessenger = localizedMessenger;
    }


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
            localizedMessenger.sendLocalizedMessage(p, "FLY_MODE_ON");
        } else {
            p.setAllowFlight(false);
            localizedMessenger.sendLocalizedMessage(p, "FLY_MODE_OFF");
        }
        return true;
    }
}