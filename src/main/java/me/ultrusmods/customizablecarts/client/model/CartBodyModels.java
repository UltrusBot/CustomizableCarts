package me.ultrusmods.customizablecarts.client.model;

import me.ultrusmods.customizablecarts.client.CustomizableCartsClient;
import me.ultrusmods.customizablecarts.entity.CustomizableMinecart;
import me.ultrusmods.customizablecarts.model.ModelSettingsType;
import me.ultrusmods.customizablecarts.registry.CustomizableCartModelTypes;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CartBodyModels {
    public static final Map<ModelSettingsType<?>, Function<ModelPart, EntityModel<CustomizableMinecart>>> CART_BODY_MODELS = new HashMap<>();
    public static final Map<ModelSettingsType<?>, ModelLayerLocation> CART_BODY_LAYERS = new HashMap<>();

    public static void init() {
        CART_BODY_MODELS.put(CustomizableCartModelTypes.DEFAULT, RegularMinecartModel::new);
        CART_BODY_MODELS.put(CustomizableCartModelTypes.TWO_LAYER, TwoLayerMinecartModel::new);

        CART_BODY_LAYERS.put(CustomizableCartModelTypes.DEFAULT, CustomizableCartsClient.CUSTOMIZABLE_CART_LAYER);
        CART_BODY_LAYERS.put(CustomizableCartModelTypes.TWO_LAYER, CustomizableCartsClient.TWO_LAYER_CUSTOMIZABLE_CART_LAYER);
    }
}
