package com.mineplex.game.listener;

import com.mineplex.Gladiators;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public final class SoupListener implements Listener {

    private final ItemStack BOWL = ItemStack.of(Material.BOWL);

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getHand() == EquipmentSlot.OFF_HAND)
            return;

        if (!event.getAction().isRightClick())
            return;

        final var item = event.getItem();

        if (item == null || item.getType() != Material.MUSHROOM_STEW)
            return;

        final var game = Gladiators.getInstance().getGameManager().getGame();

        if (game == null)
            return;

        event.setCancelled(true);

        final var player = event.getPlayer();
        final var attribute = player.getAttribute(Attribute.MAX_HEALTH);

        final var health = player.getHealth();
        final var maxHealth = attribute != null ? attribute.getValue() : 20.0D;

        if (health < maxHealth) {

            var heal = health + 7.0D;

            if (heal > maxHealth)
                heal = maxHealth;

            player.setHealth(heal);

            player.getInventory().setItemInMainHand(BOWL);

        }

    }

}