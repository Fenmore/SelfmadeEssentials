package de.wonderworld.plugins.selfmadeEssentials.events;

import de.wonderworld.plugins.selfmadeEssentials.commands.EssentialCommands;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class EventPlayerQuit implements Listener {

    private ModYMLManager modYMLManager;

    public EventPlayerQuit(ModYMLManager modYMLManager) {
        this.modYMLManager = modYMLManager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        List<String> vanishList = modYMLManager.getVanishActiveList();
        if (vanishList.contains(event.getPlayer().getName())) {
            event.setQuitMessage(null);
            if (Essentials.board.getTeam("vanishVisible").getEntries().contains(event.getPlayer().getName())) {
                Essentials.board.getTeam("vanishVisible").removeEntry(event.getPlayer().getName());
            }
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("selfmadeEssentials.vanish")) {
                    p.sendMessage(EssentialCommands.message(Constants.PLAYER_LEFT_VANISH_FORMAT, event.getPlayer().getName()));
                }
            }
        }
    }
}
