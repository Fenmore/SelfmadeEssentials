package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class CommandEffect implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(EssentialCommands.message(LAN_EN.NOT_INSTANCEOF_PLAYER));
                return true;
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
                sender.sendMessage(EssentialCommands.message(LAN_EN.EFFECT_NOT_FOUND_FORMAT, args[0]));
            } else {
                ((Player) sender).addPotionEffect(PotionEffectType.getByName(args[0].toUpperCase()).createEffect(Integer.MAX_VALUE, 10));
                sender.sendMessage(EssentialCommands.message(LAN_EN.EFFECT_GAINED_FORMAT, args[0]));
            }
        }

        return true;
    }
}
