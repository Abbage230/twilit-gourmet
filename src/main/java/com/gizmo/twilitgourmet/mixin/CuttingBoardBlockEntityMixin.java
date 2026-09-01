package com.gizmo.twilitgourmet.mixin;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetItems;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import twilightforest.init.TFItems;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;

@Mixin(CuttingBoardBlockEntity.class)
public abstract class CuttingBoardBlockEntityMixin extends BlockEntity {

	public CuttingBoardBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
		super(type, pos, blockState);
	}

	@WrapOperation(method = "lambda$processStoredItemUsingTool$2", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;copy()Lnet/minecraft/world/item/ItemStack;"))
	public ItemStack smeltCuttingBoardItemsWithFieryTools(ItemStack instance, Operation<ItemStack> original, @Local(argsOnly = true) ItemStack toolStack) {
		if (this.level instanceof ServerLevel sl && (toolStack.is(TwilitGourmet.COOKS_CUTTING_BOARD_ITEMS))) {
			ItemStack smelted = sl.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(instance.copy()), sl)
					.map(holder -> holder.value().getResultItem(sl.registryAccess()).copy())
					.filter(stack -> !stack.isEmpty())
					.orElse(instance);

			return original.call(smelted);
		}
		return original.call(instance);
	}
}
