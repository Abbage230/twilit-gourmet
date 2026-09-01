package com.gizmo.twilitgourmet.block;

import com.gizmo.twilitgourmet.Syrup;
import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.block.entity.SyrupCauldronBlockEntity;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.utility.ShapeUtils;

import java.util.Map;

public class TreeTapperBlock extends HorizontalDirectionalBlock {

	public static final Map<Direction, VoxelShape> SHAPES = ShapeUtils.getShapesRotatedFromNorth(Shapes.or(
			Block.box(7.0D, 8.0D, 8.0D, 9.0D, 10.0D, 16.0D),
			Block.box(7.0D, 6.0D, 8.0D, 9.0D, 8.0D, 10.0D)));

	public static final BooleanProperty POURING = BooleanProperty.create("pouring");
	private static final MapCodec<TreeTapperBlock> CODEC = simpleCodec(TreeTapperBlock::new);

	public TreeTapperBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(POURING, false));
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPES.get(state.getValue(FACING));
	}

	private boolean canAttachTo(BlockGetter blockReader, BlockPos pos, Direction direction) {
		BlockState blockstate = blockReader.getBlockState(pos);
		return blockstate.isFaceSturdy(blockReader, pos, direction);
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		Direction direction = state.getValue(FACING);
		return this.canAttachTo(level, pos.relative(direction.getOpposite()), direction);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Direction placedDir = context.getClickedFace();
		if (this.canAttachTo(level, pos.relative(placedDir.getOpposite()), placedDir)) {
			return super.getStateForPlacement(context).setValue(FACING, placedDir).setValue(POURING, arePouringConditionsMet(level, pos, placedDir));
		}
		return null;
	}

	private static boolean arePouringConditionsMet(LevelAccessor level, BlockPos pos, Direction tapFacing) {
		ResourceKey<Syrup> syrup = level.getBlockState(pos.relative(tapFacing.getOpposite())).getBlock().builtInRegistryHolder().getData(TwilitGourmet.SYRUP_DATA_MAP);
		if (syrup == null) return false;
		if (level.getBlockState(pos.below()).is(GourmetBlocks.SYRUP_CAULDRON)) {
			return level.getBlockState(pos.below()).getValue(LayeredCauldronBlock.LEVEL) < LayeredCauldronBlock.MAX_FILL_LEVEL && level.getBlockEntity(pos.below()) instanceof SyrupCauldronBlockEntity cauldron && cauldron.getSyrupKey() == syrup;
		} else {
			return level.getBlockState(pos.below()).is(Blocks.CAULDRON);
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (state.getValue(POURING)) {
			ResourceKey<Syrup> syrup = level.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).getBlock().builtInRegistryHolder().getData(TwilitGourmet.SYRUP_DATA_MAP);
			if (syrup != null && random.nextFloat() <= level.registryAccess().registryOrThrow(TwilitGourmet.SYRUP_KEY).getOrThrow(syrup).chance()) {
				if (level.getBlockState(pos.below()).is(Blocks.CAULDRON)) {
					level.setBlockAndUpdate(pos.below(), GourmetBlocks.SYRUP_CAULDRON.get().defaultBlockState());
					if (level.getBlockEntity(pos.below()) instanceof SyrupCauldronBlockEntity cauldron) {
						cauldron.setSyrup(syrup);
					}
				} else if (level.getBlockState(pos.below()).is(GourmetBlocks.SYRUP_CAULDRON) && level.getBlockState(pos.below()).getValue(LayeredCauldronBlock.LEVEL) < LayeredCauldronBlock.MAX_FILL_LEVEL) {
					if (level.getBlockEntity(pos.below()) instanceof SyrupCauldronBlockEntity cauldron && cauldron.getSyrupKey() == syrup) {
						level.setBlockAndUpdate(pos.below(), level.getBlockState(pos.below()).cycle(LayeredCauldronBlock.LEVEL));
					}
				}
			}
		}
	}

	@Override
	protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
		if (direction == state.getValue(FACING).getOpposite() || direction == Direction.DOWN) {
			if (!state.canSurvive(level, pos)) {
				return Blocks.AIR.defaultBlockState();
			}
			return state.setValue(POURING, arePouringConditionsMet(level, pos, state.getValue(FACING)));
		}
		return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType type) {
		return false;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, POURING);
	}
}
