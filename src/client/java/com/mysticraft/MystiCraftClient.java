package com.mysticraft;

import com.mysticraft.hud.HudManager; // Import your HudManager
import com.mysticraft.item.ModItems;
import net.fabricmc.api.ClientModInitializer;

public class MystiCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		MystiCraft.LOGGER.info("Initializing MystiCraft client-side!");

		// Initialize the HUD Manager here, as it's a client-side component.
		HudManager.init();
	}
}
