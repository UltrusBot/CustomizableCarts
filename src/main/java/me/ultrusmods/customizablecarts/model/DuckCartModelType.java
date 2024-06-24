package me.ultrusmods.customizablecarts.model;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.customizablecarts.registry.CustomizableCartModelTypes;
import net.minecraft.resources.ResourceLocation;

public record DuckCartModelType(ResourceLocation texture) implements ModelSettings {
    public static final MapCodec<DuckCartModelType> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            ResourceLocation.CODEC.fieldOf("texture").forGetter(DuckCartModelType::texture)
    ).apply(instance, DuckCartModelType::new));

    @Override
    public ModelSettingsType<?> getType() {
        return CustomizableCartModelTypes.DUCK;
    }

    @Override
    public ResourceLocation getTexture() {
        return texture;
    }
}
