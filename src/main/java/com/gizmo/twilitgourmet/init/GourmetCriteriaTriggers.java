package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.advancement.EatPancakeStacksTrigger;
import com.gizmo.twilitgourmet.advancement.EatSlicesFromAppleTrigger;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import twilightforest.TwilightForestMod;

public class GourmetCriteriaTriggers {

	public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, TwilightForestMod.ID);

	public static final DeferredHolder<CriterionTrigger<?>, EatSlicesFromAppleTrigger> EAT_APPLE_SLICES = TRIGGERS.register("eat_apple_slices", EatSlicesFromAppleTrigger::new);
	public static final DeferredHolder<CriterionTrigger<?>, EatPancakeStacksTrigger> EAT_PANCAKE_STACKS = TRIGGERS.register("eat_pancake_stacks", EatPancakeStacksTrigger::new);

}
