package com.gizmo.twilitgourmet.client;

import net.minecraft.client.model.Model;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import twilightforest.client.renderer.armor.TFSimpleArmorRenderer;

public class ShellHelmetExtension extends TFSimpleArmorRenderer {

	public ShellHelmetExtension() {
		super(ShellHelmetModel::new, ShellHelmetModel.SHELL_HELMET_INNER, ShellHelmetModel.SHELL_HELMET_OUTER);
	}

	@Override
	public void setupModelAnimations(LivingEntity entity, ItemStack stack, EquipmentSlot slot, Model model, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
		if (model instanceof ShellHelmetModel shell) {
			shell.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}
