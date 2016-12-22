package de.wonderworld.plugins.selfmadeEssentials.exceptions;

public class PlayerNotFoundException extends Throwable {

    public String playerName;

    public PlayerNotFoundException(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
