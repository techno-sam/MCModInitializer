package com.slimeist.base_mod.common;

import com.slimeist.base_mod.BaseMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class BaseModTags {
    public static void init() {
        Blocks.init();
        Items.init();
    }

    public static class Blocks {
        private static void init() {}

        public static final Tags.IOptionalNamedTag<Block> DEMO_BLOCK = tag("demo_block");

        private static Tags.IOptionalNamedTag<Block> tag(String name) {
            return BlockTags.createOptional(BaseMod.getId(name));
        }

        private static Tags.IOptionalNamedTag<Block> forgeTag(String name) {
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        private static void init() {}

        public static final Tags.IOptionalNamedTag<Item> DEMO_ITEM = tag("demo_item");

        private static Tags.IOptionalNamedTag<Item> tag(String name) {
            return ItemTags.createOptional(BaseMod.getId(name));
        }

        private static Tags.IOptionalNamedTag<Item> forgeTag(String name) {
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
    }
}
