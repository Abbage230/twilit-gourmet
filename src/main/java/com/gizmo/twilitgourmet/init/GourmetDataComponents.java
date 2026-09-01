package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.Syrup;
import com.gizmo.twilitgourmet.TwilitGourmet;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GourmetDataComponents {

	public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, TwilitGourmet.MODID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<ResourceKey<Syrup>>> SYRUP = COMPONENTS.register("syrup", () -> DataComponentType.<ResourceKey<Syrup>>builder().persistent(ResourceKey.codec(TwilitGourmet.SYRUP_KEY)).networkSynchronized(ResourceKey.streamCodec(TwilitGourmet.SYRUP_KEY)).build());
}
