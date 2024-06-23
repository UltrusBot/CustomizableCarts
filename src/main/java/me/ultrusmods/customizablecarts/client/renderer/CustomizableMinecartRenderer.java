package me.ultrusmods.customizablecarts.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import me.ultrusmods.customizablecarts.client.model.CartBodyModels;
import me.ultrusmods.customizablecarts.entity.CustomizableMinecart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class CustomizableMinecartRenderer extends EntityRenderer<CustomizableMinecart> {
    private final BlockRenderDispatcher blockRenderer;
    private final EntityRendererProvider.Context context;

    public CustomizableMinecartRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.blockRenderer = context.getBlockRenderDispatcher();
        this.context = context;
    }

    @Override
    public ResourceLocation getTextureLocation(CustomizableMinecart abstractMinecart) {
        return abstractMinecart.getBodyPartType().model().getTexture();
    }

    @Override
    public void render(CustomizableMinecart customizableMinecart, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        super.render(customizableMinecart, f, g, poseStack, multiBufferSource, i);
        poseStack.pushPose();
        long l = (long)customizableMinecart.getId() * 493286711L;
        l = l * l * 4392167121L + l * 98761L;
        float h = (((float)(l >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float j = (((float)(l >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        float k = (((float)(l >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
        poseStack.translate(h, j, k);
        double d = Mth.lerp(g, customizableMinecart.xOld, customizableMinecart.getX());
        double e = Mth.lerp(g, customizableMinecart.yOld, customizableMinecart.getY());
        double m = Mth.lerp(g, customizableMinecart.zOld, customizableMinecart.getZ());
        Vec3 vec3 = customizableMinecart.getPos(d, e, m);
        float o = Mth.lerp(g, customizableMinecart.xRotO, customizableMinecart.getXRot());
        if (vec3 != null) {
            Vec3 vec32 = customizableMinecart.getPosOffs(d, e, m, 0.3F);
            Vec3 vec33 = customizableMinecart.getPosOffs(d, e, m, -0.3F);

            if (vec32 == null) vec32 = vec3;
            if (vec33 == null) vec33 = vec3;

            poseStack.translate(vec3.x - d, (vec32.y + vec33.y) / 2.0 - e, vec3.z - m);
            Vec3 vec34 = vec33.add(-vec32.x, -vec32.y, -vec32.z);
            if (vec34.length() != 0.0) {
                vec34 = vec34.normalize();
                f = (float)(Math.atan2(vec34.z, vec34.x) * 180.0 / Math.PI);
                o = (float)(Math.atan(vec34.y) * 73.0);
            }
        }

        poseStack.translate(0.0F, 0.375F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(-o));
        float p = (float)customizableMinecart.getHurtTime() - g;
        float q = customizableMinecart.getDamage() - g;
        if (q < 0.0F) {
            q = 0.0F;
        }

        if (p > 0.0F) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(p) * p * q / 10.0F * (float)customizableMinecart.getHurtDir()));
        }

        int r = customizableMinecart.getDisplayOffset();
        BlockState blockState = customizableMinecart.getDisplayBlockState();
        if (blockState.getRenderShape() != RenderShape.INVISIBLE) {
            poseStack.pushPose();
            poseStack.scale(0.75F, 0.75F, 0.75F);
            poseStack.translate(-0.5F, (float)(r - 8) / 16.0F, 0.5F);
            poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
            this.renderMinecartContents(blockState, poseStack, multiBufferSource, i);
            poseStack.popPose();
        }

        poseStack.scale(-1.0F, -1.0F, 1.0F);
        var model = CartBodyModels.CART_BODY_MODELS.get(customizableMinecart.getBodyPartType().model().getType()).apply(context.bakeLayer(CartBodyModels.CART_BODY_LAYERS.get(customizableMinecart.getBodyPartType().model().getType())));
        model.setupAnim(customizableMinecart, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(model.renderType(this.getTextureLocation(customizableMinecart)));
        model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }

    protected void renderMinecartContents(BlockState blockState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        this.blockRenderer.renderSingleBlock(blockState, poseStack, multiBufferSource, i, OverlayTexture.NO_OVERLAY);
    }
}
