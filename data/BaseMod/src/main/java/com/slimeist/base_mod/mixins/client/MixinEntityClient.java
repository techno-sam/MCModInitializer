package com.slimeist.base_mod.mixins.client;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Entity.class)
public class MixinEntityClient {
/*
    @Inject(at = @At(value="RETURN"), method="isInvisibleTo(Lnet/minecraft/entity/player/PlayerEntity;)Z", cancellable = true)
    private void isInvisibleTo(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (player != null && false) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At(value="RETURN"), method="isInvisible()Z", cancellable = true)
    private void isInvisible(CallbackInfoReturnable<Boolean> cir) {
        Entity this_entity = (Entity) (Object) this;
        if (this_entity instanceof PlayerEntity && MiscUtil.isPlayerWearingFullShimmeringArmor((PlayerEntity) this_entity)) {
            cir.setReturnValue(true);
        }
    }*/
}