package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.json.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandPtime extends PlayerCommand {


    private LocalizedMessenger localizedMessenger;

    public CommandPtime(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.localizedMessenger = localizedMessenger;
    }

    @Override
    public boolean onPlayerCommand(Player player, Command cmd, String label, String[] args) {

        if(args.length == 0) {
            if(player.isPlayerTimeRelative() && player.getPlayerTime() == player.getWorld().getFullTime()) {
                localizedMessenger.sendLocalizedMessage(player, "PTIME_EQUAL_TO_SERVER_FORMAT");
                return true;
            }
            else if(!player.isPlayerTimeRelative()) {
                localizedMessenger.sendLocalizedMessage(player, "PTIME_ABSOLUTE_FORMAT", player.getPlayerTime());
                return true;
            }
            else if(player.getPlayerTimeOffset() > 0) {
                localizedMessenger.sendLocalizedMessage(player, "PTIME_RELATIVE_AHEAD_FORMAT", player.getPlayerTimeOffset());
            }
            else if(((Player) player).getPlayerTimeOffset() < 0) {
                localizedMessenger.sendLocalizedMessage(player, "PTIME_RELATIVE_BEHIND_FORMAT", ((Player) player).getPlayerTimeOffset());
            }
        }
        else if(args[0].equalsIgnoreCase("list")){
            localizedMessenger.sendLocalizedMessage(player, "PTIME_INTRO");
            JsonWrapper json = new JsonWrapper().wrapJsonTellraw(player.getName());
            Map<String, List<String>> ptimeMap = getCommandMap();
            for(String key : ptimeMap.keySet()) {
                json.addText(key, JsonColor.GOLD, JsonFormat.UNDERLINED).addHoverEvent(HoverAction.SHOW_TEXT, ptimeMap.get(key).get(0), JsonColor.GOLD)
                        .addClickEvent(ClickAction.SUGGEST_COMMAND, ptimeMap.get(key).get(1)).addText(" ");
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), json.toString());
        }
        else if(args[0].equalsIgnoreCase("reset")) {
            ((Player) player).resetPlayerTime();
            new PlayerYMLManager().remPtime(player.getName());
            localizedMessenger.sendLocalizedMessage(player, "PTIME_RESET");
        }
        else if(args[0].equalsIgnoreCase("day")){
            ((Player) player).setPlayerTime(6000, false);
            new PlayerYMLManager().setPtime(player.getName(), 6000, false);
            localizedMessenger.sendLocalizedMessage(player, "PTIME_CONSTANT_FORMAT", 6000);
        }
        else if(args[0].equalsIgnoreCase("night")){
            ((Player) player).setPlayerTime(18000, false);
            new PlayerYMLManager().setPtime(player.getName(), 18000, false);
            localizedMessenger.sendLocalizedMessage(player, "PTIME_CONSTANT_FORMAT", 18000);
        }
        else if(args[0].equalsIgnoreCase("dawn")){
            ((Player) player).setPlayerTime(0, false);
            new PlayerYMLManager().setPtime(player.getName(), 0, false);
            localizedMessenger.sendLocalizedMessage(player, "PTIME_CONSTANT_FORMAT", 0);
        }
        else if(args[0].equalsIgnoreCase("sunset")){
            ((Player) player).setPlayerTime(12000, false);
            new PlayerYMLManager().setPtime(player.getName(), 12000, false);
            localizedMessenger.sendLocalizedMessage(player, "PTIME_CONSTANT_FORMAT", 12000);
        }
        else {
            long l = 0;
            try {
                l = Long.valueOf(args[0]);
            }
            catch (NumberFormatException e) {
                localizedMessenger.sendLocalizedMessage(player, "PTIME_NOT_NUMBER_FORMAT", args[0]);
            }
            if(args.length == 1) {
                ((Player) player).setPlayerTime(l, false);
                new PlayerYMLManager().setPtime(player.getName(), l, false);
            }
            else {
                try {
                    boolean b = Boolean.valueOf(args[1]);
                    ((Player) player).setPlayerTime(l, b);
                    new PlayerYMLManager().setPtime(player.getName(), l, b);
                }
                catch (NumberFormatException e) {
                    localizedMessenger.sendLocalizedMessage(player, "PTIME_NOT_BOOLEAN_FORMAT", args[1]);
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
