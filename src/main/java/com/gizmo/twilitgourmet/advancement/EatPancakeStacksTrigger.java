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

public class EatPancakeStacksTrigger extends SimpleCriterionTrigger<EatPancakeStacksTrigger.TriggerInstance> {

	@Override
	public Codec<EatPancakeStacksTrigger.TriggerInstance> codec() {
		return EatPancakeStacksTrigger.TriggerInstance.CODEC;
	}

	public void trigger(ServerPlayer player, int stacks) {
		this.trigger(player, (instance) -> instance.matches(stacks));
	}

	public record TriggerInstance(Optional<ContextAwarePredicate> player, MinMaxBounds.Ints stacks) implements SimpleInstance {

		public static final Codec<EatPancakeStacksTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(EatPancakeStacksTrigger.TriggerInstance::player),
				MinMaxBounds.Ints.CODEC.fieldOf("stacks").forGetter(EatPancakeStacksTrigger.TriggerInstance::stacks))
			.apply(instance, EatPancakeStacksTrigger.TriggerInstance::new));

		public static Criterion<EatPancakeStacksTrigger.TriggerInstance> eatenPancakeStacks(MinMaxBounds.Ints amount) {
			return GourmetCriteriaTriggers.EAT_PANCAKE_STACKS.get().createCriterion(new EatPancakeStacksTrigger.TriggerInstance(Optional.empty(), amount));
		}

		public boolean matches(int amount) {
			return this.stacks.matches(amount);
		}
	}
}
