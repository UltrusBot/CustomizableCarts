package me.ultrusmods.customizablecarts.datagen;

import me.ultrusmods.customizablecarts.registry.CustomizableCartsEntities;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEntityTypeTags;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class CustomizableCartsTagGenererator extends FabricTagProvider.EntityTypeTagProvider {
    public CustomizableCartsTagGenererator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        this.getOrCreateTagBuilder(ConventionalEntityTypeTags.MINECARTS)
                .add(CustomizableCartsEntities.CUSTOMIZABLE_MINECART);

    }
}
