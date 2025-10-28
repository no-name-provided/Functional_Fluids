package com.github.no_name_provided.fun_fluids.common.fluids.registries;

import com.github.no_name_provided.fun_fluids.common.items.BetterBucketItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.github.no_name_provided.fun_fluids.FunFluids.MODID;

public class ItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredHolder<Item, BucketItem> COOL_LAVA_BUCKET = ITEMS.register(
            "cool_lava_bucket",
            () -> new BetterBucketItem(FluidRegistries.FunFluids.COOL_LAVA.get(), new Item.Properties())
    );
    public static final DeferredHolder<Item, BucketItem> THICK_AIR_BUCKET = ITEMS.register(
            "dense_air_bucket",
            () -> new BetterBucketItem(FluidRegistries.FunFluids.THICK_AIR_FLUID.get(), new Item.Properties())
    );
    public static final DeferredHolder<Item, BucketItem> CONFIGURABLE_FLUID_BUCKET = ITEMS.register(
            "configurable_fluid_bucket",
            () -> new BetterBucketItem(FluidRegistries.FunFluids.CONFIGURABLE_FLUID.get(), new Item.Properties())
    );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
