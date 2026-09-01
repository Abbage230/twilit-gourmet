package com.gizmo.twilitgourmet.item;

import com.gizmo.twilitgourmet.init.GourmetItems;
import com.gizmo.twilitgourmet.init.GourmetSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CrabPartItem extends Item {

	public CrabPartItem(Properties properties) {
		super(properties);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (entity instanceof Player player) {
			ItemStack fragment = new ItemStack(GourmetItems.CRAB_SHELL_FRAGMENT.get(), entity.getRandom().nextInt(2) + 1);
			if (!player.getInventory().add(fragment)) {
				player.drop(fragment, true);
			}
			level.playLocalSound(player, GourmetSounds.SHELL_CRACK.get(), SoundSource.PLAYERS, 1.0F, player.getRandom().nextFloat() * 0.1F + 0.9F);
		}
		return super.finishUsingItem(stack, level, entity);
	}
}
