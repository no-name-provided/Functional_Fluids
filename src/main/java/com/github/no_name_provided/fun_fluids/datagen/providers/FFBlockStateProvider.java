package com.github.no_name_provided.fun_fluids.datagen.providers;

import com.github.no_name_provided.fun_fluids.common.fluids.registries.BlockRegistry;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import static com.github.no_name_provided.fun_fluids.common.fluids.registries.BlockRegistry.COOL_LAVA_BLOCK;
import static com.github.no_name_provided.fun_fluids.common.fluids.registries.BlockRegistry.COOL_LAVA_CAULDRON;

/**
 * Note: import the NeoForge BlockStateProvider.
 */
public class FFBlockStateProvider extends BlockStateProvider {
    public FFBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
    }

    @Override protected void registerStatesAndModels() {
//        simpleBlock(COOL_LAVA_CAULDRON.get(), );

        simpleBlock(
                COOL_LAVA_CAULDRON.get(),
                models().getExistingFile(mcLoc("lava_cauldron"))
//                models().generatedModels.get(mcLoc("lava_cauldron"))
        );

        //Not necessary. Eliminates some undefined/missing blockstate warnings.
        simpleBlock(
                COOL_LAVA_BLOCK.get(),
                models().getExistingFile(mcLoc("lava"))
        );

    }
}
