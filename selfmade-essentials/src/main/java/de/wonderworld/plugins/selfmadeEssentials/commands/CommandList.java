package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import de.wonderworld.plugins.selfmadeEssentials.files.ListYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.json.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CommandList implements CommandExecutor{

    private Essentials plugin;
    private ListYMLManager listYMLManager ;
    private LocalizedMessenger localizedMessenger;
    public CommandList(Essentials plugin, ListYMLManager listYMLManager, LocalizedMessenger localizedMessenger) {
        this.plugin = plugin;
        this.listYMLManager = listYMLManager;
        this.localizedMessenger = localizedMessenger;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Map<String, Map<String, Object>> commandMap = listYMLManager.getCommandMap();
        List<String> sortedCommands = new ArrayList<>(commandMap.keySet());
        Collections.sort(sortedCommands);
        if(args.length == 0) {
            JsonWrapper json = new JsonWrapper().wrapJsonTellraw(sender.getName());
            for (String key1 : sortedCommands) {
                try {
                    String comments = "";
                    for (String comment : listYMLManager.getComments(key1)) {
                        comments += "\n- " + comment;
                    }
                    if (commandMap.get(key1).get("stage").toString().equals("stable")) {
                        json.addText(key1, JsonColor.GREEN, JsonFormat.UNDERLINED)
                                .addHoverEvent(HoverAction.SHOW_TEXT, plugin.getDescription().getCommands().get(key1).get("description") + comments, JsonColor.GOLD)
                                .addClickEvent(ClickAction.SUGGEST_COMMAND, (String) plugin.getDescription().getCommands().get(key1).get("usage"));
                    } else if (commandMap.get(key1).get("stage").toString().equals("beta")) {
                        json.addText(key1, JsonColor.YELLOW, JsonFormat.UNDERLINED)
                                .addHoverEvent(HoverAction.SHOW_TEXT, plugin.getDescription().getCommands().get(key1).get("description") + comments, JsonColor.GOLD)
                                .addClickEvent(ClickAction.SUGGEST_COMMAND, (String) plugin.getDescription().getCommands().get(key1).get("usage"));
                    } else if (commandMap.get(key1).get("stage").toString().equals("alpha")) {
                        json.addText(key1, JsonColor.RED, JsonFormat.UNDERLINED)
                                .addHoverEvent(HoverAction.SHOW_TEXT, plugin.getDescription().getCommands().get(key1).get("description") + comments, JsonColor.GOLD)
                                .addClickEvent(ClickAction.SUGGEST_COMMAND, (String) plugin.getDescription().getCommands().get(key1).get("usage"));
                    }
                    json.addText(" ");
                }
                catch (NullPointerException e) {
                    listYMLManager.removeCommand(key1);
                }
            }
            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), json.toString());
            return true;
        }

        Map<String, Object> attributeMap = commandMap.get(args[0].toLowerCase());
        if(attributeMap == null) {
            localizedMessenger.sendLocalizedMessage(sender, "COMMAND_NOT_FOUND_FORMAT", args[0]);
            return true;
        }

        if(args.length == 1) {
            List<String> comments = listYMLManager.getComments(args[0].toLowerCase());
            localizedMessenger.sendLocalizedMessage(sender, "COMMENT_INTRO");
            for(String comment : comments)
                sender.sendMessage("- " + comment);
            return true;
        }

        if(args.length > 2) {
            String comment = EssentialCommands.mergeArgs(args, 1);
            listYMLManager.addComment(comment, args[0].toLowerCase());
            localizedMessenger.sendLocalizedMessage(sender, "COMMENT_SET");
            return true;
        }

        if(!args[1].equalsIgnoreCase("alpha") && !args[1].equalsIgnoreCase("beta") && !args[1].equalsIgnoreCase("stable")) {
            localizedMessenger.sendLocalizedMessage(sender, "AVAILABLE_COMMAND_STAGES");
            return true;
        }

        if(args[1].equalsIgnoreCase("alpha")) {
            listYMLManager.setStage(args[0].toLowerCase(), "alpha");
            localizedMessenger.sendLocalizedMessage(sender, "COMMAND_CHANGED_TO_ALPHA_FORMAT", args[0]);
        }
        else if(args[1].equalsIgnoreCase("beta")) {
            listYMLManager.setStage(args[0].toLowerCase(), "beta");
            localizedMessenger.sendLocalizedMessage(sender, "COMMAND_CHANGED_TO_BETA_FORMAT", args[0]);
        }
        else if(args[1].equalsIgnoreCase("stable")) {
            listYMLManager.setStage(args[0].toLowerCase(), "stable");
            localizedMessenger.sendLocalizedMessage(sender, "COMMAND_CHANGED_TO_STABLE_FORMAT", args[0]);
        }
        return true;
    }
}
