package net.augustus.modules.modes.jesus;

import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.movement.Jesus;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class SpeedJesusMode extends AbstractJesusMode implements TickListener {
   private final DoubleValue speedSpeed;
   private final BooleanValue speedModifyMotionY;

   public SpeedJesusMode(Jesus jesus, SettingGroup doubleValue) {
       super(jesus, doubleValue, "Speed");
      this.speedSpeed = new DoubleValue(this.parentModule, "SpeedSpeed", 1.0, 0.0, 20.0, 2, this.modeSetting).named("Speed").visibleWhen(() -> this.isSelected());
      this.speedModifyMotionY = (BooleanValue)((BooleanValue)new BooleanValue(this.parentModule, "SpeedModifyMotionY", true, this.modeSetting).withDisplayName("ModifyMotionY")).withVisibility(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onTick() {
      if (this.shouldApplyJesus()) {
         if (mc.thePlayer.isInWater()) {
            if (!mc.gameSettings.keyBindJump.isKeyDown() && !mc.gameSettings.keyBindSneak.isKeyDown() && this.speedModifyMotionY.getValue()) {
               mc.thePlayer.motionY = 0.0;
            }

            mc.thePlayer.moveFlying(mc.thePlayer.moveStrafing, mc.thePlayer.moveForward, (float)(this.speedSpeed.getValue() / 100.0));
         }
      }
   }

}
