package com.mysticraft;

import com.mysticraft.config.ModConfig; // Import your ModConfig class
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MystiCraft implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is traditional to use the mod id as the logger's name.
	// That way, the log can be filtered to only show output from your mod.
	public static final String MOD_ID = "mysticraft";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world from MystiCraft!");

		// Initialize your mod configuration here!
		// This is crucial for AutoConfig to register your config class.
		ModConfig.init();

		// If you have other initializations that should happen on both client/server,
		// you can put them here. For client-specific initializations (like HUD),
		// use the client entrypoint.
	}
}
