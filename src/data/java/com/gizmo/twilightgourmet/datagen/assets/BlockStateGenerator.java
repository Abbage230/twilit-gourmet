package com.gizmo.twilightgourmet.datagen.assets;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.block.*;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import twilightforest.TwilightForestMod;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;
import vectorwing.farmersdelight.common.block.PieBlock;

import static com.gizmo.twilitgourmet.TwilitGourmet.prefix;

public class BlockStateGenerator extends BlockStateProvider {
	public BlockStateGenerator(PackOutput output, ExistingFileHelper helper) {
		super(output, TwilitGourmet.MODID, helper);
		//ugh
		helper.trackGenerated(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/pie_bottom"), ModelProvider.TEXTURE);
		helper.trackGenerated(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/pie_side"), ModelProvider.TEXTURE);
	}

	@Override
	protected void registerStatesAndModels() {
		this.cabinetBlock(GourmetBlocks.TWILIGHT_OAK_CABINET);
		this.cabinetBlock(GourmetBlocks.CANOPY_CABINET);
		this.cabinetBlock(GourmetBlocks.MANGROVE_CABINET);
		this.cabinetBlock(GourmetBlocks.DARK_CABINET);
		this.cabinetBlock(GourmetBlocks.TIME_CABINET);
		this.cabinetBlock(GourmetBlocks.TRANSFORMATION_CABINET);
		this.cabinetBlock(GourmetBlocks.MINING_CABINET);
		this.cabinetBlock(GourmetBlocks.SORTING_CABINET);

		this.pieBlock(GourmetBlocks.MAZE_CHEESECAKE, true);
		this.pieBlock(GourmetBlocks.CRAB_QUICHE, false);

		this.getVariantBuilder(GourmetBlocks.GIANT_APPLE.get()).forAllStates(state -> {
			int slices = state.getValue(GiantAppleBlock.SLICES);
			if (slices == 0 || slices == 8) {
				return ConfiguredModel.builder().modelFile(models().getExistingFile(prefix("block/giant_apple" + (slices == 0 ? "_core" : "")))).rotationY((int) state.getValue(GiantAppleBlock.FACING).getOpposite().toYRot()).build();
			}
			return ConfiguredModel.builder().modelFile(models().getExistingFile(prefix("block/giant_apple_" + slices + "_slice"))).rotationY((int) state.getValue(GiantAppleBlock.FACING).getOpposite().toYRot()).build();
		});

		this.horizontalBlock(GourmetBlocks.TREE_TAPPER.get(), state -> this.models().getExistingFile(prefix("block/tree_tapper" + (state.getValue(TreeTapperBlock.POURING) ? "_pouring" : ""))));
		this.getVariantBuilder(GourmetBlocks.SYRUP_CAULDRON.get()).forAllStates(state -> {
			int level = state.getValue(LayeredCauldronBlock.LEVEL);
			return ConfiguredModel.builder().modelFile(this.models().withExistingParent("syrup_cauldron_level_" + level, ResourceLocation.withDefaultNamespace("block/template_cauldron_" + (level == 3 ? "full" : "level" + level))).texture("content", ResourceLocation.withDefaultNamespace("block/water_still"))).build();
		});

		this.getVariantBuilder(GourmetBlocks.PANCAKE_STACK.get()).forAllStates(state -> {
			boolean syrup = state.getValue(PancakeStackBlock.SYRUP);
			String name = "pancake_stack_" + state.getValue(PancakeStackBlock.PANCAKES);
			String suffix = (syrup ? "_syrup" : "");
			return ConfiguredModel.builder().modelFile(this.models().withExistingParent(name + suffix, prefix("block/template_" + name))
					.texture("pancake", prefix("block/pancake" + suffix))).build();
		});
		this.horizontalBlock(GourmetBlocks.CRAB_CAKES.get(), state -> this.models().getExistingFile(prefix("block/crab_cakes_" + state.getValue(FeastBlock.SERVINGS))));
		this.horizontalBlock(GourmetBlocks.COOKED_CRAB.get(), state -> this.models().getExistingFile(prefix("block/cooked_crab_" + state.getValue(CookedCrabBlock.SERVINGS))));

		this.getMultipartBuilder(GourmetBlocks.BREADCRUMBS.get())
				.part().modelFile(this.models().getExistingFile(prefix("block/breadcrumbs"))).addModel().end()
				.part().modelFile(this.models().getExistingFile(prefix("block/breadcrumbs_extension"))).addModel().condition(BreadcrumbBlock.NORTH, true).end()
				.part().modelFile(this.models().getExistingFile(prefix("block/breadcrumbs_extension"))).rotationY(180).addModel().condition(BreadcrumbBlock.SOUTH, true).end()
				.part().modelFile(this.models().getExistingFile(prefix("block/breadcrumbs_extension"))).rotationY(90).addModel().condition(BreadcrumbBlock.EAST, true).end()
				.part().modelFile(this.models().getExistingFile(prefix("block/breadcrumbs_extension"))).rotationY(270).addModel().condition(BreadcrumbBlock.WEST, true).end();

		this.getVariantBuilder(GourmetBlocks.MUSHGLOOM_COLONY.get()).forAllStates(state -> {
			String name = "mushgloom_colony_stage" + state.getValue(MushroomColonyBlock.COLONY_AGE);
			return ConfiguredModel.builder().modelFile(this.models().getBuilder(name).parent(new ModelFile.UncheckedModelFile(TwilightForestMod.prefix("block/mushgloom")))
					.texture("cross", this.modLoc("block/" + name))
					.texture("cross2", this.modLoc("block/" + name + "_head"))).build();
		});
	}

	public void cabinetBlock(DeferredBlock<?> block) {
		this.horizontalBlock(block.get(), (state) -> {
			String suffix = state.getValue(CabinetBlock.OPEN) ? "_open" : "";
			return this.models().orientable(block.getId().getPath() + suffix, this.blockTexture(block.get()).withSuffix("_side"), this.blockTexture(block.get()).withSuffix("_front" + suffix), this.blockTexture(block.get()).withSuffix("_top"));
		});
		this.simpleBlockItem(block.get(), this.models().getExistingFile(this.blockTexture(block.get())));
	}

	public void pieBlock(DeferredBlock<Block> block, boolean mazeCrust) {
		this.getVariantBuilder(block.get()).forAllStates(state -> {
			int bites = state.getValue(PieBlock.BITES);
			return ConfiguredModel.builder()
					.modelFile(bites > 0 ? this.modelPieSlice(block.getId().getPath(), bites, mazeCrust) : this.modelPie(block.getId().getPath(), mazeCrust))
					.rotationY(((int) state.getValue(PieBlock.FACING).toYRot() + 180) % 360)
					.build();
		});
	}

	private ModelFile modelPie(String baseName, boolean mazeCrust) {
		return this.models().getBuilder(baseName).parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/template_pie")))
				.texture("bottom", mazeCrust ? prefix("block/maze_crust_bottom") : ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/pie_bottom"))
				.texture("side", mazeCrust ? prefix("block/maze_crust_side") : ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/pie_side"))
				.texture("top", prefix("block/" + baseName + "_top"));
	}

	private ModelFile modelPieSlice(String baseName, int bites, boolean mazeCrust) {
		return this.models().getBuilder(baseName + "_slice" + bites).parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/template_pie_slice" + bites)))
				.texture("bottom", mazeCrust ? prefix("block/maze_crust_bottom") : ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/pie_bottom"))
				.texture("side", mazeCrust ? prefix("block/maze_crust_side") : ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/pie_side"))
				.texture("inner", prefix("block/" + baseName + "_inner"))
				.texture("top", prefix("block/" + baseName + "_top"));
	}
}
