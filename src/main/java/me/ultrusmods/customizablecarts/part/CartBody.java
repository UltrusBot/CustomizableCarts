package me.ultrusmods.customizablecarts.part;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.customizablecarts.model.ModelSettings;
import me.ultrusmods.customizablecarts.registry.CustomizableCartsRegistryKeys;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.world.item.Item;

public record CartBody(ModelSettings model, Item item) {
    public static final Codec<CartBody> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ModelSettings.CODEC.fieldOf("model").forGetter(CartBody::model),
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(CartBody::item)
    ).apply(instance, CartBody::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<CartBody>> DIRECT_STREAM_CODEC = ByteBufCodecs.holderRegistry(CustomizableCartsRegistryKeys.CART_BODY_KEY);

    public static final Codec<Holder<CartBody>> CODEC = RegistryFileCodec.create(CustomizableCartsRegistryKeys.CART_BODY_KEY, DIRECT_CODEC);


}
