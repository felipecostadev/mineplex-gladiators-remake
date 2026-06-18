package com.mineplex.game.ability.handler;

import com.mineplex.core.util.Components;
import com.mineplex.core.util.ItemBuilder;
import com.mineplex.game.ability.Ability;
import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.key.Key;
import org.bukkit.Material;

@SuppressWarnings("UnstableApiUsage")
public final class MagmaAbilityHandler extends Ability {

    public MagmaAbilityHandler() {
        setName("Magma");
        setIconStack(
                new ItemBuilder(Material.FLINT_AND_STEEL)
                        .setData(DataComponentTypes.ITEM_MODEL, Key.key("minecraft:item/fire"))
                        .build()
        );
        setItemStack(
                new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                        .setName(Components.miniMessage("<italic:false><color:dark_gray>Slot de Kit</color>"))
                        .build()
        );
    }

}