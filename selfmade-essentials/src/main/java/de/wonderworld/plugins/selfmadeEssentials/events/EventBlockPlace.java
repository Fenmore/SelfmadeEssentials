package de.wonderworld.plugins.selfmadeEssentials.events;

import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.YMLVariable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class EventBlockPlace implements Listener {

    private PlayerYMLManager playerYMLManager;

    public EventBlockPlace() {

        this.playerYMLManager = new PlayerYMLManager();
    }


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        boolean isActive = playerYMLManager.isActive(event.getPlayer().getName(), YMLVariable.UNLIMITED);

        if (isActive) {

            ItemStack itemInHand = event.getItemInHand();
            int amount = itemInHand.getAmount();
            itemInHand.setAmount(amount);
            event.getPlayer().setItemInHand(itemInHand);
        }
    }
}
