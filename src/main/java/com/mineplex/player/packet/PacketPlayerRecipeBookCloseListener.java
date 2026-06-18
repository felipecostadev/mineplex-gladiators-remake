package com.mineplex.player.packet;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.recipe.RecipeBookSettings;
import com.github.retrooper.packetevents.protocol.recipe.RecipeBookSettings.TypeState;
import com.github.retrooper.packetevents.protocol.recipe.RecipeBookType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientSetRecipeBookState;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerRecipeBookSettings;
import org.bukkit.entity.Player;

import java.util.EnumMap;

public final class PacketPlayerRecipeBookCloseListener implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {

        if (event.getPacketType() != PacketType.Play.Client.SET_RECIPE_BOOK_STATE || event.isCancelled())
            return;

        if (!(event.getPlayer() instanceof Player player))
            return;

        final var wrapperPlayClientSetRecipeBookState = new WrapperPlayClientSetRecipeBookState(event);

        if (!wrapperPlayClientSetRecipeBookState.isBookOpen())
            return;

        final var states = new EnumMap<RecipeBookType, TypeState>(RecipeBookType.class);

        for (final var recipeBookType : RecipeBookType.values())
            states.put(recipeBookType, new TypeState(false, false));

        final var recipeBookSettings = new RecipeBookSettings(states);
        final var wrapperPlayServerRecipeBookSettings = new WrapperPlayServerRecipeBookSettings(recipeBookSettings);

        PacketEvents.getAPI().getPlayerManager().sendPacket(player, wrapperPlayServerRecipeBookSettings);

        player.updateInventory();

    }

}