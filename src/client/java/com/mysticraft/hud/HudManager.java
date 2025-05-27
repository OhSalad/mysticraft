package com.mysticraft.hud;

import com.mysticraft.MystiCraft;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class HudManager {

    public static void init() {
        MystiCraft.LOGGER.info("Initializing HUD Manager!");

        // Register the HUD renderer
        HudRenderCallback.EVENT.register(new HudOverlayRenderer());
    }
}