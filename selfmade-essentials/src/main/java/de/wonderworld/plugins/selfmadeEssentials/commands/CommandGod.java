package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localizationHandler.LocaleHandler;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.YMLVariable;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGod extends CustomCommand {

    private ModYMLManager modYMLManager;

    public CommandGod(ModYMLManager modYMLManager) {
        this.modYMLManager = modYMLManager;
    }

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException {
        Player targetPlayer = getTargetPlayer(sender, args);

        if (targetPlayer == null) {
            sendMessagePlayerNotFound(sender, args);
        } else {
            toggleGodMode(targetPlayer);
        }
        return true;
    }

    private void sendMessagePlayerNotFound(CommandSender sender, String[] args) throws NotInstanceOfPlayerException {
        if (sender instanceof Player) {
            LocaleHandler.sendLocalizedMessage(sender, "PLAYER_NOT_FOUND_FORMAT", getPlayerName(args));
        } else {
            throw new NotInstanceOfPlayerException();
        }
    }

    private String getPlayerName(String[] args) {
        return args[0];
    }

    private Player getTargetPlayer(CommandSender sender, String[] args) {
        if (args.length > 0) {
            return getPlayerByName(getPlayerName(args));
        } else if (sender instanceof Player) {
            return (Player) sender;
        } else {
            return null;
        }
    }

    private void toggleGodMode(Player targetPlayer) {
        modYMLManager.toggleVariable(targetPlayer.getName(), YMLVariable.GOD_MODE);

        if (modYMLManager.isVariableActive(targetPlayer.getName(), YMLVariable.GOD_MODE)) {
            LocaleHandler.sendLocalizedMessage(targetPlayer, "GODMODE_IS_NOW_ON");
        } else {
            LocaleHandler.sendLocalizedMessage(targetPlayer, "GODMODE_IS_NOW_OFF");
        }
    }

    private Player getPlayerByName(String arg) {
        String playerName = arg;
        return Bukkit.getServer().getPlayer(playerName);
    }
}
