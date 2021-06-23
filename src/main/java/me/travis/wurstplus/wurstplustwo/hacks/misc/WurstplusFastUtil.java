package me.travis.wurstplus.wurstplustwo.hacks.misc;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;


public class WurstplusFastUtil extends WurstplusHack {

	public WurstplusFastUtil() {
		super(WurstplusCategory.WURSTPLUS_MISC);

		this.name = "Fast Util";
		this.tag = "FastUtil";
		this.description = "use things faster";
	}

	WurstplusSetting fast_place = create("Place", "WurstplusFastPlace", false);
	WurstplusSetting fast_break = create("Break", "WurstplusFastBreak", false);
	WurstplusSetting crystal = create("Crystal", "WurstplusFastCrystal", false);
	WurstplusSetting exp = create("EXP", "WurstplusFastExp", true);
	WurstplusSetting bow = create("Bow", "Bow", true);

	@Override
	public void update() {
		Item main = mc.player.getHeldItemMainhand().getItem();
		Item off = mc.player.getHeldItemOffhand().getItem();

		boolean main_exp = main instanceof ItemExpBottle;
		boolean off_exp = off instanceof ItemExpBottle;
		boolean main_cry = main instanceof ItemEndCrystal;
		boolean off_cry = off instanceof ItemEndCrystal;

		if (main_exp | off_exp && exp.get_value(true)) {
			mc.rightClickDelayTimer = 0;
		}

		if (main_cry | off_cry && crystal.get_value(true)) {
			mc.rightClickDelayTimer = 0;
		}

		if (!(main_exp | off_exp | main_cry | off_cry) && fast_place.get_value(true)) {
			mc.rightClickDelayTimer = 0;
		}

		if (fast_break.get_value(true)) {
			mc.playerController.blockHitDelay = 0;
		}
		if (bow.get_value(true)) {
			if (mc.player.getHeldItemMainhand().getItem() instanceof net.minecraft.item.ItemBow && mc.player.isHandActive() && mc.player.getItemInUseMaxCount() >= 3) {
				mc.player.connection.sendPacket((Packet) new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, mc.player.getHorizontalFacing()));
				mc.player.connection.sendPacket((Packet) new CPacketPlayerTryUseItem(mc.player.getActiveHand()));
				mc.player.stopActiveHand();
			}
		}
	}
}