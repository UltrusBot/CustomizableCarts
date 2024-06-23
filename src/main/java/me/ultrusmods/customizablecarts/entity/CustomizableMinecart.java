package me.ultrusmods.customizablecarts.entity;

import me.ultrusmods.customizablecarts.CustomizableCarts;
import me.ultrusmods.customizablecarts.part.CartBody;
import me.ultrusmods.customizablecarts.registry.CartBodies;
import me.ultrusmods.customizablecarts.registry.CustomizableCartsEntities;
import me.ultrusmods.customizablecarts.registry.CustomizableCartsRegistryKeys;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CustomizableMinecart extends AbstractMinecart {

    private static final EntityDataAccessor<Holder<CartBody>> MINECART_BODY = SynchedEntityData.defineId(CustomizableMinecart.class, CustomizableCartsEntityDataSerializers.PART);

    public CustomizableMinecart(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public CustomizableMinecart(Level level, double x, double y, double z) {
        super(CustomizableCartsEntities.CUSTOMIZABLE_MINECART, level, x, y, z);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        RegistryAccess registryAccess = this.registryAccess();
        Registry<CartBody> registry = registryAccess.registryOrThrow(CustomizableCartsRegistryKeys.CART_BODY_KEY);
        builder.define(MINECART_BODY, registry.getHolder(CartBodies.REGULAR_MINECART).or(registry::getAny).orElseThrow());
    }

    @Override
    protected Item getDropItem() {
        return this.getBodyPartType().item();
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(this.getBodyPartType().item());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        this.getBodyPart().unwrapKey().ifPresent(resourceKey -> compoundTag.putString("body_part", resourceKey.location().toString()));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        Optional.ofNullable(ResourceLocation.tryParse(compoundTag.getString("body_part")))
                .map(resourceLocation -> ResourceKey.create(CustomizableCartsRegistryKeys.CART_BODY_KEY, resourceLocation))
                .flatMap(resourceKey -> this.registryAccess().registryOrThrow(CustomizableCartsRegistryKeys.CART_BODY_KEY).getHolder(resourceKey))
                .ifPresent(this::setBodyPartType);
    }

    public void setBodyPartType(Holder<CartBody> part) {
        if (part.value() instanceof CartBody) {
            this.entityData.set(MINECART_BODY, part);
        } else {
            RegistryAccess registryAccess = this.registryAccess();
            Registry<CartBody> registry = registryAccess.registryOrThrow(CustomizableCartsRegistryKeys.CART_BODY_KEY);
            this.entityData.set(MINECART_BODY, registry.getHolder(CartBodies.REGULAR_MINECART).or(registry::getAny).orElseThrow());
            CustomizableCarts.LOGGER.error("Attempted to set a non-body part as the body part of a minecart. Defaulting to regular minecart.");
        }
    }

    public Holder<CartBody> getBodyPart() {
        return this.entityData.get(MINECART_BODY);
    }

    public CartBody getBodyPartType() {
        return this.getBodyPart().value();
    }


    // NOTE: Seems like 1.6x max speed is the highest before it starts to come off rails constantly

    @Override
    public InteractionResult interact(Player player, InteractionHand interactionHand) {
        if (player.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        } else if (this.isVehicle()) {
            return InteractionResult.PASS;
        } else if (!this.level().isClientSide) {
            return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            return InteractionResult.SUCCESS;
        }
    }

    @Nullable
    public BlockState getRailBlockOn() {
        int i = Mth.floor(this.getX());
        int j = Mth.floor(this.getY());
        int k = Mth.floor(this.getZ());
        if (this.level().getBlockState(new BlockPos(i, j - 1, k)).is(BlockTags.RAILS)) {
            --j;
        }

        BlockPos blockPos = new BlockPos(i, j, k);
        BlockState blockState = this.level().getBlockState(blockPos);
        if (blockState.is(BlockTags.RAILS)) {
            return blockState;
        } else {
            return null;
        }

    }

    public void activateMinecart(int i, int j, int k, boolean bl) {
        if (bl) {
            if (this.isVehicle()) {
                this.ejectPassengers();
            }

            if (this.getHurtTime() == 0) {
                this.setHurtDir(-this.getHurtDir());
                this.setHurtTime(10);
                this.setDamage(50.0F);
                this.markHurt();
            }
        }

    }

    @Override
    public AbstractMinecart.Type getMinecartType() {
        return Type.RIDEABLE;
    }

}
