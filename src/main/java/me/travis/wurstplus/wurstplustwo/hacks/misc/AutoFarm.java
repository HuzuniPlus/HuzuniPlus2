package me.travis.wurstplus.wurstplustwo.hacks.misc;

import java.util.ArrayList;

import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.util.math.BlockPos;
import java.util.List;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.block.BlockCrops;
import java.util.Comparator;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;

public class AutoFarm extends WurstplusHack
{
    WurstplusSetting range;
    WurstplusSetting tickDelay;
    int waitCounter;
    
    public AutoFarm() {
        super(WurstplusCategory.WURSTPLUS_MISC);
        this.range = this.create("Range", "AutoFarmRange", 4.5, 0.0, 10.0);
        this.tickDelay = this.create("TickDelay", "AutoFarmTickDelay", 5, 0, 100);
        this.waitCounter = 0;
        this.name = "Auto Farm";
        this.tag = "AutoFarm";
        this.description = "i farm u";
    }
    
    @Override
    public void update() {
        final List<BlockPos> blockPosList = this.getSphere(getPlayerPos(), (float)this.range.get_value(1), 1, false, true, 0);
        blockPosList.stream().sorted(Comparator.comparing(b -> mc.player.getDistance((double)b.getX(), (double)b.getY(), (double)b.getZ()))).forEach(blockPos -> {
            if (mc.world.getBlockState(blockPos).getBlock() instanceof BlockCrops) {
                if (this.waitCounter < this.tickDelay.get_value(1)) {
                    ++this.waitCounter;
                }
                else {
                    mc.playerController.clickBlock(blockPos, EnumFacing.UP);
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                    this.waitCounter = 0;
                }
            }
        });
    }
    
    public void enable() {
        this.waitCounter = 0;
    }
    
    public List<BlockPos> getSphere(final BlockPos loc, final float r, final int h, final boolean hollow, final boolean sphere, final int plus_y) {
        final List<BlockPos> circleblocks = new ArrayList<BlockPos>();
        final int cx = loc.getX();
        final int cy = loc.getY();
        final int cz = loc.getZ();
        for (int x = cx - (int)r; x <= cx + r; ++x) {
            for (int z = cz - (int)r; z <= cz + r; ++z) {
                for (int y = sphere ? (cy - (int)r) : cy; y < (sphere ? (cy + r) : ((float)(cy + h))); ++y) {
                    final double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
                    if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
                        final BlockPos l = new BlockPos(x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                }
            }
        }
        return circleblocks;
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
    }
}
