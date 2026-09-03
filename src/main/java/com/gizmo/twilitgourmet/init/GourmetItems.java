package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.item.CrabPartItem;
import com.gizmo.twilitgourmet.item.FieryKnifeItem;
import com.gizmo.twilitgourmet.item.KnightmetalKnifeItem;
import com.gizmo.twilitgourmet.item.SyrupBottleItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import twilightforest.util.TFToolMaterials;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.*;
import vectorwing.farmersdelight.common.registry.ModItems;

public class GourmetItems {
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TwilitGourmet.MODID);

	public static final DeferredItem<Item> GROUND_VENISON = ITEMS.register("ground_venison", () -> new Item(new Item.Properties().food(GourmetFoods.GROUND_VENISON)));
	public static final DeferredItem<Item> VENISON_PATTY = ITEMS.register("venison_patty", () -> new Item(new Item.Properties().food(GourmetFoods.VENISON_PATTY)));
	public static final DeferredItem<Item> MINCED_MEEF = ITEMS.register("minced_meef", () -> new Item(new Item.Properties().food(GourmetFoods.MINCED_MEEF)));
	public static final DeferredItem<Item> MEEF_PATTY = ITEMS.register("meef_patty", () -> new Item(new Item.Properties().food(GourmetFoods.MEEF_PATTY)));
	public static final DeferredItem<KnifeItem> IRONWOOD_KNIFE = ITEMS.register("ironwood_knife", () -> new KnifeItem(TFToolMaterials.IRONWOOD, ModItems.knifeItem(TFToolMaterials.IRONWOOD)));
	public static final DeferredItem<KnifeItem> STEELEAF_KNIFE = ITEMS.register("steeleaf_knife", () -> new KnifeItem(TFToolMaterials.STEELEAF, ModItems.knifeItem(TFToolMaterials.STEELEAF)));
	public static final DeferredItem<KnifeItem> KNIGHTMETAL_KNIFE = ITEMS.register("knightmetal_knife", () -> new KnightmetalKnifeItem(TFToolMaterials.KNIGHTMETAL, ModItems.knifeItem(TFToolMaterials.KNIGHTMETAL)));
	public static final DeferredItem<KnifeItem> FIERY_KNIFE = ITEMS.register("fiery_knife", () -> new FieryKnifeItem(TFToolMaterials.FIERY, ModItems.knifeItem(TFToolMaterials.FIERY)));
	public static final DeferredItem<KnifeItem> ICE_KNIFE = ITEMS.register("ice_knife", () -> new KnifeItem(TFToolMaterials.ICE, ModItems.knifeItem(TFToolMaterials.ICE)));
	public static final DeferredItem<Item> MUSHGLOOM_COLONY = ITEMS.register("mushgloom_colony", () -> new MushroomColonyItem(GourmetBlocks.MUSHGLOOM_COLONY.get(), new Item.Properties()));

	public static final DeferredItem<Item> APPLE_SLICE = ITEMS.register("apple_slice", () -> new Item(new Item.Properties().food(GourmetFoods.APPLE_SLICE)));
	public static final DeferredItem<Item> PANCAKE = ITEMS.register("pancake", () -> new Item(new Item.Properties().food(GourmetFoods.PANCAKE)));
	public static final DeferredItem<Item> PANCAKE_STACK = ITEMS.register("pancake_stack", () -> new PlaceableItem(GourmetBlocks.PANCAKE_STACK.get(), new Item.Properties()));
	public static final DeferredItem<Item> SYRUP_BOTTLE = ITEMS.register("syrup_bottle", () -> new SyrupBottleItem(new Item.Properties().food(GourmetFoods.SYRUP_BOTTLE).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	public static final DeferredItem<Item> RAW_BOARKCHOP = ITEMS.register("boarkchop", () -> new Item(new Item.Properties().food(GourmetFoods.RAW_BOARKCHOP)));
	public static final DeferredItem<Item> COOKED_BOARKCHOP = ITEMS.register("cooked_boarkchop", () -> new Item(new Item.Properties().food(GourmetFoods.COOKED_BOARKCHOP)));
	public static final DeferredItem<Item> VENISON_KEBAB = ITEMS.register("venison_kebab", () -> new ConsumableItem(new Item.Properties().food(GourmetFoods.VENISON_KEBAB).craftRemainder(Items.STICK)));
	public static final DeferredItem<Item> VENISON_SLIDER = ITEMS.register("venison_slider", () -> new ConsumableItem(new Item.Properties().food(GourmetFoods.VENISON_SLIDER)));
	public static final DeferredItem<Item> VENISON_AND_VEGGIES = ITEMS.register("venison_and_root_veggies", () -> new ConsumableItem(new Item.Properties().food(GourmetFoods.VENISON_AND_VEGGIES).stacksTo(16).craftRemainder(Items.BOWL)));
	public static final DeferredItem<Item> GOULASH = ITEMS.register("goulash", () -> new ConsumableItem(new Item.Properties().food(GourmetFoods.GOULASH).stacksTo(16).craftRemainder(Items.BOWL)));
	public static final DeferredItem<Item> MUSHGLOOM_STEW = ITEMS.register("mushgloom_stew", () -> new ConsumableItem(new Item.Properties().food(GourmetFoods.MUSHGLOOM_STEW).stacksTo(16).craftRemainder(Items.BOWL)));
	public static final DeferredItem<Item> FIDDLEHEAD_RISOTTO = ITEMS.register("fiddlehead_risotto", () -> new ConsumableItem(new Item.Properties().food(GourmetFoods.FIDDLEHEAD_RISOTTO).stacksTo(16).craftRemainder(Items.BOWL)));
	public static final DeferredItem<Item> BERRY_SMOOTHIE = ITEMS.register("berry_smoothie", () -> new DrinkableItem(new Item.Properties().food(GourmetFoods.BERRY_SMOOTHIE).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE), false, false));
	public static final DeferredItem<Item> MAZE_WAFER_CRUST = ITEMS.register("maze_wafer_crust", () -> new Item(new Item.Properties().food(FoodValues.PIE_CRUST)));
	public static final DeferredItem<Item> MAZE_CHEESECAKE = ITEMS.register("maze_cheesecake", () -> new PlaceableItem(GourmetBlocks.MAZE_CHEESECAKE.get(), new Item.Properties()));
	public static final DeferredItem<Item> MAZE_CHEESECAKE_SLICE = ITEMS.register("maze_cheesecake_slice", () -> new ConsumableItem(new Item.Properties().food(FoodValues.PIE_SLICE)));
	public static final DeferredItem<Item> CREMESCHNITTE = ITEMS.register("cremeschnitte", () -> new PlaceableItem(GourmetBlocks.CREMESCHNITTE.get(), new Item.Properties()));
	public static final DeferredItem<Item> CREMESCHNITTE_SLICE = ITEMS.register("cremeschnitte_slice", () -> new ConsumableItem(new Item.Properties().food(FoodValues.PIE_SLICE)));

	//crab things
	public static final DeferredItem<Item> CRAB_SHELL_FRAGMENT = ITEMS.register("crab_shell_fragment", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> CRAB_LEG = ITEMS.register("crab_leg", () -> new CrabPartItem(new Item.Properties().food(GourmetFoods.CRAB_MEAT)));
	public static final DeferredItem<Item> CRAB_CLAW = ITEMS.register("crab_claw", () -> new CrabPartItem(new Item.Properties().food(GourmetFoods.CRAB_MEAT)));
	public static final DeferredItem<Item> SHELL_HELMET = ITEMS.register("shell_helmet", () -> new ArmorItem(GourmetArmorMaterials.SHELL, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(20))));
	public static final DeferredItem<Item> CRAB_MEAT = ITEMS.register("crab_meat", () -> new Item(new Item.Properties().food(GourmetFoods.CRAB_MEAT)));
	public static final DeferredItem<Item> COOKED_CRAB_MEAT = ITEMS.register("cooked_crab_meat", () -> new Item(new Item.Properties().food(GourmetFoods.COOKED_CRAB_MEAT)));
	public static final DeferredItem<Item> CRAB_JERKY = ITEMS.register("crab_jerky", () -> new Item(new Item.Properties().food(GourmetFoods.CRAB_JERKY)));
	public static final DeferredItem<Item> WHOLE_CRAB = ITEMS.register("whole_crab", () -> new Item(new Item.Properties()));
	public static final DeferredItem<Item> COOKED_CRAB = ITEMS.register("cooked_crab", () -> new PlaceableItem(GourmetBlocks.COOKED_CRAB.get(), new Item.Properties()));
	public static final DeferredItem<Item> CRAB_CAKES = ITEMS.register("crab_cakes", () -> new PlaceableItem(GourmetBlocks.CRAB_CAKES.get(), new Item.Properties()));
	public static final DeferredItem<Item> CRAB_CAKE = ITEMS.register("crab_cake", () -> new ConsumableItem(new Item.Properties().food(GourmetFoods.CRAB_CAKE).stacksTo(16).craftRemainder(Items.BOWL)));
	public static final DeferredItem<Item> CRAB_QUICHE = ITEMS.register("crab_quiche", () -> new PlaceableItem(GourmetBlocks.CRAB_QUICHE.get(), new Item.Properties()));
	public static final DeferredItem<Item> CRAB_QUICHE_SLICE = ITEMS.register("crab_quiche_slice", () -> new ConsumableItem(new Item.Properties().food(FoodValues.PIE_SLICE)));
}
