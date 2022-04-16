package com.slimeist.base_mod.common.recipies;

import com.slimeist.base_mod.BaseMod;
import net.minecraft.item.crafting.IRecipeType;

public class RecipeTypeEnderFuel implements IRecipeType<EnderFuelRecipe> {

    @Override
    public String toString() {
        return BaseMod.getId("ender_fuel_recipe").toString();
    }
}
