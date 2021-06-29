package me.travis.wurstplus.mixins;

import me.travis.wurstplus.Wurstplus;
import net.minecraft.client.gui.GuiBossOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiBossOverlay.class)
//from past
public class PASTMixinGuiBossOverlay {
    @Inject(method = "renderBossHealth", at = @At("HEAD"), cancellable = true)
    private void renderBossHealth(CallbackInfo callbackInfo) {
        if (Wurstplus.get_hack_manager().get_module_with_tag("NoRender").is_active() && Wurstplus.get_setting_manager().get_setting_with_tag("NoRender", "NoBossOverlay").get_value(true)) {
            callbackInfo.cancel();
        }
    }
}