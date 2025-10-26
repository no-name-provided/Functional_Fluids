package com.github.no_name_provided.fun_fluids.common.capabilities;

import com.github.no_name_provided.fun_fluids.common.fluids.registries.ItemRegistry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;

import static com.github.no_name_provided.fun_fluids.FunFluids.MODID;

@EventBusSubscriber(modid = MODID)
public class Events {
    @SubscribeEvent
    static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerItem(
                Capabilities.FluidHandler.ITEM,
                (stack, ctx) -> new FluidBucketWrapper(stack),
                ItemRegistry.COOL_LAVA_BUCKET.get()
        );
    }
}
