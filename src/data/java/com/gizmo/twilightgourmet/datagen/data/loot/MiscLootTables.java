package com.gizmo.twilightgourmet.datagen.data.loot;

import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.gizmo.twilitgourmet.init.GourmetItems;
import com.gizmo.twilitgourmet.init.GourmetLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;

import java.util.function.BiConsumer;

public record MiscLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
		output.accept(GourmetLootTables.AURORA_PALACE_INJECTION, LootTable.lootTable().withPool(LootPool.lootPool()
				.add(LootItem.lootTableItem(GourmetItems.ICE_KNIFE).setWeight(3))
				.add(LootItem.lootTableItem(GourmetItems.ICE_KNIFE).apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)))
				.add(EmptyLootItem.emptyItem().setWeight(15))));

		output.accept(GourmetLootTables.STRONGHOLD_INJECTION, LootTable.lootTable().withPool(LootPool.lootPool()
				.add(LootItem.lootTableItem(GourmetItems.KNIGHTMETAL_KNIFE).setWeight(3))
				.add(LootItem.lootTableItem(GourmetItems.KNIGHTMETAL_KNIFE).apply(EnchantRandomlyFunction.randomApplicableEnchantment(this.registries)))
				.add(EmptyLootItem.emptyItem().setWeight(15))));

		output.accept(GourmetLootTables.GIANT_APPLE, LootTable.lootTable().withPool(LootPool.lootPool()
				.add(LootItem.lootTableItem(GourmetBlocks.GIANT_APPLE))
				.when(BonusLevelTableCondition.bonusLevelFlatChance(this.registries.holderOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
	}
}
