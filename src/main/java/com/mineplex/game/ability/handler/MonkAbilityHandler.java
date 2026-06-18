package com.mineplex.game.ability.handler;

import com.mineplex.core.util.Components;
import com.mineplex.core.util.ItemBuilder;
import com.mineplex.game.ability.Ability;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class MonkAbilityHandler extends Ability {

    public MonkAbilityHandler() {
        setName("Monk");
        setIconStack(ItemStack.of(Material.BLAZE_ROD));
        setItemStack(
                new ItemBuilder(Material.BLAZE_ROD)
                        .setUnbreakable(true)
                        .setName(Components.miniMessage("<italic:false><color:yellow>Monk Kit</color>"))
                        .build()
        );
    }

}