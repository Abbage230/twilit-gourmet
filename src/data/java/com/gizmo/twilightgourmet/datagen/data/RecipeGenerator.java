package com.gizmo.twilightgourmet.datagen.data;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.gizmo.twilitgourmet.init.GourmetItems;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import twilightforest.data.tags.ItemTagGenerator;
import twilightforest.init.TFBlocks;
import twilightforest.init.TFItems;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.data.recipe.CookingRecipes;
import vectorwing.farmersdelight.data.recipe.CuttingRecipes;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends RecipeProvider {

	public RecipeGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void buildRecipes(RecipeOutput output, HolderLookup.Provider registries) {
		foodSmeltingRecipes("cooked_ground_venison", GourmetItems.GROUND_VENISON, GourmetItems.VENISON_PATTY, 0.35F, output);
		foodSmeltingRecipes("meef_patty", GourmetItems.MINCED_MEEF, GourmetItems.MEEF_PATTY, 0.35F, output);
		foodSmeltingRecipes("crab_meat", GourmetItems.CRAB_MEAT, GourmetItems.COOKED_CRAB_MEAT, 0.35F, output);
		foodSmeltingRecipes("cooked_boarkchop", GourmetItems.RAW_BOARKCHOP, GourmetItems.COOKED_BOARKCHOP, 0.35F, output);

		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFItems.RAW_MEEF), CuttingRecipes.KNIVES, GourmetItems.MINCED_MEEF, 2).save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFItems.RAW_VENISON), CuttingRecipes.KNIVES, GourmetItems.GROUND_VENISON, 2).save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(GourmetItems.WHOLE_CRAB), CuttingRecipes.KNIVES, GourmetItems.CRAB_MEAT, 4)
				.addResultWithChance(GourmetItems.CRAB_MEAT, 0.5F, 2)
				.addResultWithChance(GourmetItems.CRAB_SHELL_FRAGMENT, 0.75F, 2)
				.save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(GourmetItems.RAW_BOARKCHOP), CuttingRecipes.KNIVES, ModItems.BACON.get(), 2).save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFItems.STALE_BREAD), CuttingRecipes.KNIVES, GourmetBlocks.BREADCRUMBS, 3).save(output);

		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(GourmetItems.CRAB_QUICHE), CuttingRecipes.KNIVES, GourmetItems.CRAB_QUICHE_SLICE, 4).save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(GourmetItems.MAZE_CHEESECAKE), CuttingRecipes.KNIVES, GourmetItems.MAZE_CHEESECAKE_SLICE, 4).save(output);

		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(GourmetBlocks.MUSHGLOOM_COLONY), CuttingRecipes.KNIVES, TFBlocks.MUSHGLOOM, 5).save(output);

		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFBlocks.FIREFLY), CuttingRecipes.KNIVES, Items.GLOWSTONE).save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFBlocks.CICADA), CuttingRecipes.KNIVES, Items.GRAY_DYE).save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFBlocks.MOONWORM), CuttingRecipes.KNIVES, Items.LIME_DYE).save(output);

		stripLogForBark(output, TFBlocks.TWILIGHT_OAK_LOG, TFBlocks.STRIPPED_TWILIGHT_OAK_LOG);
		stripLogForBark(output, TFBlocks.TWILIGHT_OAK_WOOD, TFBlocks.STRIPPED_TWILIGHT_OAK_WOOD);
		stripLogForBark(output, TFBlocks.CANOPY_LOG, TFBlocks.STRIPPED_CANOPY_LOG);
		stripLogForBark(output, TFBlocks.CANOPY_WOOD, TFBlocks.STRIPPED_CANOPY_WOOD);
		stripLogForBark(output, TFBlocks.MANGROVE_LOG, TFBlocks.STRIPPED_MANGROVE_LOG);
		stripLogForBark(output, TFBlocks.MANGROVE_WOOD, TFBlocks.STRIPPED_MANGROVE_WOOD);
		stripLogForBark(output, TFBlocks.DARK_LOG, TFBlocks.STRIPPED_DARK_LOG);
		stripLogForBark(output, TFBlocks.DARK_WOOD, TFBlocks.STRIPPED_DARK_WOOD);
		stripLogForBark(output, TFBlocks.TIME_LOG, TFBlocks.STRIPPED_TIME_LOG);
		stripLogForBark(output, TFBlocks.TIME_WOOD, TFBlocks.STRIPPED_TIME_WOOD);
		stripLogForBark(output, TFBlocks.TRANSFORMATION_LOG, TFBlocks.STRIPPED_TRANSFORMATION_LOG);
		stripLogForBark(output, TFBlocks.TRANSFORMATION_WOOD, TFBlocks.STRIPPED_TRANSFORMATION_WOOD);
		stripLogForBark(output, TFBlocks.MINING_LOG, TFBlocks.STRIPPED_MINING_LOG);
		stripLogForBark(output, TFBlocks.MINING_WOOD, TFBlocks.STRIPPED_MINING_WOOD);
		stripLogForBark(output, TFBlocks.SORTING_LOG, TFBlocks.STRIPPED_SORTING_LOG);
		stripLogForBark(output, TFBlocks.SORTING_WOOD, TFBlocks.STRIPPED_SORTING_WOOD);

		salvagePlankFromFurniture(output, "twilight_oak", TFBlocks.TWILIGHT_OAK_PLANKS, TFBlocks.TWILIGHT_OAK_DOOR, TFBlocks.TWILIGHT_OAK_TRAPDOOR, TFBlocks.TWILIGHT_OAK_SIGN, TFBlocks.TWILIGHT_OAK_HANGING_SIGN, TFBlocks.TWILIGHT_OAK_FENCE, TFBlocks.TWILIGHT_OAK_GATE, TFBlocks.TWILIGHT_OAK_PLATE, TFBlocks.TWILIGHT_OAK_BUTTON, TFItems.TWILIGHT_OAK_BOAT, GourmetBlocks.TWILIGHT_OAK_CABINET);
		salvagePlankFromFurniture(output, "canopy", TFBlocks.CANOPY_PLANKS, TFBlocks.CANOPY_DOOR, TFBlocks.CANOPY_TRAPDOOR, TFBlocks.CANOPY_SIGN, TFBlocks.CANOPY_HANGING_SIGN, TFBlocks.CANOPY_FENCE, TFBlocks.CANOPY_GATE, TFBlocks.CANOPY_PLATE, TFBlocks.CANOPY_BUTTON, TFItems.CANOPY_BOAT, GourmetBlocks.CANOPY_CABINET);
		salvagePlankFromFurniture(output, "mangrove", TFBlocks.MANGROVE_PLANKS, TFBlocks.MANGROVE_DOOR, TFBlocks.MANGROVE_TRAPDOOR, TFBlocks.MANGROVE_SIGN, TFBlocks.MANGROVE_HANGING_SIGN, TFBlocks.MANGROVE_FENCE, TFBlocks.MANGROVE_GATE, TFBlocks.MANGROVE_PLATE, TFBlocks.MANGROVE_BUTTON, TFItems.MANGROVE_BOAT, GourmetBlocks.MANGROVE_CABINET);
		salvagePlankFromFurniture(output, "dark", TFBlocks.DARK_PLANKS, TFBlocks.DARK_DOOR, TFBlocks.DARK_TRAPDOOR, TFBlocks.DARK_SIGN, TFBlocks.DARK_HANGING_SIGN, TFBlocks.DARK_FENCE, TFBlocks.DARK_GATE, TFBlocks.DARK_PLATE, TFBlocks.DARK_BUTTON, TFItems.DARK_BOAT, GourmetBlocks.DARK_CABINET);
		salvagePlankFromFurniture(output, "time", TFBlocks.TIME_PLANKS, TFBlocks.TIME_DOOR, TFBlocks.TIME_TRAPDOOR, TFBlocks.TIME_SIGN, TFBlocks.TIME_HANGING_SIGN, TFBlocks.TIME_FENCE, TFBlocks.TIME_GATE, TFBlocks.TIME_PLATE, TFBlocks.TIME_BUTTON, TFItems.TIME_BOAT, GourmetBlocks.TIME_CABINET);
		salvagePlankFromFurniture(output, "transformation", TFBlocks.TRANSFORMATION_PLANKS, TFBlocks.TRANSFORMATION_DOOR, TFBlocks.TRANSFORMATION_TRAPDOOR, TFBlocks.TRANSFORMATION_SIGN, TFBlocks.TRANSFORMATION_HANGING_SIGN, TFBlocks.TRANSFORMATION_FENCE, TFBlocks.TRANSFORMATION_GATE, TFBlocks.TRANSFORMATION_PLATE, TFBlocks.TRANSFORMATION_BUTTON, TFItems.TRANSFORMATION_BOAT, GourmetBlocks.TRANSFORMATION_CABINET);
		salvagePlankFromFurniture(output, "mining", TFBlocks.MINING_PLANKS, TFBlocks.MINING_DOOR, TFBlocks.MINING_TRAPDOOR, TFBlocks.MINING_SIGN, TFBlocks.MINING_HANGING_SIGN, TFBlocks.MINING_FENCE, TFBlocks.MINING_GATE, TFBlocks.MINING_PLATE, TFBlocks.MINING_BUTTON, TFItems.MINING_BOAT, GourmetBlocks.MINING_CABINET);
		salvagePlankFromFurniture(output, "sorting", TFBlocks.SORTING_PLANKS, TFBlocks.SORTING_DOOR, TFBlocks.SORTING_TRAPDOOR, TFBlocks.SORTING_SIGN, TFBlocks.SORTING_HANGING_SIGN, TFBlocks.SORTING_FENCE, TFBlocks.SORTING_GATE, TFBlocks.SORTING_PLATE, TFBlocks.SORTING_BUTTON, TFItems.SORTING_BOAT, GourmetBlocks.SORTING_CABINET);

		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFItems.TWILIGHT_OAK_CHEST_BOAT), CuttingRecipes.HOES, TFItems.TWILIGHT_OAK_BOAT).addResult(Items.CHEST).salvaging().save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFItems.CANOPY_CHEST_BOAT), CuttingRecipes.HOES, TFItems.CANOPY_BOAT).addResult(Items.CHEST).salvaging().save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFItems.MANGROVE_CHEST_BOAT), CuttingRecipes.HOES, TFItems.MANGROVE_BOAT).addResult(Items.CHEST).salvaging().save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFItems.DARK_CHEST_BOAT), CuttingRecipes.HOES, TFItems.DARK_BOAT).addResult(Items.CHEST).salvaging().save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFItems.TIME_CHEST_BOAT), CuttingRecipes.HOES, TFItems.TIME_BOAT).addResult(Items.CHEST).salvaging().save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFItems.TRANSFORMATION_CHEST_BOAT), CuttingRecipes.HOES, TFItems.TRANSFORMATION_BOAT).addResult(Items.CHEST).salvaging().save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFItems.MINING_CHEST_BOAT), CuttingRecipes.HOES, TFItems.MINING_BOAT).addResult(Items.CHEST).salvaging().save(output);
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(TFItems.SORTING_CHEST_BOAT), CuttingRecipes.HOES, TFItems.SORTING_BOAT).addResult(Items.CHEST).salvaging().save(output);

		CookingPotRecipeBuilder.cookingPotRecipe(TFItems.MAZE_WAFER, 2, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP)
				.addIngredient(GourmetBlocks.BREADCRUMBS)
				.addIngredient(GourmetItems.SYRUP_BOTTLE)
				.addIngredient(Tags.Items.DRINKS_MILK)
				.unlockedByAnyIngredient(TFItems.MAZE_WAFER)
				.setRecipeBookTab(CookingPotRecipeBookTab.MISC)
				.save(output);

		CookingPotRecipeBuilder.cookingPotRecipe(TFItems.SHIKA_SENBEI, 2, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP)
				.addIngredient(ModItems.RICE.get(), 2)
				.addIngredient(Items.WHEAT)
				.addIngredient(DataComponentIngredient.of(false, DataComponentPredicate.builder().expect(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER)).build(), Items.POTION))
				.unlockedByAnyIngredient(TFItems.SHIKA_SENBEI)
				.setRecipeBookTab(CookingPotRecipeBookTab.MISC)
				.save(output);

		CookingPotRecipeBuilder.cookingPotRecipe(GourmetItems.CRAB_CAKES, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
				.addIngredient(GourmetItems.CRAB_MEAT, 2)
				.addIngredient(GourmetBlocks.BREADCRUMBS, 2)
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.addIngredient(Tags.Items.EGGS)
				.unlockedByItems("has_crab", GourmetItems.CRAB_MEAT)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);

		CookingPotRecipeBuilder.cookingPotRecipe(GourmetItems.CRAB_QUICHE, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
				.addIngredient(Ingredient.of(ModItems.PIE_CRUST.get(), GourmetItems.MAZE_WAFER_CRUST))
				.addIngredient(Tags.Items.EGGS)
				.addIngredient(Tags.Items.EGGS)
				.addIngredient(GourmetItems.CRAB_MEAT, 2)
				.unlockedByItems("has_crab", GourmetItems.CRAB_MEAT)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);

		CookingPotRecipeBuilder.cookingPotRecipe(GourmetItems.VENISON_AND_VEGGIES, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
				.addIngredient(TFItems.RAW_VENISON)
				.addIngredient(TFItems.FIERY_BLOOD)
				.addIngredient(Ingredient.of(Tags.Items.FOODS_VEGETABLE))
				.addIngredient(Ingredient.of(Tags.Items.FOODS_VEGETABLE))
				.unlockedByAnyIngredient(TFItems.RAW_VENISON, TFItems.FIERY_BLOOD)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);

		CookingPotRecipeBuilder.cookingPotRecipe(GourmetItems.GOULASH, 1, CookingRecipes.SLOW_COOKING, CookingRecipes.LARGE_EXP)
				.addIngredient(Ingredient.of(GourmetItems.GROUND_VENISON, GourmetItems.MINCED_MEEF))
				.addIngredient(CommonTags.Items.CROPS_ONION)
				.addIngredient(DataComponentIngredient.of(false, DataComponentPredicate.builder().expect(DataComponents.POTION_CONTENTS, new PotionContents(Potions.WATER)).build(), Items.POTION))
				.addIngredient(ModItems.TOMATO_SAUCE.get())
				.addIngredient(CommonTags.Items.FOODS_PASTA)
				.unlockedByAnyIngredient(GourmetItems.GROUND_VENISON, GourmetItems.MINCED_MEEF, ModItems.RAW_PASTA.get(), ModItems.TOMATO_SAUCE.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);

		CookingPotRecipeBuilder.cookingPotRecipe(GourmetItems.MUSHGLOOM_STEW, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
				.addIngredient(TFItems.TORCHBERRIES)
				.addIngredient(TFBlocks.MUSHGLOOM, 2)
				.addIngredient(TFItems.LIVEROOT)
				.addIngredient(ModItems.BONE_BROTH.get())
				.unlockedByAnyIngredient(TFBlocks.MUSHGLOOM, TFItems.TORCHBERRIES, TFItems.LIVEROOT)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);

		CookingPotRecipeBuilder.cookingPotRecipe(GourmetItems.FIDDLEHEAD_RISOTTO, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP)
				.addIngredient(TFBlocks.FIDDLEHEAD, 3)
				.addIngredient(CommonTags.Items.CROPS_RICE)
				.addIngredient(CommonTags.Items.CROPS_RICE)
				.addIngredient(ModItems.BONE_BROTH.get())
				.unlockedByAnyIngredient(TFBlocks.FIDDLEHEAD, ModItems.BONE_BROTH.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);

		CookingPotRecipeBuilder.cookingPotRecipe(GourmetItems.BERRY_SMOOTHIE, 3, CookingRecipes.FAST_COOKING, CookingRecipes.SMALL_EXP)
				.addIngredient(Tags.Items.DRINKS_MILK)
				.addIngredient(Items.SUGAR)
				.addIngredient(Items.SWEET_BERRIES)
				.addIngredient(Items.GLOW_BERRIES)
				.addIngredient(TFItems.TORCHBERRIES)
				.unlockedByAnyIngredient(Items.SWEET_BERRIES, Items.GLOW_BERRIES, TFItems.TORCHBERRIES)
				.setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
				.save(output);

		CookingPotRecipeBuilder.cookingPotRecipe(GourmetItems.COOKED_CRAB, 1, CookingRecipes.NORMAL_COOKING, CookingRecipes.MEDIUM_EXP, Items.BOWL)
				.addIngredient(GourmetItems.WHOLE_CRAB)
				.unlockedByItems("has_crab", GourmetItems.WHOLE_CRAB)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GourmetBlocks.TREE_TAPPER)
				.pattern(" W ")
				.pattern("WWW")
				.pattern("  W")
				.define('W', ItemTagGenerator.WROUGHT_IRON_INGOTS)
				.unlockedBy("has_wrought_iron", has(ItemTagGenerator.WROUGHT_IRON_INGOTS))
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GourmetBlocks.TWILIGHT_OAK_CABINET)
				.pattern("___")
				.pattern("D D")
				.pattern("___")
				.define('_', TFBlocks.TWILIGHT_OAK_SLAB)
				.define('D', TFBlocks.TWILIGHT_OAK_TRAPDOOR)
				.unlockedBy("has_trapdoor", has(TFBlocks.TWILIGHT_OAK_TRAPDOOR))
				.group("fd_cabinet")
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GourmetBlocks.CANOPY_CABINET)
				.pattern("___")
				.pattern("D D")
				.pattern("___")
				.define('_', TFBlocks.CANOPY_SLAB)
				.define('D', TFBlocks.CANOPY_TRAPDOOR)
				.unlockedBy("has_trapdoor", has(TFBlocks.CANOPY_TRAPDOOR))
				.group("fd_cabinet")
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GourmetBlocks.MANGROVE_CABINET)
				.pattern("___")
				.pattern("D D")
				.pattern("___")
				.define('_', TFBlocks.MANGROVE_SLAB)
				.define('D', TFBlocks.MANGROVE_TRAPDOOR)
				.unlockedBy("has_trapdoor", has(TFBlocks.MANGROVE_TRAPDOOR))
				.group("fd_cabinet")
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GourmetBlocks.DARK_CABINET)
				.pattern("___")
				.pattern("D D")
				.pattern("___")
				.define('_', TFBlocks.DARK_SLAB)
				.define('D', TFBlocks.DARK_TRAPDOOR)
				.unlockedBy("has_trapdoor", has(TFBlocks.DARK_TRAPDOOR))
				.group("fd_cabinet")
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GourmetBlocks.TIME_CABINET)
				.pattern("___")
				.pattern("D D")
				.pattern("___")
				.define('_', TFBlocks.TIME_SLAB)
				.define('D', TFBlocks.TIME_TRAPDOOR)
				.unlockedBy("has_trapdoor", has(TFBlocks.TIME_TRAPDOOR))
				.group("fd_cabinet")
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GourmetBlocks.TRANSFORMATION_CABINET)
				.pattern("___")
				.pattern("D D")
				.pattern("___")
				.define('_', TFBlocks.TRANSFORMATION_SLAB)
				.define('D', TFBlocks.TRANSFORMATION_TRAPDOOR)
				.unlockedBy("has_trapdoor", has(TFBlocks.TRANSFORMATION_TRAPDOOR))
				.group("fd_cabinet")
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GourmetBlocks.MINING_CABINET)
				.pattern("___")
				.pattern("D D")
				.pattern("___")
				.define('_', TFBlocks.MINING_SLAB)
				.define('D', TFBlocks.MINING_TRAPDOOR)
				.unlockedBy("has_trapdoor", has(TFBlocks.MINING_TRAPDOOR))
				.group("fd_cabinet")
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, GourmetBlocks.SORTING_CABINET)
				.pattern("___")
				.pattern("D D")
				.pattern("___")
				.define('_', TFBlocks.SORTING_SLAB)
				.define('D', TFBlocks.SORTING_TRAPDOOR)
				.unlockedBy("has_trapdoor", has(TFBlocks.SORTING_TRAPDOOR))
				.group("fd_cabinet")
				.save(output);

		ItemEnchantments.Mutable ironwoodEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
		ironwoodEnchants.set(registries.holderOrThrow(Enchantments.UNBREAKING), 1);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, new ItemStack(GourmetItems.IRONWOOD_KNIFE, 1, DataComponentPatch.builder().set(DataComponents.ENCHANTMENTS, ironwoodEnchants.toImmutable()).build()))
				.pattern("I")
				.pattern("S")
				.define('I', ItemTagGenerator.IRONWOOD_INGOTS)
				.define('S', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_ingot", has(ItemTagGenerator.IRONWOOD_INGOTS))
				.save(output);

		ItemEnchantments.Mutable steeleafEnchants = new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);
		steeleafEnchants.set(registries.holderOrThrow(Enchantments.SHARPNESS), 2);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, new ItemStack(GourmetItems.STEELEAF_KNIFE, 1, DataComponentPatch.builder().set(DataComponents.ENCHANTMENTS, steeleafEnchants.toImmutable()).build()))
				.pattern("I")
				.pattern("S")
				.define('I', ItemTagGenerator.STEELEAF_INGOTS)
				.define('S', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_ingot", has(ItemTagGenerator.STEELEAF_INGOTS))
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, GourmetItems.KNIGHTMETAL_KNIFE)
				.pattern("I")
				.pattern("S")
				.define('I', ItemTagGenerator.KNIGHTMETAL_INGOTS)
				.define('S', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_ingot", has(ItemTagGenerator.KNIGHTMETAL_INGOTS))
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, GourmetItems.FIERY_KNIFE)
				.pattern("I")
				.pattern("S")
				.define('I', ItemTagGenerator.FIERY_INGOTS)
				.define('S', Tags.Items.RODS_WOODEN)
				.unlockedBy("has_ingot", has(ItemTagGenerator.FIERY_INGOTS))
				.save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GourmetItems.PANCAKE, 3)
				.requires(Items.WHEAT, 2)
				.requires(Items.SUGAR)
				.requires(Tags.Items.EGGS)
				.requires(Tags.Items.DRINKS_MILK)
				.unlockedBy("has_egg", has(Tags.Items.EGGS))
				.unlockedBy("has_sugar", has(Items.SUGAR))
				.unlockedBy("has_milk", has(Tags.Items.DRINKS_MILK))
				.save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GourmetItems.PANCAKE_STACK)
				.requires(Items.BOWL)
				.requires(GourmetItems.PANCAKE, 3)
				.unlockedBy("has_pancake", has(GourmetItems.PANCAKE))
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, GourmetItems.MAZE_WAFER_CRUST)
				.pattern("wmw")
				.pattern(" w ")
				.define('w', TFItems.MAZE_WAFER)
				.define('m', Tags.Items.DRINKS_MILK)
				.unlockedBy("has_wafer", has(TFItems.MAZE_WAFER))
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, GourmetItems.MAZE_CHEESECAKE)
				.pattern("WTW")
				.pattern("SMS")
				.define('W', Items.WHEAT)
				.define('T', TFItems.TORCHBERRIES)
				.define('S', Items.SUGAR)
				.define('M', GourmetItems.MAZE_WAFER_CRUST)
				.unlockedBy("has_torchberries", has(TFItems.TORCHBERRIES))
				.unlockedBy("has_crust", has(GourmetItems.MAZE_WAFER_CRUST))
				.group("tg_maze_cheesecake")
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, GourmetItems.MAZE_CHEESECAKE)
				.pattern("SS")
				.pattern("SS")
				.define('S', GourmetItems.MAZE_CHEESECAKE_SLICE)
				.unlockedBy("has_slice", has(GourmetItems.MAZE_CHEESECAKE_SLICE))
				.group("tg_maze_cheesecake")
				.save(output, TwilitGourmet.prefix("maze_cheesecake_from_slices"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GourmetItems.VENISON_KEBAB, 2)
				.requires(Items.STICK, 2)
				.requires(TFItems.COOKED_VENISON)
				.requires(CommonTags.Items.CROPS_ONION)
				.requires(Tags.Items.MUSHROOMS)
				.requires(Tags.Items.CROPS_POTATO)
				.unlockedBy("has_venison", has(TFItems.COOKED_VENISON))
				.save(output);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, GourmetItems.VENISON_SLIDER.get())
				.requires(Tags.Items.FOODS_BREAD)
				.requires(GourmetItems.VENISON_PATTY)
				.requires(CommonTags.Items.FOODS_LEAFY_GREEN)
				.requires(CommonTags.Items.CROPS_TOMATO)
				.unlockedBy("has_venison", has(GourmetItems.VENISON_PATTY))
				.save(output);

		ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, GourmetItems.SHELL_HELMET.get())
				.pattern("LFL")
				.pattern("CHL")
				.pattern("FFF")
				.define('L', GourmetItems.CRAB_LEG)
				.define('C', GourmetItems.CRAB_CLAW)
				.define('F', GourmetItems.CRAB_SHELL_FRAGMENT)
				.define('H', TFItems.KNIGHTMETAL_HELMET)
				.unlockedBy("no_toast_for_you", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()))
				.save(output);

		//FD recipes using mod ingredients
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.APPLE_PIE.get(), 1)
				.pattern("###")
				.pattern("aaa")
				.pattern("xOx")
				.define('#', Tags.Items.CROPS_WHEAT)
				.define('a', Items.APPLE)
				.define('x', Items.SUGAR)
				.define('O', GourmetItems.MAZE_WAFER_CRUST)
				.unlockedBy("has_pie_crust", has(GourmetItems.MAZE_WAFER_CRUST.get()))
				.group("fd_apple_pie")
				.save(output, TwilitGourmet.prefix("compat/apple_pie"));
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.SWEET_BERRY_CHEESECAKE.get(), 1)
				.pattern("sss")
				.pattern("sss")
				.pattern("mOm")
				.define('s', Items.SWEET_BERRIES)
				.define('m', Tags.Items.DRINKS_MILK)
				.define('O', GourmetItems.MAZE_WAFER_CRUST)
				.unlockedBy("has_pie_crust", has(GourmetItems.MAZE_WAFER_CRUST))
				.group("fd_sweet_berry_cheesecake")
				.save(output, TwilitGourmet.prefix("compat/sweet_berry_cheesecake"));
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.CHOCOLATE_PIE.get(), 1)
				.pattern("ccc")
				.pattern("mmm")
				.pattern("xOx")
				.define('c', Items.COCOA_BEANS)
				.define('m', Tags.Items.DRINKS_MILK)
				.define('x', Items.SUGAR)
				.define('O', GourmetItems.MAZE_WAFER_CRUST)
				.unlockedBy("has_pie_crust", has(GourmetItems.MAZE_WAFER_CRUST))
				.group("fd_chocolate_pie")
				.save(output, TwilitGourmet.prefix("compat/chocolate_pie"));
		ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Items.PUMPKIN_PIE, 2)
				.pattern("cec")
				.pattern("csc")
				.pattern(" O ")
				.define('c', ModItems.PUMPKIN_SLICE.get())
				.define('e', Tags.Items.EGGS)
				.define('s', Items.SUGAR)
				.define('O', GourmetItems.MAZE_WAFER_CRUST)
				.unlockedBy("has_pie_crust", has(GourmetItems.MAZE_WAFER_CRUST))
				.group("fd_pumpkin_pie")
				.save(output, TwilitGourmet.prefix("compat/pumpkin_pie_from_pie_crust"));

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HAMBURGER.get())
				.requires(Tags.Items.FOODS_BREAD)
				.requires(GourmetItems.MEEF_PATTY)
				.requires(CommonTags.Items.FOODS_LEAFY_GREEN)
				.requires(CommonTags.Items.CROPS_TOMATO)
				.requires(CommonTags.Items.CROPS_ONION)
				.unlockedBy("has_meef_patty", has(GourmetItems.MEEF_PATTY))
				.save(output, TwilitGourmet.prefix("compat/meef_hamburger"));
	}

	private static void salvagePlankFromFurniture(RecipeOutput output, String woodType, ItemLike plank, ItemLike... furniture) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(furniture), CuttingRecipes.AXES, plank, 1, 0.75F).save(output, TwilitGourmet.prefix("salvaging/" + woodType + "_furniture"));
	}

	private static void stripLogForBark(RecipeOutput output, ItemLike log, ItemLike strippedLog) {
		CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(log), CuttingRecipes.AXES_STRIP, strippedLog).addResult(ModItems.TREE_BARK.get()).addSound(SoundEvents.AXE_STRIP).saveToFD(output);
	}

	private static void foodSmeltingRecipes(String name, ItemLike ingredient, ItemLike result, float experience, RecipeOutput output) {
		String namePrefix = TwilitGourmet.prefix(name).toString();
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 200)
				.unlockedBy(name, has(ingredient))
				.save(output);
		SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 600)
				.unlockedBy(name, has(ingredient))
				.save(output, namePrefix + "_from_campfire_cooking");
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(ingredient), RecipeCategory.FOOD, result, experience, 100)
				.unlockedBy(name, has(ingredient))
				.save(output, namePrefix + "_from_smoking");
	}
}
