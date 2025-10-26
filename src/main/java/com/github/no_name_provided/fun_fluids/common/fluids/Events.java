package com.github.no_name_provided.fun_fluids.common.fluids;

import com.github.no_name_provided.fun_fluids.common.fluids.registries.BlockRegistry;
import com.github.no_name_provided.fun_fluids.common.fluids.registries.FluidRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
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

    /**
     * Runs immediately before the server starts (presumably during the world loading screen).
     * <p>
     * This is where we register all fluid interactions that don't involve falling. Arbitrary, I know.
     * </p>
     *
     */
    @SubscribeEvent
    static void onServerAboutToStart(ServerAboutToStartEvent event) {
        // They seem to work if we "register" them here, so we might as well. For whatever reason, the (Neo)Forge team
        // decided to use a bespoke "registry" (not deferred) with a basic synchronized static addition method.
        // This may cause lag if a bunch of mods try to add fluid interactions at the same time.
        FluidInteractionRegistry.addInteraction(
                FluidRegistries.FunFluidTypes.COOL_LAVA.get(),
                new FluidInteractionRegistry.InteractionInformation(
                        NeoForgeMod.WATER_TYPE.value(),
                        fluidState -> fluidState.isSource() ? Blocks.OBSIDIAN.defaultBlockState() : Blocks.COBBLESTONE.defaultBlockState())
        );
        FluidInteractionRegistry.addInteraction(
                FluidRegistries.FunFluidTypes.COOL_LAVA.get(),
                new FluidInteractionRegistry.InteractionInformation(
                        (level, currentPos, relativePos, currentState) -> level.getBlockState(currentPos.below()).is(Blocks.SOUL_SOIL) && level.getBlockState(relativePos).is(Blocks.BLUE_ICE),
                        Blocks.BASALT.defaultBlockState())
        );

    }
}
