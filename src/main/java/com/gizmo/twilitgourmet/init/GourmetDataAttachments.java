package com.gizmo.twilitgourmet.init;

import com.gizmo.twilitgourmet.TGAdvancementTracker;
import com.gizmo.twilitgourmet.TwilitGourmet;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class GourmetDataAttachments {

	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, TwilitGourmet.MODID);

	public static final DeferredHolder<AttachmentType<?>, AttachmentType<TGAdvancementTracker>> ADVANCEMENT_TRACKER = ATTACHMENT_TYPES.register("advancement_tracker", () -> AttachmentType.builder(TGAdvancementTracker::new).serialize(TGAdvancementTracker.CODEC).build());
}
