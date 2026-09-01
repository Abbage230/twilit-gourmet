package com.gizmo.twilitgourmet.block;

import com.gizmo.twilitgourmet.init.GourmetItems;
import com.gizmo.twilitgourmet.init.GourmetSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import vectorwing.farmersdelight.common.BlockShapes;
import vectorwing.farmersdelight.common.block.RotatedFeastBlock;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.utility.ShapeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CookedCrabBlock extends RotatedFeastBlock {

	public static final IntegerProperty SERVINGS = IntegerProperty.create("servings", 0, 6);

	private final List<Map<Direction, VoxelShape>> rotatedShapesPerState = new ArrayList<>();

	public CookedCrabBlock(Properties properties) {
		super(properties, GourmetItems.CRAB_SHELL_FRAGMENT, true, new VoxelShape[]{}, BlockShapes.TRAY_SHAPE);
		this.makeShapes();
	}

	private void makeShapes() {
		for (int i = 0; i <= 6; i++) {
			VoxelShape initial = switch (i) {
				case 0 -> Shapes.or(Block.box(4.0D, 1.0D, 6.0D, 12.0D, 4.0D, 12.0D), BlockShapes.TRAY_SHAPE);
				case 1, 2, 3 -> Shapes.or(
						Block.box(4.0D, 1.0D, 6.0D, 12.0D, 4.0D, 12.0D),
						Block.box(4.5D, 1.0D, 12.0D, 11.5D, 2.0D, 14.0D),
						BlockShapes.TRAY_SHAPE);
				case 4, 5 -> Shapes.or(
						Block.box(4.0D, 1.0D, 6.0D, 12.0D, 4.0D, 12.0D),
						Block.box(4.5D, 1.0D, 12.0D, 11.5D, 3.0D, 14.0D),
						BlockShapes.TRAY_SHAPE);
				default -> BlockShapes.TRAY_SHAPE;
			};

			this.rotatedShapesPerState.add(ShapeUtils.getShapesRotatedFromNorth(initial));
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return this.rotatedShapesPerState.get(state.getValue(SERVINGS)).get(state.getValue(FACING));
	}

	@Override
	public IntegerProperty getServingsProperty() {
		return SERVINGS;
	}

	@Override
	public int getMaxServings() {
		return 6;
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack heldStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (state.getValue(SERVINGS) == 0) {
			level.playSound(null, pos, SoundEvents.WOOD_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F);
			level.destroyBlock(pos, true);
			return ItemInteractionResult.SUCCESS;
		} else {
			if (heldStack.canPerformAction(KnifeItem.KNIFE_DIG)) {
				ItemInteractionResult result = this.takeServing(level, pos, state, player, hand);
				if (result.consumesAction()) {
					heldStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));

					int servingsLeft = state.getValue(SERVINGS);
					if (servingsLeft != 2 && servingsLeft != 5 && !level.isClientSide()) {
						level.playSound(null, pos, GourmetSounds.SHELL_CRACK.get(), SoundSource.BLOCKS, 1.0F, player.getRandom().nextFloat() * 0.1F + 0.9F);
					}
				}
				return result;
			} else {
				player.displayClientMessage(Component.translatable("block.twilitgourmet.cooked_crab.use_knife"), true);
			}
		}
		return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
	}

	@Override
	public ItemStack getServingItem(BlockState state) {
		return switch (state.getValue(SERVINGS)) {
			case 1 -> new ItemStack(GourmetItems.CRAB_SHELL_FRAGMENT.get(), 5);
			case 2 -> new ItemStack(GourmetItems.COOKED_CRAB_MEAT.get(), 3);
			case 3 -> new ItemStack(GourmetItems.CRAB_CLAW.get(), 2);
			case 4 -> new ItemStack(GourmetItems.CRAB_LEG.get(), 6);
			case 5 -> new ItemStack(GourmetItems.COOKED_CRAB_MEAT.get(), 5);
			case 6 -> new ItemStack(GourmetItems.CRAB_SHELL_FRAGMENT.get(), 3);
			default -> new ItemStack(GourmetItems.CRAB_SHELL_FRAGMENT.get());
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, SERVINGS);
	}
}
