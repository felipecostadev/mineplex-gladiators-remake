package com.mineplex.game.ability.handler;

import com.mineplex.core.util.Components;
import com.mineplex.core.util.ItemBuilder;
import com.mineplex.game.ability.Ability;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class NinjaAbilityHandler extends Ability {

    public NinjaAbilityHandler() {
        setName("Ninja");
        setIconStack(ItemStack.of(Material.ENDER_PEARL));
        setItemStack(
                new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                        .setName(Components.miniMessage("<italic:false><color:dark_gray>Slot de Kit</color>"))
                        .build()
        );
    }

}