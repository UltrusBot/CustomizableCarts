package me.ultrusmods.customizablecarts.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.ultrusmods.customizablecarts.part.CartBody;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public record CustomizableCartData(Holder<CartBody> body, ItemStack bodyItem) {
    public static final Codec<CustomizableCartData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            CartBody.CODEC.fieldOf("body").forGetter(CustomizableCartData::body),
            ItemStack.CODEC.fieldOf("body_item").forGetter(CustomizableCartData::bodyItem)
    ).apply(instance, CustomizableCartData::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, CustomizableCartData> STREAM_CODEC = StreamCodec.composite(
            CartBody.DIRECT_STREAM_CODEC,
            CustomizableCartData::body,
            ItemStack.STREAM_CODEC,
            CustomizableCartData::bodyItem,
            CustomizableCartData::new
    );


}
