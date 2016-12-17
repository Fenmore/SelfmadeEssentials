package de.wonderworld.plugins.selfmadeEssentials.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class EventWeatherChange implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.toWeatherState())
            event.setCancelled(true);
    }
}
