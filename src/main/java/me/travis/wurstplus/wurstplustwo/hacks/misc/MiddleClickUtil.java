package me.travis.wurstplus.wurstplustwo.hacks.misc;

import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventPlayerTravel;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;

import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import org.lwjgl.input.Mouse;
//made by mapeadoh :coolguy:
// pasted from AnasheClient+
public class MiddleClickUtil extends WurstplusHack {
    private boolean clicked;
    public MiddleClickUtil() {
        super(WurstplusCategory.WURSTPLUS_MISC);
        this.name = "MCUtils";
        this.tag = "MiddleClickUtils";
        this.description = "diferent actions from press middleclick, in EXP mode use with feetXP and hotbarreplenish";
    }

    WurstplusSetting mode = create("Function", "Mode", "EPearl", combobox("EPearl", "EXP"));

    @EventHandler
    private final Listener<WurstplusEventPlayerTravel> listener = new Listener<>(p_Event -> {
        if (mc.currentScreen == null && Mouse.isButtonDown(2)) {
                if (!this.clicked && mode.in("EPearl")) {
                    final int pearlSLot = findPearlInHotbar();
                    if (pearlSLot != -1) {
                        final int oldSlot = mc.player.inventory.currentItem;
                        mc.player.inventory.currentItem = pearlSLot;
                        mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
                        mc.player.inventory.currentItem = oldSlot;
                    }
                }
                if (!this.clicked && mode.in("EXP")) {
                    final int EXPSLot = findEXPInHotbar();
                    if (EXPSLot != -1) {
                        final int oldSlot = mc.player.inventory.currentItem;
                        mc.player.inventory.currentItem = EXPSLot;
                        mc.playerController.processRightClick(mc.player, mc.world, EnumHand.MAIN_HAND);
                        mc.player.inventory.currentItem = oldSlot;
                    }
                    this.clicked = true;
                } else {
                    this.clicked = false;
                }
            }
    });

    private boolean isItemStackPearl(final ItemStack itemStack) {
        return itemStack.getItem() instanceof ItemEnderPearl;
    }
    private boolean isItemStackEXP(final ItemStack itemStack2) {
        return itemStack2.getItem() instanceof ItemExpBottle;
    }

    private int findPearlInHotbar() {
        for (int index = 0; InventoryPlayer.isHotbar(index); index++) {
            if (isItemStackPearl(mc.player.inventory.getStackInSlot(index))) return index;
        }
        return -1;
    }
        private int findEXPInHotbar() {
            for (int index = 0; InventoryPlayer.isHotbar(index); index++) {
                if (isItemStackEXP(mc.player.inventory.getStackInSlot(index))) return index;
            }
            return -1;
        }
}