package net.augustus.modules.modes.faststop;

import net.augustus.events.EventMoveInput;
import net.augustus.events.EventSafeWalk;
import net.augustus.modules.movement.FastStop;
import net.augustus.settings.SettingGroup;

public class UnlegitFastStopMode extends FastStopMode {
   public UnlegitFastStopMode(FastStop fastStop, SettingGroup doubleValue) {
      super(fastStop, doubleValue, "Unlegit");
   }

   @Override
   public void updateEventSubscriptions() {
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onUpdate() {
      if (this.isNotInLiquid()) {
         if (this.justStoppedMoving()) {
            if (this.isPlayerMoving()) {
               mc.thePlayer.motionX = 0.0;
               mc.thePlayer.motionZ = 0.0;
            }
         }
      }
   }

   @Override
   public void onMoveInput(EventMoveInput event) {
   }

   @Override
   public void onSafeWalk(EventSafeWalk event) {
   }

}
