package me.ultrusmods.customizablecarts.registry;

import me.ultrusmods.customizablecarts.CustomizableCarts;
import me.ultrusmods.customizablecarts.item.CustomizableCartItem;
import me.ultrusmods.customizablecarts.part.CartBody;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class CustomizableCartItems {
    public static final Item GOLD_MINECART = registerCartBody("gold_minecart", CartBodies.GOLD_MINECART);
    public static final Item CARNIVAL_MINECART = registerCartBody("carnival_minecart", CartBodies.CARNIVAL_CART);
    public static final Item BLUE_CARNIVAL_MINECART = registerCartBody("blue_carnival_minecart", CartBodies.BLUE_CARNIVAL_CART);
    public static final Item MINT_CARNIVAL_MINECART = registerCartBody("mint_carnival_minecart", CartBodies.MINT_CARNIVAL_CART);
    public static final Item ORANGE_CARNIVAL_MINECART = registerCartBody("orange_carnival_minecart", CartBodies.ORANGE_CARNIVAL_CART);
    public static final Item PINK_CARNIVAL_MINECART = registerCartBody("pink_carnival_minecart", CartBodies.PINK_CARNIVAL_CART);
    public static final Item YELLOW_CARNIVAL_MINECART = registerCartBody("yellow_carnival_minecart", CartBodies.YELLOW_CARNIVAL_CART);
    public static final Item TRIAL_MINECART = registerCartBody("trial_minecart", CartBodies.TRIAL_CART);
    public static final Item MUSIC_MINECART = registerCartBody("music_minecart", CartBodies.MUSIC_CART);
    public static final Item TUFF_MINECART = registerCartBody("tuff_minecart", CartBodies.TUFF_CART);
    public static final Item END_FRAME_MINECART = registerCartBody("end_frame_minecart", CartBodies.END_FRAME_CART);
    public static final Item DUCK_MINECART = registerCartBody("duck_minecart", CartBodies.DUCK_CART);

    public static final CreativeModeTab TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, CustomizableCarts.id("items"), FabricItemGroup.builder()
            .icon(Items.MINECART::getDefaultInstance)
            .title(Component.translatable("itemGroup.customizablecarts"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(CustomizableCartItems.GOLD_MINECART);
                        output.accept(CustomizableCartItems.CARNIVAL_MINECART);
                        output.accept(CustomizableCartItems.BLUE_CARNIVAL_MINECART);
                        output.accept(CustomizableCartItems.MINT_CARNIVAL_MINECART);
                        output.accept(CustomizableCartItems.ORANGE_CARNIVAL_MINECART);
                        output.accept(CustomizableCartItems.PINK_CARNIVAL_MINECART);
                        output.accept(CustomizableCartItems.YELLOW_CARNIVAL_MINECART);
                        output.accept(CustomizableCartItems.TRIAL_MINECART);
                        output.accept(CustomizableCartItems.MUSIC_MINECART);
                        output.accept(CustomizableCartItems.TUFF_MINECART);
                        output.accept(CustomizableCartItems.END_FRAME_MINECART);
                        output.accept(CustomizableCartItems.DUCK_MINECART);
                    })
            .build());
    public static void init() {

    }

    public static Item registerCartBody(String name, ResourceKey<CartBody> resourceKey) {
        return register(name, new CustomizableCartItem(new Item.Properties().stacksTo(1), resourceKey));
    }

    public static Item register(String path, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, CustomizableCarts.id(path), item);
    }
}
