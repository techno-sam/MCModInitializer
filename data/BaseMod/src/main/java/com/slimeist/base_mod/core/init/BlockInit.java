package com.slimeist.base_mod.core.init;

import com.slimeist.base_mod.BaseMod;
import com.slimeist.base_mod.common.blocks.CustomOreBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.ToIntFunction;

public final class BlockInit {
    public static OreBlock DEMO_BLOCK;

    private BlockInit() {}

    public static void registerAll(RegistryEvent.Register<Block> event) {

    DEMO_BLOCK = register("demo_block", new CustomOreBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.SAND)
            .requiresCorrectToolForDrops()
            .strength(3.0F, 9.0F)
    ));

    // RenderLayerHandler.setRenderType(BASE_PIPE, RenderLayerHandler.RenderTypeSkeleton.CUTOUT_MIPPED);
        // initializeSpawnEggs();
    }

    private static <T extends Block> T register(String name, T block) {
        ResourceLocation id = BaseMod.getId(name);
        block.setRegistryName(id);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }

    private static boolean always(BlockState p_235426_0_, IBlockReader p_235426_1_, BlockPos p_235426_2_) {
        return true;
    }

    private static boolean never(BlockState p_235436_0_, IBlockReader p_235436_1_, BlockPos p_235436_2_) {
        return false;
    }

    private static boolean never(BlockState p_235427_0_, IBlockReader p_235427_1_, BlockPos p_235427_2_, EntityType<?> p_235427_3_) {
        return false;
    }

    private static boolean always(BlockState p_235437_0_, IBlockReader p_235437_1_, BlockPos p_235437_2_, EntityType<?> p_235437_3_) {
        return true;
    }

    private static ToIntFunction<BlockState> enabledBlockEmission(int p_235420_0_) {
        return (p_235421_1_) -> p_235421_1_.getValue(BlockStateProperties.ENABLED) ? p_235420_0_ : 0;
    }

    //public static void initializeSpawnEggs() {
    //    ModSpawnEggItem.initUnaddedEggs();
    //}
}

