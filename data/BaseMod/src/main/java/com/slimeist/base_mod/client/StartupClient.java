package com.slimeist.base_mod.client;

import com.slimeist.base_mod.core.util.RenderLayerHandler;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class StartupClient {
    @SubscribeEvent
    public static void setupClient(final FMLClientSetupEvent event) {
        RenderLayerHandler.init();
    }

    @SubscribeEvent
    public static void loadExtraTextures(final TextureStitchEvent.Pre event) {
        /*if (event.getMap().location().equals(PlayerContainer.BLOCK_ATLAS)) {
            event.addSprite(AlternateForceTubeTileEntityRenderer.SHIMMER_LOCATION);
            event.addSprite(AlternateForceTubeTileEntityRenderer.OUTLINE_LOCATION);
        }*/
    }
}
