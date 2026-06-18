package com.mineplex.player.user;

public enum UserState {

    ROUND_OF_PLAYERS,

    QUARTER_FINALS,

    SEMI_FINALS,

    FINALS;

    public UserState getNextState() {
        return this == FINALS ? FINALS : values()[ordinal() + 1];
    }

}