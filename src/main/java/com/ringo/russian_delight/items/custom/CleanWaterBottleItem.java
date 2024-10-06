package com.ringo.russian_delight.items.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

public class CleanWaterBottleItem extends Item {
    public CleanWaterBottleItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
        super.finishUsingItem(stack, world, entity);

        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        }

        // Если это игрок и не в режиме креатива, возвращаем пустую бутылку
        if (entity instanceof Player player && !player.getAbilities().instabuild) {
            ItemStack emptyBottle = new ItemStack(Items.GLASS_BOTTLE);

            if (!player.getInventory().add(emptyBottle)) {
                player.drop(emptyBottle, false);
            }
        }

        // Уменьшаем количество в стеке
        stack.shrink(1);

        return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 32;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        return ItemUtils.startUsingInstantly(world, player, hand);
    }
}
