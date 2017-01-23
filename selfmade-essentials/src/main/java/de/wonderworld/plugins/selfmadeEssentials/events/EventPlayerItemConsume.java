package de.wonderworld.plugins.selfmadeEssentials.events;

import de.wonderworld.plugins.selfmadeEssentials.files.PlayerYMLManager;
import de.wonderworld.plugins.selfmadeEssentials.files.YMLVariable;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventPlayerItemConsume implements Listener {

    private PlayerYMLManager playerYMLManager;

    public EventPlayerItemConsume() {

        this.playerYMLManager = new PlayerYMLManager();
    }


    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {

        boolean isActive = playerYMLManager.isActive(event.getPlayer().getName(), YMLVariable.UNLIMITED);

        if(isActive) {

            ItemStack itemInMainHand = event.getItem();

            itemInMainHand.setAmount(itemInMainHand.getAmount() + 1);

            event.setItem(itemInMainHand);

            event.getPlayer().updateInventory();
        }

    }
}


