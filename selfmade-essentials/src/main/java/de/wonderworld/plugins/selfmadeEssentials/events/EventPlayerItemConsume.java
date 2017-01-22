package de.wonderworld.plugins.selfmadeEssentials.events;

import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.YMLVariable;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class EventPlayerItemConsume implements Listener {

    private PlayerYMLManager playerYMLManager;

    public EventPlayerItemConsume() {

        this.playerYMLManager = new PlayerYMLManager();
    }


    @EventHandler
    public void onBlockPlace(PlayerItemConsumeEvent event) {

        boolean isActive = playerYMLManager.isActive(event.getPlayer().getName(), YMLVariable.UNLIMITED);

        if(isActive) {

            ItemStack itemInMainHand = event.getItem();
            int oldItemAmount = itemInMainHand.getAmount();
            int newItemAmount = oldItemAmount + 2;
            System.out.println("PlayerItemConsumeEvent: oldItemAmount: " + oldItemAmount + " newItemAmount: " + newItemAmount);

            ItemStack newItemStack = new ItemStack(itemInMainHand.getType(), newItemAmount);
            int slot = event.getPlayer().getInventory().getHeldItemSlot();

            event.getPlayer().getInventory().setItem(slot, newItemStack);

            //event.setItem(newItemStack);
        }

    }
}


