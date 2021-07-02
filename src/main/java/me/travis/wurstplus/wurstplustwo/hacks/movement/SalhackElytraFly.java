package me.travis.wurstplus.wurstplustwo.hacks.movement;

import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketEntityAction.Action;

import me.travis.wurstplus.Wurstplus;
import me.travis.wurstplus.wurstplustwo.event.events.WurstplusEventPlayerTravel;
import me.travis.wurstplus.wurstplustwo.guiscreen.settings.WurstplusSetting;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusCategory;
import me.travis.wurstplus.wurstplustwo.hacks.WurstplusHack;
import me.travis.wurstplus.wurstplustwo.util.SalTimer;
import me.travis.wurstplus.wurstplustwo.util.WurstplusMathUtil;
import me.zero.alpine.fork.listener.EventHandler;
import me.zero.alpine.fork.listener.Listener;

public final class SalhackElytraFly extends WurstplusHack{
    public SalhackElytraFly() {
        
        super(WurstplusCategory.WURSTPLUS_MOVEMENT);

        this.name = "Sal E Fly";
        this.tag = "SalEFly";
        this.description = "use whit ElytraFly or not work";
    }

    WurstplusSetting mode = create("Mode", "Mode", "Control", combobox("Normal", "Tarzan", "Superior", "Packet", "Control"));
    WurstplusSetting speed = create("Speed", "Speed", 1.81f, 0f, 5f);
    WurstplusSetting down_speed = create("Down Speed", "DownSpeed", 1f, 0f, 5f);
    WurstplusSetting glide_speed = create("Glide Speed", "GlideSpeed", 1f, 0f, 5f);
    WurstplusSetting up_speed = create("Up Speed", "UpSpeed", 1f, 0f, 5f);
    WurstplusSetting acelerate = create("Acelerate", "Acelerate", true);
    WurstplusSetting aceleration_timer = create("Aceleration", "AcelerationTimer", 1000, 0, 10000);
    WurstplusSetting water_cancel = create("Cancel in Water", "CancelInWater", true);
    WurstplusSetting auto_fly = create("Instant Fly", "InstantFly", true);
    WurstplusSetting timer = create("InstantFlyTimer", "InstantFTimer", 0.5f, 0.5f, 10f);
    WurstplusSetting auto_elytra = create("Equip Elytra", "EquipElytra", true);
    
    private SalTimer AccelerationTimer = new SalTimer();
    private SalTimer AccelerationResetTimer = new SalTimer();
    private int ElytraSlot = -1;
    
    @Override
    public void enable()
    {
        super.enable();
        
        ElytraSlot = -1;
        
        if (auto_elytra.get_value(true))
        {
            if (mc.player != null && mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.ELYTRA)
            {
                for (int l_I = 0; l_I < 44; ++l_I)
                {
                    ItemStack l_Stack = mc.player.inventory.getStackInSlot(l_I);
                    
                    if (l_Stack.isEmpty() || l_Stack.getItem() != Items.ELYTRA)
                        continue;
                    
                    ItemElytra l_Elytra = (ItemElytra)l_Stack.getItem();
                    
                    ElytraSlot = l_I;
                    break;
                }
                
                if (ElytraSlot != -1)
                {
                    boolean l_HasArmorAtChest = mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.AIR;
                    
                    mc.playerController.windowClick(mc.player.inventoryContainer.windowId, ElytraSlot, 0, ClickType.PICKUP, mc.player);
                    mc.playerController.windowClick(mc.player.inventoryContainer.windowId, 6, 0, ClickType.PICKUP, mc.player);
                    
                    if (l_HasArmorAtChest)
                        mc.playerController.windowClick(mc.player.inventoryContainer.windowId, ElytraSlot, 0, ClickType.PICKUP, mc.player);
                }
            }
        }
    }

    @Override
    public void disable()
    {
        super.disable();
        
        if (mc.player == null)
            return;
        
        if (ElytraSlot != -1)
        {
            boolean l_HasItem = !mc.player.inventory.getStackInSlot(ElytraSlot).isEmpty() || mc.player.inventory.getStackInSlot(ElytraSlot).getItem() != Items.AIR;
            
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, 6, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(mc.player.inventoryContainer.windowId, ElytraSlot, 0, ClickType.PICKUP, mc.player);
            
            if (l_HasItem)
                mc.playerController.windowClick(mc.player.inventoryContainer.windowId, 6, 0, ClickType.PICKUP, mc.player);
        }
    }
    public String getMetaData()
    {
        return this.mode.get_current_value();
    }

    @EventHandler
    private Listener<WurstplusEventPlayerTravel> OnTravel = new Listener<>(p_Event ->
    {

        /// Player must be wearing an elytra.
        if (mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.ELYTRA)
            return;

        if (!mc.player.isElytraFlying())
        {
            if (auto_fly.get_value(true))
            {
                if(this.mc.player.onGround)
                mc.timer.tickLength = 50;
            else
            if(mc.player.isElytraFlying()){
                mc.timer.tickLength = 50;
    
            }
            else{mc.timer.tickLength = 170.0f / timer.get_value(0);}
    
                    
            }

            return;
        }

        switch (mode.get_current_value())
        {
            case "Normal":
            case "Tarzan":
            case "Packet":
                HandleNormalModeElytra(p_Event);
                break;
            case "Superior":
                HandleImmediateModeElytra(p_Event);
                break;
            case "Control":
                HandleControlMode(p_Event);
                break;
            default:
                break;
        }
    });

    public void HandleNormalModeElytra(WurstplusEventPlayerTravel p_Travel)
    {

        boolean l_IsMoveKeyDown = mc.player.movementInput.moveForward > 0 || mc.player.movementInput.moveStrafe > 0;

        boolean l_CancelInWater = !mc.player.isInWater() && !mc.player.isInLava() && water_cancel.get_value(true);
        
        if (mc.player.movementInput.jump)
        {
            p_Travel.cancel();
            Accelerate();
            return;
        }
        else if (mode.in("Tarzan") && l_CancelInWater ){
            if (acelerate.get_value(true))
            {
                if (AccelerationTimer.passed(aceleration_timer.get_value(1)))
                {
                    Accelerate();
                    return;
                }
            }
            return;
        }

        p_Travel.cancel();
        Accelerate();
    }

    public void HandleImmediateModeElytra(WurstplusEventPlayerTravel p_Travel)
    {
        if (mc.player.movementInput.jump)
        {
            double l_MotionSq = Math.sqrt(mc.player.motionX * mc.player.motionX + mc.player.motionZ * mc.player.motionZ);
            
            if (l_MotionSq > 1.0)
            {
                return;
            }
            else
            {
                double[] dir = WurstplusMathUtil.directionSpeedNoForward(speed.get_value(1));
                
                mc.player.motionX = dir[0];
                mc.player.motionY = -(glide_speed.get_value(1) / 10000f);
                mc.player.motionZ = dir[1];
            }

            p_Travel.cancel();
            return;
        }
        
        mc.player.setVelocity(0, 0, 0);

        p_Travel.cancel();
        double[] dir = WurstplusMathUtil.directionSpeed(speed.get_value(1));

        if (mc.player.movementInput.moveStrafe != 0 || mc.player.movementInput.moveForward != 0)
        {
            mc.player.motionX = dir[0];
            mc.player.motionY = -(glide_speed.get_value(1) / 10000f);
            mc.player.motionZ = dir[1];
        }

        if (mc.player.movementInput.sneak)
            mc.player.motionY = -down_speed.get_value(1);
        
        mc.player.prevLimbSwingAmount = 0;
        mc.player.limbSwingAmount = 0;
        mc.player.limbSwing = 0;
    }

    public void Accelerate()
    {
        if (AccelerationResetTimer.passed(aceleration_timer.get_value(1)))
        {
            AccelerationResetTimer.reset();
            AccelerationTimer.reset();
        }

        float l_Speed = this.speed.get_value(1);

        final double[] dir = WurstplusMathUtil.directionSpeed(l_Speed);

        mc.player.motionY = -(glide_speed.get_value(1) / 10000f);

        if (mc.player.movementInput.moveStrafe != 0 || mc.player.movementInput.moveForward != 0)
        {
            mc.player.motionX = dir[0];
            mc.player.motionZ = dir[1];
        }
        else
        {
            mc.player.motionX = 0;
            mc.player.motionZ = 0;
        }

        if (mc.player.movementInput.sneak)
            mc.player.motionY = -down_speed.get_value(1);

        mc.player.prevLimbSwingAmount = 0;
        mc.player.limbSwingAmount = 0;
        mc.player.limbSwing = 0;
    }


    private void HandleControlMode(WurstplusEventPlayerTravel p_Event)
    {
        final double[] dir = WurstplusMathUtil.directionSpeed(speed.get_value(1));
        
        if (mc.player.movementInput.moveStrafe != 0 || mc.player.movementInput.moveForward != 0)
        {
            mc.player.motionX = dir[0];
            mc.player.motionZ = dir[1];
            
            mc.player.motionX -= (mc.player.motionX*(Math.abs(mc.player.rotationPitch)+90)/90) - mc.player.motionX;
            mc.player.motionZ -= (mc.player.motionZ*(Math.abs(mc.player.rotationPitch)+90)/90) - mc.player.motionZ;
        }
        else
        {
            mc.player.motionX = 0;
            mc.player.motionZ = 0;
        }
        
        mc.player.motionY = (-WurstplusMathUtil.degToRad(mc.player.rotationPitch)) * mc.player.movementInput.moveForward;
        

        mc.player.prevLimbSwingAmount = 0;
        mc.player.limbSwingAmount = 0;
        mc.player.limbSwing = 0;
        p_Event.cancel();
    }
}
