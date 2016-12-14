package de.fenmore.tobiasThirolf.selfmadeEssentials.events;

import de.fenmore.tobiasThirolf.selfmadeEssentials.essentials.Essentials;
import de.fenmore.tobiasThirolf.selfmadeEssentials.files.ModYMLManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

/**
 * Created by Fenmore on 24.11.2016.
 */
public class EventPlayerQuit implements Listener{

    private ModYMLManager modYMLManager;
    public EventPlayerQuit(ModYMLManager modYMLManager) {
        this.modYMLManager = modYMLManager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        List<String> vanishList = modYMLManager.getVanishActiveList();
        if(vanishList.contains(event.getPlayer().getName())) {
            event.setQuitMessage(null);
            if(Essentials.board.getTeam("vanishVisible").getEntries().contains(event.getPlayer().getName())) {
                Essentials.board.getTeam("vanishVisible").removeEntry(event.getPlayer().getName());
            }
        }
    }
}
