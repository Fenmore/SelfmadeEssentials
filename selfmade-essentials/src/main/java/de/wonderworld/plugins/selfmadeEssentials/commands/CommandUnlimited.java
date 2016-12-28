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
    protected boolean onPlayerCommand(Player sender, Command command, String label, String[] args) throws PlayerNotFoundException, InvalidPlayerNameException {
        /*ItemStack item = sender.getInventory().getItemInMainHand();
        if (){

        }
        else{
            //Class PlayerItemConsumeEvent
            //static HandlerList 	getHandlerList()
            //HandlerList 	getHandlers()
            //ItemStack 	getItem()
            //Gets the item that is being consumed.
            //boolean 	isCancelled()
           // Gets the cancellation state of this event.
           // void 	setCancelled(boolean cancel)
           // Sets the cancellation state of this event.
           // void 	setItem(ItemStack item)
           // Set the item being consumed
        }*/

    return false;
    }
}
    //getPreviousSlot()