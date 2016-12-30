package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.NotInstanceOfPlayerException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayersNotTeleportedException;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
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

    private static Location getSafeLocation(Location locToTest) {
        Location loc = new Location(locToTest.getWorld(), locToTest.getX(), locToTest.getY(), locToTest.getZ());
        while (true) {
            if (loc.getBlockY() < 0)
                return null;
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
        if (p == null) {
            throw new PlayerNotFoundException(name);
        }
        return p;
    }

    public static void teleportPlayers(Player[] playersToTeleport, Location destination) throws PlayersNotTeleportedException {
        PlayerYMLManager playerYMLManager = new PlayerYMLManager();
        Location safeLoc = getSafeLocation(destination);
        List<Player> playersNotTeleported = new ArrayList<>();
        for (Player p : playersToTeleport) {
            if (safeLoc == null) {
                if (p.getAllowFlight()) {
                    playerYMLManager.setBackLocation(p.getName(), p.getLocation());
                    p.teleport(destination);
                } else {
                    playersNotTeleported.add(p);
                }
            } else if (destination.getBlockY() == safeLoc.getBlockY()) {
                playerYMLManager.setBackLocation(p.getName(), p.getLocation());
                p.teleport(destination);
            } else {
                if (p.getAllowFlight()) {
                    playerYMLManager.setBackLocation(p.getName(), p.getLocation());
                    p.teleport(destination);
                    p.setFlying(true);
                } else {
                    playerYMLManager.setBackLocation(p.getName(), p.getLocation());
                    p.teleport(safeLoc);
                }
            }
        }
        if(!playersNotTeleported.isEmpty())
            throw new PlayersNotTeleportedException(playersNotTeleported);
    }

    public static List<Player> getPlayersFromArgumets(String[] args, CommandSender sender) throws NotInstanceOfPlayerException, InvalidPlayerNameException, PlayerNotFoundException {

        List<Player> playersFromArguments = new ArrayList<>();
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                playersFromArguments.add(p);
            } else {
                throw new NotInstanceOfPlayerException();
            }
        } else {
            for (String name : args) {
                Player p = EssentialCommands.getPlayer(name);
                playersFromArguments.add(p);
            }
        }

        return playersFromArguments;
    }
}
