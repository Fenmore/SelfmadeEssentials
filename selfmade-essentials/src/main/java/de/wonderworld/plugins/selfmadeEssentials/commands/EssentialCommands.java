package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class EssentialCommands {

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

    public static List<Block> getLineOfSight(Player player) {
        List<Block> list = player.getLineOfSight((Set) null, 100);
        Block lastBlock = list.get(list.size() - 1);
        //if(lastBlock.getType().equals(Material.GRASS) || lastBlock.getType().equals(Material.VINE) || lastBlock.getType().equals(Material.GRASS))
        return list;
    }

    public static Location getSafeLocation(Location loc) {
        while (true) {
            if (loc.getWorld().getBlockAt(loc).getType().isSolid() || loc.getWorld().getBlockAt(loc).getType().equals(Material.STATIONARY_WATER) ||
                    loc.getWorld().getBlockAt(loc).getType().equals(Material.STATIONARY_LAVA) || loc.getWorld().getBlockAt(loc).getType().equals(Material.WATER) ||
                    loc.getWorld().getBlockAt(loc).getType().equals(Material.LAVA)) {
                loc.setY(loc.getBlockY() + 1);
                break;
            }
            loc.setY(loc.getBlockY() - 1);
        }
        return loc;
    }

    public static Player getPlayer(String name) throws InvalidPlayerNameException, PlayerNotFoundException {
        Player p = Bukkit.getPlayer(name);
        if(p == null) {
            throw new PlayerNotFoundException(name);
        }
        return p;
    }
}
