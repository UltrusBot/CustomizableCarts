package me.ultrusmods.customizablecarts.model;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;

public interface ModelSettings {
    Codec<ModelSettings> CODEC = ModelSettingsType.MODEL_SETTINGS_TYPE_CODEC.dispatch(
            ModelSettings::getType,
            ModelSettingsType::codec
    );

    ModelSettingsType<?> getType();

    /**
     * Gets the main texture of the model that's passed to the renderer
     * @return the texture
     */
    ResourceLocation getTexture();
}
