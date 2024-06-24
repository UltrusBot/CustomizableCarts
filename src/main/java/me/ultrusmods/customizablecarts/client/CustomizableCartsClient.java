package me.ultrusmods.customizablecarts.client;

import me.ultrusmods.customizablecarts.CustomizableCarts;
import me.ultrusmods.customizablecarts.client.model.CartBodyModels;
import me.ultrusmods.customizablecarts.client.model.RegularMinecartModel;
import me.ultrusmods.customizablecarts.client.model.TwoLayerMinecartModel;
import me.ultrusmods.customizablecarts.client.model.TwoLayerWithSquareMinecartModel;
import me.ultrusmods.customizablecarts.client.renderer.CustomizableMinecartRenderer;
import me.ultrusmods.customizablecarts.registry.CustomizableCartsEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class CustomizableCartsClient implements ClientModInitializer {
    public static final ModelLayerLocation CUSTOMIZABLE_CART_LAYER = new ModelLayerLocation(CustomizableCarts.id("customizable_cart"), "main");
    public static final ModelLayerLocation TWO_LAYER_CUSTOMIZABLE_CART_LAYER = new ModelLayerLocation(CustomizableCarts.id("two_layer_customizable_cart"), "main");
    public static final ModelLayerLocation TWO_LAYER_CUSTOMIZABLE_CART_WITH_SQUARE_LAYER = new ModelLayerLocation(CustomizableCarts.id("two_layer_customizable_cart_with_square"), "main");

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(
                CustomizableCartsEntities.CUSTOMIZABLE_MINECART,
                CustomizableMinecartRenderer::new
        );
        EntityModelLayerRegistry.registerModelLayer(
                CUSTOMIZABLE_CART_LAYER,
                RegularMinecartModel::createBodyLayer
        );

        EntityModelLayerRegistry.registerModelLayer(
                TWO_LAYER_CUSTOMIZABLE_CART_LAYER,
                TwoLayerMinecartModel::createBodyLayer
        );
        EntityModelLayerRegistry.registerModelLayer(
                TWO_LAYER_CUSTOMIZABLE_CART_WITH_SQUARE_LAYER,
                TwoLayerWithSquareMinecartModel::createBodyLayer
        );

        CartBodyModels.init();
    }
}
