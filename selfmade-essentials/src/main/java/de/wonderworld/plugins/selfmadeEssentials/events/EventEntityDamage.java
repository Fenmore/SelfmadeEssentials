package de.wonderworld.plugins.selfmadeEssentials.events;
import de.wonderworld.plugins.selfmadeEssentials.files.YMLVariable;
import de.wonderworld.plugins.selfmadeEssentials.files.ModYMLManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventEntityDamage implements Listener {

    private ModYMLManager modYMLManager;

    public EventEntityDamage() {

        this.modYMLManager = new ModYMLManager();
    }


    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (modYMLManager.isVariableActive(event.getEntity().getName(), YMLVariable.GOD_MODE)) {
                event.setCancelled(true);
            }
        }
    }
}
