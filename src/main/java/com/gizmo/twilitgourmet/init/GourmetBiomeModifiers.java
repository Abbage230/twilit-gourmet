package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.TwilitGourmet;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import twilightforest.init.TFBiomes;

public class GourmetBiomeModifiers {

	public static ResourceKey<BiomeModifier> WILD_ONIONS = register("wild_onions");
	public static ResourceKey<BiomeModifier> WILD_TOMATOES = register("wild_tomatoes");
	public static ResourceKey<BiomeModifier> WILD_CARROTS = register("wild_carrots");
	public static ResourceKey<BiomeModifier> WILD_POTATOES = register("wild_potatoes");
	public static ResourceKey<BiomeModifier> WILD_RICE = register("wild_rice");
	public static ResourceKey<BiomeModifier> BROWN_MUSHROOM_COLONIES = register("brown_mushroom_colony");
	public static ResourceKey<BiomeModifier> RED_MUSHROOM_COLONIES = register("red_mushroom_colony");

	private static ResourceKey<BiomeModifier> register(String name) {
		return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, TwilitGourmet.prefix(name));
	}
	
	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
		HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);
		HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);
		
		context.register(WILD_ONIONS, createSimpleModifier(
				HolderSet.direct(biomeGetter.getOrThrow(TFBiomes.DENSE_FOREST), biomeGetter.getOrThrow(TFBiomes.FIREFLY_FOREST)),
				placedFeatureGetter.getOrThrow(GourmetPlacedFeatures.WILD_ONIONS)
		));
		context.register(WILD_TOMATOES, createSimpleModifier(
				HolderSet.direct(biomeGetter.getOrThrow(TFBiomes.OAK_SAVANNAH), biomeGetter.getOrThrow(TFBiomes.FIRE_SWAMP)),
				placedFeatureGetter.getOrThrow(GourmetPlacedFeatures.WILD_TOMATOES)
		));
		context.register(WILD_CARROTS, createSimpleModifier(
				HolderSet.direct(biomeGetter.getOrThrow(TFBiomes.CLEARING), biomeGetter.getOrThrow(TFBiomes.FOREST)),
				placedFeatureGetter.getOrThrow(GourmetPlacedFeatures.WILD_CARROTS)
		));
		context.register(WILD_POTATOES, createSimpleModifier(
				HolderSet.direct(biomeGetter.getOrThrow(TFBiomes.SNOWY_FOREST), biomeGetter.getOrThrow(TFBiomes.HIGHLANDS)),
				placedFeatureGetter.getOrThrow(GourmetPlacedFeatures.WILD_POTATOES)
		));
		context.register(WILD_RICE, createSimpleModifier(
				HolderSet.direct(biomeGetter.getOrThrow(TFBiomes.SWAMP), biomeGetter.getOrThrow(TFBiomes.STREAM)),
				placedFeatureGetter.getOrThrow(GourmetPlacedFeatures.WILD_RICE)
		));
		context.register(BROWN_MUSHROOM_COLONIES, createSimpleModifier(
				HolderSet.direct(biomeGetter.getOrThrow(TFBiomes.MUSHROOM_FOREST), biomeGetter.getOrThrow(TFBiomes.DENSE_MUSHROOM_FOREST)),
				placedFeatureGetter.getOrThrow(GourmetPlacedFeatures.BROWN_MUSHROOM_COLONIES)
		));
		context.register(RED_MUSHROOM_COLONIES, createSimpleModifier(
				HolderSet.direct(biomeGetter.getOrThrow(TFBiomes.MUSHROOM_FOREST), biomeGetter.getOrThrow(TFBiomes.DENSE_MUSHROOM_FOREST)),
				placedFeatureGetter.getOrThrow(GourmetPlacedFeatures.RED_MUSHROOM_COLONIES)
		));
	}

	private static BiomeModifier createSimpleModifier(HolderSet<Biome> biomes, Holder<PlacedFeature> feature) {
		return new BiomeModifiers.AddFeaturesBiomeModifier(
				biomes,
				HolderSet.direct(feature),
				GenerationStep.Decoration.VEGETAL_DECORATION
		);
	}
}
