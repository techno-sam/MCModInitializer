package com.slimeist.base_mod.core.init;

import com.slimeist.base_mod.BaseMod;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

public final class ContainerTypeInit {
    //public static ContainerType<ContainerForceController> FORCE_CONTROLLER_TYPE;
    //public static ContainerType<ContainerForceModifier> FORCE_MODIFIER_TYPE;

    private ContainerTypeInit() {}

    public static void registerAll(RegistryEvent.Register<ContainerType<?>> event) {
        //FORCE_CONTROLLER_TYPE = register("force_controller", IForgeContainerType.create(ContainerForceController::createContainerClientSide));
        //FORCE_MODIFIER_TYPE = register("force_modifier", IForgeContainerType.create(ContainerForceModifier::createContainerClientSide));
    }

    private static <T extends ContainerType<?>> T register(String name, T containertype) {
        ResourceLocation id = BaseMod.getId(name);
        containertype.setRegistryName(id);
        ForgeRegistries.CONTAINERS.register(containertype);
        return containertype;
    }
}
