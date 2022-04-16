package com.slimeist.base_mod.core.util;

import com.slimeist.base_mod.common.BaseModTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.Random;

public class MiscUtil {

    public static void syncTE(TileEntity tile) {
        SUpdateTileEntityPacket packet = tile.getUpdatePacket();
        if (packet!=null && tile.getLevel() instanceof ServerWorld) {
            ((ServerChunkProvider) tile.getLevel().getChunkSource()).chunkMap
                    .getPlayers(new ChunkPos(tile.getBlockPos()), false)
                    .forEach(e -> e.connection.send(packet));
        }
    }

    public static double randomDouble(double lowerBound, double upperBound, Random rand) {
        double zero_to_1 = rand.nextDouble();
        double zero_to_scaled = zero_to_1 * (upperBound-lowerBound);
        return zero_to_scaled + lowerBound;
    }

    public static double randomSignedDouble(double lowerBound, double upperBound, Random rand) {
        return randomDouble(lowerBound, upperBound, rand) * (rand.nextBoolean() ? -1 : 1);
    }

    public static Object randomChoice(ArrayList<?> list, Random rand) {
        int index = rand.nextInt(list.size());
        return list.get(index);
    }

    /**
     * 0.0d percent is a.
     * 1.0d percent is b.
     * 0.5d percent is evenly mixed, etc..
     */
    public static double lerp(double a, double b, double percent) {
        percent = Math.min(1.0d, Math.max(0.0d, percent));
        return (b*percent)+(a*(1.0d-percent));
    }
}
