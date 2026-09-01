package com.gizmo.twilightgourmet.datagen.data;

import com.gizmo.twilightgourmet.datagen.data.loot.BlockLootTables;
import com.gizmo.twilightgourmet.datagen.data.loot.EntityLootTables;
import com.gizmo.twilightgourmet.datagen.data.loot.MiscLootTables;
import com.gizmo.twilitgourmet.init.GourmetLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LootGenerator extends LootTableProvider {

	public LootGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, GourmetLootTables.allBuiltin(), List.of(
				new LootTableProvider.SubProviderEntry(BlockLootTables::new, LootContextParamSets.BLOCK),
				new LootTableProvider.SubProviderEntry(MiscLootTables::new, LootContextParamSets.CHEST),
				new LootTableProvider.SubProviderEntry(EntityLootTables::new, LootContextParamSets.ENTITY)
		), registries);
	}

	@Override
	protected void validate(WritableRegistry<LootTable> registry, ValidationContext context, ProblemReporter.Collector collector) {

	}
}
