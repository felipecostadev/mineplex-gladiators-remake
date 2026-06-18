package com.mineplex.game;

import com.mineplex.Gladiators;
import com.mineplex.core.util.Components;
import com.mineplex.core.util.Converter;
import com.mineplex.player.user.User;
import com.mineplex.player.user.UserService;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.function.BiConsumer;

@Getter
public final class Game implements Runnable {

    private final GameManager gameManager;

    private GameState state = GameState.LOADING;

    private String formattedTimeOfClock;
    private String formattedTime;

    private int time = -1;

    private boolean forceStart = false;

    private final ObjectArrayList<Component> scoreboardLines = new ObjectArrayList<>();

    public Game(GameManager gameManager) {
        this.gameManager = gameManager;

        updateState();
    }

    public boolean hasState(GameState gameState) {
        return this.state == gameState;
    }

    public boolean shouldNextState() {
        return this.time == 0;
    }

    public boolean shouldAnnounce() {

        if (formattedTime == null) return false;

        return this.time > 0 && this.time % 60 == 0 || this.time == 30 || this.time == 15 || this.time == 10 || this.time > 0 && this.time <= 5;
    }

    public void shouldPlayer(BiConsumer<Player, User> consumer, UserService userService) {

        final var uniqueIds = gameManager.getPlayers();

        for (final var uniqueId : uniqueIds) {

            final var user = userService.getUser(uniqueId);

            if (user == null)
                continue;

            final var player = user.getPlayer();

            if (player != null)
                consumer.accept(player, user);

        }

    }

    public void updateState() {
        this.state = state.getNextState();
        this.time = state.getTiming();
        this.formattedTime = null;
    }

    public void updateTime() {
        this.formattedTimeOfClock = Converter.formatAsClock(time);
        this.formattedTime = Converter.formatAsTime(time);
    }

    public void updateScoreboardLines() {

        scoreboardLines.clear();
        scoreboardLines.add(Components.EMPTY);

        final var uniqueIds = gameManager.getPlayers();
        final var playerCount = uniqueIds.size();

        switch (state) {

            case LOADING -> {

            }

            case STARTING -> {

                scoreboardLines.add(Components.miniMessage("<color:yellow><b>Players</b></color>"));
                scoreboardLines.add(Components.of(playerCount + "/" + 16));
                scoreboardLines.add(Components.EMPTY);
                scoreboardLines.add(Components.miniMessage("<color:yellow><b>Inicia em</b></color>"));
                scoreboardLines.add(Components.of(formattedTimeOfClock));

            }

            case EARLY_GAME -> {

                scoreboardLines.add(Components.miniMessage("<color:yellow><b>Players</b></color>"));
                scoreboardLines.add(Components.of(playerCount + " Vivo" + (playerCount != 1 ? "s" : "")));

            }

            default -> {

                scoreboardLines.add(Components.miniMessage("<color:yellow><b>Players</b></color>"));

                final var userService = Gladiators.getInstance().getUserService();

                for (final var uniqueId : uniqueIds) {

                    final var user = userService.getUser(uniqueId);

                    if (user == null)
                        continue;

                    final var player = user.getPlayer();

                    if (player != null)
                        scoreboardLines.add(Components.of(player.getName()));

                }

            }

        }

        scoreboardLines.add(Components.EMPTY);
        scoreboardLines.add(Components.SCOREBOARD_WEBSITE);

    }

    public void increaseTime() {
        this.time++;
    }

    public void decreaseTime() {
        this.time--;
    }

    public void forceStart() {
        this.forceStart = true;
        this.time = 0;
    }

    public void broadcast(String input) {
        Bukkit.broadcast(Components.miniMessage(input));
    }

    public void broadcast() {
        Bukkit.broadcast(Components.EMPTY);
    }

    @Override
    public void run() {

        final var plugin = Gladiators.getInstance();
        final var userService = plugin.getUserService();

        switch (state) {

            case STARTING -> {

                if (shouldNextState()) {
                    updateState();
                    broadcast("<color:dark_green><strikethrough>=============================================</strikethrough></color>");
                    broadcast("<color:green>Game</color> <color:gray>-</color> <color:yellow><b>Gladiators</b></color>");
                    broadcast();
                    broadcast("  This is a 1v1 soup tournament!");
                    broadcast("  Kill an then run to the next arena!");
                    broadcast("  There is only one victor!");
                    broadcast();
                    broadcast("<color:green>Map</color> <color:gray>-</color> <color:white><b>Ethereal Caverns</b></color> <color:gray>created by</color> <color:white><b>Mineplex Build Team</b></color>");
                    broadcast("<color:dark_green><strikethrough>=============================================</strikethrough></color>");

                    final var cooldownService = plugin.getCooldownService();

                    shouldPlayer((player, user) -> cooldownService.addCooldown(player, "Game Start", Duration.ofSeconds(time + 1), true), userService);

                    return;
                }

                decreaseTime();

            }

            case EARLY_GAME -> {

                if (shouldNextState()) {
                    updateState();

                    final var playerManager = plugin.getPlayerManager();

                    shouldPlayer((player, user) -> playerManager.grantTemplatePerState(player, user), userService);

                    return;
                }

                decreaseTime();

            }

            case MIDDLE_GAME -> {

                increaseTime();

            }

        }

        updateTime();
        updateScoreboardLines();

    }

}