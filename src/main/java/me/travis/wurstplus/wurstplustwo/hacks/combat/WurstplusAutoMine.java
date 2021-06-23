package me.travis.wurstplus.wurstplustwo.hacks.combat;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.WurstplusBreakUtil;
import me.travis.wurstplus.wurstplustwo.util.WurstplusEntityUtil;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMessageUtil;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.math.BlockPos;

public class WurstplusAutoMine extends WurstplusHack {

    public WurstplusAutoMine() {
        super(WurstplusCategory.WURSTPLUS_COMBAT);

        this.name        = "Auto City";
        this.tag         = "AutoCity";
        this.description = "jumpy is now never going to use the client again";
    }

    WurstplusSetting end_crystal = create("End Crystal", "MineEndCrystal", false);
    WurstplusSetting range = create("Range", "MineRange", 4, 0, 6);
    WurstplusSetting ray_trace = create("Ray Trace", "MineRayTrace", false);
    WurstplusSetting swap = create("Swap to Pick","MineSwap", true);

    private BlockPos target_block = null;

    @Override
    protected void enable() {
        target_block = null;

        for (EntityPlayer player : mc.world.playerEntities) {
            if (mc.player.getDistance(player) > range.get_value(1)) continue;

            BlockPos p = WurstplusEntityUtil.is_cityable(player, end_crystal.get_value(true));

            if (p != null) {
                target_block = p;
            }
        }

        if (target_block == null) {
            WurstplusMessageUtil.send_client_message("cannot find block");
            this.set_active(false);
            return;
        }

        int pickSlot = findPickaxe();
        if (swap.get_value(true) && pickSlot != -1) {
            mc.player.inventory.currentItem = pickSlot;
        }

        WurstplusBreakUtil.set_current_block(target_block);
    }

    @Override
    public void update() {
        WurstplusBreakUtil.update(range.get_value(1), ray_trace.get_value(true));
        if(mc.world.getBlockState(target_block).getBlock() instanceof BlockAir) {
            this.set_active(false);
        }
    }

    @Override
    protected void disable() {
        WurstplusBreakUtil.set_current_block(null);
    }

    private int findPickaxe() {
        for (int i = 0; i < 9; i++) {
            if (mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemPickaxe) {
                return i;
            }
        }

        return -1;
    }
}