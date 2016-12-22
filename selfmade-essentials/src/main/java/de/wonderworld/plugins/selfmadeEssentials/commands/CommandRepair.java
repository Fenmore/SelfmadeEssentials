package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import de.wonderworld.plugins.selfmadeEssentials.localization.LAN_EN;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class CommandRepair extends PlayerCommand {

    @Override
    protected boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) throws PlayerNotFoundException, InvalidPlayerNameException {
        ItemStack item = sender.getInventory().getItemInMainHand();
            if(item.getType().equals(Material.AIR)) {
                sender.sendMessage(EssentialCommands.message(LAN_EN.ITEM_MUST_BE_AN_OBJECT));
            }

            else{
                item.setDurability((short) 0);
                sender.sendMessage(EssentialCommands.message(LAN_EN.ITEM_REPAIRED) );

            }

        return true;
    }
}
