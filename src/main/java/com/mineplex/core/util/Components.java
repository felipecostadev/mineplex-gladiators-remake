package com.mineplex.core.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Components {

    public static final LegacyComponentSerializer LEGACY_AMPERSAND = LegacyComponentSerializer.legacyAmpersand();

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public static final Component EMPTY = Component.empty();

    public static final Component SPACE = Component.space();

    public static final Component GAME_STARTED = miniMessage("<color:green><b>Start!</b></color>");

    public static final Component SCOREBOARD_TITLE = miniMessage("<gradient:white:yellow:gold:yellow:white><b>GLADIATORS</b></gradient>");
    public static final Component SCOREBOARD_WEBSITE = miniMessage("<color:gold>mineplex.com</color>");

    public static Component miniMessage(String input) {
        return MINI_MESSAGE.deserialize(input);
    }

    public static Component legacyAmpersand(String input) {
        return LEGACY_AMPERSAND.deserialize(input);
    }

    public static Component of(String input) {
        return Component.text(input);
    }

}