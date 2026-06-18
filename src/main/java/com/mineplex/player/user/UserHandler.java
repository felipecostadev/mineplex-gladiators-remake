package com.mineplex.player.user;

import com.mineplex.core.util.Components;
import com.mineplex.game.GameManager;

public record UserHandler(UserService userService, GameManager gameManager) implements Runnable {

    @Override
    public void run() {

        final var game = gameManager.getGame();

        if (game == null)
            return;

        for (final var user : userService.getUsers()) {

            if (user == null)
                continue;

            final var player = user.getPlayer();

            if (player == null)
                continue;

            final var scoreboard = user.getScoreboard();

            if (scoreboard != null) {
                scoreboard.updateTitle(Components.SCOREBOARD_TITLE);
                scoreboard.updateLines(game.getScoreboardLines());
            }

        }

    }

}