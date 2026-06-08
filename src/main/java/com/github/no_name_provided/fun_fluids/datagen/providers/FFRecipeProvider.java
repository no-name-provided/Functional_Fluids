package com.github.no_name_provided.fun_fluids.datagen.providers;

import com.github.no_name_provided.fun_fluids.common.fluids.registries.ItemRegistry;
import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.CompletableFuture;

import static com.github.no_name_provided.fun_fluids.FunFluids.MODID;

/**
 * This is where we create our recipe files.
 */
@ParametersAreNonnullByDefault @MethodsReturnNonnullByDefault
public class FFRecipeProvider extends RecipeProvider {
    /**
     * We need to reference this later, and the corresponding field in our superclass is access-restricted.
     * <p>
     * Alternatively, you could use an Access Transformer.
     * </p>
     */
    private final RecipeOutput recipeOutput;
    
    public FFRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
        this.recipeOutput = recipeOutput;
    }
    
    /**
     * This is where we actually "register" our recipes.
     */
    @Override
    protected void buildRecipes() {
        shapeless(
                RecipeCategory.MISC,
                new ItemStackTemplate(ItemRegistry.COOL_LAVA_BUCKET)
        ).requires(Items.SNOW_BLOCK)
                .requires(Items.MAGMA_CREAM)
                .requires(Items.BUCKET)
                .unlockedBy("has_magma_cream", has(Items.MAGMA_CREAM))
                .save(recipeOutput);
        shapeless(
                RecipeCategory.MISC,
                ItemRegistry.THICK_AIR_BUCKET.get()
        ).requires(Items.DRAGON_BREATH)
                .requires(Items.SNOW_BLOCK)
                .requires(Items.BUCKET)
                .requires(Items.CAMPFIRE)
                .unlockedBy("has_dragon_breath", has(Items.DRAGON_BREATH))
                .save(recipeOutput);
        shapeless(
                RecipeCategory.MISC,
                ItemRegistry.CONFIGURABLE_FLUID_BUCKET.get()
        ).requires(Items.ICE)
                .requires(Items.BUCKET)
                .requires(Items.CAMPFIRE)
                .unlockedBy("has_ice", has(Items.ICE))
                .save(recipeOutput);
        shapeless(
                RecipeCategory.MISC,
                ItemRegistry.RIVER_OF_TIME_BUCKET.get()
        ).requires(Items.ENDER_PEARL)
                .requires(Items.BUCKET)
                .requires(Items.CAMPFIRE)
                .unlockedBy("has_pearl", has(Items.ENDER_PEARL))
                .save(recipeOutput);
        
        // No default recipe for buckets of Flood because they're too dangerous
    }
    
    /**
     * This is what we "register"; it consumes our CompletableFuture and runs our provider.
     */
    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }
        
        /**
         * Provides our recipe provider.
         *
         * @param provider Can be used to query registries.
         * @param output   The relevant RecipeOutput.
         * @return The RecipeProvider.
         */
        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput output) {
            return new FFRecipeProvider(provider, output);
        }
        
        /**
         * Returns an identifier for this runner.
         *
         * @return An arbitrary, unique identifier.
         */
        @Override
        public String getName() {
            return MODID + "_recipe_provider";
        }
    }
}
