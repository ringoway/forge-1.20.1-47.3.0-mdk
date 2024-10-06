package com.ringo.russian_delight.fluid;

import com.ringo.russian_delight.RussianDelight;
import com.ringo.russian_delight.blocks.ModBlocks;
import com.ringo.russian_delight.items.ModItems;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, RussianDelight.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_CLEAN_WATER = FLUIDS.register("clean_water_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.CLEAN_WATER_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLOWING_CLEAN_WATER = FLUIDS.register("flowing_clean_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.CLEAN_WATER_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties CLEAN_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.CLEAN_WATER_FLUID_TYPE, SOURCE_CLEAN_WATER, FLOWING_CLEAN_WATER)
            .slopeFindDistance(1)
            .levelDecreasePerBlock(2)
            .block(ModBlocks.CLEAN_WATER_BLOCK)
            .bucket(ModItems.CLEAN_WATER_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
