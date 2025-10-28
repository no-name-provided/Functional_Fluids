package com.github.no_name_provided.fun_fluids.client;

import com.github.no_name_provided.fun_fluids.common.CommonConfig;
import com.github.no_name_provided.fun_fluids.common.fluids.registries.FluidRegistries;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import javax.annotation.ParametersAreNonnullByDefault;

import static com.github.no_name_provided.fun_fluids.FunFluids.MODID;
@ParametersAreNonnullByDefault @MethodsReturnNonnullByDefault
@EventBusSubscriber(modid=MODID)
public class Events {

    /**
     * This is where we set fluid tint, under fluid overlay, fluid overlay, still and flowing textures and custom
     * rendering logic.
     * */
    @SubscribeEvent
    static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {

//        final ResourceLocation UNDER_COOL_LAVA_LOCATION = ResourceLocation.parse("textures/misc/underwater.png");
        final ResourceLocation COOL_LAVA_STILL = ResourceLocation.withDefaultNamespace("block/lava_still");
        final ResourceLocation COOL_LAVA_FLOW = ResourceLocation.withDefaultNamespace("block/lava_flow");
//        final ResourceLocation COOL_LAVA_OVERLAY = ResourceLocation.fromNamespaceAndPath(MODID, "block/water_overlay");

        event.registerFluidType(
                new IClientFluidTypeExtensions() {
                    // Make sure you either use the parameterless methods (a fallback) or the ones
                    // with all parameters. The versions that just take a fluid state are ignored, and,
                    // if those are all you define, you'll get a null pointer error during fluid tesselation.
                    // #BlameTheNeoForgeTeam
                    @Override
                    public ResourceLocation getStillTexture() {
                        return COOL_LAVA_STILL;
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
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

                },
                FluidRegistries.FunFluidTypes.COOL_LAVA
        );
        event.registerFluidType(
                new IClientFluidTypeExtensions() {

                    final ResourceLocation THICK_AIR_STILL = ResourceLocation.fromNamespaceAndPath(MODID, "block/transparent");
                    final ResourceLocation THICK_AIR_FLOW = ResourceLocation.fromNamespaceAndPath(MODID, "block/transparent");

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return THICK_AIR_STILL;
                    }

                    @Override
                    public ResourceLocation getStillTexture() {
                        return THICK_AIR_FLOW;
                    }

                    /**
                     * Custom logic run before the fluid renderer. Executed once for each block.
                     * <p>
                     * Return true to disable vanilla rendering (which happens immediately after this code executes).
                     * </p>
                     * */
                    @Override @ParametersAreNonnullByDefault
                    public boolean renderFluid(FluidState fluidState, BlockAndTintGetter getter, BlockPos pos, VertexConsumer vertexConsumer, BlockState blockState) {
                        // Skip the vanilla fluid renderer?
                        return !ClientConfig.renderThickAir;
                    }
                },
                FluidRegistries.FunFluidTypes.THICK_AIR
        );
        event.registerFluidType(
                new IClientFluidTypeExtensions() {

                    // THe vanilla water textures are good, grayscale fluid textures. Unless you have an artistic bent,
                    // I'd recommend just using those and applying a tint.
                    final ResourceLocation C_FLUID_STILL = ResourceLocation.withDefaultNamespace("block/water_still");
                    final ResourceLocation C_FLUID_FLOW = ResourceLocation.withDefaultNamespace("block/water_flow");
                    // This is the only vanilla water texture that's colored, so it probably shouldn't be reused.
                    final ResourceLocation C_FLUID_OVERLAY = ResourceLocation.withDefaultNamespace("block/water_overlay");
                    final ResourceLocation UNDER_C_FLUID_LOCATION = ResourceLocation.withDefaultNamespace("textures/misc/underwater.png");

                    @Override
                    public ResourceLocation getStillTexture() {
                        return C_FLUID_STILL;
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return C_FLUID_FLOW;
                    }

                    @Override
                    public ResourceLocation getOverlayTexture() {
                        return C_FLUID_OVERLAY;
                    }

                    @Override
                    public ResourceLocation getRenderOverlayTexture(Minecraft minecraft) {
                        return UNDER_C_FLUID_LOCATION;
                    }

                    /**
                     * Only used as a fallback? Might only apply if biome doesn't have a water color set.
                     *  */
                    @Override
                    public int getTintColor() {
                        // net.neoforged.neoforge.client.ClientNeoForgedMod water_type uses, 0xFF3F76E4
                        // which works out to 255, 63, 118, 228 ARGB, as the default color.
                        // Many other vanilla colors can be found in BiomeColors.

                        // Alpha value will be ignored unless you change the render layer to transparent in the
                        // completely unrelated client setup event.

                        // You can use the FastColor class to easily convert
                        // between decimal components and aggregate ARGB values.
                        // Alternatively, here's an online converter: https://argb-int-calculator.netlify.app/.
                        return CommonConfig.cFColor;
                    }

                    /**Custom logic run before the fluid renderer. Executed once for each block.
                     * Right now, this override is a placeholder created to help me remember the NeoForge team's
                     * unfortunate naming conventions.*/
                    @Override @ParametersAreNonnullByDefault
                    public boolean renderFluid(FluidState fluidState, BlockAndTintGetter getter, BlockPos pos, VertexConsumer vertexConsumer, BlockState blockState) {
                        // Skip the vanilla fluid renderer?
                        return CommonConfig.cFVisibility;
                    }
                },
                FluidRegistries.FunFluidTypes.C_FLUID
        );
    }
}
