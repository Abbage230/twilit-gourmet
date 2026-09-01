package com.gizmo.twilitgourmet.block.entity;

import com.gizmo.twilitgourmet.Syrup;
import com.gizmo.twilitgourmet.TwilitGourmet;
import com.gizmo.twilitgourmet.init.GourmetBlocks;
import com.gizmo.twilitgourmet.init.GourmetSyrups;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SyrupCauldronBlockEntity extends BlockEntity implements SyrupHolder {

	@Nullable
	private ResourceKey<Syrup> syrup = GourmetSyrups.OAK;

	public SyrupCauldronBlockEntity(BlockPos pos, BlockState state) {
		super(GourmetBlocks.SYRUP_CAULDRON_BE.get(), pos, state);
	}

	@Nullable
	@Override
	public ResourceKey<Syrup> getSyrupKey() {
		return this.syrup;
	}

	@Override
	public void setSyrup(@Nullable ResourceKey<Syrup> syrup) {
		this.syrup = syrup;
	}

	@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		tag.put("syrup", ResourceKey.codec(TwilitGourmet.SYRUP_KEY).encodeStart(NbtOps.INSTANCE, this.getSyrupKey()).getOrThrow());
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		ResourceKey.codec(TwilitGourmet.SYRUP_KEY).parse(NbtOps.INSTANCE, tag.get("syrup")).resultOrPartial().ifPresent(this::setSyrup);
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		return this.saveCustomOnly(registries);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
}
