package com.gizmo.twilightgourmet.datagen.data;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.gizmo.twilitgourmet.init.GourmetDataComponents;
import com.gizmo.twilitgourmet.init.GourmetItems;
import com.gizmo.twilitgourmet.init.GourmetSyrups;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import twilightforest.TwilightForestMod;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementGenerator extends AdvancementProvider {

	public AdvancementGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper helper) {
		super(output, registries, helper, List.of(new Generator()));
	}

	static class Generator implements AdvancementGenerator {

		@Override
		public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer, ExistingFileHelper helper) {
			var root = Advancement.Builder.advancement().display(GourmetBlocks.MUSHGLOOM_COLONY,
							Component.translatable("advancement.twilitgourmet.root.title"),
							Component.translatable("advancement.twilitgourmet.root.desc"),
							TwilightForestMod.prefix("block/stripped_canopy_log.png"),
							AdvancementType.TASK, false, false, false)
					.addCriterion("has_root_tf_advancement", this.advancementTrigger("twilightforest:root"))
					.addCriterion("has_root_fd_advancement", this.advancementTrigger("farmersdelight:main/root"))
					.requirements(AdvancementRequirements.Strategy.AND)
					.save(consumer, "twilitgourmet:root");

			Advancement.Builder.advancement().parent(root).display(GourmetBlocks.BREADCRUMBS,
							Component.translatable("advancement.twilitgourmet.follow_breadcrumbs.title"),
							Component.translatable("advancement.twilitgourmet.follow_breadcrumbs.desc"),
							null, AdvancementType.TASK, true, true, false)
					.addCriterion("step_on_me_daddy", EnterBlockTrigger.TriggerInstance.entersBlock(GourmetBlocks.BREADCRUMBS.get()))
					.save(consumer, "twilitgourmet:follow_breadcrumbs");

			Advancement.Builder.advancement().parent(root).display(new ItemStack(GourmetItems.SYRUP_BOTTLE, 1, DataComponentPatch.builder().set(GourmetDataComponents.SYRUP.get(), GourmetSyrups.SORTING).build()),
							Component.translatable("advancement.twilitgourmet.collect_sryup.title"),
							Component.translatable("advancement.twilitgourmet.collect_sryup.desc"),
							null, AdvancementType.TASK, true, true, false)
					.addCriterion("collect_syrup", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(GourmetBlocks.SYRUP_CAULDRON.get())), ItemPredicate.Builder.item().of(Items.GLASS_BOTTLE)))
					.save(consumer, "twilitgourmet:collect_syrup");

			Advancement.Builder.advancement().parent(root).display(new ItemStack(GourmetItems.SYRUP_BOTTLE, 1, DataComponentPatch.builder().set(GourmetDataComponents.SYRUP.get(), GourmetSyrups.SORTING).build()),
							Component.translatable("advancement.twilitgourmet.collect_sryup.title"),
							Component.translatable("advancement.twilitgourmet.collect_sryup.desc"),
							null, AdvancementType.TASK, true, true, false)
					.addCriterion("collect_syrup", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location().setBlock(BlockPredicate.Builder.block().of(GourmetBlocks.SYRUP_CAULDRON.get())), ItemPredicate.Builder.item().of(Items.GLASS_BOTTLE)))
					.save(consumer, "twilitgourmet:collect_syrup");
		}

		private Criterion<PlayerTrigger.TriggerInstance> advancementTrigger(String name) {
			return CriteriaTriggers.TICK.createCriterion(new PlayerTrigger.TriggerInstance(Optional.of(ContextAwarePredicate.create(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().subPredicate(PlayerPredicate.Builder.player().checkAdvancementDone(ResourceLocation.parse(name), true).build())).build()))));
		}
	}
}
