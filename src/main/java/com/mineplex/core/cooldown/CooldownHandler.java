package com.mineplex.core.cooldown;

import com.mineplex.core.event.CooldownExpireEvent;
import com.mineplex.core.util.Components;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public record CooldownHandler(CooldownService cooldownService, Map<UUID, Map<String, GlyphFrame>> cache) implements Runnable {

    private static final int BAR_LENGTH = 24;

    private static final String[] FILLED_CACHE = new String[BAR_LENGTH + 1];
    private static final String[] EMPTY_CACHE = new String[BAR_LENGTH + 1];

    static {

        for (var index = 0; index <= BAR_LENGTH; index++) {

            FILLED_CACHE[index] = "§a▌".repeat(index);
            EMPTY_CACHE[index] = "§c▌".repeat(BAR_LENGTH - index);

        }

    }

    @Override
    public void run() {

        for (final var iteratored = cooldownService.getCooldowns().iterator(); iteratored.hasNext(); ) {

            final var entry = iteratored.next();

            final var uniqueId = entry.getKey();
            final var map = entry.getValue();

            if (map.isEmpty()) {
                iteratored.remove();
                cache.remove(uniqueId);
                continue;
            }

            final var player = Bukkit.getPlayer(uniqueId);

            if (player == null) {
                iteratored.remove();
                cache.remove(uniqueId);
                continue;
            }

            final var cooldowns = map.values();

            for (final var iterator = cooldowns.iterator(); iterator.hasNext(); ) {

                final var cooldown = iterator.next();

                if (cooldown == null) {
                    iterator.remove();
                    continue;
                }

                if (cooldown.isExpired()) {
                    iterator.remove();
                    player.sendActionBar(Components.EMPTY);
                    clearCache(uniqueId, cooldown.namespace());
                    new CooldownExpireEvent(player, cooldown).callEvent();
                    continue;
                }

                if (cooldown.showInBar())
                    trySendCooldownMessage(player, uniqueId, cooldown);

            }

        }

    }

    private void trySendCooldownMessage(Player target, UUID uniqueId, Cooldown cooldown) {

        final var progress = Math.max(0.0D, Math.min(100.0D, cooldown.progress()));
        final var count = (int) Math.round((progress / 100.0D) * BAR_LENGTH);
        final var formatted = cooldown.formatted();
        final var namespace = cooldown.namespace();

        final var map = cache.computeIfAbsent(uniqueId, k -> new ConcurrentHashMap<>());
        final var previous = map.get(namespace);

        if (previous != null && previous.count() == count && previous.formatted().equals(formatted))
            return;

        map.put(namespace, new GlyphFrame(count, formatted));

        target.sendActionBar(buildCooldownMessage(cooldown, count, formatted));

    }

    private void clearCache(UUID uniqueId, String namespace) {

        final var map = cache.get(uniqueId);

        if (map != null) {
            map.remove(namespace);

            if (map.isEmpty())
                cache.remove(uniqueId);

        }

    }

    private Component buildCooldownMessage(Cooldown cooldown, int count, String formatted) {

        final var bar = FILLED_CACHE[count] + EMPTY_CACHE[count];

        return Components.legacyAmpersand(cooldown.namespace() + " " + bar + " §f" + formatted + " Seconds");

    }

    public record GlyphFrame(int count, String formatted) {}

}