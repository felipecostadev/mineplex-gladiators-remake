package com.mineplex.game.view;

import com.mineplex.Gladiators;
import com.mineplex.core.util.Components;
import com.mineplex.core.util.ItemBuilder;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.TooltipDisplay;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import me.devnatan.inventoryframework.View;
import me.devnatan.inventoryframework.context.OpenContext;
import me.devnatan.inventoryframework.context.RenderContext;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

@SuppressWarnings("UnstableApiUsage")
public final class AbilityView extends View {

    @Override
    public void onOpen(@NotNull OpenContext open) {

        open.modifyConfig()
                .cancelInteractions()
                .title("Kits que você possui")
                .size(9);

    }

    @Override
    public void onFirstRender(@NonNull RenderContext render) {

        final var player = render.getPlayer();

        final var abilityService = Gladiators.getInstance().getAbilityService();

        for (final var ability : abilityService.getAbilities()) {

            if (ability == null)
                continue;

            final var abilityItem = new ItemBuilder(ability.getIconStack())
                    .setData(DataComponentTypes.TOOLTIP_DISPLAY, TooltipDisplay.tooltipDisplay().addHiddenComponents(DataComponentTypes.ATTRIBUTE_MODIFIERS).build())
                    .setName(Components.miniMessage("<italic:false><color:green>" + ability.getName() + "</color>"));

            final var summary = ability.getSummary();

            if (summary != null && !summary.isEmpty()) {

                final var lore = new ObjectArrayList<Component>();

                for (final var text : summary)
                    lore.add(Components.miniMessage("<italic:false><color:white>" + text + "</color>"));

                abilityItem.setLore(lore);

            }

            render.availableSlot(abilityItem.build())
                    .onClick(context -> {

                        context.closeForPlayer();

                        player.sendMessage(Components.miniMessage("<color:green>Você agora é um " + ability.getName() + "</color>"));

                    });

        }

    }

}