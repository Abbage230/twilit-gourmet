package com.gizmo.twilitgourmet.block.entity;

import com.gizmo.twilitgourmet.Syrup;
import com.gizmo.twilitgourmet.TwilitGourmet;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.Nullable;

public interface SyrupHolder {

	@Nullable
	ResourceKey<Syrup> getSyrupKey();

	@Nullable
	default Syrup getSyrup(HolderLookup.Provider registries) {
		if (this.getSyrupKey() != null) {
			return registries.lookupOrThrow(TwilitGourmet.SYRUP_KEY).getOrThrow(this.getSyrupKey()).value();
		}
		return null;
	}

	void setSyrup(@Nullable ResourceKey<Syrup> syrup);
}
