package de.wonderworld.plugins.selfmadeEssentials.events;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventEntityDamage implements Listener {

    private ModYMLManager modYMLManager;

    public EventEntityDamage(ModYMLManager modYMLManager) {
        this.modYMLManager = modYMLManager;
    }


    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (modYMLManager.isGodmodeActive(event.getEntity().getName())) {
                event.setCancelled(true);
            }
        }
    }
}
