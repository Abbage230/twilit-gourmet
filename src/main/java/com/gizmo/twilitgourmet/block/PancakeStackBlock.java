package com.gizmo.twilitgourmet.block;

import com.gizmo.twilitgourmet.TGAdvancementTracker;
import com.gizmo.twilitgourmet.block.entity.PancakeStackBlockEntity;
import com.gizmo.twilitgourmet.init.*;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.BlockShapes;

import java.util.List;

public class PancakeStackBlock extends BaseEntityBlock {

	public static final IntegerProperty PANCAKES = IntegerProperty.create("pancakes", 0, 3);
	public static final BooleanProperty SYRUP = BooleanProperty.create("syrup");

	private static final List<VoxelShape> SHAPES = List.of(
			BlockShapes.TRAY_SHAPE,
			Shapes.or(Block.box(3.0D, 1.0D, 3.0D, 13.0D, 4.0D, 13.0D), BlockShapes.TRAY_SHAPE),
			Shapes.or(Block.box(3.0D, 1.0D, 3.0D, 13.0D, 7.0D, 13.0D), BlockShapes.TRAY_SHAPE),
			Shapes.or(Block.box(3.0D, 1.0D, 3.0D, 13.0D, 10.0D, 13.0D), BlockShapes.TRAY_SHAPE)
	);

	private static final MapCodec<PancakeStackBlock> CODEC = simpleCodec(PancakeStackBlock::new);

	public PancakeStackBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.getStateDefinition().any().setValue(PANCAKES, 3).setValue(SYRUP, false));
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPES.get(state.getValue(PANCAKES));
	}

	@Override
	protected RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (!state.getValue(SYRUP) && stack.get(GourmetDataComponents.SYRUP) != null) {
			level.setBlockAndUpdate(pos, state.setValue(SYRUP, true));
			level.playSound(null, pos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
			if (level.getBlockEntity(pos) instanceof PancakeStackBlockEntity pancake) {
				pancake.setSyrup(stack.get(GourmetDataComponents.SYRUP));
			}
			if (stack.hasCraftingRemainingItem()) {
				ItemStack emptyContainer = stack.getCraftingRemainingItem();
				stack.consume(1, player);
				if (stack.isEmpty()) {
					player.setItemInHand(hand, emptyContainer);
				} else {
					if (!player.getInventory().add(emptyContainer)) {
						player.drop(emptyContainer, false);
					}
				}
			} else {
				stack.consume(1, player);
			}
			return ItemInteractionResult.sidedSuccess(level.isClientSide());
		}
		return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		int i = state.getValue(PANCAKES);
		if (i == 0) {
			level.playSound(null, pos, SoundEvents.WOOD_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
			level.destroyBlock(pos, true);
			return InteractionResult.SUCCESS;
		} else if (player.canEat(false)) {
			boolean syrup = state.getValue(SYRUP);
			int pancakeHunger = GourmetFoods.PANCAKE.nutrition();
			float pancakeSat = GourmetFoods.PANCAKE.saturation();
			player.getFoodData().eat(syrup ? pancakeHunger + 2 : pancakeHunger, syrup ? pancakeSat + 0.2F : pancakeSat);
			player.spawnItemParticles(new ItemStack(GourmetItems.PANCAKE.get()), 5);

			if (syrup && level.getBlockEntity(pos) instanceof PancakeStackBlockEntity stack && stack.getSyrupKey() != null) {
				stack.getSyrup(level.registryAccess()).effect().ifPresent(effect ->
						player.addEffect(new MobEffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier() + 1, effect.isAmbient(), effect.isVisible())));
			}

			level.setBlock(pos, state.setValue(PANCAKES, i - 1), 3);
			level.playSound(null, pos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.5F, level.getRandom().nextFloat() * 0.1F + 0.9F);
			level.gameEvent(player, GameEvent.EAT, pos);

			if (player instanceof ServerPlayer sp) {
				if (i == 1 && state.getValue(SYRUP)) {
					TGAdvancementTracker tracker = player.getData(GourmetDataAttachments.ADVANCEMENT_TRACKER);
					tracker.syrupPancakeStacksEaten++;
					player.setData(GourmetDataAttachments.ADVANCEMENT_TRACKER, tracker);
					GourmetCriteriaTriggers.EAT_PANCAKE_STACKS.get().trigger(sp, tracker.syrupPancakeStacksEaten);
				}

				CriteriaTriggers.CONSUME_ITEM.trigger(sp, new ItemStack(GourmetItems.PANCAKE.get()));
				player.awardStat(Stats.ITEM_USED.get(GourmetItems.PANCAKE.get()));
			}

			return InteractionResult.sidedSuccess(level.isClientSide());
		}
		return InteractionResult.PASS;
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new PancakeStackBlockEntity(pos, state);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(PANCAKES, SYRUP);
	}
}
