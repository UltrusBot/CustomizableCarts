package me.ultrusmods.customizablecarts.model;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.customizablecarts.registry.CustomizableCartModelTypes;
import net.minecraft.resources.ResourceLocation;

public record DefaultCartModelType(ResourceLocation texture) implements ModelSettings {
    public static final MapCodec<DefaultCartModelType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("texture").forGetter(DefaultCartModelType::texture)
    ).apply(instance, DefaultCartModelType::new));

    @Override
    public ModelSettingsType<?> getType() {
        return CustomizableCartModelTypes.DEFAULT;
    }

    @Override
    public ResourceLocation getTexture() {
        return texture;
    }
}
