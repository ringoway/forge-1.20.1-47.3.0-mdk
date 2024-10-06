package com.ringo.russian_delight.fluid;

import com.ringo.russian_delight.RussianDelight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3f;

public class ModFluidTypes {
    public static final ResourceLocation CLEAN_WATER_STILL_RL = new ResourceLocation(RussianDelight.MOD_ID, "block/clean_water_still");
    public static final ResourceLocation CLEAN_WATER_FLOWING_RL = new ResourceLocation(RussianDelight.MOD_ID, "block/clean_water_flow");
    public static final ResourceLocation CLEAN_WATER_OVERLAY_RL = new ResourceLocation(RussianDelight.MOD_ID, "misc/undercleanwater");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, RussianDelight.MOD_ID);


    public static final RegistryObject<FluidType> CLEAN_WATER_FLUID_TYPE = registerFluidType("clean_water_fluid",
            CLEAN_WATER_STILL_RL, CLEAN_WATER_FLOWING_RL, CLEAN_WATER_OVERLAY_RL, //текстуры
            0xFF4097E3, new Vector3f(64f / 255f, 151f / 255f, 227f / 255f),
            1f, 6f, //скорость в жидкости, начало тумана, конец тумана.
            FluidType.Properties.create()
                    .fallDistanceModifier(0F)
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(true)
    );


    public static RegistryObject<FluidType> registerFluidType(String name, ResourceLocation stillTexture, ResourceLocation flowingTexture,
                                                              ResourceLocation overlayTexture, int tintColor, Vector3f fogColor, float fogStart, float fogEnd,
                                                              FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(
                stillTexture, flowingTexture, overlayTexture, tintColor, fogColor, fogStart, fogEnd, properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}