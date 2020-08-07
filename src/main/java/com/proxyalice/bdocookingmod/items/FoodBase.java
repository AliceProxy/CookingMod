package com.proxyalice.bdocookingmod.items;

import com.proxyalice.bdocookingmod.BdoCooking;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodBase extends Item {
    public FoodBase() {
        super(new Item.Properties()
                .group(BdoCooking.TAB)
                .food(new Food.Builder()
                        .hunger(2)
                        .build()

                )



        );
    }
}
