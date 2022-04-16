package com.slimeist.base_mod.common;

import com.slimeist.base_mod.core.init.BlockInit;
import com.slimeist.base_mod.core.util.MiscUtil;
import com.slimeist.base_mod.core.util.MobHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.IWorld;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CommonEventHandler {
    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        Block block = state.getBlock();
        IWorld world = event.getWorld();
        if (block.is(BaseModTags.Blocks.DEMO_BLOCK)) {
            if (false) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityTargeted(final LivingSetAttackTargetEvent event) {
        if (event.getTarget() instanceof PlayerEntity) {
            if (false) {
                MobHelper.resetTarget((MobEntity) event.getEntity());
            }
        }
    }

    @SubscribeEvent
    public static void onEntityUpdate(final LivingEvent.LivingUpdateEvent event) {
        PlayerEntity player = null;

        if (event.getEntityLiving() instanceof MobEntity) {
            MobEntity mob = (MobEntity) event.getEntityLiving();
            if (mob.getTarget() instanceof PlayerEntity) {
                player = (PlayerEntity) mob.getTarget();
            } else if (mob.getLastHurtByMob() instanceof PlayerEntity) {
                player = (PlayerEntity) mob.getLastHurtByMob();
            } else if (mob instanceof IAngerable) {
                IAngerable angerableMob = (IAngerable) mob;
                if (angerableMob.getPersistentAngerTarget()!=null && mob.level.getPlayerByUUID(angerableMob.getPersistentAngerTarget()) != null) {
                    player = mob.level.getPlayerByUUID(angerableMob.getPersistentAngerTarget());
                }
            }
            if (player!=null && false) {
                MobHelper.resetTarget(mob, true);
                MobHelper.resetPersistantAnger(mob);
            }
        }
    }
}
