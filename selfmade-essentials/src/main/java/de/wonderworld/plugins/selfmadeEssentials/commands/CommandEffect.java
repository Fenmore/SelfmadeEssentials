package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class CommandEffect extends CustomCommand {

    private LocalizedMessenger localizedMessenger;

    public CommandEffect(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.localizedMessenger = localizedMessenger;
    }

    @Override
    public boolean onCustomCommand(CommandSender sender, Command cmd, String label, String[] args) throws NotInstanceOfPlayerException {

        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                throw new NotInstanceOfPlayerException();
            }

            List<String> effectTypeList = new ArrayList<>();
            boolean firstRun = true;
            for (PotionEffectType type : PotionEffectType.values()) {
                if (firstRun) {
                    firstRun = false;
                } else {
                    effectTypeList.add(type.getName());
                }
            }

            if (args[0].equalsIgnoreCase("list")) {
                sender.sendMessage(effectTypeList.toString());
                return true;
            } else if (args[0].equalsIgnoreCase("remove")) {

            } else if (!effectTypeList.contains(args[0].toUpperCase())) {
                localizedMessenger.sendLocalizedMessage(sender, "EFFECT_NOT_FOUND_FORMAT", args[0]);
            } else {
                ((Player) sender).addPotionEffect(PotionEffectType.getByName(args[0].toUpperCase()).createEffect(Integer.MAX_VALUE, 10));
                localizedMessenger.sendLocalizedMessage(sender, "EFFECT_GAINED_FORMAT", args[0]);
            }
        }

        return true;
    }
}
