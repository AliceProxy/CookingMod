package com.proxyalice.bdocookingmod.init;

import com.proxyalice.bdocookingmod.BdoCooking;
import com.proxyalice.bdocookingmod.container.CookingContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, BdoCooking.MOD_ID);

    public static final RegistryObject<ContainerType<CookingContainer>> COOKING_CHEST = CONTAINER_TYPES.register(
            "basic_cooking_utensil",
            () -> IForgeContainerType.create(CookingContainer::new)
    );
}
