package me.ultrusmods.customizablecarts.registry;

import me.ultrusmods.customizablecarts.CustomizableCarts;
import me.ultrusmods.customizablecarts.part.CartBody;
import net.minecraft.resources.ResourceKey;

public class CartBodies {
    public static final ResourceKey<CartBody> REGULAR_MINECART = createKey("regular_minecart");
    public static final ResourceKey<CartBody> GOLD_MINECART = createKey("gold_minecart");
    public static final ResourceKey<CartBody> MUSIC_CART = createKey("music_cart");
    public static final ResourceKey<CartBody> CARNIVAL_CART = createKey("carnival_cart");
    public static final ResourceKey<CartBody> BLUE_CARNIVAL_CART = createKey("blue_carnival_cart");
    public static final ResourceKey<CartBody> MINT_CARNIVAL_CART = createKey("mint_carnival_cart");
    public static final ResourceKey<CartBody> ORANGE_CARNIVAL_CART = createKey("orange_carnival_cart");
    public static final ResourceKey<CartBody> PINK_CARNIVAL_CART = createKey("pink_carnival_cart");
    public static final ResourceKey<CartBody> YELLOW_CARNIVAL_CART = createKey("yellow_carnival_cart");
    public static final ResourceKey<CartBody> TRIAL_CART = createKey("trial_cart");
    public static final ResourceKey<CartBody> TUFF_CART = createKey("tuff_cart");

    private static ResourceKey<CartBody> createKey(String id) {
        return ResourceKey.create(CustomizableCartsRegistryKeys.CART_BODY_KEY, CustomizableCarts.id(id));
    }
}
