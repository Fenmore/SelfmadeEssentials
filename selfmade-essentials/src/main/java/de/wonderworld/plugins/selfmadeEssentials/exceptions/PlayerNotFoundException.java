package de.wonderworld.plugins.selfmadeEssentials.exceptions;

public class PlayerNotFoundException extends Throwable {

    public String playerName;

    public PlayerNotFoundException(String playerName) throws InvalidPlayerNameException {
        if (validPlayerName(playerName))
            this.playerName = playerName;
        else
            throw new InvalidPlayerNameException(playerName);
    }

    private static boolean validPlayerName(String name) {
        final StringBuilder nameBldr = new StringBuilder(name);
        for (int i = 0; i < nameBldr.length(); i++) {
            if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_".indexOf(nameBldr.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    public String getPlayerName() {
        return playerName;
    }
}
