package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.block.*;
import com.gizmo.twilitgourmet.block.entity.PancakeStackBlockEntity;
import com.gizmo.twilitgourmet.block.entity.SyrupCauldronBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import twilightforest.init.TFBlocks;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.PieBlock;

import java.util.function.Supplier;

public class GourmetBlocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TwilitGourmet.MODID);
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, TwilitGourmet.MODID);

	//expected compat things
	public static final DeferredBlock<Block> TWILIGHT_OAK_CABINET = register("twilight_oak_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)));
	public static final DeferredBlock<Block> CANOPY_CABINET = register("canopy_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).mapColor(MapColor.PODZOL)));
	public static final DeferredBlock<Block> MANGROVE_CABINET = register("mangrove_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).mapColor(MapColor.DIRT)));
	public static final DeferredBlock<Block> DARK_CABINET = register("dark_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).mapColor(MapColor.COLOR_BROWN)));
	public static final DeferredBlock<Block> TIME_CABINET = register("time_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).mapColor(MapColor.DIRT)));
	public static final DeferredBlock<Block> TRANSFORMATION_CABINET = register("transformation_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)));
	public static final DeferredBlock<Block> MINING_CABINET = register("mining_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).mapColor(MapColor.SAND)));
	public static final DeferredBlock<Block> SORTING_CABINET = register("sorting_cabinet", () -> new CabinetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).mapColor(MapColor.PODZOL)));
	public static final DeferredBlock<Block> MUSHGLOOM_COLONY = BLOCKS.register("mushgloom_colony", () -> new MushgloomColonyBlock(BlockBehaviour.Properties.ofFullCopy(TFBlocks.MUSHGLOOM.get())));

	//new stuff
	public static final DeferredBlock<Block> BREADCRUMBS = register("breadcrumbs", () -> new BreadcrumbBlock(BlockBehaviour.Properties.of().replaceable().noCollission().noOcclusion().mapColor(MapColor.TERRACOTTA_BROWN).pushReaction(PushReaction.DESTROY).sound(SoundType.ROOTS)));
	public static final DeferredBlock<Block> GIANT_APPLE = register("giant_apple", () -> new GiantAppleBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(2.0F, 10.0F).pushReaction(PushReaction.DESTROY).sound(SoundType.WOOL)));
	public static final DeferredBlock<Block> TREE_TAPPER = register("tree_tapper", () -> new TreeTapperBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.0F, 10.0F).randomTicks().sound(SoundType.METAL)));
	public static final DeferredBlock<Block> SYRUP_CAULDRON = BLOCKS.register("syrup_cauldron", () -> new SyrupCauldronBlock(BlockBehaviour.Properties.ofLegacyCopy(Blocks.CAULDRON)));
	public static final DeferredBlock<Block> PANCAKE_STACK = BLOCKS.register("pancake_stack", () -> new PancakeStackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)));
	public static final DeferredBlock<Block> COOKED_CRAB = BLOCKS.register("cooked_crab", () -> new CookedCrabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE).sound(SoundType.ROOTS)));
	public static final DeferredBlock<Block> MAZE_CHEESECAKE = BLOCKS.register("maze_cheesecake", () -> new PieBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), GourmetItems.MAZE_CHEESECAKE_SLICE));
	public static final DeferredBlock<Block> CREMESCHNITTE = BLOCKS.register("cremeschnitte", () -> new PieBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), GourmetItems.CREMESCHNITTE_SLICE));
	public static final DeferredBlock<Block> CRAB_QUICHE = BLOCKS.register("crab_quiche", () -> new PieBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), GourmetItems.CRAB_QUICHE_SLICE));
	public static final DeferredBlock<Block> CRAB_CAKES = BLOCKS.register("crab_cakes", () -> new FeastBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), GourmetItems.CRAB_CAKE, true));

	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SyrupCauldronBlockEntity>> SYRUP_CAULDRON_BE = BLOCK_ENTITIES.register("syrup_cauldron", () -> BlockEntityType.Builder.of(SyrupCauldronBlockEntity::new, SYRUP_CAULDRON.get()).build(null));
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PancakeStackBlockEntity>> PANCAKE_STACK_BE = BLOCK_ENTITIES.register("pancake_stack", () -> BlockEntityType.Builder.of(PancakeStackBlockEntity::new, PANCAKE_STACK.get()).build(null));

	public static <T extends Block> DeferredBlock<T> register(String name, Supplier<T> block) {
		DeferredBlock<T> ret = BLOCKS.register(name, block);
		GourmetItems.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties()));
		return ret;
	}
}
