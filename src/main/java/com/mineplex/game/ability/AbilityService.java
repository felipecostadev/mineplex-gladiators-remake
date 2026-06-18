package com.mineplex.game.ability;

import com.mineplex.Gladiators;
import com.mineplex.game.ability.handler.*;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import lombok.Getter;

public final class AbilityService {

    private final Object2ObjectMap<String, Ability> abilities = new Object2ObjectOpenHashMap<>();

    @Getter
    private final Ability defaultAbility;

    public AbilityService(Gladiators plugin) {

        this.defaultAbility = new NoneAbilityHandler();

        register(plugin, new BoxerAbilityHandler(), new FishermanAbilityHandler(), new MagmaAbilityHandler(), new MonkAbilityHandler(), new NinjaAbilityHandler(), defaultAbility, new SnailAbilityHandler(), new ViperAbilityHandler());

    }

    public ObjectCollection<Ability> getAbilities() {
        return abilities.values();
    }

    public ObjectArrayList<Ability> getCopyOfAbilities() {
        return new ObjectArrayList<>(abilities.values());
    }

    public Ability getAbility(String name) {
        return abilities.get(name);
    }

    public void register(Gladiators plugin, Ability... abilities) {

        final var pluginManager = plugin.getServer().getPluginManager();

        for (final var ability : abilities) {

            pluginManager.registerEvents(ability, plugin);

            this.abilities.put(ability.getName(), ability);

        }

    }

    public void unregister(Ability... abilities) {

        for (final var ability : abilities)
            this.abilities.remove(ability.getName());

    }

}