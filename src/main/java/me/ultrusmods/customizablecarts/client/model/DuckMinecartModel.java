package me.ultrusmods.customizablecarts.client.model;


import me.ultrusmods.customizablecarts.client.CustomizableCartsClient;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class DuckMinecartModel<T extends Entity> extends HierarchicalModel<T> implements CustomizableMinecartModel {
	private final ModelPart root;

	public DuckMinecartModel(ModelPart root) {
		this.root = root;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();

		partDefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(23.0F, -1.0F, 0.0F, 6.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(20, 26).addBox(3.0F, 4.0F, -8.0F, 2.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 18).addBox(21.0F, 4.0F, -8.0F, 2.0F, 8.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(36, 50).addBox(5.0F, 4.0F, -8.0F, 16.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 50).addBox(5.0F, 4.0F, 6.0F, 16.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(3.0F, 12.0F, -8.0F, 20.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(40, 18).addBox(1.0F, -6.0F, -4.0F, 5.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(20, 18).addBox(-4.0F, -2.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, -9.0f, 0.0F));

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
		return CustomizableCartsClient.TWO_LAYER_CUSTOMIZABLE_CART_LAYER;
	}
}