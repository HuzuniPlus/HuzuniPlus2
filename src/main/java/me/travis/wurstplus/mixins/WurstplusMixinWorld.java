package me.travis.wurstplus.mixins;

import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.event.WurstplusEventBus;
import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventEntityRemoved;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = World.class)
public class WurstplusMixinWorld {
    
    @Inject(method = "onEntityRemoved", at = @At("HEAD"), cancellable = true)
    public void onEntityRemoved(Entity event_packet, CallbackInfo p_Info)
    {
        WurstplusEventEntityRemoved l_Event = new WurstplusEventEntityRemoved(event_packet);

        WurstplusEventBus.EVENT_BUS.post(l_Event);

    }

    @Inject(method = "spawnEntity", at = @At("HEAD"), cancellable = true)
    public void spawnEntity(Entity entityIn, CallbackInfoReturnable<Boolean> cir) {
        if(Wurstplus.get_hack_manager().get_module_with_tag("NoRender").is_active() && Wurstplus.get_setting_manager().get_setting_with_tag("NoRender", "FireWorks").get_value(true) && entityIn instanceof EntityFireworkRocket) {
            cir.cancel();
        }
    }

    // cancel ticking rockets
    @Inject(method = "updateEntity", at = @At("HEAD"), cancellable = true)
    public void updateEntity(Entity ent, CallbackInfo ci) {
        if(Wurstplus.get_hack_manager().get_module_with_tag("NoRender").is_active() && Wurstplus.get_setting_manager().get_setting_with_tag("NoRender", "FireWorks").get_value(true) && ent instanceof EntityFireworkRocket) {
            ci.cancel();
        }
    }
}
