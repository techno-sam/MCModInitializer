package com.slimeist.base_mod.common.blocks;

import com.slimeist.base_mod.core.init.BlockInit;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class CustomOreBlock extends OreBlock {
    public CustomOreBlock(Properties p_i48357_1_) {
        super(p_i48357_1_);
    }

    @Override
    protected int xpOnDrop(Random p_220281_1_) {
        if (this == BlockInit.DEMO_BLOCK) {
            return MathHelper.nextInt(p_220281_1_, 2, 6);
        } else {
            return super.xpOnDrop(p_220281_1_);
        }
    }
}
