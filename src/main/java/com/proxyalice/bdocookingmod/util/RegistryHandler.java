package com.proxyalice.bdocookingmod.util;

import com.proxyalice.bdocookingmod.BdoCooking;
import com.proxyalice.bdocookingmod.blocks.*;
import com.proxyalice.bdocookingmod.init.ModContainerTypes;
import com.proxyalice.bdocookingmod.init.ModTileEntityTypes;
import com.proxyalice.bdocookingmod.items.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, BdoCooking.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, BdoCooking.MOD_ID);

    public static final RegistryObject<Beer> BEER = ITEMS.register("beer", Beer::new);
    public static final RegistryObject<BalenosMeal> BALENOS_MEAL = ITEMS.register("balenos_meal", BalenosMeal::new);
    public static final RegistryObject<Cheese> CHEESE = ITEMS.register("cheese", Cheese::new);
    public static final RegistryObject<GrilledSausage> GRILLED_SAUSAGE = ITEMS.register("grilled_sausage", GrilledSausage::new);
    public static final RegistryObject<SuteTea> SUTE_TEA = ITEMS.register("sute_tea", SuteTea::new);
    public static final RegistryObject<TeaFineScent> TEA_FINE_SCENT = ITEMS.register("tea_fine_scent", TeaFineScent::new);

    public static void init()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        ModContainerTypes.CONTAINER_TYPES.register(modEventBus);

    }

    // Items
    public static final RegistryObject<Item> ONION = ITEMS.register("onion", FoodBase::new);
    public static final RegistryObject<Item> ANCIENT_CRON_SPICE = ITEMS.register("ancient_cron_spice", ItemBase::new);
    public static final RegistryObject<Item> BASE_SAUCE = ITEMS.register("base_sauce", FoodBase::new);
    public static final RegistryObject<Item> BUTTER = ITEMS.register("butter", FoodBase::new);
    public static final RegistryObject<Item> COOKING_WINE = ITEMS.register("cooking_wine", ItemBase::new);
    public static final RegistryObject<Item> DEEP_FRYING_OIL = ITEMS.register("deep_frying_oil", ItemBase::new);
    public static final RegistryObject<Item> OLIVE_OIL = ITEMS.register("olive_oil", ItemBase::new);
    public static final RegistryObject<Item> DOUGH = ITEMS.register("dough", ItemBase::new);
    public static final RegistryObject<Item> FIG = ITEMS.register("fig", FoodBase::new);
    public static final RegistryObject<Item> HOT_PEPPER = ITEMS.register("hot_pepper", FoodBase::new);
    public static final RegistryObject<Item> MINERAL_WATER = ITEMS.register("mineral_water", ItemBase::new);
    public static final RegistryObject<Item> PEPPER = ITEMS.register("pepper", FoodBase::new);
    public static final RegistryObject<Item> RED_SAUCE = ITEMS.register("red_sauce", FoodBase::new);
    public static final RegistryObject<Item> SALT = ITEMS.register("salt", ItemBase::new);

    //


    // Blocks
    public static final RegistryObject<Block> BASIC_COOKING_UTENSIL_BLOCK = BLOCKS.register("basic_cooking_utensil", BasicCookingUtensil::new);


    public static final RegistryObject<Block> ONION_CROP = BLOCKS.register("onion_crop", () -> new OnionCrop(Block.Properties.from(Blocks.BEETROOTS)));
    public static final RegistryObject<Block> FIG_CROP = BLOCKS.register("fig_crop", () -> new FigCrop(Block.Properties.from(Blocks.BEETROOTS)));
    public static final RegistryObject<Block> PEPPER_CROP = BLOCKS.register("pepper_crop", () -> new PepperCrop(Block.Properties.from(Blocks.BEETROOTS)));
    public static final RegistryObject<Block> HOT_PEPPER_CROP = BLOCKS.register("hot_pepper_crop", () -> new HotPepperCrop(Block.Properties.from(Blocks.BEETROOTS)));

    // Block Items
    public static final RegistryObject<Item> BASIC_COOKING_UTENSIL_BLOCK_ITEM = ITEMS.register("basic_cooking_utensil", () -> new BlockItemBase(BASIC_COOKING_UTENSIL_BLOCK.get()));

    public static final RegistryObject<Item> ONION_SEEDS = ITEMS.register("onion_seeds", () -> new BlockItemBase(ONION_CROP.get()));
    public static final RegistryObject<Item> FIG_SEEDS = ITEMS.register("fig_seeds", () -> new BlockItemBase(FIG_CROP.get()));
    public static final RegistryObject<Item> PEPPER_SEEDS = ITEMS.register("pepper_seeds", () -> new BlockItemBase(PEPPER_CROP.get()));
    public static final RegistryObject<Item> HOT_PEPPER_SEEDS = ITEMS.register("hot_pepper_seeds", () -> new BlockItemBase(HOT_PEPPER_CROP.get()));

}
