package com.mineplex.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum GameState {

    LOADING(-1),

    STARTING(60),

    EARLY_GAME(10),

    MIDDLE_GAME(0),

    ENDGAME(20);

    @Getter
    private final int timing;

    public GameState getNextState() {
        return this == ENDGAME ? ENDGAME : values()[ordinal() + 1];
    }

}