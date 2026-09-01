package com.gizmo.twilightgourmet.datagen.data.loot;

import com.gizmo.twilitgourmet.init.GourmetItems;
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import twilightforest.init.TFEntities;
import twilightforest.init.TFItems;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.stream.Stream;

public class EntityLootTables extends EntityLootSubProvider {

	public EntityLootTables(HolderLookup.Provider registries) {
		super(FeatureFlags.REGISTRY.allFlags(), registries);
	}

	@Override
	public void generate() {
		this.add(TFEntities.HELMET_CRAB.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(TFItems.ARMOR_SHARD.get())
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						//try rolling whole crab if killed with knife
						.add(LootItem.lootTableItem(GourmetItems.WHOLE_CRAB)
								.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.25F, 0.1F))
								//if no crab, try rolling extra fragments or meat
								.otherwise(AlternativesEntry.alternatives(
										LootItem.lootTableItem(GourmetItems.CRAB_SHELL_FRAGMENT)
												.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
												.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))),
										LootItem.lootTableItem(GourmetItems.CRAB_MEAT)
												.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
												.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
												.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))))
						.when(LootItemKilledByPlayerCondition.killedByPlayer().and(this.killedWithKnife())))
				//only roll this if a knife kill didnt happen. This is just the fragment/meat table above but with less drops
				.withPool(LootPool.lootPool()
						.add(AlternativesEntry.alternatives(
										LootItem.lootTableItem(GourmetItems.CRAB_SHELL_FRAGMENT)
												.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.5F, 0.1F)),
										LootItem.lootTableItem(GourmetItems.CRAB_MEAT)
												.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
												.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
												.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))
								.when(this.killedWithKnife().invert()))));

		this.add(TFEntities.BOAR.get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(GourmetItems.RAW_BOARKCHOP)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
								.apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot()))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))));
	}

	private LootItemCondition.Builder killedWithKnife() {
		return LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity()
				.equipment(EntityEquipmentPredicate.Builder.equipment().mainhand(ItemPredicate.Builder.item().of(ModTags.Items.KNIVES)).build()).build());
	}

	@Override
	protected Stream<EntityType<?>> getKnownEntityTypes() {
		return Stream.of(TFEntities.BOAR.get(), TFEntities.HELMET_CRAB.get());
	}
}
