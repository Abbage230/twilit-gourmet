package com.gizmo.twilitgourmet.advancement;

import com.gizmo.twilitgourmet.init.GourmetCriteriaTriggers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.Optional;

public class EatSlicesFromAppleTrigger extends SimpleCriterionTrigger<EatSlicesFromAppleTrigger.TriggerInstance> {

	@Override
	public Codec<EatSlicesFromAppleTrigger.TriggerInstance> codec() {
		return EatSlicesFromAppleTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player, int slices) {
		this.trigger(player, (instance) -> instance.matches(slices));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, MinMaxBounds.Ints slices) implements SimpleInstance {

		public static final Codec<EatSlicesFromAppleTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(EatSlicesFromAppleTrigger.TriggerInstance::player),
				MinMaxBounds.Ints.CODEC.fieldOf("slices").forGetter(EatSlicesFromAppleTrigger.TriggerInstance::slices))
			.apply(instance, EatSlicesFromAppleTrigger.TriggerInstance::new));

		public static Criterion<EatSlicesFromAppleTrigger.TriggerInstance> eatenSlicesFromApple(MinMaxBounds.Ints amount) {
			return GourmetCriteriaTriggers.EAT_APPLE_SLICES.get().createCriterion(new EatSlicesFromAppleTrigger.TriggerInstance(Optional.empty(), amount));
		}

		public boolean matches(int amount) {
			return this.slices.matches(amount);
		}
	}
}
