package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.TwilitGourmet;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import vectorwing.farmersdelight.common.world.WildCropGeneration;

import java.util.List;

public class GourmetPlacedFeatures {

	public static ResourceKey<PlacedFeature> WILD_ONIONS = register("wild_onions");
	public static ResourceKey<PlacedFeature> WILD_TOMATOES = register("wild_tomatoes");
	public static ResourceKey<PlacedFeature> WILD_CARROTS = register("wild_carrots");
	public static ResourceKey<PlacedFeature> WILD_POTATOES = register("wild_potatoes");
	public static ResourceKey<PlacedFeature> WILD_RICE = register("wild_rice");
	public static ResourceKey<PlacedFeature> BROWN_MUSHROOM_COLONIES = register("brown_mushroom_colony");
	public static ResourceKey<PlacedFeature> RED_MUSHROOM_COLONIES = register("red_mushroom_colony");

	private static ResourceKey<PlacedFeature> register(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, TwilitGourmet.prefix(name));
	}

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureLookup = context.lookup(Registries.CONFIGURED_FEATURE);
		context.register(WILD_ONIONS, createPlacedFeature(configuredFeatureLookup, WildCropGeneration.FEATURE_PATCH_WILD_ONIONS, 50));
		context.register(WILD_TOMATOES, createPlacedFeature(configuredFeatureLookup, WildCropGeneration.FEATURE_PATCH_WILD_TOMATOES, 40));
		context.register(WILD_CARROTS, createPlacedFeature(configuredFeatureLookup, WildCropGeneration.FEATURE_PATCH_WILD_CARROTS, 50));
		context.register(WILD_POTATOES, createPlacedFeature(configuredFeatureLookup, WildCropGeneration.FEATURE_PATCH_WILD_POTATOES, 40));
		context.register(WILD_RICE, createPlacedFeature(configuredFeatureLookup, WildCropGeneration.FEATURE_PATCH_WILD_RICE, 20));
		context.register(BROWN_MUSHROOM_COLONIES, createPlacedFeature(configuredFeatureLookup, WildCropGeneration.FEATURE_PATCH_BROWN_MUSHROOM_COLONIES, 15));
		context.register(RED_MUSHROOM_COLONIES, createPlacedFeature(configuredFeatureLookup, WildCropGeneration.FEATURE_PATCH_RED_MUSHROOM_COLONIES, 15));
	}

	private static PlacedFeature createPlacedFeature(HolderGetter<ConfiguredFeature<?, ?>> featureGetter, ResourceKey<ConfiguredFeature<?, ?>> feature, int rarity) {
		return new PlacedFeature(featureGetter.getOrThrow(feature), List.of(
				RarityFilter.onAverageOnceEvery(rarity),
				InSquarePlacement.spread(),
				HeightmapPlacement.onHeightmap(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES),
				BiomeFilter.biome()
		));
	}
}
