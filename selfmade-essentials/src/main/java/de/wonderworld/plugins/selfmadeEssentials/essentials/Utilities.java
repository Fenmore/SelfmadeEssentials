package de.wonderworld.plugins.selfmadeEssentials.essentials;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utilities {

    public static Player getPlayer(String name) throws InvalidPlayerNameException, PlayerNotFoundException {
        Player p = Bukkit.getPlayer(name);
        if (p == null) {
            throw new PlayerNotFoundException(name);
        }
        return p;
    }

    public static void removeVanishPlayerFromBoard(String playerName) {
        Essentials.board.getTeam("vanishVisible").removeEntry(playerName);
        Bukkit.getPlayer(playerName).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    public static void addVanishPlayerToBoard(String playerName) {
        Essentials.board.getTeam("vanishVisible").addEntry(playerName);
        Bukkit.getPlayer(playerName).setScoreboard(Essentials.board);
    }

}
