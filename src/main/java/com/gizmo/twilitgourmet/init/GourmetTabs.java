package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.Syrup;
import com.gizmo.twilitgourmet.TwilitGourmet;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import twilightforest.config.TFConfig;

public class GourmetTabs {
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TwilitGourmet.MODID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB = TABS.register("twilightgourmet", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(GourmetItems.SYRUP_BOTTLE, 1, DataComponentPatch.builder().set(GourmetDataComponents.SYRUP.get(), GourmetSyrups.OAK).build()))
			.title(Component.translatable("itemGroup.twilitgourmet"))
			.displayItems((parameters, output) -> {
				HolderLookup.RegistryLookup<Enchantment> lookup = parameters.holders().lookupOrThrow(Registries.ENCHANTMENT);
				generateGearWithEnchants(output, GourmetItems.IRONWOOD_KNIFE, new EnchantmentInstance(lookup.getOrThrow(Enchantments.UNBREAKING), 1));
				generateGearWithEnchants(output, GourmetItems.STEELEAF_KNIFE, new EnchantmentInstance(lookup.getOrThrow(Enchantments.SHARPNESS), 2));
				output.accept(GourmetItems.KNIGHTMETAL_KNIFE);
				output.accept(GourmetItems.FIERY_KNIFE);
				output.accept(GourmetItems.ICE_KNIFE);
				output.accept(GourmetBlocks.TWILIGHT_OAK_CABINET);
				output.accept(GourmetBlocks.CANOPY_CABINET);
				output.accept(GourmetBlocks.MANGROVE_CABINET);
				output.accept(GourmetBlocks.DARK_CABINET);
				output.accept(GourmetBlocks.TIME_CABINET);
				output.accept(GourmetBlocks.TRANSFORMATION_CABINET);
				output.accept(GourmetBlocks.MINING_CABINET);
				output.accept(GourmetBlocks.SORTING_CABINET);
				output.accept(GourmetItems.MUSHGLOOM_COLONY);

				output.accept(GourmetItems.GROUND_VENISON);
				output.accept(GourmetItems.VENISON_PATTY);
				output.accept(GourmetItems.MINCED_MEEF);
				output.accept(GourmetItems.MEEF_PATTY);
				output.accept(GourmetItems.RAW_BOARKCHOP);
				output.accept(GourmetItems.COOKED_BOARKCHOP);

				output.accept(GourmetBlocks.BREADCRUMBS);
				output.accept(GourmetItems.APPLE_SLICE);
				output.accept(GourmetBlocks.GIANT_APPLE);
				output.accept(GourmetItems.PANCAKE);
				output.accept(GourmetItems.PANCAKE_STACK);
				output.accept(GourmetItems.VENISON_KEBAB);
				output.accept(GourmetItems.VENISON_SLIDER);
				output.accept(GourmetItems.VENISON_AND_VEGGIES);
				output.accept(GourmetItems.GOULASH);
				output.accept(GourmetItems.MUSHGLOOM_STEW);
				output.accept(GourmetItems.FIDDLEHEAD_RISOTTO);
				output.accept(GourmetItems.BERRY_SMOOTHIE);
				output.accept(GourmetItems.MAZE_WAFER_CRUST);
				output.accept(GourmetItems.MAZE_CHEESECAKE);
				output.accept(GourmetItems.MAZE_CHEESECAKE_SLICE);
				output.accept(GourmetItems.CREMESCHNITTE);
				output.accept(GourmetItems.CREMESCHNITTE_SLICE);

				output.accept(GourmetItems.CRAB_SHELL_FRAGMENT);
				output.accept(GourmetItems.CRAB_LEG);
				output.accept(GourmetItems.CRAB_CLAW);
				output.accept(GourmetItems.WHOLE_CRAB);
				output.accept(GourmetItems.COOKED_CRAB);
				output.accept(GourmetItems.CRAB_MEAT);
				output.accept(GourmetItems.COOKED_CRAB_MEAT);
				output.accept(GourmetItems.CRAB_JERKY);
				output.accept(GourmetItems.CRAB_CAKES);
				output.accept(GourmetItems.CRAB_CAKE);
				output.accept(GourmetItems.CRAB_QUICHE);
				output.accept(GourmetItems.CRAB_QUICHE_SLICE);

				output.accept(GourmetBlocks.TREE_TAPPER);
				generateSyrups(output, parameters.holders().lookupOrThrow(TwilitGourmet.SYRUP_KEY));
			}).build());

	private static void generateSyrups(CreativeModeTab.Output output, HolderLookup<Syrup> syrups) {
		syrups.listElements()
				.map(syrup -> new ItemStack(GourmetItems.SYRUP_BOTTLE, 1, DataComponentPatch.builder().set(GourmetDataComponents.SYRUP.get(), syrup.key()).build()))
				.forEach(stack -> output.accept(stack, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY));
	}

	private static void generateGearWithEnchants(CreativeModeTab.Output output, ItemLike item, EnchantmentInstance... instances) {
		ItemStack stack = new ItemStack(item);
		if (TFConfig.defaultItemEnchants) {
			for (EnchantmentInstance enchant : instances) {
				stack.enchant(enchant.enchantment, enchant.level);
			}
		}
		output.accept(stack);
	}
}
