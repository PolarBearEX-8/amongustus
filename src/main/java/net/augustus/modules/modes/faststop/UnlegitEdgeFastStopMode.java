package net.augustus.modules.modes.faststop;

import net.augustus.events.EventMoveInput;
import net.augustus.events.EventSafeWalk;
import net.augustus.modules.movement.FastStop;
import net.augustus.settings.SettingGroup;

public class UnlegitEdgeFastStopMode extends FastStopMode {
   private boolean safeWalkActive = false;

   public UnlegitEdgeFastStopMode(FastStop fastStop, SettingGroup doubleValue) {
      super(fastStop, doubleValue, "UnlegitEdge");
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
   }

   @Override
   public void onMoveInput(EventMoveInput event) {
   }

   @Override
   public void onSafeWalk(EventSafeWalk event) {
      if (this.isPlayerMoving()) {
         if (this.justStoppedMoving()) {
            this.safeWalkActive = true;
         }

         if (this.safeWalkActive) {
            event.setSafeWalk(true);
         }
      } else {
         this.safeWalkActive = false;
      }
   }

}
