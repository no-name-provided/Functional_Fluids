package com.github.no_name_provided.fun_fluids.common.fluids.fluidtypes;

import com.github.no_name_provided.cfa.client.particles.CFAParticleTypes;
import com.github.no_name_provided.fun_fluids.datagen.providers.FFFluidTagsProvider;
import com.mojang.logging.annotations.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.SoundActions;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.github.no_name_provided.fun_fluids.FunFluids.MODID;

@ParametersAreNonnullByDefault @MethodsReturnNonnullByDefault
public class ThickAirFluidType extends TaggedFluidType {
    // Easter Egg - feel free to ignore this key
    public static final ResourceKey<LootTable> THICK_AIR_FISHING_LOOT =
            ResourceKey.create(Registries.LOOT_TABLE, Identifier.fromNamespaceAndPath(MODID, "thick_air_fishing"));
    
    public ThickAirFluidType() {
        super(Properties.create()
                .temperature(20)
                .pathType(PathType.BLOCKED)
                .canConvertToSource(false)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                .pathType(PathType.DAMAGING)
                .adjacentPathType(PathType.BLOCKED)
                // Don't scale fall damage
                .fallDistanceModifier(1)
                .canDrown(false)
                .canPushEntity(false)
                .density(9999)
                .fallDistanceModifier(0)
                .motionScale(0)
                .rarity(Rarity.UNCOMMON)
                .supportsBoating(true)
                .viscosity(900)
                .lightLevel(0)
        );
    }
    
    /**
     * Since vanilla has leaned heavily into the use of tags, we're now associating one with each fluid type. This
     * greatly simplifies the mixins required to replace the now-retiring Fluid API.
     */
    @Override
    public TagKey<Fluid> getTag() {
        return FFFluidTagsProvider.THICK_AIR;
    }
    
    /**
     * Performs how an entity moves when within the fluid. If the method returns {@code true}, no regular movement logic
     * will be performed (the player will simply stop moving). Otherwise, the movement logic for water will be run after
     * your code.
     * <p>
     * This is implicitly called in, and optionally disables most of, LivingEntity#travel. I recommend referencing this
     * method when writing your own logic.
     * </p>
     *
     * @param state        <s>the state of the fluid</s> Fluids.EMPTY#defaultState, unless called by a mod.
     * @param entity       the entity moving within the fluid
     * @param travelVector the velocity of how the entity wants to move
     * @param gravity      the gravity to apply to the entity
     * @return {@code true} if <b>only</b> custom movement logic is performed, {@code false} otherwise
     */
    @Override
    public boolean move(FluidState state, LivingEntity entity, Vec3 travelVector, double gravity) {
        if (entity.onGround() && !entity.isVisuallySwimming()) {
            // If we're just walking around on land, we don't really want to do anything different,
            // though you might want to add a tiny bit of slowdown, for immersion
            entity.travelInAir(travelVector);
        } else {
            // This call requires about a half dozen ATs. Feel free to copy them from
            // src/main/resources/META-INF/accesstransformer.cfg
            entity.travelInWater(travelVector, gravity, entity.getDeltaMovement().y <= 0.0, entity.getY());
            // Jumping in air is a finicky thing. It's easiest to just explicitly call it here
            if (entity.isJumping()) {
                // The 0.02 value was experimentally determined
                entity.setDeltaMovement(entity.getDeltaMovement().add(0, 0.02, 0));
            }
            // For some reason, we need this here or we still manage to take fall damage based on our highest position
            // since touching the ground. This might be a bug in the CFAPI
            entity.resetFallDistance();
        }
        return true;
    }
    
    /**
     * The loot table to use while fishing in this fluid.
     * <p>
     * Returns: The ResourceKey pointing at the loot table to be used to roll fishing loot.
     * </p>
     **/
    @Override
    public ResourceKey<LootTable> getFishingLootTableKey(Level lureLevel, BlockPos lurePos) {
        // Easter Egg - as a one-off that has nothing to do with fluids,
        // this table was made using https://misode.github.io/loot-table/.
        return THICK_AIR_FISHING_LOOT;
    }
    
    /**
     * Should the entity (type) make splashes when it enters this fluid?
     *
     * @param splashingType The type of entity making splashes.
     * @return True to make splashes; otherwise false.
     */
    @Override
    public boolean shouldSplash(EntityType<?> splashingType) {
        return false;
    }
    
    /**
     * Boats are covered in FluidType#supportsBoating. This method is for other vehicles. Minecarts are supported
     * out-of-the-box. Other vehicles will need to implement support.
     *
     * @param vehicle The vehicle to affect.
     * @return True if the vehicle should be affected by fluids of this type. Otherwise, false.
     */
    @Override
    public boolean affectsVehicle(VehicleEntity vehicle) {
        return false;
    }
    
    /**
     * Should the fluid make mobs wet?
     *
     * @param toWet The type of entity getting wet.
     * @return True if it should get wet, false otherwise.
     */
    @Override
    public boolean makesWet(EntityType<?> toWet) {
        return false;
    }
    
    @Override
    public boolean hasUnderWaterMusic() {
        return false;
    }
    
    /**
     * Will touching this fluid hurt this living entity?
     *
     * @param toHurt The living entity being hurt.
     * @return True if the entity should be hurt; otherwise false.
     */
    @Override
    public boolean hurtsEntity(LivingEntity toHurt) {
        return false;
    }
    
    /**
     * Create the particle we use for splash effects (wolf shake, water entry, etc.) on the client. May be called from
     * common code. Typically no-ops on server threads.
     */
    @Override
    public void createSplashParticleOnClient(Fluid fluid, Level level, double x, double y, double z, double xAux, double yAux, double zAux) {
        if (level.isClientSide()) {
            level.addParticle(ColorParticleOption.create(
                            CFAParticleTypes.TINTED_SPLASH_PARTICLE.get(),
                            // White
                            ARGB.color(255, 255, 255, 255)),
                    x,
                    y,
                    z,
                    xAux,
                    yAux,
                    zAux
            );
        }
    }
    
    /**
     * Create the particle we use for splash effects (wolf shake, water entry, etc.) from the server side.
     */
    @Override
    public int createSplashParticleOnServer(Fluid fluid, ServerLevel level, double x, double y, double z, int count, double xDist, double yDist, double zDist, double speed) {
        return level.sendParticles(
                ColorParticleOption.create(
                        CFAParticleTypes.TINTED_SPLASH_PARTICLE.get(),
                        // White
                        ARGB.color(255, 255, 255, 255)
                ), x,
                y,
                z,
                count,
                xDist,
                yDist,
                zDist,
                speed
        );
    }
    
    /**
     * Create the particle we use for wake effects (fish approaching fishing bob) from the server side.
     */
    @Override
    public int createWakeParticleOnServer(Fluid fluid, ServerLevel level, double x, double y, double z, int count, double xDist, double yDist, double zDist, double speed) {
        return level.sendParticles(
                ColorParticleOption.create(
                        CFAParticleTypes.TINTED_WAKE_PARTICLE.get(),
                        // White
                        ARGB.color(255, 255, 255, 255)
                ), x,
                y,
                z,
                count,
                xDist,
                yDist,
                zDist,
                speed
        );
    }
}
