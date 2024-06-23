package me.ultrusmods.customizablecarts.registry;

import me.ultrusmods.customizablecarts.CustomizableCarts;
import me.ultrusmods.customizablecarts.model.DefaultCartModelType;
import me.ultrusmods.customizablecarts.model.ModelSettings;
import me.ultrusmods.customizablecarts.model.ModelSettingsType;
import me.ultrusmods.customizablecarts.model.TwoLayerCartModelType;
import net.minecraft.core.Registry;

public class CustomizableCartModelTypes {
    public static final ModelSettingsType<DefaultCartModelType> DEFAULT = register("default", new ModelSettingsType<>(DefaultCartModelType.CODEC));
    public static final ModelSettingsType<TwoLayerCartModelType> TWO_LAYER = register("two_layer", new ModelSettingsType<>(TwoLayerCartModelType.CODEC));


    public static <T extends ModelSettings>ModelSettingsType<T> register(String id, ModelSettingsType<T> type) {
        return Registry.register(CustomizableCartsRegistries.MODEL_SETTINGS_TYPE, CustomizableCarts.id(id), type);
    }

    public static void init() {

    }
}
