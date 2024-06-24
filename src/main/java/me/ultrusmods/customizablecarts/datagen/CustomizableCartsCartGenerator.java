package me.ultrusmods.customizablecarts.datagen;

import me.ultrusmods.customizablecarts.CustomizableCarts;
import me.ultrusmods.customizablecarts.model.DefaultCartModelType;
import me.ultrusmods.customizablecarts.model.DuckCartModelType;
import me.ultrusmods.customizablecarts.model.TwoLayerCartModelType;
import me.ultrusmods.customizablecarts.model.TwoLayerWithSquareCartModelType;
import me.ultrusmods.customizablecarts.part.CartBody;
import me.ultrusmods.customizablecarts.registry.CustomizableCartItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricCodecDataProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class CustomizableCartsCartGenerator extends FabricCodecDataProvider<CartBody> {
    protected CustomizableCartsCartGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, registriesFuture, PackOutput.Target.DATA_PACK, "customizablecarts/cart_body", CartBody.DIRECT_CODEC);
    }

    @Override
    protected void configure(BiConsumer<ResourceLocation, CartBody> provider, HolderLookup.Provider lookup) {
        addCartPart(provider, "regular_minecart", new CartBody(
                new DefaultCartModelType(
                        ResourceLocation.withDefaultNamespace("textures/entity/minecart.png")
                ),
                Items.MINECART
        ));
        addCartPart(provider, "gold_minecart", new CartBody(
                new DefaultCartModelType(
                        CustomizableCarts.id("textures/entity/minecart/gold_minecart.png")
                ),
                CustomizableCartItems.GOLD_MINECART
        ));
        addCartPart(provider, "carnival_cart", new CartBody(
                new TwoLayerCartModelType(
                        CustomizableCarts.id("textures/entity/minecart/carnival_cart.png")
                ),
                CustomizableCartItems.CARNIVAL_MINECART
        ));
        addCartPart(provider, "blue_carnival_cart", new CartBody(
                new TwoLayerCartModelType(
                        CustomizableCarts.id("textures/entity/minecart/blue_carnival_cart.png")
                ),
                CustomizableCartItems.BLUE_CARNIVAL_MINECART
        ));
        addCartPart(provider, "mint_carnival_cart", new CartBody(
                new TwoLayerCartModelType(
                        CustomizableCarts.id("textures/entity/minecart/mint_carnival_cart.png")
                ),
                CustomizableCartItems.MINT_CARNIVAL_MINECART
        ));
        addCartPart(provider, "orange_carnival_cart", new CartBody(
                new TwoLayerCartModelType(
                        CustomizableCarts.id("textures/entity/minecart/orange_carnival_cart.png")
                ),
                CustomizableCartItems.ORANGE_CARNIVAL_MINECART
        ));
        addCartPart(provider, "pink_carnival_cart", new CartBody(
                new TwoLayerCartModelType(
                        CustomizableCarts.id("textures/entity/minecart/pink_carnival_cart.png")
                ),
                CustomizableCartItems.PINK_CARNIVAL_MINECART
        ));
        addCartPart(provider, "yellow_carnival_cart", new CartBody(
                new TwoLayerCartModelType(
                        CustomizableCarts.id("textures/entity/minecart/yellow_carnival_cart.png")
                ),
                CustomizableCartItems.YELLOW_CARNIVAL_MINECART
        ));
        addCartPart(provider, "music_cart", new CartBody(
                new DefaultCartModelType(
                        CustomizableCarts.id("textures/entity/minecart/music_cart.png")

                ),
                CustomizableCartItems.MUSIC_MINECART
        ));

        addCartPart(provider, "trial_cart", new CartBody(
                new TwoLayerCartModelType(
                        CustomizableCarts.id("textures/entity/minecart/trial_cart.png")
                ),
                CustomizableCartItems.TRIAL_MINECART
        ));
        addCartPart(provider, "tuff_cart", new CartBody(
                new TwoLayerCartModelType(
                        CustomizableCarts.id("textures/entity/minecart/tuff_cart.png")
                ),
                CustomizableCartItems.TUFF_MINECART
        ));
        addCartPart(provider, "end_frame_cart", new CartBody(
                new TwoLayerWithSquareCartModelType(
                        CustomizableCarts.id("textures/entity/minecart/end_frame_cart.png")
                ),
                CustomizableCartItems.END_FRAME_MINECART
        ));
        addCartPart(provider, "duck_cart", new CartBody(
                new DuckCartModelType(
                        CustomizableCarts.id("textures/entity/minecart/duck_cart.png")
                ),
                CustomizableCartItems.DUCK_MINECART
        ));
    }

    public static void addCartPart(BiConsumer<ResourceLocation, CartBody> provider, String name, CartBody part) {
        provider.accept(CustomizableCarts.id(name), part);
    }

    @Override
    public String getName() {
        return "Cart Parts";
    }
}
