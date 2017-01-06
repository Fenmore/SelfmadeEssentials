package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.LocalizedMessenger;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.InvalidPlayerNameException;
import de.wonderworld.plugins.selfmadeEssentials.exceptions.PlayerNotFoundException;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class CommandRepair extends PlayerCommand {


    private LocalizedMessenger localizedMessenger;

    public CommandRepair(LocalizedMessenger localizedMessenger) {
        super(localizedMessenger);
        this.localizedMessenger = localizedMessenger;
    }

    @Override
    protected boolean onPlayerCommand(Player sender, Command cmd, String label, String[] args) throws PlayerNotFoundException, InvalidPlayerNameException {
        ItemStack item = sender.getInventory().getItemInMainHand();
            if(item.getType().equals(Material.AIR)) {
                localizedMessenger.sendLocalizedMessage(sender, "ITEM_MUST_BE_AN_OBJECT");
            }
            else{
                item.setDurability((short) 0);
                localizedMessenger.sendLocalizedMessage(sender, "ITEM_REPAIRED");
            }
        return true;
    }
}
