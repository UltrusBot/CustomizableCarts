package me.ultrusmods.customizablecarts.model;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.customizablecarts.registry.CustomizableCartModelTypes;
import net.minecraft.resources.ResourceLocation;

public record TwoLayerCartModelType(ResourceLocation texture) implements ModelSettings {
    public static final MapCodec<TwoLayerCartModelType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("texture").forGetter(TwoLayerCartModelType::texture)
    ).apply(instance, TwoLayerCartModelType::new));

    @Override
    public ModelSettingsType<?> getType() {
        return CustomizableCartModelTypes.TWO_LAYER;
    }

    @Override
    public ResourceLocation getTexture() {
        return texture;
    }
}
