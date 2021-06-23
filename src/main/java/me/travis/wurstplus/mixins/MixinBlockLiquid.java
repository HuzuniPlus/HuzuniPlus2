package me.travis.wurstplus.mixins;

import cf.warriorcrystal.other.xulu.Wrapper;
import me.travis.wurstplus.Wurstplus;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SideOnly(Side.CLIENT)
@Mixin(value = BlockLiquid.class, priority = 9999)
public class MixinBlockLiquid {


    @Inject(method = "canCollideCheck", at = @At("RETURN"), cancellable = true, require = 1)
    private void IcanCollide(IBlockState state, boolean hitIfLiquid, CallbackInfoReturnable<Boolean> returnable) {
        returnable.setReturnValue(Wurstplus.get_hack_manager().get_module_with_tag("LiquidInteract").is_active());
    }


        }


