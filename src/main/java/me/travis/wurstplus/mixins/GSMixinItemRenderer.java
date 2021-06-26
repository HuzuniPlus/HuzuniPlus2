package me.travis.wurstplus.mixins;

import cf.warriorcrystal.other.gamesense.TransformSideFirstPersonEvent;
import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.event.WurstplusEventBus;
//import com.gamesense.client.module.modules.render.NoRender;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Check ViewModel.class for further credits
 */

@Mixin(ItemRenderer.class)
public class GSMixinItemRenderer {
// idk what is this
    @Inject(method = "transformSideFirstPerson", at = @At("HEAD"))
    public void transformSideFirstPerson(EnumHandSide hand, float p_187459_2_, CallbackInfo callbackInfo) {
        TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
        WurstplusEventBus.EVENT_BUS.post(event);
    }
// noeat setting, im retard it isnt fov setting
    @Inject(method = "transformEatFirstPerson", at = @At("HEAD"), cancellable = true)
    public void transformEatFirstPerson(float p_187454_1_, EnumHandSide hand, ItemStack stack, CallbackInfo callbackInfo) {
        TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
        WurstplusEventBus.EVENT_BUS.post(event);

        if (Wurstplus.get_hack_manager().get_module_with_tag("GSViewModel").is_active() && Wurstplus.get_setting_manager().get_setting_with_tag("GSViewModel", "CancelEatAnim").get_value(true)) {
            callbackInfo.cancel();
        }
    }
// idk what is it
    @Inject(method = "transformFirstPerson", at = @At("HEAD"))
    public void transformFirstPerson(EnumHandSide hand, float p_187453_2_, CallbackInfo callbackInfo) {
        TransformSideFirstPersonEvent event = new TransformSideFirstPersonEvent(hand);
        WurstplusEventBus.EVENT_BUS.post(event);
    }
// por si los 2 mixins no andan bien juntos, agrego esto por las dudas
    @Inject(method={"renderFireInFirstPerson"}, at={@At(value="HEAD")}, cancellable=true)
    public void renderFireInFirstPersonHook(CallbackInfo info) {
        if (Wurstplus.get_hack_manager().get_module_with_tag("NoRender").is_active() && Wurstplus.get_setting_manager().get_setting_with_tag("NoRender", "Fire").get_value(true)) {
            info.cancel();
        }
    }
// gs no render, no overlay
    /*@Inject(method = "renderOverlays", at = @At("HEAD"), cancellable = true)
    public void renderOverlays(float partialTicks, CallbackInfo callbackInfo) {
        NoRender noRender = ModuleManager.getModule(NoRender.class);

        if (noRender.isEnabled() && noRender.noOverlay.getValue()) {
            callbackInfo.cancel();
        }
    }*/
}