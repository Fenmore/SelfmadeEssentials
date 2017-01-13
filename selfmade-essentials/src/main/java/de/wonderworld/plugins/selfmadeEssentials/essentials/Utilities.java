package de.wonderworld.plugins.selfmadeEssentials.essentials;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Utilities {

    public static Player getPlayer(String name) throws InvalidPlayerNameException, PlayerNotFoundException {
        Player p = Bukkit.getPlayer(name);
        if (p == null) {
            throw new PlayerNotFoundException(name);
        }
        return p;
    }

    public static void removeVanishPlayerFromBoard(String playerName) throws InvalidPlayerNameException, PlayerNotFoundException {
        Essentials.board.getTeam("vanishVisible").removeEntry(playerName);
        Utilities.getPlayer(playerName).setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    public static void addVanishPlayerToBoard(String playerName) throws InvalidPlayerNameException, PlayerNotFoundException {
        Essentials.board.getTeam("vanishVisible").addEntry(playerName);
        Utilities.getPlayer(playerName).setScoreboard(Essentials.board);
    }

    public static void vanishPermissionSet(String playerName) {

        try {

            Player playerToAllowVanish = Utilities.getPlayer(playerName);
            ModYMLManager modYMLManager = new ModYMLManager();
            List<String> vanishActiveList = modYMLManager.getVanishActiveList();

            System.out.println(vanishActiveList);

            Bukkit.getOnlinePlayers()
                    .stream()
                    .filter(player -> vanishActiveList.contains(player.getName()))
                    .forEach(playerToAllowVanish::showPlayer);

            Utilities.addVanishPlayerToBoard(playerName);

        } catch (PlayerNotFoundException | InvalidPlayerNameException ignored) {

        }
    }

    public static void vanishPermissionRemoved(String playerName) {

        try {

            Player playerToDenyVanish = Utilities.getPlayer(playerName);
            ModYMLManager modYMLManager = new ModYMLManager();
            List<String> vanishActiveList = modYMLManager.getVanishActiveList();

            System.out.println(vanishActiveList);

            if (vanishActiveList.contains(playerName)) {
                modYMLManager.remVanish(playerName);
                playerToDenyVanish.removePotionEffect(PotionEffectType.INVISIBILITY);
            }

            for (Player player : Bukkit.getOnlinePlayers()) {
                if (vanishActiveList.contains(player.getName())) {
                    playerToDenyVanish.hidePlayer(player);
                }
                if (!player.hasPermission(Essentials.vanishPermission)) {
                    player.showPlayer(playerToDenyVanish);
                }
            }

            Utilities.removeVanishPlayerFromBoard(playerName);

        } catch (InvalidPlayerNameException | PlayerNotFoundException ignored) {

        }

    }
}
