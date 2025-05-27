package com.mysticraft.hud;

import com.mysticraft.config.ModConfig; // Import your ModConfig class

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;

public class HudOverlayRenderer implements HudRenderCallback {

    // Constant values for mana
    private static final float MAX_MANA = 100.0f;
    private static final float CURRENT_MANA = 100.0f; // Mana is now constant

    // Bar dimensions
    private static final int BAR_WIDTH = 120;
    private static final int BAR_HEIGHT = 10;
    private static final int BAR_SPACING = 5;

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        // Check if the HUD is enabled via config or if client/player is null
        if (!ModConfig.get().enableHud || client == null || client.player == null) {
            return; // Don't render if HUD is disabled or client/player is null
        }

        PlayerEntity player = client.player;
        TextRenderer textRenderer = client.textRenderer;

        // Get scaled window dimensions
        int scaledWidth = drawContext.getScaledWindowWidth();
        int scaledHeight = drawContext.getScaledWindowHeight();

        // Determine base HUD position from config, adjusting Y relative to screen height
        int hudX = ModConfig.get().hudX;
        // The Y coordinate is relative to the bottom of the screen.
        // A positive hudY in config moves it down, a negative moves it up from the bottom.
        int hudY = scaledHeight + ModConfig.get().hudY;

        // Get transparency from config and convert to alpha for ARGB color
        int alpha = (int) (ModConfig.get().hudTransparency * 255.0f) << 24;

        // --- Render Health Bar ---
        renderHealthBar(drawContext, textRenderer, player, hudX, hudY, alpha);

        // --- Render Mana Bar (only if enabled in config) ---
        if (ModConfig.get().showManaBar) {
            int manaY = hudY + BAR_HEIGHT + BAR_SPACING;
            renderManaBar(drawContext, textRenderer, manaY, hudX, alpha);
        }

        // --- Optional: Player Name Display (e.g., top-left, not affected by hudX/hudY) ---
        renderPlayerName(drawContext, textRenderer, player);
    }
    /**
     * Renders a generic bar with a background, foreground, and centered text.
     *
     * @param drawContext The DrawContext for rendering.
     * @param textRenderer The TextRenderer for drawing text.
     * @param x The X coordinate of the bar's top-left corner.
     * @param y The Y coordinate of the bar's top-left corner.
     * @param current The current value for the bar.
     * @param max The maximum value for the bar.
     * @param text The text to display on the bar.
     * @param backgroundColor The ARGB color for the bar's background.
     * @param foregroundColor The ARGB color for the bar's foreground.
     * @param alpha The alpha component (transparency) for the colors.
     */
    private void renderBar(DrawContext drawContext, TextRenderer textRenderer, int x, int y,
                           float current, float max, String text, int backgroundColor, int foregroundColor, int alpha) {
        float percentage = current / max;

        // Background for the bar
        drawContext.fill(x, y, x + BAR_WIDTH, y + BAR_HEIGHT, alpha | backgroundColor);
        // Foreground bar
        drawContext.fill(x, y, x + (int)(BAR_WIDTH * percentage), y + BAR_HEIGHT, alpha | foregroundColor);

        // Text on the bar (White, shadow included)
        drawContext.drawTextWithShadow(textRenderer, text, x + BAR_WIDTH / 2 - textRenderer.getWidth(text) / 2, y + 1, 0xFFFFFFFF);
    }

    /**
     * Renders the player's health bar.
     *
     * @param drawContext The DrawContext for rendering.
     * @param textRenderer The TextRenderer for drawing text.
     * @param player The PlayerEntity whose health is being displayed.
     * @param hudX The base X coordinate for the HUD.
     * @param hudY The base Y coordinate for the HUD.
     * @param alpha The alpha component (transparency) for the colors.
     */
    private void renderHealthBar(DrawContext drawContext, TextRenderer textRenderer, PlayerEntity player,
                                 int hudX, int hudY, int alpha) {
        float playerHealth = player.getHealth();
        float playerMaxHealth = player.getMaxHealth();
        String healthText = String.format("HP: %.0f / %.0f", playerHealth, playerMaxHealth);

        renderBar(drawContext, textRenderer, hudX, hudY, playerHealth, playerMaxHealth,
                healthText, 0x440000, 0xFF0000, alpha); // Dark Red background, Bright Red foreground
    }

    /**
     * Renders the mana bar.
     *
     * @param drawContext The DrawContext for rendering.
     * @param textRenderer The TextRenderer for drawing text.
     * @param manaY The Y coordinate for the mana bar.
     * @param hudX The base X coordinate for the HUD.
     * @param alpha The alpha component (transparency) for the colors.
     */
    private void renderManaBar(DrawContext drawContext, TextRenderer textRenderer, int manaY, int hudX, int alpha) {
        String manaText = String.format("Mana: %.0f / %.0f", CURRENT_MANA, MAX_MANA);

        renderBar(drawContext, textRenderer, hudX, manaY, CURRENT_MANA, MAX_MANA,
                manaText, 0x000044, 0x0000FF, alpha); // Dark Blue background, Bright Blue foreground
    }

    /**
     * Renders the player's name at a fixed top-left position.
     *
     * @param drawContext The DrawContext for rendering.
     * @param textRenderer The TextRenderer for drawing text.
     * @param player The PlayerEntity whose name is being displayed.
     */
    private void renderPlayerName(DrawContext drawContext, TextRenderer textRenderer, PlayerEntity player) {
        int nameX = 10;
        int nameY = 10;
        String playerName = player.getName().getString();
        // Player name text (Gold, shadow included, full opacity)
        drawContext.drawTextWithShadow(textRenderer, playerName, nameX, nameY, 0xFFFFCC00);
    }
}