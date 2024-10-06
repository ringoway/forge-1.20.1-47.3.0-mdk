package com.ringo.russian_delight.datagen;

import com.ringo.russian_delight.RussianDelight;
import com.ringo.russian_delight.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CLEAN_WATER_BOTTLE.get(), 4)
                .requires(Items.GLASS_BOTTLE, 4)
                .requires(ModItems.CLEAN_WATER_BUCKET.get())
                .unlockedBy(getHasName(ModItems.CLEAN_WATER_BUCKET.get()), has(ModItems.CLEAN_WATER_BUCKET.get()))
                .save(writer);

        oreSmelting(writer, List.of(Items.WATER_BUCKET), RecipeCategory.MISC, ModItems.CLEAN_WATER_BUCKET.get(), 0.1f, 200, "clean_water_bucket");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> finishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> recipeSerializer, List<ItemLike> itemLikeList, RecipeCategory recipeCategory, ItemLike result, float exp, int time, String group, String recipeName) {
        for (ItemLike itemlike : itemLikeList) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), recipeCategory, result, exp, time, recipeSerializer)
                    .group(group)
                    .unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(finishedRecipeConsumer, RussianDelight.MOD_ID + ":" + getItemName(result) + recipeName + "_" + getItemName(itemlike));
        }
    }
    protected static void oreSmelting(Consumer<FinishedRecipe> finishedRecipeConsumer, List<ItemLike> itemLikeList, RecipeCategory recipeCategory, ItemLike result, float exp, int time, String group) {
        oreCooking(finishedRecipeConsumer,
                RecipeSerializer.SMELTING_RECIPE, itemLikeList, recipeCategory, result, exp, time, group, "_from_smelting");
    }
}
