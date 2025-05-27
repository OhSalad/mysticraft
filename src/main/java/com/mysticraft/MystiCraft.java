package com.mysticraft;

import com.mysticraft.config.ModConfig;
import com.mysticraft.item.ModItems; // Ensure this import is correct
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MystiCraft implements ModInitializer {
	public static final String MOD_ID = "mysticraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world from MystiCraft!");

		// Initialize your mod configuration here!
		ModConfig.init();
		ModItems.initialize();
		// Call the new item registration method
		// If you have other initializations that should happen on both client/server,
		// you can put them here. For client-specific initializations (like HUD),
		// use the client entrypoint.
	}
}