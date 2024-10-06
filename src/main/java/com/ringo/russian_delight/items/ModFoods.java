package com.ringo.russian_delight.items;

import com.ringo.russian_delight.RussianDelight;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.tuple.Pair; // Использование Pair для хранения эффекта и вероятности
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModFoods {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RussianDelight.MOD_ID);

    // Пример использования универсального метода регистрации предметов еды
    public static final RegistryObject<Item> CLASSIC_SHCHI = registerFoodItem(
            "classic_shchi",7, 6f,
            List.of(
                    Pair.of(new MobEffectInstance(MobEffects.SATURATION, 1200, 1), 1f)
            ),"tooltip.russian_delight.classic_shchi.tooltip", ChatFormatting.BLUE,true, 1);

    public static final RegistryObject<Item> SALT = registerFoodItem(
            "salt", 0,0f,
            List.of(
                    Pair.of(new MobEffectInstance(MobEffects.BLINDNESS, 1200, 0), 1f),
                    Pair.of(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 6000, 1), 1f),
                    Pair.of(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 6000, 1), 1f),
                    Pair.of(new MobEffectInstance(MobEffects.CONFUSION, 1200, 0), 1f),
                    Pair.of(new MobEffectInstance(MobEffects.POISON, 6000, 0), 1f),
                    Pair.of(new MobEffectInstance(MobEffects.HUNGER, 2000, 0), 1f),
                    Pair.of(new MobEffectInstance(MobEffects.WEAKNESS, 36000, 1), 1f),
                    Pair.of(new MobEffectInstance(MobEffects.WITHER, 200, 0), 0.1f),
                    Pair.of(new MobEffectInstance(MobEffects.DARKNESS, 1200, 0), 1f)
            ),"tooltip.russian_delight.salt.tooltip", ChatFormatting.GRAY,false, 16);

    private static RegistryObject<Item> registerFoodItem(String name, int nutrition, float saturation,
                                                         List<Pair<MobEffectInstance, Float>> effects,
                                                         @Nullable String tooltipKey,
                                                         @Nullable ChatFormatting color,
                                                         boolean useFinishUsingItem, int stacking) {
        return ITEMS.register(name, () -> new Item(new Item.Properties()
                .food(createFoodProperties(nutrition, saturation, effects)).stacksTo(stacking)) {

            @Override
            public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
                // Проверяем, нужно ли добавлять описание
                if (tooltipKey != null && color != null) {
                    addTooltip(tooltip, tooltipKey, color);
                }
            }

            @Override
            public @NotNull ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
                // Определяем, нужно ли использовать finishUsingItem для возврата миски
                if (useFinishUsingItem) {
                    ItemStack itemstack = super.finishUsingItem(stack, world, entity);
                    return entity instanceof Player && ((Player) entity).getAbilities().instabuild ? itemstack : new ItemStack(Items.BOWL);
                } else {
                    return super.finishUsingItem(stack, world, entity);
                }
            }
        });
    }

    /**
     * Метод для создания FoodProperties с учетом указанных эффектов.
     */
    private static FoodProperties createFoodProperties(int nutrition, float saturation, List<Pair<MobEffectInstance, Float>> effects) {
        FoodProperties.Builder builder = new FoodProperties.Builder()
                .alwaysEat()
                .nutrition(nutrition)
                .saturationMod(saturation);

        for (Pair<MobEffectInstance, Float> effect : effects) {
            builder.effect(() -> effect.getLeft(), effect.getRight());
        }

        return builder.build();
    }

    /**
     * Вспомогательный метод для добавления описания к предметам.
     */
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