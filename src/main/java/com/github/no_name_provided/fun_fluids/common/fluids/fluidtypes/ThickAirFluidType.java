package com.github.no_name_provided.fun_fluids.common.fluids.fluidtypes;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.fluids.FluidType;

public class ThickAirFluidType extends FluidType {

    public ThickAirFluidType() {
        super(Properties.create()
                .temperature(20)
                .pathType(PathType.WATER)
                .canConvertToSource(false)
                // Don't scale fall damage
                .fallDistanceModifier(1)
                .canDrown(false)
                .canPushEntity(false)
                .density(9999)
                .fallDistanceModifier(1)
                .motionScale(0)
                .rarity(Rarity.UNCOMMON)
                .supportsBoating(true)
                .viscosity(900)
                .lightLevel(0)
        );
    }
}
