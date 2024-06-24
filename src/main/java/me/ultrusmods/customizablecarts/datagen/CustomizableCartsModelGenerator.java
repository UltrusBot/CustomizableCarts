package me.ultrusmods.customizablecarts.datagen;

import me.ultrusmods.customizablecarts.registry.CustomizableCartItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class CustomizableCartsModelGenerator extends FabricModelProvider {
    public CustomizableCartsModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(CustomizableCartItems.CARNIVAL_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(CustomizableCartItems.BLUE_CARNIVAL_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(CustomizableCartItems.MINT_CARNIVAL_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(CustomizableCartItems.ORANGE_CARNIVAL_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(CustomizableCartItems.PINK_CARNIVAL_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(CustomizableCartItems.YELLOW_CARNIVAL_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(CustomizableCartItems.GOLD_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(CustomizableCartItems.MUSIC_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(CustomizableCartItems.TRIAL_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(CustomizableCartItems.TUFF_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(CustomizableCartItems.END_FRAME_MINECART, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(CustomizableCartItems.DUCK_MINECART, ModelTemplates.FLAT_ITEM);
    }
}
