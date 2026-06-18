package com.mineplex.game.command;

import com.mineplex.Gladiators;
import com.mineplex.game.view.AbilityView;
import dev.despical.commandframework.CommandArguments;
import dev.despical.commandframework.annotations.Command;
import dev.despical.commandframework.annotations.Command.SenderType;
import org.bukkit.entity.Player;

public final class AbilityCommand {

    @Command(name = "kit", senderType = SenderType.PLAYER)
    public void execute(CommandArguments commandArguments) {

        final var player = (Player) commandArguments.getSender();

        Gladiators.getInstance().getViewFramework().open(AbilityView.class, player);

    }

}