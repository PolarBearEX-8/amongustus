package net.augustus.modules.modes.fastladder;

import net.augustus.events.EventLadder;
import net.augustus.events.listeners.LadderListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.movement.FastLadder;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;

public class VerusFastLadderMode extends FastLadderMode implements LadderListener {
   public final DoubleValue verusMotionY;

   public VerusFastLadderMode(FastLadder fastLadder, SettingGroup doubleValue) {
       super(fastLadder, doubleValue, "Verus");
      this.verusMotionY = new DoubleValue(this.parentModule, "VerusMotionY", 0.5, 0.2, 2.0, 3, this.modeSetting).named("MotionY").visibleWhen(() -> this.isSelected());
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
      if (((FastLadder)this.parentModule).isOnClimbable() && mc.thePlayer.ticksExisted % 3 != 0) {
         event.setMotionY(this.verusMotionY.getValue());
      }
   }

}
