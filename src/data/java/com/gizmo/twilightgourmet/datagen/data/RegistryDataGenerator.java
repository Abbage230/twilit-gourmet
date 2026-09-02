package com.gizmo.twilightgourmet.datagen.data;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetBiomeModifiers;
import com.gizmo.twilitgourmet.init.GourmetPlacedFeatures;
import com.gizmo.twilitgourmet.init.GourmetSyrups;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import twilightforest.TwilightForestMod;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {

	private static final RegistrySetBuilder REGISTRIES = new RegistrySetBuilder()
			.add(Registries.PLACED_FEATURE, GourmetPlacedFeatures::bootstrap)
			.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, GourmetBiomeModifiers::bootstrap)
			.add(TwilitGourmet.SYRUP_KEY, GourmetSyrups::bootstrap);

	public RegistryDataGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, REGISTRIES, Set.of("minecraft", TwilightForestMod.ID, FarmersDelight.MODID, TwilitGourmet.MODID));
	}
}
