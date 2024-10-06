package com.ringo.russian_delight.datagen;

import com.ringo.russian_delight.RussianDelight;
import com.ringo.russian_delight.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RussianDelight.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider p_256380_) {


        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.FERMENTATION_BARREL.get());


        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.FERMENTATION_BARREL.get());
    }
}

