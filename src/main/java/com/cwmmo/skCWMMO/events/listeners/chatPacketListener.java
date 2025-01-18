package com.cwmmo.skCWMMO.events.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.cwmmo.skCWMMO.events.PlayerChatReceiveEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class chatPacketListener extends PacketAdapter {
    public chatPacketListener(Plugin plugin) {
        super(plugin, PacketType.Play.Server.CHAT);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        Player player = event.getPlayer();
        String message = event.getPacket().getStrings().read(0);

        new PlayerChatReceiveEvent(player, message).PlayerChatReceive(player, message);
        // listen for chat receive packets and fire the event
    }
}
