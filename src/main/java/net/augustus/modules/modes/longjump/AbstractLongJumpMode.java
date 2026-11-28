package net.augustus.modules.modes.longjump;

import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.modules.modes.AbstractSubMode;
import net.augustus.modules.movement.LongJump;
import net.augustus.settings.SettingGroup;

public abstract class AbstractLongJumpMode extends AbstractSubMode implements MoveInputListener {

   public AbstractLongJumpMode(LongJump longJump, SettingGroup doubleValue, String str) {
      super(longJump, doubleValue, str);
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(21);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      event.setMoveForward(1.0F);
   }



}
