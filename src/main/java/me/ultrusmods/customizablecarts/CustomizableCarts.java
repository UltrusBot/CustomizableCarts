package me.ultrusmods.customizablecarts;

import me.ultrusmods.customizablecarts.entity.CustomizableCartsEntityDataSerializers;
import me.ultrusmods.customizablecarts.registry.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomizableCarts implements ModInitializer {
    public static final String MOD_ID = "customizablecarts";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        CustomizableCartsRegistries.init();
        CustomizableCartModelTypes.init();
        CustomizableCartDataComponents.init();
        CustomizableCartsEntityDataSerializers.init();
        CustomizableCartsEntities.init();
        CustomizableCartItems.init();

    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.tryBuild(MOD_ID, path);
    }
}
