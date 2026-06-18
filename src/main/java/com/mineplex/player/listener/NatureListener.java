package com.mineplex.player.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public final class NatureListener implements Listener {

    @EventHandler
    private void onEntityExplode(EntityExplodeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    private void onEntityPickupItem(EntityPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    private void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    private void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

}