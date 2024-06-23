package me.ultrusmods.customizablecarts.entity;

import me.ultrusmods.customizablecarts.part.CartBody;
import net.minecraft.core.Holder;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;

public class CustomizableCartsEntityDataSerializers {
    public static final EntityDataSerializer<Holder<CartBody>> PART = EntityDataSerializer.forValueType(CartBody.DIRECT_STREAM_CODEC);

    public static void init() {
        EntityDataSerializers.registerSerializer(PART);

    }
}
