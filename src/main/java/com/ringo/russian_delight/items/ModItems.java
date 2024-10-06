package com.ringo.russian_delight.items;

import com.ringo.russian_delight.RussianDelight;
import com.ringo.russian_delight.fluid.ModFluids;
import com.ringo.russian_delight.items.custom.CleanWaterBottleItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RussianDelight.MOD_ID);

    public static final RegistryObject<Item> CLEAN_WATER_BUCKET = ITEMS.register("clean_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_CLEAN_WATER,
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> CLEAN_WATER_BOTTLE = ITEMS.register("clean_water_bottle",
            () -> new CleanWaterBottleItem(new Item.Properties().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));


    private static RegistryObject<Item> registerItemWithTooltip(String name, String tooltipKey, ChatFormatting color) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()) {
            @Override
            public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                addTooltip(tooltip, tooltipKey, color);
            }
        });
    }

    private static RegistryObject<Item> registerSimpleItem(String name) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()));
    }

    private static void addTooltip(List<Component> tooltip, String tooltipKey, ChatFormatting color) {
        String description = Component.translatable(tooltipKey).getString();

        String[] descriptionLines = description.split("\n");
        for (String line : descriptionLines) {
            tooltip.add(Component.literal(line).withStyle(color));
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
