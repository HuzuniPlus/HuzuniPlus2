package me.travis.wurstplus.wurstplustwo.hacks.movement;


import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventGUIScreen;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.input.Keyboard;
import java.util.function.Predicate;
import net.minecraft.client.gui.GuiChat;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;
import net.minecraft.client.settings.KeyBinding;



public class InventoryMove extends WurstplusHack {


	private static final KeyBinding[] KEYS;
	@EventHandler
	private final Listener<WurstplusEventGUIScreen> state_gui;


    public InventoryMove() {
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);
        this.name = "Inventory Move";
        this.tag = "InventoryMove";
        this.description = "move in guis";


		this.state_gui = new Listener<WurstplusEventGUIScreen>(event -> {
			if (mc.player == null && mc.world == null) {
				return;
			}
			else if (event.get_guiscreen() instanceof GuiChat || event.get_guiscreen() == null) {
				return;
			}
			else {
				this.walk();
				return;
			}
        
    });
	}

		@Override
		public void update() {
			if (mc.player == null && mc.world == null) {
				return;
			}
			if (mc.currentScreen instanceof GuiChat || mc.currentScreen == null) {
				return;
			}
			this.walk();
		}

		public void walk() {
			for (final KeyBinding key_binding : KEYS) {
				if (Keyboard.isKeyDown(key_binding.getKeyCode())) {
					if (key_binding.getKeyConflictContext() != KeyConflictContext.UNIVERSAL) {
						key_binding.setKeyConflictContext((IKeyConflictContext)KeyConflictContext.UNIVERSAL);
					}
					KeyBinding.setKeyBindState(key_binding.getKeyCode(), true);
				}
				else {
					KeyBinding.setKeyBindState(key_binding.getKeyCode(), false);
				}
			}
		}

		static {
			KEYS = new KeyBinding[] { mc.gameSettings.keyBindForward, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindJump, mc.gameSettings.keyBindSprint };
		}
	}