package com.gizmo.twilitgourmet;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.Optional;

public record Syrup(float chance, int color, Optional<MobEffectInstance> effect) {

	public static final Codec<Syrup> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.floatRange(0.0F, 1.0F).fieldOf("fill_chance_on_random_tick").forGetter(Syrup::chance),
			Codec.INT.fieldOf("color").forGetter(Syrup::color),
			MobEffectInstance.CODEC.optionalFieldOf("effect").forGetter(Syrup::effect)
	).apply(instance, Syrup::new));

	public Syrup(float chance, int color, MobEffectInstance effect) {
		this(chance, color, Optional.of(effect));
	}
}
