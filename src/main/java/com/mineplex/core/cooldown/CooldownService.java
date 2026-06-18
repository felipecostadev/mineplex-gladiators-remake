package com.mineplex.core.cooldown;

import com.mineplex.Gladiators;
import com.mineplex.core.util.Components;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class CooldownService {

    private final Map<UUID, Object2ObjectMap<String, Cooldown>> cooldowns = new ConcurrentHashMap<>();

    public CooldownService(Gladiators plugin) {
        plugin.syncTimer(new CooldownHandler(this, new ConcurrentHashMap<>()), 1L, 1L);
    }

    public Set<Entry<UUID, Object2ObjectMap<String, Cooldown>>> getCooldowns() {
        return cooldowns.entrySet();
    }

    public Cooldown getCooldown(UUID uniqueId, String namespace) {

        final var map = cooldowns.get(uniqueId);

        return map != null ? map.get(namespace) : null;

    }

    public Cooldown getCooldown(Player player, String namespace) {
        return getCooldown(player.getUniqueId(), namespace);
    }

    public void addCooldown(Player player, String namespace, Duration duration, boolean showInBar) {

        final var map = cooldowns.computeIfAbsent(player.getUniqueId(), k -> new Object2ObjectOpenHashMap<>());
        map.putIfAbsent(namespace, new Cooldown(namespace, System.nanoTime(), duration.toNanos(), showInBar));

    }

    public void removeCooldown(UUID uniqueId, String namespace) {

        final var map = cooldowns.get(uniqueId);

        if (map != null) {
            map.remove(namespace);

            if (map.isEmpty())
                cooldowns.remove(uniqueId);

        }

    }

    public void removeCooldown(Player player, String namespace) {

        player.sendActionBar(Components.EMPTY);

        removeCooldown(player.getUniqueId(), namespace);

    }

}