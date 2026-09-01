package com.gizmo.twilightgourmet.datagen.data.tags;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import twilightforest.init.TFBlocks;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends BlockTagsProvider {

	public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, @Nullable ExistingFileHelper helper) {
		super(output, registries, TwilitGourmet.MODID, helper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		//vanilla tags
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(
				GourmetBlocks.TWILIGHT_OAK_CABINET.get(),
				GourmetBlocks.CANOPY_CABINET.get(),
				GourmetBlocks.MANGROVE_CABINET.get(),
				GourmetBlocks.DARK_CABINET.get(),
				GourmetBlocks.TIME_CABINET.get(),
				GourmetBlocks.TRANSFORMATION_CABINET.get(),
				GourmetBlocks.MINING_CABINET.get(),
				GourmetBlocks.SORTING_CABINET.get());

		this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
				GourmetBlocks.TREE_TAPPER.get(),
				GourmetBlocks.SYRUP_CAULDRON.get());

		this.tag(ModTags.Blocks.MINEABLE_WITH_KNIFE).add(
				GourmetBlocks.MAZE_CHEESECAKE.get(),
				GourmetBlocks.CRAB_QUICHE.get());

		this.tag(BlockTags.REPLACEABLE).add(GourmetBlocks.BREADCRUMBS.get());
		this.tag(BlockTags.REPLACEABLE_BY_TREES).add(GourmetBlocks.BREADCRUMBS.get());

		//FD tags
		this.tag(ModTags.Blocks.FEASTS).add(
				GourmetBlocks.CRAB_CAKES.get(),
				GourmetBlocks.COOKED_CRAB.get(),
				GourmetBlocks.PANCAKE_STACK.get());

		this.tag(ModTags.Blocks.PIES).add(
				GourmetBlocks.MAZE_CHEESECAKE.get(),
				GourmetBlocks.CRAB_QUICHE.get());

		this.tag(ModTags.Blocks.CABINETS_WOODEN).add(
				GourmetBlocks.TWILIGHT_OAK_CABINET.get(),
				GourmetBlocks.CANOPY_CABINET.get(),
				GourmetBlocks.MANGROVE_CABINET.get(),
				GourmetBlocks.DARK_CABINET.get(),
				GourmetBlocks.TIME_CABINET.get(),
				GourmetBlocks.TRANSFORMATION_CABINET.get(),
				GourmetBlocks.MINING_CABINET.get(),
				GourmetBlocks.SORTING_CABINET.get());

		this.tag(ModTags.Blocks.MUSHROOM_COLONIES).add(GourmetBlocks.MUSHGLOOM_COLONY.get());

		this.tag(ModTags.Blocks.PLANTED_FROM_BELOW).add(
				TFBlocks.ROOT_STRAND.get(),
				TFBlocks.TROLLVIDR.get(),
				TFBlocks.UNRIPE_TROLLBER.get(),
				TFBlocks.TROLLBER.get());

	}
}
