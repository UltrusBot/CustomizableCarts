package me.ultrusmods.customizablecarts.registry;

import me.ultrusmods.customizablecarts.CustomizableCarts;
import me.ultrusmods.customizablecarts.entity.CustomizableMinecart;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class CustomizableCartsEntities {
    public static final EntityType<CustomizableMinecart> CUSTOMIZABLE_MINECART = register("customizable_minecart",
            EntityType.Builder.<CustomizableMinecart>of(CustomizableMinecart::new, MobCategory.MISC).sized(0.98F, 0.7F).passengerAttachments(0.1875F).clientTrackingRange(8));

    public static void init() {

    }

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, CustomizableCarts.id(id), builder.build(CustomizableCarts.id(id).toString()));
    }
}
