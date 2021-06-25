package me.travis.wurstplus.wurstplustwo.hacks.misc;

import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventPlayerTravel;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;

import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import org.lwjgl.input.Mouse;

public class MCP extends WurstplusHack {
	   private boolean clicked;
	public MCP() {
		super(WurstplusCategory.WURSTPLUS_MISC);
		this.name = "MCP";
		this.tag = "MCP";
		this.description = "throws a pearl when middleclick";
	}
    @EventHandler
    private final Listener<WurstplusEventPlayerTravel> listener = new Listener<>(p_Event -> {
        if(mc.currentScreen == null && Mouse.isButtonDown(2)) {
            if(!this.clicked) {
                    final int pearlSLot = findPearlInHotbar();
                    if(pearlSLot  != -1) {
                        final int oldSlot = mc.player.inventory.currentItem;
                        mc.player.inventory.currentItem = pearlSLot;
                        mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
                        mc.player.inventory.currentItem = oldSlot;
                    }
                }
                this.clicked = true;
            } else {
                this.clicked = false;
            }
        });

    private boolean isItemStackPearl(final ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemEnderPearl;
    }


    private int findPearlInHotbar() {
        for (int index = 0; InventoryPlayer.isHotbar(index); index++) {
            if (isItemStackPearl(mc.player.inventory.getStackInSlot(index))) return index;
        }
        return -1;
    }
}
