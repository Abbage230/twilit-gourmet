package com.gizmo.twilitgourmet.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.FoodValues;

public class GourmetFoods {

	public static final FoodProperties APPLE_SLICE = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1F).fast().build();
	public static final FoodProperties PANCAKE = new FoodProperties.Builder().nutrition(3).build();
	public static final FoodProperties SYRUP_BOTTLE = new FoodProperties.Builder().saturationModifier(0.3F).alwaysEdible().build();
	public static final FoodProperties RAW_BOARKCHOP = new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build();
	public static final FoodProperties COOKED_BOARKCHOP = new FoodProperties.Builder().nutrition(9).saturationModifier(0.85F).build();
	public static final FoodProperties GROUND_VENISON = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).fast().build();
	public static final FoodProperties VENISON_PATTY = new FoodProperties.Builder().nutrition(4).saturationModifier(0.8F).fast().build();
	public static final FoodProperties MINCED_MEEF = new FoodProperties.Builder().nutrition(2).saturationModifier(0.3F).fast().build();
	public static final FoodProperties MEEF_PATTY = new FoodProperties.Builder().nutrition(4).saturationModifier(0.8F).fast().build();
	public static final FoodProperties CRAB_MEAT = new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).fast().build();
	public static final FoodProperties COOKED_CRAB_MEAT = new FoodProperties.Builder().nutrition(4).saturationModifier(0.5F).fast().build();
	public static final FoodProperties VENISON_KEBAB = new FoodProperties.Builder().nutrition(8).saturationModifier(0.6F).build();
	public static final FoodProperties VENISON_SLIDER = new FoodProperties.Builder().nutrition(11).saturationModifier(0.6F).build();
	public static final FoodProperties CRAB_CAKE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.6F).effect(() -> FoodValues.nourishment(3600), 1.0F).build();
	public static final FoodProperties VENISON_AND_VEGGIES = new FoodProperties.Builder().nutrition(12).saturationModifier(0.8F).effect(() -> FoodValues.nourishment(3600), 1.0F).build();
	public static final FoodProperties GOULASH = new FoodProperties.Builder().nutrition(14).saturationModifier(1.1F).effect(() -> FoodValues.nourishment(3600), 1.0F).build();
	public static final FoodProperties MUSHGLOOM_STEW = new FoodProperties.Builder().nutrition(8).saturationModifier(0.6F).effect(() -> FoodValues.nourishment(3600), 1.0F).build();
	public static final FoodProperties FIDDLEHEAD_RISOTTO = new FoodProperties.Builder().nutrition(8).saturationModifier(0.5F).effect(() -> FoodValues.nourishment(1200), 1.0F).build();
	public static final FoodProperties BERRY_SMOOTHIE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.5F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 1200, 0), 1.0F).build();
}
