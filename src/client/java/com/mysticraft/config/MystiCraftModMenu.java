package com.mysticraft.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class MystiCraftModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory getModConfigScreenFactory() {
        // This tells Mod Menu to use our ModConfigScreen to create the config GUI
        // The ModConfigScreen will then use AutoConfig to build the actual UI
        return parent -> ModConfigScreen.create(parent);
    }
}