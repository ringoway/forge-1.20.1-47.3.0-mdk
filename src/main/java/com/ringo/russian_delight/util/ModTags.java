package com.ringo.russian_delight.util;

import com.ringo.russian_delight.RussianDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(RussianDelight.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> BEEF_OR_CHICKEN = tag("beef_or_chicken");
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(RussianDelight.MOD_ID, name));
        }
    }

    public static class ModBiomeTags {
        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(RussianDelight.MOD_ID, name));
        }

    }
}
