package com.mineplex.player.user;

import com.mineplex.game.ability.Ability;
import fr.mrmicky.fastboard.adventure.FastBoard;
import lombok.Data;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
public final class User {

    private final UUID uniqueId;

    private Player player;

    private Ability ability;

    private UserState state;

    private FastBoard scoreboard;

    public User(UUID uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void updateState() {
        this.state = state.getNextState();
    }

}