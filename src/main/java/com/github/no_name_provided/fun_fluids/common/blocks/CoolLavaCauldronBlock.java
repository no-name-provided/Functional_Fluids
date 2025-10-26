package com.github.no_name_provided.fun_fluids.common.blocks;

import com.github.no_name_provided.fun_fluids.common.fluids.registries.ItemRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Each type of fluid that can be placed in a cauldron is represented by a separate block. If you want cauldrons to hold
 * your fluids, you'll need to jump through some hoops.
 */
@ParametersAreNonnullByDefault @MethodsReturnNonnullByDefault
public class CoolLavaCauldronBlock extends AbstractCauldronBlock {

    public static final MapCodec<CoolLavaCauldronBlock> CODEC = simpleCodec(CoolLavaCauldronBlock::new);

    @Override
    public MapCodec<CoolLavaCauldronBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    public CoolLavaCauldronBlock(Properties properties) {
        super(properties, createInteractionMap());
    }

    /**
     * We can't add capabilities checks to a vanilla bucket (without mixins or shenanigans), so we
     * create an interaction map instead. A similar technique (with default cauldron interactions)
     * could probably be used to replace the fluid capability check in BetterBucketItem.
     */
    private static CauldronInteraction.InteractionMap createInteractionMap() {
        CauldronInteraction.InteractionMap interaction = CauldronInteraction.newInteractionMap("cool_lava");
        interaction.map().put(Items.BUCKET,  (state, level, pos, player, hand, stack) ->
                CauldronInteraction.fillBucket(
                        state,
                        level,
                        pos,
                        player,
                        hand,
                        stack,
                        new ItemStack(ItemRegistry.COOL_LAVA_BUCKET),
                        other_state -> true, // Honestly not sure what state this is.
                        SoundEvents.BUCKET_FILL_LAVA
                )
        );

        return interaction;
    }

}
