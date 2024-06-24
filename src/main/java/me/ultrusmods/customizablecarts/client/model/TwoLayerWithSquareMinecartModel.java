package me.ultrusmods.customizablecarts.client.model;

import me.ultrusmods.customizablecarts.client.CustomizableCartsClient;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class TwoLayerWithSquareMinecartModel<T extends Entity> extends HierarchicalModel<T> implements CustomizableMinecartModel {
    private final ModelPart root;

    public TwoLayerWithSquareMinecartModel(ModelPart root) {
        this.root = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        partDefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(36, 44).addBox(-10.0F, -10.0F, -8.0F, 2.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(0, 44).addBox(8.0F, -10.0F, -8.0F, 2.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(56, 26).addBox(-8.0F, -10.0F, -8.0F, 16.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(56, 0).addBox(-8.0F, -10.0F, 6.0F, 16.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-10.0F, -10.0F, -8.0F, 20.0F, 10.0F, 16.0F, new CubeDeformation(0.5F))
                .texOffs(0, 26).addBox(-10.0F, -2.0F, -8.0F, 20.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(114, 0).addBox(-11.0F, -8.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0f, 0.0F));

        return LayerDefinition.create(meshDefinition, 128, 128);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public ModelLayerLocation getModelLayerLocation() {
        return CustomizableCartsClient.TWO_LAYER_CUSTOMIZABLE_CART_WITH_SQUARE_LAYER;
    }

}
