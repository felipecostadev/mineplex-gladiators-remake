package com.mineplex.game.ability.handler;

import com.mineplex.core.util.Components;
import com.mineplex.core.util.ItemBuilder;
import com.mineplex.game.ability.Ability;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class NoneAbilityHandler extends Ability {

    public NoneAbilityHandler() {
        setName("Nenhum");
        setIconStack(ItemStack.of(Material.ITEM_FRAME));
        setItemStack(
                new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                        .setName(Components.miniMessage("<italic:false><color:dark_gray>Slot de Kit</color>"))
                        .build()
        );
    }

}