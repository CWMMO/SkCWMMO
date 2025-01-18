package com.cwmmo.skCWMMO.elements;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.cwmmo.skCWMMO.events.PlayerChatReceiveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ChatReceiveEvent extends SkriptEvent {
    Literal<Player> player;
    Literal<String> message;

    static {
        Skript.registerEvent(
                "Chat Receive Event",
                ChatReceiveEvent.class,
                PlayerChatReceiveEvent.class, // event
                "[player] chat receive event")
                .description("Called when a player receives a chat message")
                .examples(
                        "on player chat receive event",
                        "on chat receive event")
                .since("2.6.4");
        EventValues.registerEventValue(PlayerChatReceiveEvent.class, Player.class, new Getter<Player, PlayerChatReceiveEvent>() {
            @Override
            @Nullable
            public Player get(PlayerChatReceiveEvent event) {
                return event.getPlayer();
            }
        }, 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Literal<?>[] args, int matchedPattern, @NotNull ParseResult parseResult) {
        player = (Literal<Player>) args[0];
        message = (Literal<String>) args[1];

        return true;
    }

    @Override
    public boolean check(@NotNull Event event) {
        if (player != null && message != null)
            return player.check(event, data -> true);
        return true;
    }

    @Override
    public @NotNull String toString(@Nullable Event event, boolean debug) {
        return "Chat Receive sent by %s containing message %s"
                .formatted(player, message);
    }
}
