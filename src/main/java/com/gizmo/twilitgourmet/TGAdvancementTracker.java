package com.gizmo.twilitgourmet;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;

public class TGAdvancementTracker {

	public AppleData data;
	public int syrupPancakeStacksEaten;

	public static final Codec<TGAdvancementTracker> CODEC = RecordCodecBuilder.create(instance -> instance.group(
					AppleData.CODEC.fieldOf("apple_data").forGetter(o -> o.data),
					Codec.INT.fieldOf("syrup_pancakes_eaten").forGetter(o -> o.syrupPancakeStacksEaten))
			.apply(instance, TGAdvancementTracker::new));

	public TGAdvancementTracker() {
		this(null, 0, 0);
	}

	public TGAdvancementTracker(@Nullable BlockPos lastPos, int slicesEaten, int syrupPancakesEaten) {
		this(new AppleData(lastPos, slicesEaten), syrupPancakesEaten);
	}

	private TGAdvancementTracker(AppleData data, int syrupPancakesEaten) {
		this.data = data;
		this.syrupPancakeStacksEaten = syrupPancakesEaten;
	}

	public static class AppleData {
		@Nullable
		public BlockPos lastApplePos;
		public int slicesEaten;

		public static final Codec<AppleData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
						BlockPos.CODEC.optionalFieldOf("last_apple_pos", null).forGetter(o -> o.lastApplePos),
						Codec.INT.fieldOf("slices_eaten").forGetter(o -> o.slicesEaten))
				.apply(instance, AppleData::new));

		public AppleData(@Nullable BlockPos lastApplePos, int slicesEaten) {
			this.lastApplePos = lastApplePos;
			this.slicesEaten = slicesEaten;
		}
	}
}
