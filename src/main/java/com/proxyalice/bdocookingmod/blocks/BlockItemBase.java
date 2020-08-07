package com.proxyalice.bdocookingmod.blocks;

import com.proxyalice.bdocookingmod.BdoCooking;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class BlockItemBase extends BlockItem {
    public BlockItemBase(Block block) {
        super(block, new Item.Properties().group(BdoCooking.TAB));
    }
}
