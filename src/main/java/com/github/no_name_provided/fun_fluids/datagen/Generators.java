package com.github.no_name_provided.fun_fluids.datagen;

import com.github.no_name_provided.fun_fluids.datagen.providers.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static com.github.no_name_provided.fun_fluids.FunFluids.MODID;

/**
 * All data (json) generators need to be "registered" here. For convenience, the providers are defined in separate
 * classes.
 * <p>
 * This is what actually creates your mod's built-in datapack.
 * </p>
 * <p>
 * Pay attention to the order in which providers are added. For whatever reason, this actually matters.
 * </p>
 * <p>
 * Note: this is the "basic" approach. If your datagen classes need to share information, you may need to add a few
 * lines or use methods that bypass the ExistingFileHelper (the "helper" bit is a misnomer).
 */
@EventBusSubscriber(modid = MODID)
public class Generators {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent.Client event) {
        // Note for porters: the old methods work fine, and are probably better to learn since it's easier to see how
        // you'd generalize them. However, I have chosen to demonstrate the new helper methods here, which are a bit
        // more concise and eliminate the need for intermediate variables
        event.createProvider(FFRecipeProvider.Runner::new);
        event.createProvider(FFModelProvider::new);
        event.createProvider(FFFluidTagsProvider::new);
        event.createProvider(FFEnUsLanguageProvider::new);
        event.createProvider(FFParticleDescriptionProvider::new);
    }
}
