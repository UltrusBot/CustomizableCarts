package me.ultrusmods.customizablecarts.model;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import me.ultrusmods.customizablecarts.registry.CustomizableCartsRegistries;

public record ModelSettingsType<T extends ModelSettings>(MapCodec<T> codec) {
    public static final Codec<ModelSettingsType<?>> MODEL_SETTINGS_TYPE_CODEC = CustomizableCartsRegistries.MODEL_SETTINGS_TYPE.byNameCodec();
}
