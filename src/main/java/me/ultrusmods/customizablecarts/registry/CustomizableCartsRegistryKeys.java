package me.ultrusmods.customizablecarts.registry;

import me.ultrusmods.customizablecarts.CustomizableCarts;
import me.ultrusmods.customizablecarts.model.ModelSettingsType;
import me.ultrusmods.customizablecarts.part.CartBody;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class CustomizableCartsRegistryKeys {
    public static final ResourceKey<Registry<CartBody>> CART_BODY_KEY = ResourceKey.createRegistryKey(CustomizableCarts.id("cart_body"));
    public static final ResourceKey<Registry<ModelSettingsType<?>>> MODEL_SETTINGS_TYPE_KEY = ResourceKey.createRegistryKey(CustomizableCarts.id("cart_model_settings_type"));
}
