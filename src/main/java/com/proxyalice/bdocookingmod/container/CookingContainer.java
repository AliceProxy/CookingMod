package com.proxyalice.bdocookingmod.container;

import com.proxyalice.bdocookingmod.init.ModContainerTypes;
import com.proxyalice.bdocookingmod.tileentity.CookingTileEntity;
import com.proxyalice.bdocookingmod.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class CookingContainer extends Container {

    public final CookingTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public CookingContainer(final int windowId, final PlayerInventory playerInventory, final CookingTileEntity tileEntity) {
        super(ModContainerTypes.COOKING_CHEST.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        // MAIN INVENTORY
        int startX = 8;
        int startY = 31;
        int slotSizePlus = 18;
        for(int col = 0; col < 5; ++col) {
            this.addSlot(new Slot(tileEntity, col, startX + (col * slotSizePlus), startY));
        }


        // Main Player Inventory
        int startPlayerInvY = 84;
        for(int row = 0; row < 3; ++row)
        {
            for(int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + col, startX + (col * slotSizePlus), startPlayerInvY + (row * slotSizePlus)));
            }
        }

        // Player Hotbar
        int hotbarY = 142;
        for(int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, startX + (col * slotSizePlus), hotbarY));
        }

    }

    private static CookingTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "Player Inv Cant Be Null");
        Objects.requireNonNull(data, "Data Cant Be Null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if(tileAtPos instanceof CookingTileEntity) {
            return (CookingTileEntity)tileAtPos;
        }
        throw new IllegalStateException("Cooking Tile Entity not Correct!" + tileAtPos);
    }

    public CookingContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, RegistryHandler.BASIC_COOKING_UTENSIL_BLOCK.get());
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if(slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();
            if(index < 5) {
                if(!this.mergeItemStack(itemStack1, 5, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemStack, 0, 5, false)) {
                return ItemStack.EMPTY;
            }
            if(itemStack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemStack;
    }
}
