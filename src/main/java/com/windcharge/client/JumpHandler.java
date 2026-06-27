package com.windcharge.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import com.windcharge.config.ModConfig;

public class JumpHandler {
    private static long lastJumpTime = 0;
    private static final long JUMP_COOLDOWN = 100; // milliseconds

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!ModConfig.ENABLED || client.player == null) {
                return;
            }

            handleAutoJump(client.player);
        });
    }

    private static void handleAutoJump(PlayerEntity player) {
        // Check if player is holding Wind Charge
        if (!isHoldingWindCharge(player)) {
            return;
        }

        // Check if player is looking down enough
        float pitch = player.getPitch();
        if (pitch < ModConfig.MIN_PITCH) {
            return;
        }

        // Check cooldown to prevent spam
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastJumpTime < JUMP_COOLDOWN) {
            return;
        }

        // Check if looking in area range
        float horizontalRotation = player.getYaw() % 360;
        float minArea = ModConfig.MIN_AREA;
        float maxArea = ModConfig.MAX_AREA;

        boolean inArea = false;
        if (minArea < maxArea) {
            inArea = horizontalRotation >= minArea && horizontalRotation <= maxArea;
        } else {
            inArea = horizontalRotation >= minArea || horizontalRotation <= maxArea;
        }

        if (!inArea && ModConfig.AREA_CHECK_ENABLED) {
            return;
        }

        // Perform jump
        if (player.isOnGround()) {
            player.jump();
            lastJumpTime = currentTime;
        }
    }

    private static boolean isHoldingWindCharge(PlayerEntity player) {
        // Check main hand
        if (player.getMainHandStack().getItem() == Items.WIND_CHARGE) {
            return true;
        }
        // Check off hand
        if (player.getOffHandStack().getItem() == Items.WIND_CHARGE) {
            return true;
        }
        return false;
    }
}
