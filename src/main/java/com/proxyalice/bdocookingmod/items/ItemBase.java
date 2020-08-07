package com.proxyalice.bdocookingmod.items;

import com.proxyalice.bdocookingmod.BdoCooking;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {
    public ItemBase() {
        super(new Item.Properties().group(BdoCooking.TAB));
    }
}
