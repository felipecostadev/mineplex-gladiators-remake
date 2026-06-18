package com.mineplex;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.PacketEventsAPI;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.mineplex.core.cooldown.CooldownService;
import com.mineplex.core.util.PacketHolder;
import com.mineplex.game.GameManager;
import com.mineplex.game.ability.AbilityService;
import com.mineplex.game.command.AbilityCommand;
import com.mineplex.game.listener.BlockListener;
import com.mineplex.game.listener.SoupListener;
import com.mineplex.game.listener.WorldListener;
import com.mineplex.game.view.AbilityView;
import com.mineplex.player.PlayerManager;
import com.mineplex.player.listener.NatureListener;
import com.mineplex.player.listener.SessionListener;
import com.mineplex.player.packet.PacketPlayerRecipeBookCloseListener;
import com.mineplex.player.user.UserService;
import dev.despical.commandframework.CommandFramework;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import lombok.Getter;
import me.devnatan.inventoryframework.ViewFrame;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class Gladiators extends JavaPlugin {

    @Getter
    private static Gladiators instance;

    private GameManager gameManager;

    private PlayerManager playerManager;

    private UserService userService;

    private CooldownService cooldownService;

    private AbilityService abilityService;

    private CommandFramework commandFramework;

    private ViewFrame viewFramework;

    @Override
    public void onLoad() {

        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().load();

    }

    @Override
    public void onEnable() {

        final var packetEventsAPI = PacketEvents.getAPI();
        packetEventsAPI.init();

        instance = this;

        playerManager = new PlayerManager();

        userService = new UserService(this, gameManager = new GameManager(this));

        cooldownService = new CooldownService(this);

        abilityService = new AbilityService(this);

        commandFramework = new CommandFramework(this);

        registerCommands(new AbilityCommand());

        viewFramework = ViewFrame.create(this).with(new AbilityView()).register();

        registerEvents(new SessionListener(), new NatureListener(), new BlockListener(), new SoupListener(), new WorldListener());

        registerPackets(packetEventsAPI, new PacketHolder(new PacketPlayerRecipeBookCloseListener(), PacketListenerPriority.NORMAL));

    }

    public void registerCommands(Object... commands) {

        for (final var command : commands)
            commandFramework.registerCommands(command);

    }

    public void registerEvents(Listener... listeners) {

        final var pluginManager = getServer().getPluginManager();

        for (final var listener : listeners)
            pluginManager.registerEvents(listener, this);

    }

    private void registerPackets(PacketEventsAPI<?> packetEventsAPI, PacketHolder... packets) {

        final var eventManager = packetEventsAPI.getEventManager();

        for (final var packet : packets)
            eventManager.registerListener(packet.listener(), packet.priority());

    }

    public void registerPackets(PacketHolder... packets) {
        registerPackets(PacketEvents.getAPI(), packets);
    }

    public void sync(Runnable runnable) {
        getServer().getScheduler().runTask(this, runnable);
    }

    public void syncTimer(Runnable runnable, long delay, long period) {
        getServer().getScheduler().runTaskTimer(this, runnable, delay, period);
    }

}