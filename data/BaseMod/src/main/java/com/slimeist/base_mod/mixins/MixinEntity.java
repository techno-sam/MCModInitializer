package com.slimeist.base_mod.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class MixinEntity {
    @Shadow
    public AxisAlignedBB getBoundingBox() {
        throw new RuntimeException("getBoundingBox() should have been mixed in");
    }

    @Shadow
    public World level;

    @Shadow
    private int remainingFireTicks;

    @Shadow
    public void setRemainingFireTicks(int ticks) {
        throw new RuntimeException("setRemainingFireTicks() should have been mixed in");}

    @Shadow
    protected int getFireImmuneTicks() {
        throw new RuntimeException("getFireImmuneTicks() should have been mixed in");
    }

    @Inject(at = @At(value="INVOKE", target="Lnet/minecraft/entity/Entity;setRemainingFireTicks(I)V", ordinal=0), method="move(Lnet/minecraft/entity/MoverType;Lnet/minecraft/util/math/vector/Vector3d;)V", cancellable = true)
    private void modifyTicks(CallbackInfo callback) {
        if (BlockPos.betweenClosedStream(this.getBoundingBox().deflate(0.001D)).noneMatch((p_233572_0_) -> {
            BlockState state = level.getBlockState(p_233572_0_);
            /*if (state.getBlock() instanceof ForceTubeBlock) {
                TileEntity tile = level.getBlockEntity(p_233572_0_);
                if (tile instanceof ForceTubeTileEntity) {
                    return ((ForceTubeBlock) state.getBlock()).getBurningType((Entity) (Object) this, (ForceTubeTileEntity) tile) == BurningType.BURN;
                }
            }*/
            return false;
        })) {
            this.setRemainingFireTicks(-this.getFireImmuneTicks());
        }
        callback.cancel();
    }
}