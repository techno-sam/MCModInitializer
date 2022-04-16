package com.slimeist.base_mod.core.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class CreativeTabInit {

    public static final ItemGroup BASE_MOD = new ItemGroup("base_mod") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ItemInit.DEMO_ITEM);
        }
    };
}


