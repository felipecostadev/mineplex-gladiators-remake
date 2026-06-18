package com.mineplex.game.ability.handler;

import com.mineplex.core.util.Components;
import com.mineplex.core.util.ItemBuilder;
import com.mineplex.game.ability.Ability;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class FishermanAbilityHandler extends Ability {

    public FishermanAbilityHandler() {
        setName("Fisherman");
        setIconStack(ItemStack.of(Material.FISHING_ROD));
        setItemStack(
                new ItemBuilder(Material.FISHING_ROD)
                        .setUnbreakable(true)
                        .setName(Components.miniMessage("<italic:false><color:yellow>Fisherman Kit</color>"))
                        .build()
        );
    }

}