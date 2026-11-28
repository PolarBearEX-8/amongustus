package net.augustus.modules.modes.velocity;

import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;

public class PushVelocityHurtTimeEntry {
   private final DoubleValue pushMotionSetting;
   private final BooleanValue pushOnGroundSetting;
   public final PushVelocityMode pushVelocityMode;

   public PushVelocityHurtTimeEntry(PushVelocityMode pushVelocityMode2, DoubleValue stringValue, BooleanValue booleanValue) {
      this.pushVelocityMode = pushVelocityMode2;
      this.pushMotionSetting = stringValue;
      this.pushOnGroundSetting = booleanValue;
   }

   public double getPushMotionFactor() {
      return this.pushMotionSetting.getValue() / 100.0;
   }

   public boolean shouldSpoofOnGround() {
      return this.pushOnGroundSetting.getValue();
   }
}
