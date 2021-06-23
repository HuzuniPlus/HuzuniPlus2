package me.travis.wurstplus.wurstplustwo.hacks.misc;

import cf.warriorcrystal.other.xulu.Wrapper;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.OnePopBlockInteractionHelper;
import me.travis.wurstplus.wurstplustwo.util.WurstplusBlockInteractHelper;
import me.travis.wurstplus.wurstplustwo.util.WurstplusEntityUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Scaffold extends WurstplusHack {
    WurstplusSetting future = this.create("Ticks", "Ticks", 0, 0, 60);
    WurstplusSetting tower = this.create("Tower", "Tower", true);

    public Scaffold() {
        super(WurstplusCategory.WURSTPLUS_MISC);
        this.name = "Scaffold";
        this.tag = "Scaffold";
        this.description = "eskafol from cebe plos beonce";
    }

    @Override
    public void update() {
        if (mc.player == null) {
            return;
        }
        Vec3d vec3d = WurstplusEntityUtil.getInterpolatedPos((Entity)mc.player, this.future.get_value(0));
        BlockPos blockPos = new BlockPos(vec3d).down();
        BlockPos belowBlockPos = blockPos.down();
        if (!Wrapper.getWorld().getBlockState(blockPos).getMaterial().isReplaceable()) {
            return;
        }
        int newSlot = -1;
        for (int i = 0; i < 9; ++i) {
            Block block;
            ItemStack stack = Wrapper.getPlayer().inventory.getStackInSlot(i);
            if (stack == ItemStack.EMPTY || !(stack.getItem() instanceof ItemBlock) || OnePopBlockInteractionHelper.blackList.contains((Object)(block = ((ItemBlock)stack.getItem()).getBlock())) || block instanceof BlockContainer || !Block.getBlockFromItem((Item)stack.getItem()).getDefaultState().isFullBlock() || ((ItemBlock)stack.getItem()).getBlock() instanceof BlockFalling && Wrapper.getWorld().getBlockState(belowBlockPos).getMaterial().isReplaceable()) continue;
            newSlot = i;
            break;
        }
        if (newSlot == -1) {
            return;
        }
        int oldSlot = Wrapper.getPlayer().inventory.currentItem;
        Wrapper.getPlayer().inventory.currentItem = newSlot;
        if (!OnePopBlockInteractionHelper.checkForNeighbours(blockPos)) {
            return;
        }
        OnePopBlockInteractionHelper.placeBlockScaffold(blockPos);
        Wrapper.getPlayer().inventory.currentItem = oldSlot;
    }
}


