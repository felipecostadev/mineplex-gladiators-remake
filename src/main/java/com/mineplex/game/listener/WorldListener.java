package com.mineplex.game.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.WorldInitEvent;

public final class WorldListener implements Listener {

    @EventHandler
    public void onWorldInit(WorldInitEvent event) {

        final var world = event.getWorld();
        world.setAutoSave(false);

    }

    @EventHandler
    private void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

}