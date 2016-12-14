package de.fenmore.tobiasThirolf.selfmadeEssentials.events;

import de.fenmore.tobiasThirolf.selfmadeEssentials.files.UuidYMLManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * Created by Fenmore on 03.12.2016.
 */
public class EventAsyncPlayerPreLogin implements Listener {

    @EventHandler
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        UuidYMLManager.checkState(event.getUniqueId().toString(), event.getName());
    }
}
