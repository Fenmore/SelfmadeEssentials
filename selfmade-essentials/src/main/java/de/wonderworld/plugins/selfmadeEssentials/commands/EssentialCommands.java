package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class EssentialCommands {

    public static boolean validPlayerName(String name) {
        final StringBuilder nameBldr = new StringBuilder(name);
        for(int i = 0; i < nameBldr.length(); i++) {
            if(Constants.AVAILABLE_CHARS_IN_NAME.indexOf(nameBldr.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    public static String mergeArgs(String[] args, int start) {
        final StringBuilder bldr = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            if (i != start) {
                bldr.append(" ");
            }
            bldr.append(args[i]);
        }
        return bldr.toString();
    }

    public static String message(String string, Object... args) {
        return ChatColor.translateAlternateColorCodes('&', String.format(string, args));
    }

    public static String message(String string, String arg1, String arg2) {
        return ChatColor.translateAlternateColorCodes('&', String.format(string, arg1, arg2));
    }

    public static String message(String string, long arg) {
        return ChatColor.translateAlternateColorCodes('&', String.format(string, arg));
    }

    public static String message(String string, String arg) {
        return ChatColor.translateAlternateColorCodes('&', String.format(string, arg));
    }

    public static String message(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<Block> getLineOfSight(Player player) {
        List<Block> list = player.getLineOfSight((Set) null, 100);
        Block lastBlock = list.get(list.size() - 1);
        //if(lastBlock.getType().equals(Material.GRASS) || lastBlock.getType().equals(Material.VINE) || lastBlock.getType().equals(Material.GRASS))
        return list;
    }
}
