package com.github.no_name_provided.fun_fluids.client;

import com.github.no_name_provided.fun_fluids.FunFluids;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = FunFluids.MODID)
public class ClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue RENDER_THICK_AIR = BUILDER
            .comment("Should thick air be visible? (Set to true for ease of building.)")
            .define("renderThickAir", false
            );

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean renderThickAir;

    /**
     * This should update the configurable constants every time this config is loaded or reloaded.
     * */
    @SubscribeEvent
    static void onConfigUpdate(final ModConfigEvent event) {
        // A common crash on server stop is caused by trying to check values that have already been unloaded.
        // #BlameTheNeoForgeTeam
        if (!(event instanceof ModConfigEvent.Unloading) && event.getConfig().getType() == ModConfig.Type.CLIENT) {
            renderThickAir = RENDER_THICK_AIR.get();
        }
    }

}
