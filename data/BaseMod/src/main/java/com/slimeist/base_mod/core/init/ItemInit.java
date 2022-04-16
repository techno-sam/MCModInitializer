package com.slimeist.base_mod.core.init;

import com.slimeist.base_mod.BaseMod;
import com.slimeist.base_mod.common.items.ArmorMaterials;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

public final class ItemInit {

    public static BlockItem DEMO_BLOCK;

    public static Item DEMO_ITEM;

    private ItemInit() {}

    public static void registerAll(RegistryEvent.Register<Item> event) {

        //BlockItems
        DEMO_BLOCK = register("demo_block", new BlockItem(BlockInit.DEMO_BLOCK, new Item.Properties()
                .tab(CreativeTabInit.BASE_MOD)
        ));

        //Misc items
        DEMO_ITEM = register("demo_item", new Item(new Item.Properties().tab(CreativeTabInit.BASE_MOD)));
    }

    private static <T extends Item> T register(String name, T item) {
        ResourceLocation id = BaseMod.getId(name);
        item.setRegistryName(id);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }
}
