package com.gizmo.twilightgourmet.datagen.assets;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.gizmo.twilitgourmet.init.GourmetItems;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.loaders.ItemLayerModelBuilder;
import net.neoforged.neoforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.gizmo.twilitgourmet.TwilitGourmet.prefix;

public class ItemModelGenerator extends ItemModelProvider {
	public ItemModelGenerator(PackOutput output, ExistingFileHelper helper) {
		super(output, TwilitGourmet.MODID, helper);
	}

	@Override
	protected void registerModels() {
		this.tool(GourmetItems.ICE_KNIFE.getId().getPath(), prefix("item/ice_knife_solid"), prefix("item/ice_knife_clear"));
		this.singleTex(GourmetItems.APPLE_SLICE);
		this.singleTexTool(GourmetItems.IRONWOOD_KNIFE);
		this.singleTexTool(GourmetItems.STEELEAF_KNIFE);
		this.singleTexTool(GourmetItems.KNIGHTMETAL_KNIFE);
		this.singleTexFullbrightTool(GourmetItems.FIERY_KNIFE);
		this.generated("syrup_bottle", ResourceLocation.withDefaultNamespace("item/potion_overlay"), ResourceLocation.withDefaultNamespace("item/potion"));

		this.singleTex(GourmetItems.RAW_BOARKCHOP);
		this.singleTex(GourmetItems.COOKED_BOARKCHOP);
		this.singleTex(GourmetItems.GROUND_VENISON);
		this.singleTex(GourmetItems.VENISON_PATTY);
		this.singleTex(GourmetItems.MINCED_MEEF);
		this.singleTex(GourmetItems.MEEF_PATTY);
		this.singleTex(GourmetItems.MAZE_WAFER_CRUST);
		this.singleTex(GourmetItems.VENISON_KEBAB);
		this.singleTex(GourmetItems.VENISON_SLIDER);
		this.singleTex(GourmetItems.VENISON_AND_VEGGIES);
		this.bowlFood(GourmetItems.GOULASH);
		this.bowlFood(GourmetItems.MUSHGLOOM_STEW);
		this.singleTex(GourmetItems.FIDDLEHEAD_RISOTTO);
		this.singleTex(GourmetItems.BERRY_SMOOTHIE);
		this.singleTex(GourmetItems.CRAB_SHELL_FRAGMENT);
		this.singleTex(GourmetItems.CRAB_CLAW);
		this.singleTex(GourmetItems.CRAB_LEG);
		this.singleTex(GourmetItems.SHELL_HELMET);
		this.singleTex(GourmetItems.CRAB_MEAT);
		this.singleTex(GourmetItems.COOKED_CRAB_MEAT);
		this.singleTex(GourmetItems.CRAB_JERKY);
		this.singleTex(GourmetItems.WHOLE_CRAB);
		this.singleTex(GourmetItems.COOKED_CRAB);
		this.singleTex(GourmetItems.CRAB_CAKE);
		this.singleTex(GourmetItems.CRAB_CAKES);
		this.singleTex(GourmetItems.CRAB_QUICHE);
		this.singleTex(GourmetItems.CRAB_QUICHE_SLICE);
		this.singleTex(GourmetItems.MAZE_CHEESECAKE);
		this.singleTex(GourmetItems.MAZE_CHEESECAKE_SLICE);
		this.singleTex(GourmetItems.CREMESCHNITTE);
		this.singleTex(GourmetItems.CREMESCHNITTE_SLICE);
		this.singleTex(GourmetItems.PANCAKE);
		this.singleTex(GourmetItems.PANCAKE_STACK);
		//I dont use the block textures here due to the soil darkening.
		//basically, because the mushgloom emits light and has parts with set light values, the soil looks really bright in block form. So I darkened it a bunch so it looks normal.
		//the item texture uses the old coloring so it doesnt look really dark in the inventory
		this.singleTex(GourmetItems.MUSHGLOOM_COLONY);

		this.basicItem(GourmetBlocks.BREADCRUMBS.asItem());
		this.basicItem(GourmetBlocks.TREE_TAPPER.asItem());

		this.giantApple("giant_apple", ResourceLocation.withDefaultNamespace("item/apple"));
	}

	private void toBlock(Block b) {
		toBlockModel(b, BuiltInRegistries.BLOCK.getKey(b).getPath());
	}

	private void toBlockModel(Block b, String model) {
		toBlockModel(b, TwilitGourmet.prefix("block/" + model));
	}

	private void toBlockModel(Block b, ResourceLocation model) {
		withExistingParent(BuiltInRegistries.BLOCK.getKey(b).getPath(), model);
	}

	private ItemModelBuilder singleTexFullbright(DeferredItem<? extends Item> item) {
		return fullbright(item.getId().getPath(), prefix("item/" + item.getId().getPath()));
	}

	private ItemModelBuilder singleTexFullbrightTool(DeferredItem<? extends Item> item) {
		return fullbrightTool(item.getId().getPath(), prefix("item/" + item.getId().getPath()));
	}

	private ItemModelBuilder singleTexTool(DeferredItem<? extends Item> item) {
		return tool(item.getId().getPath(), prefix("item/" + item.getId().getPath()));
	}

	private ItemModelBuilder singleTex(DeferredItem<?> item) {
		return generated(item.getId().getPath(), prefix("item/" + item.getId().getPath()));
	}

	private ItemModelBuilder fullbright(String name, ResourceLocation... layers) {
		return buildItem(name, "item/generated", 15, layers);
	}

	private ItemModelBuilder fullbrightTool(String name, ResourceLocation... layers) {
		return buildItem(name, "item/handheld", 15, layers);
	}

	private ItemModelBuilder generated(String name, ResourceLocation... layers) {
		return buildItem(name, "item/generated", 0, layers);
	}

	private ItemModelBuilder tool(String name, ResourceLocation... layers) {
		return buildItem(name, "item/handheld", 0, layers);
	}

	private ItemModelBuilder buildItem(String name, String parent, int emissivity, ResourceLocation... layers) {
		ItemModelBuilder builder = withExistingParent(name, parent);
		for (int i = 0; i < layers.length; i++) {
			builder = builder.texture("layer" + i, layers[i]);
		}
		if (emissivity > 0)
			builder = builder.customLoader(ItemLayerModelBuilder::begin).emissive(emissivity, emissivity, 0).renderType("minecraft:translucent", 0).end();
		return builder;
	}

	private void bowlFood(DeferredItem<?> item) {
		String name = item.getId().getPath();
		this.withExistingParent(name, "item/generated")
				.texture("particle", TwilitGourmet.prefix("item/" + name))
				.texture("layer0", ResourceLocation.withDefaultNamespace("item/bowl"))
				.texture("layer1", TwilitGourmet.prefix("item/" + name));
	}

	private void giantApple(String name, ResourceLocation parent) {
		ItemModelBuilder gui = new ItemModelBuilder(this.modLoc(name + "_gui"), this.existingFileHelper).texture("all", parent)
				.element().from(0, 0, 0).to(16, 16, 0).face(Direction.SOUTH).texture("#all").uvs(4, 4, 12, 12).end().end();

		ItemModelBuilder base = new ItemModelBuilder(this.modLoc(name + "_base"), this.existingFileHelper)
				.parent(this.getExistingFile(ResourceLocation.withDefaultNamespace("item/generated")))
				.texture("layer0", parent).transforms()
				.transform(ItemDisplayContext.GROUND).translation(0.0F, 2.0F, 0.0F).scale(2.5F).end()
				.transform(ItemDisplayContext.HEAD).rotation(0.0F, 180.0F, 0.0F).translation(0.0F, 13.0F, 7.0F).scale(5.0F).end()
				.transform(ItemDisplayContext.FIXED).rotation(0.0F, 180.0F, 0.0F).scale(5.0F).end()
				.transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).translation(-0.1F, 28.0F, 1.5F).scale(4.25F).end()
				.transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).translation(-0.1F, 28.0F, 1.5F).scale(4.25F).end()
				.transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(0.0F, -90.0F, 25.0F).translation(1.13F, 3.2F, 1.13F).scale(1.7F).end()
				.transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(0.0F, 90.0F, -25.0F).translation(1.13F, 3.2F, 1.13F).scale(1.7F).end().end();

		this.withExistingParent(name, parent).customLoader(SeparateTransformsModelBuilder::begin)
				.base(base)
				.perspective(ItemDisplayContext.GUI, gui)
				.end();
	}
}
