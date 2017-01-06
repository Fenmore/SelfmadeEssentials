package de.wonderworld.plugins.selfmadeEssentials.events;

import de.fenmore.localization.LocalizedMessenger;
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
    private LocalizedMessenger localizedMessenger;

    public EventPlayerQuit(LocalizedMessenger localizedMessenger) {
        this.localizedMessenger = localizedMessenger;
        this.modYMLManager = new ModYMLManager();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        List<String> vanishList = modYMLManager.getVanishActiveList();
        if (vanishList.contains(event.getPlayer().getName())) {
            event.setQuitMessage(null);
            unregisterFromMessageBoard(event);
            informPlayer(event);
        }
    }

    private void informPlayer(PlayerQuitEvent event) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.hasPermission("selfmadeEssentials.vanish")) {
                localizedMessenger.sendLocalizedMessage(p, "PLAYER_LEFT_VANISH_FORMAT", event.getPlayer().getName());
            }
        }
    }

    private void unregisterFromMessageBoard(PlayerQuitEvent event) {
        if (Essentials.board.getTeam("vanishVisible").getEntries().contains(event.getPlayer().getName())) {
            Essentials.board.getTeam("vanishVisible").removeEntry(event.getPlayer().getName());
        }
    }
}
