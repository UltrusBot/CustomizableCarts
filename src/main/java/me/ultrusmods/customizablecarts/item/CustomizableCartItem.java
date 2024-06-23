package me.ultrusmods.customizablecarts.item;

import me.ultrusmods.customizablecarts.entity.CustomizableMinecart;
import me.ultrusmods.customizablecarts.part.CartBody;
import me.ultrusmods.customizablecarts.registry.CustomizableCartsRegistryKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.gameevent.GameEvent;

public class CustomizableCartItem extends Item {

    private final ResourceKey<CartBody> CART_BODY_KEY;
    public CustomizableCartItem(Properties properties, ResourceKey<CartBody> CART_BODY_KEY) {
        super(properties);
        this.CART_BODY_KEY = CART_BODY_KEY;
    }

    public Holder<CartBody> getCartBodyHolder(ServerLevel serverLevel) {
        Registry<CartBody> registry = serverLevel.registryAccess().registryOrThrow(CustomizableCartsRegistryKeys.CART_BODY_KEY);
        return registry.getHolder(this.CART_BODY_KEY).or(registry::getAny).orElseThrow();

    }


    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Level level = useOnContext.getLevel();
        BlockPos blockPos = useOnContext.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (!blockState.is(BlockTags.RAILS)) {
            return InteractionResult.FAIL;
        } else {
            ItemStack itemStack = useOnContext.getItemInHand();
            if (level instanceof ServerLevel) {
                ServerLevel serverLevel = (ServerLevel)level;
                RailShape railShape = blockState.getBlock() instanceof BaseRailBlock ? blockState.getValue(((BaseRailBlock)blockState.getBlock()).getShapeProperty()) : RailShape.NORTH_SOUTH;
                double d = 0.0;
                if (railShape.isAscending()) {
                    d = 0.5;
                }

//                AbstractMinecart abstractMinecart = AbstractMinecart.createMinecart(serverLevel, (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.0625 + d, (double)blockPos.getZ() + 0.5, this.type, itemStack, useOnContext.getPlayer());
                var customizableCart = new CustomizableMinecart(serverLevel, (double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.0625 + d, (double)blockPos.getZ() + 0.5);
                customizableCart.setBodyPartType(this.getCartBodyHolder(serverLevel));
                serverLevel.addFreshEntity(customizableCart);
                serverLevel.gameEvent(GameEvent.ENTITY_PLACE, blockPos, GameEvent.Context.of(useOnContext.getPlayer(), serverLevel.getBlockState(blockPos.below())));
            }

            itemStack.shrink(1);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }
}
