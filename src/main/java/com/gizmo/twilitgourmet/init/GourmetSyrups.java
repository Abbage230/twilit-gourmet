package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.Syrup;
import com.gizmo.twilitgourmet.TwilitGourmet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import twilightforest.TwilightForestMod;

public class GourmetSyrups {

	public static final ResourceKey<Syrup> OAK = makeKey(ResourceLocation.withDefaultNamespace("oak"));
	public static final ResourceKey<Syrup> SPRUCE = makeKey(ResourceLocation.withDefaultNamespace("spruce"));
	public static final ResourceKey<Syrup> BIRCH = makeKey(ResourceLocation.withDefaultNamespace("birch"));
	public static final ResourceKey<Syrup> JUNGLE = makeKey(ResourceLocation.withDefaultNamespace("jungle"));
	public static final ResourceKey<Syrup> ACACIA = makeKey(ResourceLocation.withDefaultNamespace("acacia"));
	public static final ResourceKey<Syrup> DARK_OAK = makeKey(ResourceLocation.withDefaultNamespace("dark_oak"));
	public static final ResourceKey<Syrup> CRIMSON = makeKey(ResourceLocation.withDefaultNamespace("crimson"));
	public static final ResourceKey<Syrup> WARPED = makeKey(ResourceLocation.withDefaultNamespace("warped"));
	public static final ResourceKey<Syrup> VANGROVE = makeKey(ResourceLocation.withDefaultNamespace("mangrove"));
	public static final ResourceKey<Syrup> CHERRY = makeKey(ResourceLocation.withDefaultNamespace("cherry"));

	public static final ResourceKey<Syrup> TWILIGHT_OAK = makeKey(TwilightForestMod.prefix("twilight_oak"));
	public static final ResourceKey<Syrup> CANOPY = makeKey(TwilightForestMod.prefix("canopy"));
	public static final ResourceKey<Syrup> MANGROVE = makeKey(TwilightForestMod.prefix("mangrove"));
	public static final ResourceKey<Syrup> DARKWOOD = makeKey(TwilightForestMod.prefix("darkwood"));
	public static final ResourceKey<Syrup> TIME = makeKey(TwilightForestMod.prefix("time"));
	public static final ResourceKey<Syrup> TRANSFORMATION = makeKey(TwilightForestMod.prefix("transformation"));
	public static final ResourceKey<Syrup> MINING = makeKey(TwilightForestMod.prefix("mining"));
	public static final ResourceKey<Syrup> SORTING = makeKey(TwilightForestMod.prefix("sorting"));

	public static final ResourceKey<Syrup> FALLBACK = makeKey(TwilitGourmet.prefix("fallback"));

	private static ResourceKey<Syrup> makeKey(ResourceLocation name) {
		return ResourceKey.create(TwilitGourmet.SYRUP_KEY, name);
	}

	public static void bootstrap(BootstrapContext<Syrup> context) {
		context.register(OAK, new Syrup(0.125F, 0xFFB99558, new MobEffectInstance(MobEffects.HEAL, 1)));
		context.register(SPRUCE, new Syrup(0.125F, 0xFF705934, new MobEffectInstance(MobEffects.CONDUIT_POWER, 900)));
		context.register(BIRCH, new Syrup(0.125F, 0xFFD3C487, new MobEffectInstance(MobEffects.JUMP, 900)));
		context.register(JUNGLE, new Syrup(0.125F, 0xFFBF9165, new MobEffectInstance(MobEffects.ABSORPTION, 900)));
		context.register(ACACIA, new Syrup(0.125F, 0xFFB25B3B, new MobEffectInstance(MobEffects.SLOW_FALLING, 900)));
		context.register(DARK_OAK, new Syrup(0.125F, 0xFF4A3A24, new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 900)));
		context.register(CRIMSON, new Syrup(0.125F, 0xFF963E62, new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 900)));
		context.register(WARPED, new Syrup(0.125F, 0xFF3DA9A6, new MobEffectInstance(MobEffects.NIGHT_VISION, 900)));
		context.register(VANGROVE, new Syrup(0.125F, 0xFF804533, new MobEffectInstance(MobEffects.WATER_BREATHING, 900)));
		context.register(CHERRY, new Syrup(0.125F, 0xFFDF9CA4, new MobEffectInstance(MobEffects.REGENERATION, 900)));

		context.register(TWILIGHT_OAK, new Syrup(0.125F, 0xFF937651, new MobEffectInstance(MobEffects.HEAL, 1)));
		context.register(CANOPY, new Syrup(0.125F, 0xFF51331e, new MobEffectInstance(MobEffects.SLOW_FALLING, 900)));
		context.register(MANGROVE, new Syrup(0.125F, 0xFFbe9369, new MobEffectInstance(MobEffects.WATER_BREATHING, 900)));
		context.register(DARKWOOD, new Syrup(0.125F, 0xFF936132, new MobEffectInstance(MobEffects.NIGHT_VISION, 900)));
		context.register(TIME, new Syrup(0.125F, 0xFF664930, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 900)));
		context.register(TRANSFORMATION, new Syrup(0.125F, 0xFF887556, new MobEffectInstance(MobEffects.DAMAGE_BOOST, 900)));
		context.register(MINING, new Syrup(0.125F, 0xFFb59b7a, new MobEffectInstance(MobEffects.DIG_SPEED, 900)));
		context.register(SORTING, new Syrup(0.125F, 0xFF61401c, new MobEffectInstance(MobEffects.INVISIBILITY, 900)));

		context.register(FALLBACK, new Syrup(0.125F, 0xFFB99558, new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 450)));
	}
}
