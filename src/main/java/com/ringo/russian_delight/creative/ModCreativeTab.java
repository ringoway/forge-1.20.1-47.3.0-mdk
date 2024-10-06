package com.ringo.russian_delight.creative;

import com.ringo.russian_delight.RussianDelight;
import com.ringo.russian_delight.blocks.ModBlocks;
import com.ringo.russian_delight.items.ModFoods;
import com.ringo.russian_delight.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RussianDelight.MOD_ID);
    public static final RegistryObject<CreativeModeTab> RUSSIAN_DELIGHT_TAB = CREATIVE_MODE_TABS.register("russian_delight_tab",
            () -> CreativeModeTab
                    .builder()
                    .icon(() -> ModFoods.CLASSIC_SHCHI.get().getDefaultInstance())
                    .title(Component.translatable("creativetab.russian_delight_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModFoods.CLASSIC_SHCHI.get());
                        output.accept(ModFoods.SALT.get());
                        output.accept(ModItems.CLEAN_WATER_BUCKET.get());
                        output.accept(ModItems.CLEAN_WATER_BOTTLE.get());
                        output.accept(ModBlocks.FERMENTATION_BARREL.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
