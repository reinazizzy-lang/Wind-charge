package com.windcharge.client.screen;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import com.windcharge.config.ModConfig;

public class WindChargeConfigScreen extends Screen {
    private final Screen parent;
    private TextFieldWidget minPitchField;
    private TextFieldWidget minAreaField;
    private TextFieldWidget maxAreaField;
    private ButtonWidget toggleButton;
    private ButtonWidget areaToggleButton;

    public WindChargeConfigScreen(Screen parent) {
        super(Text.literal("Wind Charge Configuration"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        // Title
        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Back"),
                button -> this.client.setScreen(parent)
        ).position(this.width / 2 - 100, this.height - 30).width(200).build());

        // Toggle button
        toggleButton = ButtonWidget.builder(
                Text.literal(ModConfig.ENABLED ? "§aEnabled" : "§cDisabled"),
                button -> {
                    ModConfig.ENABLED = !ModConfig.ENABLED;
                    updateToggleButton();
                    ModConfig.save();
                }
        ).position(10, 30).width(150).build();
        this.addDrawableChild(toggleButton);

        // Min Pitch field
        minPitchField = new TextFieldWidget(this.textRenderer, 10, 70, 150, 20, Text.literal("Min Pitch"));
        minPitchField.setText(String.valueOf(ModConfig.MIN_PITCH));
        minPitchField.setMaxLength(3);
        this.addSelectableChild(minPitchField);
        this.addDrawableChild(minPitchField);

        ButtonWidget minPitchButton = ButtonWidget.builder(
                Text.literal("Set"),
                button -> {
                    try {
                        ModConfig.MIN_PITCH = Float.parseFloat(minPitchField.getText());
                        ModConfig.save();
                    } catch (NumberFormatException ignored) {}
                }
        ).position(170, 70).width(50).build();
        this.addDrawableChild(minPitchButton);

        // Area check toggle
        areaToggleButton = ButtonWidget.builder(
                Text.literal(ModConfig.AREA_CHECK_ENABLED ? "Area Check: ON" : "Area Check: OFF"),
                button -> {
                    ModConfig.AREA_CHECK_ENABLED = !ModConfig.AREA_CHECK_ENABLED;
                    updateAreaToggleButton();
                    ModConfig.save();
                }
        ).position(10, 110).width(150).build();
        this.addDrawableChild(areaToggleButton);

        // Min Area field
        minAreaField = new TextFieldWidget(this.textRenderer, 10, 150, 150, 20, Text.literal("Min Area (°)"));
        minAreaField.setText(String.valueOf(ModConfig.MIN_AREA));
        minAreaField.setMaxLength(3);
        this.addSelectableChild(minAreaField);
        this.addDrawableChild(minAreaField);

        ButtonWidget minAreaButton = ButtonWidget.builder(
                Text.literal("Set"),
                button -> {
                    try {
                        ModConfig.MIN_AREA = Float.parseFloat(minAreaField.getText());
                        ModConfig.save();
                    } catch (NumberFormatException ignored) {}
                }
        ).position(170, 150).width(50).build();
        this.addDrawableChild(minAreaButton);

        // Max Area field
        maxAreaField = new TextFieldWidget(this.textRenderer, 10, 190, 150, 20, Text.literal("Max Area (°)"));
        maxAreaField.setText(String.valueOf(ModConfig.MAX_AREA));
        maxAreaField.setMaxLength(3);
        this.addSelectableChild(maxAreaField);
        this.addDrawableChild(maxAreaField);

        ButtonWidget maxAreaButton = ButtonWidget.builder(
                Text.literal("Set"),
                button -> {
                    try {
                        ModConfig.MAX_AREA = Float.parseFloat(maxAreaField.getText());
                        ModConfig.save();
                    } catch (NumberFormatException ignored) {}
                }
        ).position(170, 190).width(50).build();
        this.addDrawableChild(maxAreaButton);
    }

    private void updateToggleButton() {
        toggleButton.setMessage(Text.literal(ModConfig.ENABLED ? "§aEnabled" : "§cDisabled"));
    }

    private void updateAreaToggleButton() {
        areaToggleButton.setMessage(Text.literal(ModConfig.AREA_CHECK_ENABLED ? "Area Check: ON" : "Area Check: OFF"));
    }

    @Override
    public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 10, 0xFFFFFF);

        context.drawTextWithShadow(this.textRenderer, "Toggle: V", 10, 20, 0xAAAAAAAA);
        context.drawTextWithShadow(this.textRenderer, "Config: B", 170, 20, 0xAAAAAAAA);
        context.drawTextWithShadow(this.textRenderer, "Min Pitch (degrees):", 10, 60, 0xFFFFFF);
        context.drawTextWithShadow(this.textRenderer, "Area Settings:", 10, 100, 0xFFFFFF);
        context.drawTextWithShadow(this.textRenderer, "Min Direction (°):", 10, 140, 0xFFFFFF);
        context.drawTextWithShadow(this.textRenderer, "Max Direction (°):", 10, 180, 0xFFFFFF);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void tick() {
        if (minPitchField != null) minPitchField.tick();
        if (minAreaField != null) minAreaField.tick();
        if (maxAreaField != null) maxAreaField.tick();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
