package com.gizmo.twilitgourmet.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Consumer;

//TODO remove once https://github.com/neoforged/NeoForge/pull/3453 is merged and backported to 1.21.1
@Deprecated
@Mixin(NestedLootTable.class)
public class NestedLootTableMixin {

	@Redirect(method = "createItemStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootTable;getRandomItemsRaw(Lnet/minecraft/world/level/storage/loot/LootContext;Ljava/util/function/Consumer;)V"), remap = false)
	public void allowNestedTablesToUseGLMs(LootTable instance, LootContext context, Consumer<ItemStack> output) {
		instance.getRandomItems(context, output);
	}
}
