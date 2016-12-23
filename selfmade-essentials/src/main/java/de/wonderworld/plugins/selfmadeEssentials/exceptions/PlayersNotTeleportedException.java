package de.wonderworld.plugins.selfmadeEssentials.exceptions;

import org.bukkit.entity.Player;

import java.util.List;

public class PlayersNotTeleportedException extends Throwable {

    private List<Player> players;

    public PlayersNotTeleportedException(List<Player> playersNotTeleported) {
        this.players = playersNotTeleported;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
