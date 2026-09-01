package com.gizmo.twilitgourmet.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import vectorwing.farmersdelight.common.item.KnifeItem;

import java.util.List;

public class FieryKnifeItem extends KnifeItem {

	public FieryKnifeItem(Tier tier, Properties properties) {
		super(tier, properties);
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		boolean result = super.hurtEnemy(stack, target, attacker);

		if (result && !target.fireImmune()) {
			if (!target.level().isClientSide()) {
				target.igniteForSeconds(15);
			} else {
				target.level().addParticle(ParticleTypes.FLAME, target.getX(), target.getY() + target.getBbHeight() * 0.5D, target.getZ(), target.getBbWidth() * 0.5D, target.getBbHeight() * 0.5D, target.getBbWidth() * 0.5D);
			}
		}

		return result;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
	}
}
