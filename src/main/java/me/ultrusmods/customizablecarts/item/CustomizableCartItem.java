package me.ultrusmods.customizablecarts.item;

import me.ultrusmods.customizablecarts.entity.CustomizableMinecart;
import me.ultrusmods.customizablecarts.part.CartBody;
import me.ultrusmods.customizablecarts.registry.CustomizableCartsRegistryKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class CustomizableCartItem extends Item {

    private final ResourceKey<CartBody> CART_BODY_KEY;

    private static final DispenseItemBehavior DISPENSE_ITEM_BEHAVIOR = new DefaultDispenseItemBehavior() {
        private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

        @Override
        public ItemStack execute(BlockSource blockSource, ItemStack itemStack) {
            Direction direction = blockSource.state().getValue(DispenserBlock.FACING);
            ServerLevel serverLevel = blockSource.level();
            Vec3 vec3 = blockSource.center();
            double d = vec3.x() + (double)direction.getStepX() * 1.125;
            double e = Math.floor(vec3.y()) + (double)direction.getStepY();
            double f = vec3.z() + (double)direction.getStepZ() * 1.125;
            BlockPos blockPos = blockSource.pos().relative(direction);
            BlockState blockState = serverLevel.getBlockState(blockPos);
            RailShape railShape = blockState.getBlock() instanceof BaseRailBlock
                    ? blockState.getValue(((BaseRailBlock)blockState.getBlock()).getShapeProperty())
                    : RailShape.NORTH_SOUTH;
            double g;
            if (blockState.is(BlockTags.RAILS)) {
                if (railShape.isAscending()) {
                    g = 0.6;
                } else {
                    g = 0.1;
                }
            } else {
                if (!blockState.isAir() || !serverLevel.getBlockState(blockPos.below()).is(BlockTags.RAILS)) {
                    return this.defaultDispenseItemBehavior.dispense(blockSource, itemStack);
                }

                BlockState blockState2 = serverLevel.getBlockState(blockPos.below());
                RailShape railShape2 = blockState2.getBlock() instanceof BaseRailBlock
                        ? blockState2.getValue(((BaseRailBlock)blockState2.getBlock()).getShapeProperty())
                        : RailShape.NORTH_SOUTH;
                if (direction != Direction.DOWN && railShape2.isAscending()) {
                    g = -0.4;
                } else {
                    g = -0.9;
                }
            }

            if (itemStack.getItem() instanceof CustomizableCartItem customizableCartItem) {
                var customizableCart = new CustomizableMinecart(serverLevel, d, e + g, f);
                var cartBodyHolder = customizableCartItem.getCartBodyHolder(serverLevel);
                customizableCart.setBodyPartType(cartBodyHolder);
                serverLevel.addFreshEntity(customizableCart);
            }
            itemStack.shrink(1);
            return itemStack;
        }

        @Override
        protected void playSound(BlockSource blockSource) {
            blockSource.level().levelEvent(1000, blockSource.pos(), 0);
        }
    };

    public CustomizableCartItem(Properties properties, ResourceKey<CartBody> CART_BODY_KEY) {
        super(properties);
        this.CART_BODY_KEY = CART_BODY_KEY;
        DispenserBlock.registerBehavior(this, DISPENSE_ITEM_BEHAVIOR);
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
