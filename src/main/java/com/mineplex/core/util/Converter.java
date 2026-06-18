package com.mineplex.core.util;

import java.util.StringJoiner;

public final class Converter {

    public static String formatAsClock(int timer) {

        final var hours = timer / 3600;
        final var minutes = (timer % 3600) / 60;
        final var seconds = timer % 60;

        if (hours > 0)
            return String.format("%01d:%02d:%02d", hours, minutes, seconds);

        return String.format("%01d:%02d", minutes, seconds);

    }

    public static String formatAsTime(int timer) {

        if (timer <= 0)
            return "0 seconds";

        final var hours = timer / 3600;
        final var minutes = (timer % 3600) / 60;
        final var seconds = timer % 60;

        final var joiner = new StringJoiner(" ");

        if (hours > 0)
            joiner.add(unit(hours, "hour", "hours"));

        if (minutes > 0)
            joiner.add(unit(minutes, "minute", "minutes"));

        if (seconds > 0)
            joiner.add(unit(seconds, "second", "seconds"));

        return joiner.toString();

    }

    private static String unit(int value, String singular, String plural) {
        return value + " " + (value == 1 ? singular : plural);
    }

}