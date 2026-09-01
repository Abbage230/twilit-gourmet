package com.gizmo.twilightgourmet.datagen.assets;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.gizmo.twilitgourmet.init.GourmetItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangGenerator extends LanguageProvider {
	public LangGenerator(PackOutput output) {
		super(output, TwilitGourmet.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.addBlock(GourmetBlocks.TWILIGHT_OAK_CABINET, "Twilight Oak Cabinet");
		this.addBlock(GourmetBlocks.CANOPY_CABINET, "Canopy Cabinet");
		this.addBlock(GourmetBlocks.MANGROVE_CABINET, "Mangrove Cabinet");
		this.addBlock(GourmetBlocks.DARK_CABINET, "Dark Cabinet");
		this.addBlock(GourmetBlocks.TIME_CABINET, "Time Cabinet");
		this.addBlock(GourmetBlocks.TRANSFORMATION_CABINET, "Transformation Cabinet");
		this.addBlock(GourmetBlocks.MINING_CABINET, "Mining Cabinet");
		this.addBlock(GourmetBlocks.SORTING_CABINET, "Sorting Cabinet");
		this.addBlock(GourmetBlocks.GIANT_APPLE, "Giant Apple");
		this.addBlock(GourmetBlocks.SYRUP_CAULDRON, "Syrup Cauldron");
		this.addBlock(GourmetBlocks.TREE_TAPPER, "Tree Tapper");
		this.addBlock(GourmetBlocks.MUSHGLOOM_COLONY, "Mushgloom Colony");
		this.addBlock(GourmetBlocks.PANCAKE_STACK, "Pancake Stack");
		this.addBlock(GourmetBlocks.CRAB_CAKES, "Crab Cakes");
		this.addBlock(GourmetBlocks.CRAB_QUICHE, "Crab Quiche");
		this.addBlock(GourmetBlocks.COOKED_CRAB, "Cooked Crab");
		this.add("block.twilitgourmet.cooked_crab.use_knife", "You need to use a knife to cut this up");
		this.addBlock(GourmetBlocks.MAZE_CHEESECAKE, "Maze Cheesecake");
		this.addBlock(GourmetBlocks.BREADCRUMBS, "Breadcrumbs");

		this.addItem(GourmetItems.APPLE_SLICE, "Apple Slice");
		this.addItem(GourmetItems.IRONWOOD_KNIFE, "Ironwood Knife");
		this.addItem(GourmetItems.STEELEAF_KNIFE, "Steeleaf Knife");
		this.addItem(GourmetItems.KNIGHTMETAL_KNIFE, "Knightmetal Knife");
		this.add("item.twilitgourmet.knightmetal_knife.desc", "Extra damage when hitting targets from behind");
		this.addItem(GourmetItems.FIERY_KNIFE, "Fiery Knife");
		this.add("item.twilitgourmet.fiery_knife.desc", "Burns targets");
		this.addItem(GourmetItems.ICE_KNIFE, "Ice Knife");

		this.addItem(GourmetItems.PANCAKE, "Pancake");
		this.addItem(GourmetItems.GROUND_VENISON, "Ground Venison");
		this.addItem(GourmetItems.VENISON_PATTY, "Venison Patty");
		this.addItem(GourmetItems.MINCED_MEEF, "Minced Meef");
		this.addItem(GourmetItems.MEEF_PATTY, "Meef Patty");
		this.addItem(GourmetItems.RAW_BOARKCHOP, "Raw Boarkchop");
		this.addItem(GourmetItems.COOKED_BOARKCHOP, "Cooked Boarkchop");
		this.addItem(GourmetItems.CRAB_MEAT, "Crab Meat");
		this.addItem(GourmetItems.COOKED_CRAB_MEAT, "Cooked Crab Meat");
		this.addItem(GourmetItems.CRAB_SHELL_FRAGMENT, "Crab Shell Fragment");
		this.addItem(GourmetItems.CRAB_LEG, "Crab Leg");
		this.addItem(GourmetItems.CRAB_CLAW, "Crab Claw");
		this.addItem(GourmetItems.SHELL_HELMET, "Crab Helmet");
		this.addItem(GourmetItems.WHOLE_CRAB, "Whole Crab");

		this.addItem(GourmetItems.MAZE_WAFER_CRUST, "Maze Wafer Crust");
		this.addItem(GourmetItems.CRAB_CAKE, "Crab Cake");
		this.addItem(GourmetItems.CRAB_QUICHE_SLICE, "Slice of Crab Quiche");
		this.addItem(GourmetItems.MAZE_CHEESECAKE_SLICE, "Slice of Maze Cheesecake");
		this.addItem(GourmetItems.VENISON_KEBAB, "Venison Kebab");
		this.addItem(GourmetItems.VENISON_SLIDER, "Venison Slider");
		this.addItem(GourmetItems.VENISON_AND_VEGGIES, "Venison and Root Veggies");
		this.addItem(GourmetItems.GOULASH, "Goulash");
		this.addItem(GourmetItems.MUSHGLOOM_STEW, "Mushgloom Stew");
		this.addItem(GourmetItems.FIDDLEHEAD_RISOTTO, "Fiddlehead Risotto");
		this.addItem(GourmetItems.BERRY_SMOOTHIE, "Berry Smoothie");

		this.addItem(GourmetItems.SYRUP_BOTTLE, "Syrup Bottle");
		this.add("item.twilitgourmet.syrup_bottle.type", "Acquired from %s");
		this.add("itemGroup.twilitgourmet", "Twilit Gourmet");

		this.add("syrup.minecraft.oak", "Oak Tree");
		this.add("syrup.minecraft.spruce", "Spruce Tree");
		this.add("syrup.minecraft.birch", "Birch Tree");
		this.add("syrup.minecraft.jungle", "Jungle Tree");
		this.add("syrup.minecraft.acacia", "Acacia Tree");
		this.add("syrup.minecraft.dark_oak", "Dark Oak Tree");
		this.add("syrup.minecraft.mangrove", "Mangrove Tree");
		this.add("syrup.minecraft.cherry", "Cherry Tree");
		this.add("syrup.minecraft.crimson", "Crimson Fungus");
		this.add("syrup.minecraft.warped", "Warped Fungus");

		this.add("syrup.twilightforest.twilight_oak", "Twilight Oak Tree");
		this.add("syrup.twilightforest.canopy", "Canopy Tree");
		this.add("syrup.twilightforest.mangrove", "Mangrove Tree");
		this.add("syrup.twilightforest.darkwood", "Darkwood Tree");
		this.add("syrup.twilightforest.time", "Tree of Time");
		this.add("syrup.twilightforest.transformation", "Tree of Transformation");
		this.add("syrup.twilightforest.mining", "Miner's Tree");
		this.add("syrup.twilightforest.sorting", "Sortingwood Tree");

		this.add("subtitles.twilitgourmet.item.crab_shell.crack", "Crab shell cracks");
	}
}
