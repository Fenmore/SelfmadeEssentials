package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localizationHandler.LocaleHandler;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandSpeed extends PlayerCommand {

    @Override
    public boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            LocaleHandler.sendLocalizedMessage(sender, "SPEED_INFO_FORMAT", ((Player) sender).getWalkSpeed(), ((Player) sender).getFlySpeed());
            return true;
        } else {
            if (args[0].equalsIgnoreCase("reset")) {
                ((Player) sender).setFlySpeed(0.1f);
                ((Player) sender).setWalkSpeed(0.2f);
                LocaleHandler.sendLocalizedMessage(sender, "SPEED_RESET");
                return true;
            }
            Float value;
            try {
                value = Float.parseFloat(args[0]);
                if (value > 100) {
                    value = 100f;
                }
                ((Player) sender).setFlySpeed((1f / 100f) * value);
                ((Player) sender).setWalkSpeed((1f / 100f) * value);
                String stringValue = String.valueOf((1f / 100f) * value);
                while (true) {
                    if (!stringValue.endsWith("0")) {
                        break;
                    }
                    stringValue = stringValue.substring(0, stringValue.length() - 2);
                }
                LocaleHandler.sendLocalizedMessage(sender, "SPEED_WALK_FLY_SET_FORMAT", stringValue);
            } catch (NumberFormatException e) {
                if (args.length == 1) {
                    return false;
                } else {
                    try {
                        value = Float.parseFloat(args[1]);
                        if (value > 100) {
                            value = 100f;
                        }
                        String stringValue = String.valueOf((1f / 100f) * value);
                        while (true) {
                            if (!stringValue.endsWith("0")) {
                                break;
                            }
                            stringValue = stringValue.substring(0, stringValue.length() - 2);
                        }
                        switch (args[0]) {
                            case "fly": {
                                ((Player) sender).setFlySpeed((1f / 100f) * value);
                                LocaleHandler.sendLocalizedMessage(sender, "SPEED_FLY_SET_FORMAT", stringValue);
                                break;
                            }
                            case "walk": {
                                ((Player) sender).setWalkSpeed((1f / 100f) * value);
                                LocaleHandler.sendLocalizedMessage(sender, "SPEED_WALK_SET_FORMAT", stringValue);
                                break;
                            }
                            default:
                                return false;
                        }
                    } catch (NumberFormatException e2) {
                        LocaleHandler.sendLocalizedMessage(sender, "SPEED_WRONG_ARGUMENT_FORMAT", args[1]);
                    }
                }
            }
        }
        return true;
    }
}
