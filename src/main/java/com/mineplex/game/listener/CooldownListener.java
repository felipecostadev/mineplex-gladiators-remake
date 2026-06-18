package com.mineplex.game.listener;

import com.mineplex.core.event.CooldownExpireEvent;
import com.mineplex.core.util.Components;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class CooldownListener implements Listener {

    @EventHandler
    private void onCooldownExpire(CooldownExpireEvent event) {

        final var player = event.getPlayer();
        final var cooldown = event.getCooldown();

        if (cooldown.namespace().equals("Game Start"))
            player.sendActionBar(Components.GAME_STARTED);

    }

}