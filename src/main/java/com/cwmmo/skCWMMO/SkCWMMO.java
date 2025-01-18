package com.cwmmo.skCWMMO;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.cwmmo.skCWMMO.events.listeners.chatPacketListener;
import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

import java.io.IOException;

public final class SkCWMMO extends JavaPlugin {
    public static SkCWMMO INSTANCE;
    public static SkriptAddon ADDON;

    @Override
    public void onEnable() {
        INSTANCE = this;
        ADDON = Skript.registerAddon(this);

        // packet listener
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener(new chatPacketListener(this));
        getLogger().info("Chat packet listener registered!");

        // Skript extension
        try {
            ADDON.loadClasses("com.cwmmo.skCWMMO", "elements");
            System.out.println("[SKCWMMO] Registered events");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[SkCWMMO] Could not register addons");
        }


    }
}
