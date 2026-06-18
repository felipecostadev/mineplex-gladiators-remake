package com.mineplex.player.user;

import com.mineplex.Gladiators;
import com.mineplex.game.GameManager;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class UserService {

    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    public UserService(Gladiators plugin, GameManager gameManager) {
        plugin.syncTimer(new UserHandler(this, gameManager), 20L, 20L);
    }

    public Collection<User> getUsers() {
        return users.values();
    }

    public User getUser(UUID uniqueId) {
        return users.get(uniqueId);
    }

    public User getUser(Player player) {
        return getUser(player.getUniqueId());
    }

    public void addUser(UUID uniqueId) {
        users.put(uniqueId, new User(uniqueId));
    }

    public User removeUser(UUID uniqueId) {
        return users.remove(uniqueId);
    }

    public User removeUser(Player player) {
        return removeUser(player.getUniqueId());
    }

}