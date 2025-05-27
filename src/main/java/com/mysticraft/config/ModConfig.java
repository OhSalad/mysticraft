// src/main/java/com/mysticraft/config/ModConfig.java
package com.mysticraft.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "mysticraft") // This will be the name of your config file (mysticraft.json)
public class ModConfig implements ConfigData {

    @Comment("Enable or disable the custom HUD.")
    public boolean enableHud = true;

    @Comment("Sets the transparency of the HUD elements (0.0 to 1.0).")
    public float hudTransparency = 0.8f;

    @Comment("Should the mana bar be visible?")
    public boolean showManaBar = true;

    @Comment("X-coordinate for the HUD's top-left corner.")
    public int hudX = 200; // Default X position

    @Comment("Y-coordinate for the HUD's top-left corner. Relative to screen height.")
    public int hudY = -50; // Default Y position (will be scaledHeight + hudY)

    // A static getter for easy access to the config instance
    public static ModConfig get() {
        // AutoConfig.getConfigHolder(ModConfig.class) loads or creates the config file
        return AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    // This method needs to be called once during mod initialization
    public static void init() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
    }
}