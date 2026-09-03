package com.gizmo.twilightgourmet.datagen.data;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.gizmo.twilitgourmet.init.GourmetItems;
import com.gizmo.twilitgourmet.init.GourmetSyrups;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import twilightforest.data.tags.BlockTagGenerator;
import twilightforest.init.TFBlocks;

import java.util.concurrent.CompletableFuture;

public class DataMapGenerator extends DataMapProvider {

	public DataMapGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void gather(HolderLookup.Provider provider) {
		var composting = this.builder(NeoForgeDataMaps.COMPOSTABLES);

		composting.add(GourmetBlocks.BREADCRUMBS.asItem().builtInRegistryHolder(), new Compostable(0.3F), false);
		composting.add(GourmetBlocks.GIANT_APPLE.asItem().builtInRegistryHolder(), new Compostable(1.0F), false);
		composting.add(GourmetBlocks.MUSHGLOOM_COLONY.asItem().builtInRegistryHolder(), new Compostable(1.0F), false);
		composting.add(GourmetItems.CRAB_SHELL_FRAGMENT, new Compostable(0.3F), false);
		composting.add(GourmetItems.CRAB_LEG, new Compostable(0.5F), false);
		composting.add(GourmetItems.CRAB_CLAW, new Compostable(0.5F), false);
		composting.add(GourmetItems.APPLE_SLICE, new Compostable(0.65F), false);
		composting.add(GourmetItems.MAZE_WAFER_CRUST, new Compostable(0.65F), false);
		composting.add(GourmetItems.PANCAKE, new Compostable(0.65F), false);
		composting.add(GourmetItems.MAZE_CHEESECAKE_SLICE, new Compostable(0.85F), false);
		composting.add(GourmetItems.CRAB_QUICHE_SLICE, new Compostable(0.85F), false);
		composting.add(GourmetItems.MAZE_CHEESECAKE, new Compostable(1.0F), false);
		composting.add(GourmetItems.CRAB_QUICHE, new Compostable(1.0F), false);

		var syrups = this.builder(TwilitGourmet.SYRUP_DATA_MAP);
		syrups.add(BlockTags.OAK_LOGS, GourmetSyrups.OAK, false);
		syrups.add(TFBlocks.GIANT_LOG, GourmetSyrups.OAK, false);
		syrups.add(BlockTags.SPRUCE_LOGS, GourmetSyrups.SPRUCE, false);
		syrups.add(BlockTags.BIRCH_LOGS, GourmetSyrups.BIRCH, false);
		syrups.add(BlockTags.JUNGLE_LOGS, GourmetSyrups.JUNGLE, false);
		syrups.add(BlockTags.ACACIA_LOGS, GourmetSyrups.ACACIA, false);
		syrups.add(BlockTags.DARK_OAK_LOGS, GourmetSyrups.DARK_OAK, false);
		syrups.add(BlockTags.CRIMSON_STEMS, GourmetSyrups.CRIMSON, false);
		syrups.add(BlockTags.WARPED_STEMS, GourmetSyrups.WARPED, false);
		syrups.add(BlockTags.MANGROVE_LOGS, GourmetSyrups.VANGROVE, false);
		syrups.add(BlockTags.CHERRY_LOGS, GourmetSyrups.CHERRY, false);

		syrups.add(BlockTagGenerator.TWILIGHT_OAK_LOGS, GourmetSyrups.TWILIGHT_OAK, false);
		syrups.add(BlockTagGenerator.CANOPY_LOGS, GourmetSyrups.CANOPY, false);
		syrups.add(BlockTagGenerator.MANGROVE_LOGS, GourmetSyrups.MANGROVE, false);
		syrups.add(BlockTagGenerator.DARKWOOD_LOGS, GourmetSyrups.DARKWOOD, false);
		syrups.add(BlockTagGenerator.TIME_LOGS, GourmetSyrups.TIME, false);
		syrups.add(BlockTagGenerator.TRANSFORMATION_LOGS, GourmetSyrups.TRANSFORMATION, false);
		syrups.add(BlockTagGenerator.MINING_LOGS, GourmetSyrups.MINING, false);
		syrups.add(BlockTagGenerator.SORTING_LOGS, GourmetSyrups.SORTING, false);
	}
}
