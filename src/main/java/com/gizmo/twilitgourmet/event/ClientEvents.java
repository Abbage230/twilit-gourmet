package com.gizmo.twilitgourmet.event;

import com.gizmo.twilitgourmet.Syrup;
import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.block.TreeTapperBlock;
import com.gizmo.twilitgourmet.block.entity.SyrupHolder;
import com.gizmo.twilitgourmet.client.ShellHelmetExtension;
import com.gizmo.twilitgourmet.client.ShellHelmetModel;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.gizmo.twilitgourmet.init.GourmetDataComponents;
import com.gizmo.twilitgourmet.init.GourmetItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

public class ClientEvents {

	public static void register(IEventBus bus) {
		bus.addListener(ClientEvents::registerExtensions);
		bus.addListener(ClientEvents::registerLayers);
		bus.addListener(ClientEvents::registerBlockColors);
		bus.addListener(ClientEvents::registerItemColors);
	}

	private static void registerExtensions(RegisterClientExtensionsEvent event) {
		event.registerItem(new ShellHelmetExtension(), GourmetItems.SHELL_HELMET);
	}

	private static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ShellHelmetModel.SHELL_HELMET_INNER, () -> ShellHelmetModel.addPieces(LayerDefinitions.INNER_ARMOR_DEFORMATION));
		event.registerLayerDefinition(ShellHelmetModel.SHELL_HELMET_OUTER, () -> ShellHelmetModel.addPieces(LayerDefinitions.OUTER_ARMOR_DEFORMATION));
	}

	private static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
		event.register((state, getter, pos, index) -> {
			if (getter != null && pos != null && getter.getBlockEntity(pos) instanceof SyrupHolder holder && holder.getSyrupKey() != null && index == 0) {
				return holder.getSyrup(Minecraft.getInstance().level.registryAccess()).color();
			}
			return -1;
		}, GourmetBlocks.SYRUP_CAULDRON.get(), GourmetBlocks.PANCAKE_STACK.get());

		//oh god
		event.register((state, getter, pos, index) -> {
			if (getter != null && pos != null && index == 1) {
				ResourceKey<Syrup> syrup = getter.getBlockState(pos.relative(state.getValue(TreeTapperBlock.FACING).getOpposite())).getBlock().builtInRegistryHolder().getData(TwilitGourmet.SYRUP_DATA_MAP);
				if (syrup != null) {
					return Minecraft.getInstance().level.registryAccess().registryOrThrow(TwilitGourmet.SYRUP_KEY).get(syrup).color();
				}
			}
			return -1;
		}, GourmetBlocks.TREE_TAPPER.get());
	}

	private static void registerItemColors(RegisterColorHandlersEvent.Item event) {
		event.register((stack, index) -> {
			Level level = Minecraft.getInstance().level;
			if (level != null && index == 0) {
				ResourceKey<Syrup> syrup = stack.get(GourmetDataComponents.SYRUP);
				if (syrup != null) {
					return level.registryAccess().registryOrThrow(TwilitGourmet.SYRUP_KEY).getOrThrow(syrup).color();
				}
			}
			return -1;
		}, GourmetItems.SYRUP_BOTTLE.get());
	}
}
