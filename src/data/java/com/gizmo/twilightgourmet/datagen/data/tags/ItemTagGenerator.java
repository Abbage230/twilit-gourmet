package com.gizmo.twilightgourmet.datagen.data.tags;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.gizmo.twilitgourmet.init.GourmetItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends ItemTagsProvider {

	public ItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper helper) {
		super(output, registries, blockTags, TwilitGourmet.MODID, helper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(TwilitGourmet.COOKS_CUTTING_BOARD_ITEMS).add(
				GourmetItems.FIERY_KNIFE.get(),
				TFItems.FIERY_SWORD.get(),
				TFItems.FIERY_PICKAXE.get()
		);

		this.tag(ItemTags.MEAT).add(
				GourmetItems.RAW_BOARKCHOP.get(),
				GourmetItems.COOKED_BOARKCHOP.get(),
				GourmetItems.MINCED_MEEF.get(),
				GourmetItems.MEEF_PATTY.get(),
				GourmetItems.GROUND_VENISON.get(),
				GourmetItems.VENISON_PATTY.get()
		);

		this.tag(ModTags.Items.SNACKS).add(
				GourmetItems.VENISON_KEBAB.get(),
				GourmetItems.VENISON_SLIDER.get(),
				GourmetItems.FIDDLEHEAD_RISOTTO.get()
		);

		this.tag(ModTags.Items.MEALS).add(
				GourmetItems.VENISON_AND_VEGGIES.get(),
				GourmetItems.MUSHGLOOM_STEW.get(),
				GourmetItems.CRAB_CAKE.get(),
				GourmetItems.GOULASH.get(),
				GourmetItems.CRAB_QUICHE_SLICE.get()
		);

		this.tag(ModTags.Items.DRINKS).add(GourmetItems.BERRY_SMOOTHIE.get(), GourmetItems.SYRUP_BOTTLE.get());

		this.tag(ModTags.Items.SWEETS).add(GourmetItems.MAZE_CHEESECAKE_SLICE.get());

		this.tag(ModTags.Items.PIES).add(
				GourmetItems.MAZE_CHEESECAKE.get(),
				GourmetItems.CRAB_QUICHE.get()
		);

		this.tag(ModTags.Items.KNIVES).add(
				GourmetItems.IRONWOOD_KNIFE.get(),
				GourmetItems.STEELEAF_KNIFE.get(),
				GourmetItems.KNIGHTMETAL_KNIFE.get(),
				GourmetItems.FIERY_KNIFE.get(),
				GourmetItems.ICE_KNIFE.get()
		);

		this.tag(Tags.Items.FOODS).add(GourmetItems.MAZE_WAFER_CRUST.get());
		this.tag(Tags.Items.FOODS_FRUIT).add(GourmetItems.APPLE_SLICE.get());
		this.tag(Tags.Items.FOODS_RAW_FISH).add(GourmetItems.CRAB_MEAT.get());
		this.tag(Tags.Items.FOODS_COOKED_FISH).add(GourmetItems.COOKED_CRAB_MEAT.get());
		this.tag(Tags.Items.FOODS_RAW_MEAT).add(GourmetItems.RAW_BOARKCHOP.get());
		this.tag(Tags.Items.FOODS_COOKED_MEAT).add(GourmetItems.COOKED_BOARKCHOP.get());
		this.tag(Tags.Items.FOODS_FOOD_POISONING).add(GourmetItems.MAZE_WAFER_CRUST.get());
		this.tag(Tags.Items.FOODS_EDIBLE_WHEN_PLACED).add(
				GourmetItems.MAZE_CHEESECAKE.get(),
				GourmetItems.CRAB_QUICHE.get()
		);
		this.tag(Tags.Items.FOODS_SOUP).add(GourmetItems.MUSHGLOOM_STEW.get());
		this.tag(Tags.Items.FOODS_PIE).add(GourmetItems.MAZE_CHEESECAKE_SLICE.get());

		this.tag(CommonTags.Items.FOODS_SAFE_RAW_FISH).add(GourmetItems.CRAB_MEAT.get());
		this.tag(CommonTags.Items.FOODS_RAW_BEEF).add(GourmetItems.MINCED_MEEF.get());
		this.tag(CommonTags.Items.FOODS_COOKED_BEEF).add(GourmetItems.MEEF_PATTY.get());
		this.tag(CommonTags.Items.FOODS_RAW_PORK).add(GourmetItems.RAW_BOARKCHOP.get());
		this.tag(CommonTags.Items.FOODS_COOKED_PORK).add(GourmetItems.COOKED_BOARKCHOP.get());
		this.tag(CommonTags.Items.TOOLS_KNIFE).add(
				GourmetItems.IRONWOOD_KNIFE.get(),
				GourmetItems.STEELEAF_KNIFE.get(),
				GourmetItems.KNIGHTMETAL_KNIFE.get(),
				GourmetItems.FIERY_KNIFE.get(),
				GourmetItems.ICE_KNIFE.get()
		);

		this.copy(ModTags.Blocks.CABINETS_WOODEN, ModTags.Items.CABINETS_WOODEN);
		this.copy(ModTags.Blocks.MUSHROOM_COLONIES, ModTags.Items.MUSHROOM_COLONIES);

		this.tag(ModTags.Items.FLAT_ON_CUTTING_BOARD)
				.add(TFBlocks.CICADA.asItem())
				.add(TFBlocks.FIREFLY.asItem())
				.add(TFBlocks.MOONWORM.asItem());

		this.tag(twilightforest.data.tags.ItemTagGenerator.RAVEN_TEMPT_ITEMS).add(GourmetBlocks.BREADCRUMBS.asItem());
		this.tag(twilightforest.data.tags.ItemTagGenerator.TINY_BIRD_TEMPT_ITEMS).add(GourmetBlocks.BREADCRUMBS.asItem());
	}
}
