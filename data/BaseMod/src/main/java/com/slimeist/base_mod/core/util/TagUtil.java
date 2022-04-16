package com.slimeist.base_mod.core.util;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class TagUtil {
    //copied from Tinker's Construct
    /**
     * Writes a block position to NBT
     * @param pos  Position to write
     * @return  Position in NBT
     */
    public static CompoundNBT writePos(BlockPos pos) {
        CompoundNBT tag = new CompoundNBT();
        tag.putInt("x", pos.getX());
        tag.putInt("y", pos.getY());
        tag.putInt("z", pos.getZ());
        return tag;
    }

    /**
     * Reads a block position from NBT
     * @param tag  Tag
     * @return  Block position, or null if invalid
     */
    @Nullable
    public static BlockPos readPos(CompoundNBT tag) {
        if (tag.contains("x", Constants.NBT.TAG_ANY_NUMERIC) && tag.contains("y", Constants.NBT.TAG_ANY_NUMERIC) && tag.contains("z", Constants.NBT.TAG_ANY_NUMERIC)) {
            return new BlockPos(tag.getInt("x"), tag.getInt("y"), tag.getInt("z"));
        }
        return null;
    }

    /**
     * Reads a block position from NBT
     * @param parent  Parent tag
     * @param key     Position key
     * @return  Block position, or null if invalid or missing
     */
    @Nullable
    public static BlockPos readPos(CompoundNBT parent, String key) {
        if (parent.contains(key, Constants.NBT.TAG_COMPOUND)) {
            return readPos(parent.getCompound(key));
        }
        return null;
    }
}
