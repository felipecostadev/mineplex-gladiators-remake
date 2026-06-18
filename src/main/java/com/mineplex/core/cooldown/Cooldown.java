package com.mineplex.core.cooldown;

public record Cooldown(String namespace, long startTime, long durationTime, boolean showInBar) {

    public boolean isExpired() {
        return remainingNanos() <= 0L;
    }

    public long remainingNanos() {
        return Math.max(0L, (startTime + durationTime) - System.nanoTime());
    }

    public double remainingSeconds() {
        return remainingNanos() / 1000000000.0D;
    }

    public double percentage() {
        return (remainingNanos() * 100.0D) / durationTime;
    }

    public double progress() {
        return 100.0D - percentage();
    }

    public String formatted() {

        if (isExpired())
            return "0.0";

        return String.format("%.1f", remainingSeconds());

    }

}