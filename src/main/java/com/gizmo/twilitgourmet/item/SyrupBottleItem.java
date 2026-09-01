package com.gizmo.twilitgourmet.item;

import com.gizmo.twilitgourmet.Syrup;
import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class SyrupBottleItem extends Item {

	public SyrupBottleItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (stack.get(GourmetDataComponents.SYRUP) != null) {
			level.registryAccess().registryOrThrow(TwilitGourmet.SYRUP_KEY).getOrThrow(stack.get(GourmetDataComponents.SYRUP)).effect().ifPresent(entity::addEffect);
		}

		return super.finishUsingItem(stack, level, entity);
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 40;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}

	@Override
	public SoundEvent getDrinkingSound() {
		return SoundEvents.HONEY_DRINK;
	}

	@Override
	public SoundEvent getEatingSound() {
		return SoundEvents.HONEY_DRINK;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		return ItemUtils.startUsingInstantly(level, player, hand);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		ResourceKey<Syrup> syrup = stack.get(GourmetDataComponents.SYRUP);
		if (syrup != null) {
			ResourceLocation syrupKey = syrup.location();
			tooltip.add(Component.translatable("item.twilitgourmet.syrup_bottle.type", Component.translatable("syrup." + syrupKey.getNamespace() + "." + syrupKey.getPath())).withStyle(ChatFormatting.GRAY));
		}
	}
}
