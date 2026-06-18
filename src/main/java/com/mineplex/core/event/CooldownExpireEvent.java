package com.mineplex.core.event;

import com.mineplex.core.cooldown.Cooldown;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
@RequiredArgsConstructor
public final class CooldownExpireEvent extends Event {

    private final Player player;

    private final Cooldown cooldown;

    @Getter
    private final static HandlerList handlerList = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

}