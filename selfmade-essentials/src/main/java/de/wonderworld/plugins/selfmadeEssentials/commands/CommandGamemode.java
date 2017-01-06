package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGamemode extends CustomCommand {


    private LocalizedMessenger localizedMessenger;

    public CommandGamemode(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.localizedMessenger = localizedMessenger;
    }


    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException, InvalidPlayerNameException, PlayerNotFoundException {
        Player p;
        GameMode mode = null;

        if (args.length <= 1) {
            if (!(sender instanceof Player)) {
                throw new NotInstanceOfPlayerException();
            }
            else {
                p = (Player) sender;
                if (args.length == 0) {
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
                } else {
                    int modeInt;
                    try {
                        modeInt = Integer.valueOf(args[0]);
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
                            default: {
                                localizedMessenger.sendLocalizedMessage(sender, "GAMEMODE_BIGGER_3", args[0]);
                                return true;
                            }
                        }
                    } catch (NumberFormatException e) {
                        localizedMessenger.sendLocalizedMessage(sender, "ARGUMENT_HAS_TO_BE_INTEGER_OR_LESS_ARGUMENTS_FORMAT", args[0]);
                        return true;
                    }
                }
            }
        }
        else {
            int modeInt;
            try{
                modeInt = Integer.valueOf(args[0]);
                p = EssentialCommands.getPlayer(args[1]);
            }
            catch (NumberFormatException e) {
                modeInt = Integer.valueOf(args[1]);
                p = EssentialCommands.getPlayer(args[0]);
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
                default: {
                    localizedMessenger.sendLocalizedMessage(sender, "GAMEMODE_BIGGER_3", args[0]);
                    return true;
                }

            }
        }

        p.setGameMode(mode);
        localizedMessenger.sendLocalizedMessage(p, "GAMEMODE_SET_FORMAT", mode.toString());

        return true;
    }
}
