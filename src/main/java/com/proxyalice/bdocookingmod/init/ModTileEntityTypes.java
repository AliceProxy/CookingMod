package com.proxyalice.bdocookingmod.init;

import com.proxyalice.bdocookingmod.BdoCooking;
import com.proxyalice.bdocookingmod.tileentity.CookingTileEntity;
import com.proxyalice.bdocookingmod.util.RegistryHandler;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(
            ForgeRegistries.TILE_ENTITIES,
            BdoCooking.MOD_ID
    );

    public static final RegistryObject<TileEntityType<CookingTileEntity>> COOKING_CHEST = TILE_ENTITY_TYPES.register(
            "basic_cooking_utensil",
            () -> TileEntityType.Builder.create(CookingTileEntity::new, RegistryHandler.BASIC_COOKING_UTENSIL_BLOCK.get()).build(null)
    );
}
