package net.augustus.modules.modes.fastladder;

import net.augustus.events.EventLadder;
import net.augustus.events.listeners.LadderListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.movement.FastLadder;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class MotionFastLadderMode extends FastLadderMode implements LadderListener {
   public final DoubleValue motionMotionY;
   public final DoubleValue motionTickDelay;

   public MotionFastLadderMode(FastLadder fastLadder, SettingGroup doubleValue) {
      super(fastLadder, doubleValue, "Motion");
      DoubleValue motionMotionYSetting = new DoubleValue(this.parentModule, "MotionMotionY", 0.4, 0.2, 2.0, 3, this.modeSetting);
      motionMotionYSetting = motionMotionYSetting.named("MotionY");
      this.motionMotionY = motionMotionYSetting.visibleWhen(() -> this.isSelected());
      this.motionTickDelay = new DoubleValue(this.parentModule, "MotionTickDelay", 0.0, 0.0, 20.0, 0, this.modeSetting).named("TickDelay").visibleWhen(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(15);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onLadder(EventLadder event) {
      if (((FastLadder)this.parentModule).isOnClimbable() && (double)mc.thePlayer.ticksExisted % (this.motionTickDelay.getValue() + 1.0) == 0.0) {
         event.setMotionY(this.motionMotionY.getValue());
      }
   }

}
