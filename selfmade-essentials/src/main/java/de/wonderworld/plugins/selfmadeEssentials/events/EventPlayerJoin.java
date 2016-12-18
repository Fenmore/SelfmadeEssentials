package de.wonderworld.plugins.selfmadeEssentials.events;

import de.wonderworld.plugins.selfmadeEssentials.commands.EssentialCommands;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Constants;
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

    public EventPlayerJoin(ModYMLManager modYMLManager) {
        this.modYMLManager = modYMLManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {


        List<String> vanishActiveList = modYMLManager.getVanishActiveList();
        //List<String> vanishVisibleList = modYMLManager.getVanishVisibleList();
        if (!event.getPlayer().hasPermission("selfmadeEssentials.vanish")) {
            for (String name : vanishActiveList) {
                Player playerToHide = Bukkit.getPlayer(name);
                if (playerToHide != null)
                    event.getPlayer().hidePlayer(playerToHide);
            }
        } else {
            Essentials.board.getTeam("vanishVisible").addEntry(event.getPlayer().getName());
            event.getPlayer().setScoreboard(Essentials.board);
                /*for(Player player : Bukkit.getOnlinePlayers()) {
                    if(vanishActiveList.contains(player.getName())) {
                        event.getPlayer().hidePlayer(player);
                        event.getPlayer().showPlayer(player);
                    }
                }*/

            if (vanishActiveList.contains(event.getPlayer().getName())) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.hasPermission("selfmadeEssentials.vanish")) {
                        p.hidePlayer(event.getPlayer());
                    } else {
                        p.sendMessage(EssentialCommands.message(Constants.PLAYER_JOINED_VANISH_FORMAT, event.getPlayer().getName()));
                    }
                }
                event.setJoinMessage(null);
            }
        }




        PlayerYMLManager pym = new PlayerYMLManager();
        if (pym.hasPtime(event.getPlayer().getName()))
            event.getPlayer().setPlayerTime(pym.getPtimeTicks(event.getPlayer().getName()), pym.getPtimeState(event.getPlayer().getName()));

    }
}
