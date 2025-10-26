package com.github.no_name_provided.fun_fluids.common.fluids;

import com.github.no_name_provided.fun_fluids.common.fluids.registries.BlockRegistry;
import com.github.no_name_provided.fun_fluids.common.fluids.registries.FluidRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.fluids.RegisterCauldronFluidContentEvent;

import static com.github.no_name_provided.fun_fluids.FunFluids.MODID;

@EventBusSubscriber(modid = MODID)
public class Events {
    @SubscribeEvent
    static void registerCauldronFluidContent(RegisterCauldronFluidContentEvent event) {
        event.register(
                BlockRegistry.COOL_LAVA_CAULDRON.get(),
                FluidRegistries.FunFluids.COOL_LAVA.get(),
                1000,
                null
        );
    }
}
