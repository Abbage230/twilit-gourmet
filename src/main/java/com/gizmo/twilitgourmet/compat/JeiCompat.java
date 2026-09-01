package com.gizmo.twilitgourmet.compat;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import twilightforest.compat.jei.categories.JEIUncraftingCategory;

import java.util.List;

@JeiPlugin
public class JeiCompat implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return TwilitGourmet.prefix("jei_plugin");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onRuntimeAvailable(IJeiRuntime runtime) {
		Minecraft.getInstance().level.getRecipeManager().byKey(TwilitGourmet.prefix("shell_helmet")).map(recipe -> (RecipeHolder<CraftingRecipe>) recipe).ifPresent(recipeHolder -> {
			runtime.getRecipeManager().hideRecipes(RecipeTypes.CRAFTING, List.of(recipeHolder));
			runtime.getRecipeManager().hideRecipes(JEIUncraftingCategory.UNCRAFTING, List.of(recipeHolder.value()));
		});
		runtime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, List.of(GourmetItems.SHELL_HELMET.toStack()));
	}
}
