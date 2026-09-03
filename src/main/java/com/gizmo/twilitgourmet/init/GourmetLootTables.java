package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.Set;

public class GourmetLootTables {

	private static final Set<ResourceKey<LootTable>> LOOT_TABLES = Sets.newHashSet();

	public static final ResourceKey<LootTable> GIANT_APPLE = register("injection/giant_apple");
	public static final ResourceKey<LootTable> AURORA_PALACE_INJECTION = register("injection/aurora_palace");
	public static final ResourceKey<LootTable> STRONGHOLD_INJECTION = register("injection/stonghold");
	public static final ResourceKey<LootTable> SEED_INJECTION = register("injection/structure_seeds");

	private static ResourceKey<LootTable> register(String id) {
		return register(ResourceKey.create(Registries.LOOT_TABLE, TwilitGourmet.prefix(id)));
	}

	private static ResourceKey<LootTable> register(ResourceKey<LootTable> id) {
		if (LOOT_TABLES.add(id)) {
			return id;
		} else {
			throw new IllegalArgumentException(id + " is already a registered built-in loot table");
		}
	}

	public static Set<ResourceKey<LootTable>> allBuiltin() {
		return Collections.unmodifiableSet(LOOT_TABLES);
	}
}
