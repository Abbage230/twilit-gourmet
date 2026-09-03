package com.gizmo.twilightgourmet.datagen;

import com.gizmo.twilightgourmet.datagen.assets.BlockStateGenerator;
import com.gizmo.twilightgourmet.datagen.assets.ItemModelGenerator;
import com.gizmo.twilightgourmet.datagen.assets.LangGenerator;
import com.gizmo.twilightgourmet.datagen.assets.SoundGenerator;
import com.gizmo.twilightgourmet.datagen.data.*;
import com.gizmo.twilightgourmet.datagen.data.tags.BlockTagGenerator;
import com.gizmo.twilightgourmet.datagen.data.tags.ItemTagGenerator;
import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetSyrups;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import twilightforest.TwilightForestMod;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.Set;

@EventBusSubscriber(modid = TwilitGourmet.MODID)
public class DataGenerators {

	@SubscribeEvent
	public static void registerDatagen(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = event.getGenerator().getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();
		generator.addProvider(event.includeClient(), new BlockStateGenerator(output, helper));
		generator.addProvider(event.includeClient(), new ItemModelGenerator(output, helper));
		generator.addProvider(event.includeClient(), new LangGenerator(output));
		generator.addProvider(event.includeClient(), new SoundGenerator(output, helper));

		var datapack = new RegistryDataGenerator(output, event.getLookupProvider());
		var provider = datapack.getRegistryProvider();

		generator.addProvider(event.includeServer(), datapack);
		generator.addProvider(event.includeServer(), new AdvancementGenerator(output, provider, helper));
		generator.addProvider(event.includeServer(), new DataMapGenerator(output, provider));
		generator.addProvider(event.includeServer(), new LootGenerator(output, provider));
		generator.addProvider(event.includeServer(), new LootModifierGenerator(output, provider));
		generator.addProvider(event.includeServer(), new RecipeGenerator(output, provider));

		var blockTags = new BlockTagGenerator(output, provider, helper);
		generator.addProvider(event.includeServer(), blockTags);
		generator.addProvider(event.includeServer(), new ItemTagGenerator(output, provider, blockTags.contentsGetter(), helper));
	}
}
