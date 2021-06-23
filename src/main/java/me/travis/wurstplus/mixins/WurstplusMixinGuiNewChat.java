package me.travis.wurstplus.mixins;

//Core

import me.travis.wurstplus.Wurstplus;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//Data
//randospongethingo

@Mixin({ GuiNewChat.class })
public class WurstplusMixinGuiNewChat {

    @Redirect(method = "drawChat", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;drawRect(IIIII)V", ordinal = 0))
    private void overrideChatBackgroundColour(int left, int top, int right, int bottom, int color) {

        if (Wurstplus.get_setting_manager().get_setting_with_tag("ChatModifications", "ClearChatbox").get_value(true)) {

            Gui.drawRect(left, top, right, bottom, color);

        }

    }

}