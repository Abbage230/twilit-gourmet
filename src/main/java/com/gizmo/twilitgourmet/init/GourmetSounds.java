package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.TwilitGourmet;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GourmetSounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, TwilitGourmet.MODID);

	public static final DeferredHolder<SoundEvent, SoundEvent> SHELL_CRACK = createEvent("item.twilitgourmet.crab_shell.crack");

	private static DeferredHolder<SoundEvent, SoundEvent> createEvent(String sound) {
		return SOUNDS.register(sound, () -> SoundEvent.createVariableRangeEvent(TwilitGourmet.prefix(sound)));
	}
}
