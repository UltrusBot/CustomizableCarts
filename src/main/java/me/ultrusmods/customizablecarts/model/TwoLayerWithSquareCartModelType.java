package me.ultrusmods.customizablecarts.model;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.customizablecarts.registry.CustomizableCartModelTypes;
import net.minecraft.resources.ResourceLocation;

public record TwoLayerWithSquareCartModelType(ResourceLocation texture) implements ModelSettings {
    public static final MapCodec<TwoLayerWithSquareCartModelType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("texture").forGetter(TwoLayerWithSquareCartModelType::texture)
    ).apply(instance, TwoLayerWithSquareCartModelType::new));

    @Override
    public ModelSettingsType<?> getType() {
        return CustomizableCartModelTypes.TWO_LAYER_WITH_SQUARE;
    }

    @Override
    public ResourceLocation getTexture() {
        return texture;
    }
}
