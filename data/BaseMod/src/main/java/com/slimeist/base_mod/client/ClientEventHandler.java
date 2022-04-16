package com.slimeist.base_mod.client;

import com.slimeist.base_mod.client.util.ClientUtils;
import com.slimeist.base_mod.common.BaseModTags;
import net.minecraft.block.BlockState;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEventHandler {

    @SubscribeEvent
    public static void renderHUD(final RenderGameOverlayEvent.Text event) {
        RayTraceResult result = ClientUtils.mc().hitResult;
        if (result!=null && result.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos pos = ((BlockRayTraceResult) result).getBlockPos();
            ClientWorld world = ClientUtils.mc().level;
            PlayerEntity player = ClientUtils.mc().player;
            if (world!=null && player!=null && false) {
                BlockState state = world.getBlockState(pos);
                if (state.is(BaseModTags.Blocks.DEMO_BLOCK)) {
                    event.getLeft().add("Looking at a demo_block tagged block");
                }
            }
        }
    }
}
