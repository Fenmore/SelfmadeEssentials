package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CommandSuicide extends PlayerCommand {


    private LocalizedMessenger localizedMessenger;

    public CommandSuicide(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.localizedMessenger = localizedMessenger;
    }

    @Override
    public boolean onPlayerCommand(Player sender, Command command, String label, String[] args) {
        sender.setHealth(0);

        for(Player player: Bukkit.getOnlinePlayers()){
            localizedMessenger.sendLocalizedMessage(player, "PLAYER_KILLED_HIMSELF_FORMAT", sender.getName());
        }
        return true;
    }
}
