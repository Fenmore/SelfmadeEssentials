package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by David on 23.12.2016.
 */
public class CommandUnlimited extends PlayerCommand {


    @Override
    public boolean onPlayerCommand(Player sender, Command command, String label, String[] args) throws PlayerNotFoundException, InvalidPlayerNameException {
        ItemStack item = sender.getInventory().getItemInMainHand();

        return false;
    }
}
