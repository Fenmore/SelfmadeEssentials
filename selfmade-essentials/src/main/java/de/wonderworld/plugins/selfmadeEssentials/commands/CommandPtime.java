package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.json.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class CommandPtime implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(EssentialCommands.message(LAN_EN.NOT_INSTANCEOF_PLAYER));
            return true;
        }

        //sender.sendMessage("Fulltime (Absolute): " + ((Player) sender).getWorld().getFullTime() + ", time (relative): " + ((Player) sender).getWorld().getTime());

        if(args.length == 0) {
            if(((Player) sender).isPlayerTimeRelative() && ((Player) sender).getPlayerTime() == ((Player) sender).getWorld().getFullTime()) {
                sender.sendMessage(EssentialCommands.message(LAN_EN.PTIME_EQUAL_TO_SERVER_FORMAT));
                return true;
            }
            else if(!((Player) sender).isPlayerTimeRelative()) {
                sender.sendMessage(EssentialCommands.message(LAN_EN.PTIME_ABSOLUTE_FORMAT, ((Player) sender).getPlayerTime()));
                return true;
            }
            else if(((Player) sender).getPlayerTimeOffset() > 0) {
                sender.sendMessage(EssentialCommands.message(LAN_EN.PTIME_RELATIVE_AHEAD_FORMAT, ((Player) sender).getPlayerTimeOffset()));
            }
            else if(((Player) sender).getPlayerTimeOffset() < 0) {
                sender.sendMessage(EssentialCommands.message(LAN_EN.PTIME_RELATIVE_BEHIND_FORMAT, ((Player) sender).getPlayerTimeOffset()));
            }
        }
        else if(args[0].equalsIgnoreCase("list")){
            sender.sendMessage(EssentialCommands.message(LAN_EN.PTIME_INTRO));
            JsonWrapper json = new JsonWrapper().wrapJsonTellraw(sender.getName());
            Map<String, List<String>> ptimeMap = getCommandMap();
            for(String key : ptimeMap.keySet()) {
                json.addText(key, JsonColor.GOLD, JsonFormat.UNDERLINED).addHoverEvent(HoverAction.SHOW_TEXT, ptimeMap.get(key).get(0), JsonColor.GOLD)
                        .addClickEvent(ClickAction.SUGGEST_COMMAND, ptimeMap.get(key).get(1)).addText(" ");
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), json.toString());
        }
        else if(args[0].equalsIgnoreCase("reset")) {
            ((Player) sender).resetPlayerTime();
            new PlayerYMLManager().remPtime(sender.getName());
            sender.sendMessage(EssentialCommands.message(LAN_EN.PTIME_RESET));
        }
        else if(args[0].equalsIgnoreCase("day")){
            ((Player) sender).setPlayerTime(6000, false);
            new PlayerYMLManager().setPtime(sender.getName(), 6000, false);
            sender.sendMessage(EssentialCommands.message(LAN_EN.PTIME_CONSTANT_FORMAT, 6000));
        }
        else if(args[0].equalsIgnoreCase("night")){
            ((Player) sender).setPlayerTime(18000, false);
            new PlayerYMLManager().setPtime(sender.getName(), 18000, false);
            sender.sendMessage(EssentialCommands.message(LAN_EN.PTIME_CONSTANT_FORMAT, 18000));
        }
        else if(args[0].equalsIgnoreCase("dawn")){
            ((Player) sender).setPlayerTime(0, false);
            new PlayerYMLManager().setPtime(sender.getName(), 0, false);
            sender.sendMessage(EssentialCommands.message(LAN_EN.PTIME_CONSTANT_FORMAT, 0));
        }
        else if(args[0].equalsIgnoreCase("sunset")){
            ((Player) sender).setPlayerTime(12000, false);
            new PlayerYMLManager().setPtime(sender.getName(), 12000, false);
            sender.sendMessage(EssentialCommands.message(LAN_EN.PTIME_CONSTANT_FORMAT, 12000));
        }
        else {
            long l = 0;
            try {
                l = Long.valueOf(args[0]);
            }
            catch (NumberFormatException e) {
                sender.sendMessage(EssentialCommands.message(LAN_EN.PTIME_NOT_NUMBER_FORMAT, args[0]));
            }
            if(args.length == 1) {
                ((Player) sender).setPlayerTime(l, false);
                new PlayerYMLManager().setPtime(sender.getName(), l, false);
            }
            else {
                try {
                    boolean b = Boolean.valueOf(args[1]);
                    ((Player) sender).setPlayerTime(l, b);
                    new PlayerYMLManager().setPtime(sender.getName(), l, b);
                }
                catch (NumberFormatException e) {
                    sender.sendMessage(EssentialCommands.message(LAN_EN.PTIME_NOT_BOOLEAN_FORMAT, args[1]));
                }
            }
        }
        return true;
    }

    private Map<String, List<String>> getCommandMap() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("reset", Arrays.asList("Reset your time to the server time." , "/ptime reset"));
        map.put("dawn", Arrays.asList("Set your player time to an endless dawn." , "/ptime dawn"));
        map.put("day", Arrays.asList("Set your player time to an endless day." , "/ptime day"));
        map.put("sunset", Arrays.asList("Set your player time to an endless sunset." , "/ptime sunset"));
        map.put("night", Arrays.asList("Set your player time to an endless night." , "/ptime night"));
        map.put("custom", Arrays.asList("Set your player time to an individual state." , "/ptime <ticks> [true|false]"));
        return map;
    }

}
