package com.github.no_name_provided.fun_fluids.client;

import com.github.no_name_provided.fun_fluids.common.fluids.registries.FluidRegistries;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.github.no_name_provided.fun_fluids.FunFluids.MODID;

@EventBusSubscriber(modid=MODID)
public class Events {
    @SubscribeEvent
    static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {

//        final ResourceLocation UNDER_COOL_LAVA_LOCATION = ResourceLocation.parse("textures/misc/underwater.png");
        final ResourceLocation COOL_LAVA_STILL = ResourceLocation.withDefaultNamespace("block/lava_still");
        final ResourceLocation COOL_LAVA_FLOW = ResourceLocation.withDefaultNamespace("block/lava_flow");
//        final ResourceLocation COOL_LAVA_OVERLAY = ResourceLocation.fromNamespaceAndPath(MODID, "block/water_overlay");

        event.registerFluidType(
                new IClientFluidTypeExtensions() {
                    @Override
                    public @NotNull ResourceLocation getStillTexture() {
                        return COOL_LAVA_STILL;
                    }

                    @Override
                    public @NotNull ResourceLocation getFlowingTexture() {
                        return COOL_LAVA_FLOW;
                    }

//                    @Override
//                    public ResourceLocation getOverlayTexture() {
//                        return COOL_LAVA_OVERLAY;
//                    }

//                    @Override @ParametersAreNonnullByDefault
//                    public ResourceLocation getRenderOverlayTexture(Minecraft minecraft) {
//                        return UNDER_COOL_LAVA_LOCATION;
//                    }

                    /**Custom logic run before the fluid renderer. Executed once for each block.
                     * Right now, this override is a placeholder created to help me remember the NeoForge team's
                     * unfortunate naming conventions.*/
                    @Override @ParametersAreNonnullByDefault
                    public boolean renderFluid(FluidState fluidState, BlockAndTintGetter getter, BlockPos pos, VertexConsumer vertexConsumer, BlockState blockState) {
                        // Skip the vanilla fluid renderer?
                        return false;
                    }
                },
                FluidRegistries.FunFluidTypes.COOL_LAVA.value()
        );
    }
}
