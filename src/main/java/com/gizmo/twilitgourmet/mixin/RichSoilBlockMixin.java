package com.gizmo.twilitgourmet.mixin;

import com.gizmo.twilitgourmet.init.GourmetBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import twilightforest.init.TFBlocks;
import vectorwing.farmersdelight.common.block.RichSoilBlock;

@Mixin(RichSoilBlock.class)
public class RichSoilBlockMixin {

	//TODO remove once FD 1.4 is released
	@Inject(method = "convertMushroomToColony", at = @At("HEAD"), cancellable = true, remap = false)
	public void allowMushgloomColoniesToGrow(BlockState targetState, BlockPos targetPos, ServerLevel level, CallbackInfoReturnable<Boolean> cir) {
		if (targetState.is(TFBlocks.MUSHGLOOM)) {
			level.setBlockAndUpdate(targetPos, GourmetBlocks.MUSHGLOOM_COLONY.get().defaultBlockState());
			cir.setReturnValue(true);
		}
	}
}
