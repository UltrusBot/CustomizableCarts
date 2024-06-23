package me.ultrusmods.customizablecarts.registry;

import me.ultrusmods.customizablecarts.CustomizableCarts;
import me.ultrusmods.customizablecarts.component.CustomizableCartData;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.UnaryOperator;

public class CustomizableCartDataComponents {
    public static final DataComponentType<CustomizableCartData> CUSTOMIZABLE_CART_DATA = register(
            "customizable_cart_data", builder -> builder.persistent(CustomizableCartData.CODEC).networkSynchronized(CustomizableCartData.STREAM_CODEC).cacheEncoding());

    public static void init() {

    }


    private static <T> DataComponentType<T> register(String path, UnaryOperator<DataComponentType.Builder<T>> unaryOperator) {
        return Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE, CustomizableCarts.id(path), ((DataComponentType.Builder)unaryOperator.apply(DataComponentType.builder())).build()
        );
    }
}
