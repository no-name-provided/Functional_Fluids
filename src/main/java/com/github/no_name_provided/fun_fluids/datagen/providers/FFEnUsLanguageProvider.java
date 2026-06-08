package com.github.no_name_provided.fun_fluids.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.Locale;
import java.util.function.Supplier;

import static com.github.no_name_provided.fun_fluids.FunFluids.MODID;
import static com.github.no_name_provided.fun_fluids.common.fluids.registries.BlockRegistry.*;
import static com.github.no_name_provided.fun_fluids.common.fluids.registries.ItemRegistry.*;

/**
 * This is where we create our translation file.
 */
public class FFEnUsLanguageProvider extends LanguageProvider {
    public FFEnUsLanguageProvider(PackOutput output) {
        super(output, MODID, Locale.US.toString().toLowerCase());
    }
    
    /**
     * This is where we "register" our localization strings with the datagen system.
     */
    @Override
    protected void addTranslations() {
        add("item_group." + MODID, "Functional Fluids");
        
        addFluidSet("Cool Lava", COOL_LAVA_BLOCK, COOL_LAVA_BUCKET, COOL_LAVA_CAULDRON);
        addFluidSet("Thick Air", THICK_AIR_BLOCK, THICK_AIR_BUCKET);
        addFluidSet("Configurable Fluid", CONFIGURABLE_FLUID_BLOCK, CONFIGURABLE_FLUID_BUCKET);
        addFluidSet("River of Time", RIVER_OF_TIME_BLOCK, RIVER_OF_TIME_BUCKET);
        addFluidSet("Flood", FLOOD_BLOCK, FLOOD_BUCKET);
        
    }
    
    /**
     * Utility method for common fluid things.
     */
    private void addFluidSet(String name, Supplier<? extends LiquidBlock> block, Supplier<? extends BucketItem> bucket, Supplier<? extends AbstractCauldronBlock> cauldron) {
        if (null != block) {
            addBlock(block, name + " Block");
            add(block.get().fluid.getFluidType().getDescriptionId(), name);
        }
        if (null != bucket) {
            addItem(bucket, name + " Bucket");
        }
        if (null != cauldron) {
            addBlock(cauldron, name + " Cauldron");
        }
    }
    
    /**
     * Utility method for common fluid things that don't have a cauldron.
     */
    private void addFluidSet(String name, Supplier<? extends LiquidBlock> block, Supplier<? extends BucketItem> bucket) {
        addFluidSet(name, block, bucket, null);
    }
    
    /**
     * Utility method for common fluid things that have neither a cauldron nor a block.
     */
    @SuppressWarnings("unused")
    private void addFluidSet(String name, Supplier<? extends BucketItem> bucket) {
        addFluidSet(name, null, bucket);
    }
    
}
