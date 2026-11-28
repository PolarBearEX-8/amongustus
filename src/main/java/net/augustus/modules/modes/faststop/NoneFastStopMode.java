package net.augustus.modules.modes.faststop;

import net.augustus.events.EventMoveInput;
import net.augustus.events.EventSafeWalk;
import net.augustus.modules.movement.FastStop;
import net.augustus.settings.SettingGroup;

public class NoneFastStopMode extends FastStopMode {
   public NoneFastStopMode(FastStop fastStop, SettingGroup doubleValue) {
      super(fastStop, doubleValue, "None");
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
   }
}
