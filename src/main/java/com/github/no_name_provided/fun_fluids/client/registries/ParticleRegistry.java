package com.github.no_name_provided.fun_fluids.client.registries;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.github.no_name_provided.fun_fluids.FunFluids.MODID;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> MIST_PARTICLE = PARTICLE_TYPES.register(
            "mist_particle",
            // false means this doesn't generate when particles are set to "limited".
            () -> new SimpleParticleType(false)
    );

    public static void register(IEventBus bus) {
        PARTICLE_TYPES.register(bus);
    }
}
