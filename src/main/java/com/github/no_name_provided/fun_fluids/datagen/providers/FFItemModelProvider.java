package com.github.no_name_provided.fun_fluids.datagen.providers;

import com.github.no_name_provided.fun_fluids.common.fluids.registries.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class FFItemModelProvider extends ItemModelProvider {
    public FFItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override protected void registerModels() {
//        mimicItem(ItemRegistry.COOL_LAVA_BUCKET.get(), Items.LAVA_BUCKET);
        withExistingParent(ItemRegistry.COOL_LAVA_BUCKET.getRegisteredName(), mcLoc("item/lava_bucket"));

        withExistingParent(ItemRegistry.THICK_AIR_BUCKET.getRegisteredName(), mcLoc("item/bucket"));
    }

//    ItemModelBuilder mimicItem(Item itemToModel, Item itemToMimic) {
//        ResourceLocation locationToModel = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(itemToModel));
//        ResourceLocation locationToCopy = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(itemToMimic));
//
//        return
//    }
}
