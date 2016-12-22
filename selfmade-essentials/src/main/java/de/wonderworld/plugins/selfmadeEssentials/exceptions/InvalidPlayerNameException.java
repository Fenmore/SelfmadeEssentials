package de.wonderworld.plugins.selfmadeEssentials.exceptions;

public class InvalidPlayerNameException extends Throwable {

    private String playerName;

    public InvalidPlayerNameException(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
