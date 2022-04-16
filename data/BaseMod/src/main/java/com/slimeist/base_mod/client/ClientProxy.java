package com.slimeist.base_mod.client;

import com.slimeist.base_mod.common.CommonProxy;
import net.minecraft.world.World;

import com.slimeist.base_mod.client.util.ClientUtils;

public class ClientProxy extends CommonProxy {

    @Override
    public World getClientWorld() {
        return ClientUtils.mc().level;
    }

}
