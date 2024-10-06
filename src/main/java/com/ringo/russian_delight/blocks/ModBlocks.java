package com.ringo.russian_delight.blocks;

import com.ringo.russian_delight.RussianDelight;
import com.ringo.russian_delight.blocks.custom.FermentationBarrelBlock;
import com.ringo.russian_delight.fluid.ModFluids;
import com.ringo.russian_delight.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RussianDelight.MOD_ID);
    public static final RegistryObject<LiquidBlock> CLEAN_WATER_BLOCK = BLOCKS.register("clean_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_CLEAN_WATER, BlockBehaviour.Properties
                    .copy(Blocks.WATER)
                    .noLootTable()));

    public static final RegistryObject<Block> FERMENTATION_BARREL = registerBlock("fermentation_barrel",
            () -> new FermentationBarrelBlock(BlockBehaviour.Properties
                    .copy(Blocks.BARREL)
                    .noOcclusion()));

    protected static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> registryObject = BLOCKS.register(name, block);
        registerBlockItem(name, registryObject);
        return registryObject;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
