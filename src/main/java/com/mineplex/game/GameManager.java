package com.mineplex.game;

import com.mineplex.Gladiators;
import com.mineplex.player.user.User;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
public final class GameManager {

    private final Game game;

    private final ObjectArrayList<UUID> players = new ObjectArrayList<>();
    private final ObjectArrayList<UUID> spectators = new ObjectArrayList<>();

    public GameManager(Gladiators plugin) {
        plugin.syncTimer(this.game = new Game(this), 20L, 20L);
    }

    public void sendJoinPlayer(Player player, User user) {

        final var uniqueId = player.getUniqueId();

        players.add(uniqueId);

    }

    public void sendQuitPlayer(Player player, User user) {

        final var uniqueId = player.getUniqueId();

        players.remove(uniqueId);
        spectators.remove(uniqueId);

    }

}