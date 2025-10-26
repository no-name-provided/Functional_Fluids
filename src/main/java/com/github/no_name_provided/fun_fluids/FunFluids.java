package com.github.no_name_provided.fun_fluids;

import com.github.no_name_provided.fun_fluids.common.fluids.registries.BlockRegistry;
import com.github.no_name_provided.fun_fluids.common.fluids.registries.FluidRegistries;
import com.github.no_name_provided.fun_fluids.common.fluids.registries.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.CreativeModeTabRegistry;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Functional Fluids is an example/tutorial mod, designed to cover the myriad steps required to register fluids and
 * implement common functionality (cauldrons, buckets, etc.). It was created as a response to the state of fluid
 * documentation, the dysfunction of "official" documentation, and frequent complaints about the difficulty of
 * implementing fluids. In this mod, you will find full examples of common fluid implementations and copious
 * documentation in the form of docstrings and comments. It's recommended you pick an example fluid that does
 * what you want, and follow along while writing your own mod.
 * <p></p>
 * Fluid examples include:
 * <p> <ul>
 *     <li>Cool Lava - Full recreation of vanilla lava EXCEPT for fire/ignition</li>
 * </ul>
 *
 * </p> <p>
 * Disclaimer: This mod is the product of my personal experience, made in isolation.
 * It may have errors or inconsistencies. Feel free to make suggestions. Just remember - word of god doesn't mean
 * anything when it comes to (Neo)Forge. Don't tell me I'm wrong until you've checked for yourself.
 * </p>
 * */
@Mod(FunFluids.MODID)
public class FunFluids {

    // Use this (almost) anywhere you need a unique identifier.
    // If two mods have the same ID, they probably won't work together.
    public static final String MODID = "fun_fluids";

    // The (Neo)Forge team loves registries. As a rule of thumb, if you make something and don't register it,
    // you've made a mistake. This registry is for our creative mode tab.
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB, MODID
    );

    // We need this tab for our buckets. This is the most idiomatic way to make them show up in recipe viewers.
    @SuppressWarnings("unused")
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FLUID_TAB = CREATIVE_MODE_TABS.register(
            MODID, () -> CreativeModeTab.builder().title(Component.translatable("item_group." + MODID))
                    .withTabsBefore(CreativeModeTabs.FOOD_AND_DRINKS)
                    .icon(Items.WATER_BUCKET::getDefaultInstance)
                    .displayItems((parameters, output) -> {
                        ItemRegistry.ITEMS.getEntries().forEach(item ->
                                output.accept(item.get())
                        );
                    }).build());

    // Mod entry point. Mostly used for registration.
    public FunFluids(IEventBus modEventBus, ModContainer modContainer) {
        // Trigger deferred registers
        FluidRegistries.register(modEventBus);
        BlockRegistry.register(modEventBus);
        ItemRegistry.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register config file(s). It's recommended to use common, as the other types don't synchronize in multiplayer.
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

}
