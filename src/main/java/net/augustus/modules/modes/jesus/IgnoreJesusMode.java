package net.augustus.modules.modes.jesus;

import net.augustus.events.EventLiquidJump;
import net.augustus.events.EventLiquidPhysics;
import net.augustus.events.listeners.LiquidJumpListener;
import net.augustus.events.listeners.LiquidPhysicsListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.movement.Jesus;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;

public class IgnoreJesusMode extends AbstractJesusMode implements LiquidPhysicsListener, LiquidJumpListener, TickListener {
   private final BooleanValue ignoreWater;
   private final BooleanValue jumpInWater;
   private final BooleanValue ignoreLava;
   private final BooleanValue jumpInLava;
   private final BooleanValue disableIgnoranceTemporaryIfDoubleJumpKeyPressed;
   private final BooleanValue disableIfYouFallingDown;
   private final DoubleValue motionYToDisable;
   private final TimeHelper timeHelper;
   private boolean temporarilyDisabled;

   public IgnoreJesusMode(Jesus jesus, SettingGroup doubleValue) {
       super(jesus, doubleValue, "Ignore");
      this.ignoreWater = (BooleanValue)new BooleanValue(this.parentModule, "Ignore water", true, this.modeSetting).withVisibility(() -> this.isSelected());
      BooleanValue booleanValue2 = new BooleanValue(this.parentModule, "Jump in water", true, this.modeSetting);
      this.jumpInWater = (BooleanValue)booleanValue2.withVisibility(() -> this.isSelected());
      booleanValue2 = new BooleanValue(this.parentModule, "Ignore lava", true, this.modeSetting);
      this.ignoreLava = (BooleanValue)booleanValue2.withVisibility(() -> this.isSelected());
      booleanValue2 = new BooleanValue(this.parentModule, "Jump in lava", true, this.modeSetting);
      this.jumpInLava = (BooleanValue)booleanValue2.withVisibility(() -> this.isSelected());
      this.disableIgnoranceTemporaryIfDoubleJumpKeyPressed = (BooleanValue)new BooleanValue(this.parentModule, "Disable ignorance temporary if double jump key pressed", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.disableIfYouFallingDown = (BooleanValue)new BooleanValue(this.parentModule, "Disable if you falling down", false, this.modeSetting).withVisibility(() -> this.isSelected());
      this.motionYToDisable = new DoubleValue(this.parentModule, "Motion y to disable", -0.7, -2.0, 0.0, 2, this.modeSetting).visibleWhen(() -> this.isDisableMotionYVisible());
      this.timeHelper = new TimeHelper();
      this.temporarilyDisabled = false;
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(66);
      this.toggleEventSubscription(67);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onLiquidPhysics(EventLiquidPhysics event) {
      if (!this.temporarilyDisabled && this.shouldApplyJesus()) {
         if (this.ignoreWater.getValue()) {
            event.setIgnoreWater(true);
         }

         if (this.ignoreLava.getValue()) {
            event.setIgnoreLava(true);
         }
      }
   }

   @Override
   public void onLiquidJump(EventLiquidJump event) {
      if (!this.temporarilyDisabled && this.shouldApplyJesus()) {
         if (this.jumpInWater.getValue()) {
            event.setIgnoreWater(true);
         }

         if (this.jumpInLava.getValue()) {
            event.setIgnoreLava(true);
         }
      }
   }

   @Override
   public void onTick() {
      if (!mc.thePlayer.isInWater() && mc.thePlayer.onGround) {
         this.temporarilyDisabled = false;
      }

      if (this.disableIfYouFallingDown.getValue() && mc.thePlayer.motionY < this.motionYToDisable.getValue() && mc.thePlayer.isInWater()) {
         this.temporarilyDisabled = true;
      }

      if (this.disableIgnoranceTemporaryIfDoubleJumpKeyPressed.getValue()) {
         if (mc.gameSettings.keyBindJump.getPressTime() > 0) {
            if (!this.timeHelper.reached(250L)) {
               this.temporarilyDisabled = !this.temporarilyDisabled;
            }

            this.timeHelper.reset();
         }

         mc.gameSettings.keyBindJump.setPressTime(0);
      }
   }

   private boolean isDisableMotionYVisible() {
      return this.isSelected() && this.disableIfYouFallingDown.getValue();
   }

}
