package com.github.no_name_provided.fun_fluids.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.world.level.material.WaterFluid.class)
abstract class NoNameProvided_WaterFluid {
//    @Inject(method = "beforeDestroyingBlock(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V", at = @At("HEAD"))
    private void NoNameProvided_beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state, CallbackInfo ci) {

    }
}