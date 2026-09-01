package com.gizmo.twilitgourmet.compat;

import com.gizmo.twilitgourmet.init.GourmetItems;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiInitRegistry;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;

@EmiEntrypoint
public class EmiCompat implements EmiPlugin {
	@Override
	public void initialize(EmiInitRegistry registry) {
		registry.disableStack(EmiStack.of(GourmetItems.SHELL_HELMET));
	}

	@Override
	public void register(EmiRegistry registry) {
	}
}
