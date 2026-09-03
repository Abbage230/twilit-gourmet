package com.gizmo.twilitgourmet;

import com.gizmo.twilitgourmet.event.ClientEvents;
import com.gizmo.twilitgourmet.event.CommonEvents;
import com.gizmo.twilitgourmet.init.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

import java.util.Locale;

@Mod(TwilitGourmet.MODID)
public class TwilitGourmet {

	public static final String MODID = "twilitgourmet";

	public static final TagKey<Item> COOKS_CUTTING_BOARD_ITEMS = ItemTags.create(prefix("cooks_cutting_board_items"));

	public static final ResourceKey<Registry<Syrup>> SYRUP_KEY = ResourceKey.createRegistryKey(prefix("syrups"));

	public static final DataMapType<Block, ResourceKey<Syrup>> SYRUP_DATA_MAP = DataMapType.builder(TwilitGourmet.prefix("syrup"), Registries.BLOCK, ResourceKey.codec(SYRUP_KEY)).synced(ResourceKey.codec(SYRUP_KEY), false).build();

	public TwilitGourmet(IEventBus bus, Dist dist) {
		GourmetArmorMaterials.ARMOR_MATERIALS.register(bus);
		GourmetBlocks.BLOCKS.register(bus);
		GourmetBlocks.BLOCK_ENTITIES.register(bus);
		GourmetCriteriaTriggers.TRIGGERS.register(bus);
		GourmetDataAttachments.ATTACHMENT_TYPES.register(bus);
		GourmetItems.ITEMS.register(bus);
		GourmetTabs.TABS.register(bus);
		GourmetSounds.SOUNDS.register(bus);
		GourmetDataComponents.COMPONENTS.register(bus);

		if (dist == Dist.CLIENT) {
			ClientEvents.register(bus);
		}

		CommonEvents.register(bus);
	}

	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name.toLowerCase(Locale.ROOT));
	}
}
