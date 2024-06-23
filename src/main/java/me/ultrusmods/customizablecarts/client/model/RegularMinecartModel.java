package me.ultrusmods.customizablecarts.client.model;

import me.ultrusmods.customizablecarts.client.CustomizableCartsClient;
import me.ultrusmods.customizablecarts.entity.CustomizableMinecart;
import net.minecraft.client.model.MinecartModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;

public class RegularMinecartModel extends MinecartModel<CustomizableMinecart> implements CustomizableMinecartModel {
    public RegularMinecartModel(ModelPart modelPart) {
        super(modelPart);
    }

    @Override
    public ModelLayerLocation getModelLayerLocation() {
        return CustomizableCartsClient.CUSTOMIZABLE_CART_LAYER;
    }
}
