package com.gizmo.twilitgourmet.block;

import com.gizmo.twilitgourmet.init.GourmetFoods;
import com.gizmo.twilitgourmet.init.GourmetItems;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.ShapeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GiantAppleBlock extends HorizontalDirectionalBlock {

	public static final IntegerProperty SLICES = IntegerProperty.create("slices", 0, 8);
	public static final VoxelShape STEM = Block.box(7.0D, 14.0D, 7.0D, 9.0D, 18.0D, 9.0D);
	private static final VoxelShape QUARTER_SHAPE = box(8.0D, 0.0D, 1.0D, 15.0D, 14.0D, 8.0D);
	private static final VoxelShape HALF_SHAPE = box(8.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	private static final VoxelShape THREE_QUARTER_SHAPE = Shapes.join(HALF_SHAPE, box(1.0D, 0.0D, 8.0D, 8.0D, 14.0D, 15.0D), BooleanOp.OR);
	private static final VoxelShape FULL_SHAPE = box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	public static final VoxelShape CORE_SHAPE = Shapes.or(STEM, Block.box(6.0D, 0.0D, 6.0D, 10.0D, 14.0D, 10.0D));

	private static final MapCodec<GiantAppleBlock> CODEC = simpleCodec(GiantAppleBlock::new);

	private final List<Map<Direction, VoxelShape>> rotatedShapesPerState = new ArrayList<>();

	public GiantAppleBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(SLICES, 8));
		this.makeShapes();
	}

	private void makeShapes() {
		for (int i = 0; i <= 8; i++) {
			VoxelShape initial = switch (i) {
				case 7, 8 -> Shapes.or(STEM, FULL_SHAPE);
				case 5, 6 -> Shapes.or(CORE_SHAPE, THREE_QUARTER_SHAPE);
				case 3, 4 -> Shapes.or(CORE_SHAPE, HALF_SHAPE);
				case 1, 2 -> Shapes.or(CORE_SHAPE, QUARTER_SHAPE);
				default -> CORE_SHAPE;
			};

			this.rotatedShapesPerState.add(ShapeUtils.getShapesRotatedFromNorth(initial));
		}
	}

	@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return this.rotatedShapesPerState.get(state.getValue(SLICES)).get(state.getValue(FACING));
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (stack.canPerformAction(KnifeItem.KNIFE_HARVEST)) {
			if (state.getValue(SLICES) > 0) {
				level.setBlock(pos, state.setValue(SLICES, state.getValue(SLICES) - 1), 3);
				ItemStack apple = new ItemStack(GourmetItems.APPLE_SLICE.get());
				if (!player.getInventory().add(apple)) {
					player.drop(apple, true);
				}
				level.playSound(null, pos, ModSounds.BLOCK_FOOD_SLICE.get(), SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
				level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
				return ItemInteractionResult.sidedSuccess(level.isClientSide());
			}
		}
		return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
		int i = state.getValue(SLICES);
		if (!player.isSecondaryUseActive()) {
			if (player.canEat(false)) {
				player.getFoodData().eat(GourmetFoods.APPLE_SLICE.nutrition(), GourmetFoods.APPLE_SLICE.saturation());
				player.spawnItemParticles(new ItemStack(GourmetItems.APPLE_SLICE.get()), 5);

				if (i == 0) {
					level.removeBlock(pos, false);
					level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.5F, level.getRandom().nextFloat() * 0.1F + 0.9F);
					level.playSound(null, pos, SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 0.5F, level.getRandom().nextFloat() * 0.1F + 0.9F);
				} else {
					level.setBlock(pos, state.setValue(SLICES, i - 1), 3);
					level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.5F, level.getRandom().nextFloat() * 0.1F + 0.9F);
				}
				level.gameEvent(player, GameEvent.EAT, pos);

				if (player instanceof ServerPlayer) {
					CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) player, new ItemStack(GourmetItems.APPLE_SLICE.get()));
					player.awardStat(Stats.ITEM_USED.get(GourmetItems.APPLE_SLICE.get()));
				}

				return InteractionResult.sidedSuccess(level.isClientSide());
			}
		}
		return InteractionResult.PASS;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(SLICES, FACING);
	}
}
