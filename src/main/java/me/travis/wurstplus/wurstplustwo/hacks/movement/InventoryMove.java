package me.travis.wurstplus.wurstplustwo.hacks.movement;

import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.input.Keyboard;

import java.util.function.Predicate;

public class InventoryMove extends WurstplusHack {
    public InventoryMove() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);
        this.name = "Inventory Walk";
        this.tag = "InvWalk";

        this.JUMP = mc.gameSettings.keyBindJump.getKeyCode();
        this.listener = new Listener((event) -> {
            if (!(mc.currentScreen instanceof GuiChat) && mc.currentScreen != null) {
                this.walk();
            }
        }, new Predicate[0]);

    }

    private static KeyBinding[] KEYS;
    int JUMP;
    @EventHandler
    public Listener listener;


    public void update() {
        if (!(mc.currentScreen instanceof GuiChat) && mc.currentScreen != null) {
            EntityPlayerSP var10000 = mc.player;
            var10000.rotationYaw += Keyboard.isKeyDown(205) ? 4.0F : (Keyboard.isKeyDown(203) ? -4.0F : 0.0F);
            mc.player.rotationPitch = (float)((double)mc.player.rotationPitch + (double)(Keyboard.isKeyDown(208) ? 4 : (Keyboard.isKeyDown(200) ? -4 : 0)) * 0.75D);
            mc.player.rotationPitch = MathHelper.clamp(mc.player.rotationPitch, -90.0F, 90.0F);
            if (Keyboard.isKeyDown(this.JUMP)) {
                if (!mc.player.isInLava() && !mc.player.isInWater()) {
                    if (mc.player.onGround) {
                        mc.player.jump();
                    }
                } else {
                    var10000 = mc.player;
                    var10000.motionY += 0.3799999952316284D;
                }
            }

            this.walk();
        }
    }

    public void walk() {
        KeyBinding[] keys = KEYS;
        int keys_n = keys.length;

        for(int keys_n_2 = 0; keys_n_2 < keys_n; ++keys_n_2) {
            KeyBinding key_binding = keys[keys_n_2];
            if (Keyboard.isKeyDown(key_binding.getKeyCode())) {
                if (key_binding.getKeyConflictContext() != KeyConflictContext.UNIVERSAL) {
                    key_binding.setKeyConflictContext(KeyConflictContext.UNIVERSAL);
                }

                KeyBinding.setKeyBindState(key_binding.getKeyCode(), true);
            } else {
                KeyBinding.setKeyBindState(key_binding.getKeyCode(), false);
            }
        }

    }

    static {
        KEYS = new KeyBinding[]{mc.gameSettings.keyBindForward, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindJump, mc.gameSettings.keyBindSprint};
    }

}