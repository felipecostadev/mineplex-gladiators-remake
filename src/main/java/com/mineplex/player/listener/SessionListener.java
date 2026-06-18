package com.mineplex.player.listener;

import com.mineplex.Gladiators;
import com.mineplex.player.user.UserState;
import fr.mrmicky.fastboard.adventure.FastBoard;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class SessionListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    private void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {

        Gladiators.getInstance().getUserService().addUser(event.getUniqueId());

    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {

        final var player = event.getPlayer();
        final var attribute = player.getAttribute(Attribute.ATTACK_SPEED);

        if (attribute != null)
            attribute.setBaseValue(1024.0D);

        player.setCooldown(Material.ENDER_PEARL, 0);

        final var plugin = Gladiators.getInstance();
        final var user = plugin.getUserService().getUser(player);

        if (user == null)
            return;

        user.setPlayer(player);
        user.setAbility(plugin.getAbilityService().getDefaultAbility());
        user.setState(UserState.ROUND_OF_PLAYERS);
        user.setScoreboard(new FastBoard(player));

        final var gameManager = plugin.getGameManager();

        if (gameManager == null)
            return;

        gameManager.sendJoinPlayer(player, user);

    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {

        final var plugin = Gladiators.getInstance();

        final var player = event.getPlayer();
        final var user = plugin.getUserService().removeUser(player);

        if (user == null)
            return;

        user.setPlayer(null);
        user.setAbility(null);
        user.setState(null);
        user.setScoreboard(null);

        final var gameManager = plugin.getGameManager();

        if (gameManager == null)
            return;

        gameManager.sendQuitPlayer(player, user);

    }

}