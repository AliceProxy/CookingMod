package com.proxyalice.bdocookingmod.items;

import com.proxyalice.bdocookingmod.BdoCooking;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class BalenosMeal extends Item {


    public BalenosMeal() {
        super(new Properties()
        .group(BdoCooking.TAB)
        .food(new Food.Builder()
            .hunger(6)
            .saturation(1.2f)
            .effect(new EffectInstance(Effects.NAUSEA, 10*20, 1), 0.3f)
            .effect(new EffectInstance(Effects.REGENERATION, 3*20, 2), 1.0f)
            .fastToEat()
            .setAlwaysEdible()
            .build()

        )



        );
    }
}
