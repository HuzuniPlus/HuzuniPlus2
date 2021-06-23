package me.travis.wurstplus.mixins;

import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.event.WurstplusEventBus;
import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventSetupFog;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// External.

@Mixin(value = EntityRenderer.class)
public class WurstplusMixinEntityRenderer {
    
    @Inject(method = "setupFog", at = @At("HEAD"), cancellable = true)
    public void setupFog(int startCoords, float partialTicks, CallbackInfo p_Info)
    {
        WurstplusEventSetupFog event = new WurstplusEventSetupFog(startCoords, partialTicks);
        WurstplusEventBus.EVENT_BUS.post(event);
        
        if (event.isCancelled()) {
			return;
        }
        
    }
    @Inject(method={"hurtCameraEffect"}, at={@At(value="HEAD")}, cancellable=true)
    public void hurtCameraEffectHook(float ticks, CallbackInfo info) {
        if (Wurstplus.get_hack_manager().get_module_with_tag("NoRender").is_active() && Wurstplus.get_setting_manager().get_setting_with_tag("NoRender", "HurtCam").get_value(true)) {
            info.cancel();
        }
    }

}