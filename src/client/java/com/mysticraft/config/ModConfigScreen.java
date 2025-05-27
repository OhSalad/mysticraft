// src/client/java/com/eldertide/config/ModConfigScreen.java
package com.mysticraft.config;

import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screen.Screen;

public class ModConfigScreen {

    // This static method is what EldertideModMenu will call to get the screen
    public static Screen create(Screen parent) {
        // AutoConfig.getConfigScreen returns a Supplier<Screen> in newer versions.
        // You need to call .get() on the Supplier to get the actual Screen instance.
        return AutoConfig.getConfigScreen(ModConfig.class, parent).get(); // <--- ADD .get() HERE
    }
}