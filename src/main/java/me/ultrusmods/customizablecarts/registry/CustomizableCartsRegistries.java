package me.ultrusmods.customizablecarts.registry;

import me.ultrusmods.customizablecarts.model.ModelSettingsType;
import me.ultrusmods.customizablecarts.part.CartBody;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.core.MappedRegistry;

public class CustomizableCartsRegistries {

    public static final MappedRegistry<ModelSettingsType<?>> MODEL_SETTINGS_TYPE = FabricRegistryBuilder.createSimple(CustomizableCartsRegistryKeys.MODEL_SETTINGS_TYPE_KEY).buildAndRegister();


    public static void init() {
        DynamicRegistries.registerSynced(
                CustomizableCartsRegistryKeys.CART_BODY_KEY,
                CartBody.DIRECT_CODEC,
                DynamicRegistries.SyncOption.SKIP_WHEN_EMPTY
        );

    }
}
