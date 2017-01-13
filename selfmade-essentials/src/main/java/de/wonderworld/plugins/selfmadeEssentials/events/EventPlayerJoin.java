package de.wonderworld.plugins.selfmadeEssentials.events;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class EventPlayerJoin implements Listener {

    private ModYMLManager modYMLManager;
    private LocalizedMessenger localizedMessenger;
    private PlayerYMLManager playerYMLManager;

    public EventPlayerJoin(ModYMLManager modYMLManager, LocalizedMessenger localizedMessenger, PlayerYMLManager playerYMLManager) {
        this.modYMLManager = modYMLManager;
        this.localizedMessenger = localizedMessenger;
        this.playerYMLManager = playerYMLManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        List<String> vanishActiveList = modYMLManager.getVanishActiveList();
        if (!event.getPlayer().hasPermission(Essentials.vanishPermission)) {
            for (String name : vanishActiveList) {
                Player playerToHide = Bukkit.getPlayer(name);
                if (playerToHide != null)
                    event.getPlayer().hidePlayer(playerToHide);
            }
        } else {
            Essentials.board.getTeam("vanishVisible").addEntry(event.getPlayer().getName());
            event.getPlayer().setScoreboard(Essentials.board);
            if (vanishActiveList.contains(event.getPlayer().getName())) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.hasPermission(Essentials.vanishPermission)) {
                        p.hidePlayer(event.getPlayer());
                    } else {
                        localizedMessenger.sendLocalizedMessage(p, "PLAYER_JOINED_VANISH_FORMAT", event.getPlayer().getName());
                    }
                }
                event.setJoinMessage(null);
            }
        }

        if (playerYMLManager.hasPtime(event.getPlayer().getName()))
            event.getPlayer().setPlayerTime(playerYMLManager.getPtimeTicks(event.getPlayer().getName()), playerYMLManager.getPtimeState(event.getPlayer().getName()));

    }
}
