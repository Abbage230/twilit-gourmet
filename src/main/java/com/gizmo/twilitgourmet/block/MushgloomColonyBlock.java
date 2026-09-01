package com.gizmo.twilitgourmet.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import twilightforest.init.TFBlocks;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;

//the mushroom type in the ctor CANNOT be the actual mushgloom item. For some reason this completely breaks the TF item for it, I have zero idea why.
public class MushgloomColonyBlock extends MushroomColonyBlock {

	public MushgloomColonyBlock(Properties properties) {
		super(null, properties);
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return new ItemStack(TFBlocks.MUSHGLOOM);
	}

	//use mushgloom place conditions instead of normal mushroom ones
	@Override
	public boolean canSurvive(BlockState state, LevelReader reader, BlockPos pos) {
		return reader.getBlockState(pos.below()).isFaceSturdy(reader, pos, Direction.UP) || reader.getBlockState(pos.below()).is(TFBlocks.UBEROUS_SOIL);
	}
}
