package com.windcharge.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import com.windcharge.config.ModConfig;

@Environment(EnvType.CLIENT)
public class WindChargeClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Load config first
        ModConfig.init();

        // Register handlers
        ClientHandler.register();
        JumpHandler.register();
    }
}
