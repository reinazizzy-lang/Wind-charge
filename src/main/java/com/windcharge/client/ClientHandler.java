package com.windcharge.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import com.windcharge.WindChargeMod;
import com.windcharge.config.ModConfig;
import com.windcharge.client.screen.WindChargeConfigScreen;
import net.minecraft.client.MinecraftClient;

public class ClientHandler {
    public static KeyBinding toggleKeybind;
    public static KeyBinding configKeybind;
    private static boolean lastToggleState = false;

    public static void register() {
        toggleKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.windcharge.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                "category.windcharge.main"
        ));

        configKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.windcharge.config",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                "category.windcharge.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKeybind.wasPressed()) {
                ModConfig.ENABLED = !ModConfig.ENABLED;
                lastToggleState = ModConfig.ENABLED;
                if (client.player != null) {
                    String status = ModConfig.ENABLED ? "§aEnabled" : "§cDisabled";
                    client.player.sendMessage(
                            net.minecraft.text.Text.literal("Wind Charge Auto-Jump " + status),
                            true
                    );
                }
            }

            while (configKeybind.wasPressed()) {
                MinecraftClient mc = MinecraftClient.getInstance();
                mc.setScreen(new WindChargeConfigScreen(null));
            }
        });
    }
}
