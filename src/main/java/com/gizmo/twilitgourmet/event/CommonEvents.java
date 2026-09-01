package com.gizmo.twilitgourmet.event;

import com.gizmo.twilitgourmet.Syrup;
import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.gizmo.twilitgourmet.init.GourmetItems;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;
import vectorwing.farmersdelight.common.item.enchantment.BackstabbingEnchantment;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class CommonEvents {

	public static void register(IEventBus bus) {
		bus.addListener(RegisterDataMapTypesEvent.class, event -> event.register(TwilitGourmet.SYRUP_DATA_MAP));
		bus.addListener(DataPackRegistryEvent.NewRegistry.class, event -> event.dataPackRegistry(TwilitGourmet.SYRUP_KEY, Syrup.CODEC, Syrup.CODEC));
		bus.addListener(BlockEntityTypeAddBlocksEvent.class, event -> event.modify(ModBlockEntityTypes.CABINET.get(),
				GourmetBlocks.TWILIGHT_OAK_CABINET.get(),
				GourmetBlocks.CANOPY_CABINET.get(),
				GourmetBlocks.MANGROVE_CABINET.get(),
				GourmetBlocks.DARK_CABINET.get(),
				GourmetBlocks.TIME_CABINET.get(),
				GourmetBlocks.TRANSFORMATION_CABINET.get(),
				GourmetBlocks.MINING_CABINET.get(),
				GourmetBlocks.SORTING_CABINET.get()));

		NeoForge.EVENT_BUS.addListener(CommonEvents::knightmetalKnifeHitBonus);
	}

	private static void knightmetalKnifeHitBonus(LivingIncomingDamageEvent event) {
		if (!event.isCanceled()) {
			LivingEntity target = event.getEntity();

			DamageContainer container = event.getContainer();
			if (!target.level().isClientSide()) {
				ItemStack weapon = container.getSource().getWeaponItem();

				if (weapon != null && weapon.is(GourmetItems.KNIGHTMETAL_KNIFE)) {
					if (BackstabbingEnchantment.isLookingBehindTarget(event.getEntity(), event.getSource().getSourcePosition())) {
						container.setNewDamage(container.getOriginalDamage() + 2);
						// enchantment attack sparkles
						((ServerLevel) target.level()).getChunkSource().broadcastAndSend(target, new ClientboundAnimatePacket(target, 5));
					}
				}
			}
		}
	}
}
