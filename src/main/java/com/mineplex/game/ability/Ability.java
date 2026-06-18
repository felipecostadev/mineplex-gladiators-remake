package com.mineplex.game.ability;

import com.mineplex.Gladiators;
import com.mineplex.game.GameManager;
import it.unimi.dsi.fastutil.objects.ObjectList;
import lombok.Data;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

@Data
public abstract class Ability implements Listener {

    private String name;

    private ObjectList<String> summary;

    private ItemStack iconStack;
    private ItemStack itemStack;

    protected GameManager getGameManager(Gladiators plugin) {
        return plugin.getGameManager();
    }

    protected GameManager getGameManager() {
        return getGameManager(Gladiators.getInstance());
    }

}