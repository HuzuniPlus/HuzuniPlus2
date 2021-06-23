package me.travis.wurstplus.wurstplustwo.hacks.movement;

import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraft.util.math.AxisAlignedBB;

public class MapReverseStep extends WurstplusHack {
    
    public MapReverseStep() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);

		this.name        = "Reverse Step";
		this.tag         = "ReverseStep";
		this.description = "anashei";
    }

    @Override
    public void update() {

    if (!mc.player.onGround || mc.player.isOnLadder() || mc.player.isInWater() || mc.player.isInLava() || mc.player.movementInput.jump || mc.player.noClip) return;
    if (mc.player.moveForward == 0 && mc.player.moveStrafing == 0) return;
    
    final double n = get_n_normal();
    mc.player.motionY = -1;
    }
    public double get_n_normal() {
        
        mc.player.stepHeight = 0.5f;

        double max_y = -1;

        final AxisAlignedBB grow = mc.player.getEntityBoundingBox().offset(0, 0.05, 0).grow(0.05);

        if (!mc.world.getCollisionBoxes(mc.player, grow.offset(0, 2, 0)).isEmpty()) return 100;

        for (final AxisAlignedBB aabb : mc.world.getCollisionBoxes(mc.player, grow)) {

            if (aabb.maxY > max_y) {
                max_y = aabb.maxY;
            }

        }

        return max_y - mc.player.posY;

    }



}
    