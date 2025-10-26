package com.github.no_name_provided.fun_fluids.common.fluids.registries;

import com.github.no_name_provided.fun_fluids.common.fluids.CoolLavaFluid;
import com.github.no_name_provided.fun_fluids.common.fluids.fluidtypes.CoolLavaFluidType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;

import static com.github.no_name_provided.fun_fluids.FunFluids.MODID;

public class FluidRegistries {

    public static class FunFluids {
        public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, MODID);

        public static final DeferredHolder<Fluid, CoolLavaFluid.Flowing> FLOWING_COOL_LAVA = FLUIDS.register(
                "flowing_cool_lava",
                CoolLavaFluid.Flowing::new
        );
        public static final DeferredHolder<Fluid, CoolLavaFluid.Source> COOL_LAVA = FLUIDS.register(
                "cool_lava",
                CoolLavaFluid.Source::new
        );
    }

    public static class FunFluidTypes {
        public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, MODID);

        public static final DeferredHolder<FluidType, CoolLavaFluidType> COOL_LAVA = FLUID_TYPES.register(
                "cool_lava",
                CoolLavaFluidType::new
        );
    }

    public static void register(IEventBus bus) {
        FunFluids.FLUIDS.register(bus);
        FunFluidTypes.FLUID_TYPES.register(bus);
    }

}
