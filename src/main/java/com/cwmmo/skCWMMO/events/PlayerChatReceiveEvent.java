package com.cwmmo.skCWMMO.events;

import com.cwmmo.skCWMMO.SkCWMMO;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerChatReceiveEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private static final SkCWMMO plugin = SkCWMMO.INSTANCE;
    private final Player player;
    private final String message;
    private boolean isCancelled;

    public PlayerChatReceiveEvent(Player player, String message) {
        this.player = player;
        this.message = message;
        this.isCancelled = false;
    }

    // event caller
    public void PlayerChatReceive(Player player, String message) {
        PlayerChatReceiveEvent event = new PlayerChatReceiveEvent(player, message);

        plugin.getServer().getPluginManager().callEvent(event);
    }

    // getters
    public String getMessage() {
        return this.message;
    }
    public Player getPlayer() {
        return this.player;
    }

    // built in event methods
    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static @NotNull HandlerList getHandlerList() {
        return handlers;
    }
}
