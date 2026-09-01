package com.gizmo.twilightgourmet.datagen.assets;

import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetSounds;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class SoundGenerator extends SoundDefinitionsProvider {

	public SoundGenerator(PackOutput output, ExistingFileHelper helper) {
		super(output, TwilitGourmet.MODID, helper);
	}

	@Override
	public void registerSounds() {
		this.add(GourmetSounds.SHELL_CRACK, SoundDefinition.definition()
				.with(SoundDefinition.Sound.sound(TwilitGourmet.prefix("shell_crack"), SoundDefinition.SoundType.SOUND))
				.subtitle("subtitles.twilitgourmet.item.crab_shell.crack"));
	}
}
