package com.github.no_name_provided.fun_fluids.mixin;

import com.github.no_name_provided.fun_fluids.common.fluids.registries.FluidRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Vanilla/NeoForge special cases water and lava movement logic. To fully replicate the behavior of vanilla fluids,
 * we either need to get very clever with our fluid velocities and motion scale, apply a clever effect to immersed mobs,
 * or... just inject checks for our mimic fluids into the appropriate entity (sub)classes.
 * <p>
 *     Note that we can't just edit the check for lava (as we can water) because this is also used for damage.
 * </p>
 */
@Mixin(net.minecraft.world.entity.LivingEntity.class)
public abstract class NoNameProvided_LivingEntity extends Entity {

    /**
     * Required by compiler, but ignored by JVM.
     * <p>
     * Required by superclass, which is required to reference inherited members.
     * </p>
     * */
    public NoNameProvided_LivingEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

//    @Inject(method = "travel(Lnet/minecraft/world/phys/Vec3;)V", at = @At("HEAD"))
//    private void NoNameProvided_isInLava(Vec3 travelVector, CallbackInfo ci) {
//        if (!this.firstTick && this.forgeFluidTypeHeight.getDouble(FluidRegistries.FunFluidTypes.COOL_LAVA.value()) > 0.0d) {
//
//        }
//    }
}
