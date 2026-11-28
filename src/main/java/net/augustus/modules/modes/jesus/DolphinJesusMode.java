package net.augustus.modules.modes.jesus;

import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.modules.movement.Jesus;
import net.augustus.settings.SettingGroup;

public class DolphinJesusMode extends AbstractJesusMode implements MoveInputListener {
   public DolphinJesusMode(Jesus jesus, SettingGroup doubleValue) {
      super(jesus, doubleValue, "Dolphin");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(21);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public void onPreMoveInput(EventMoveInput event) {
      if ((mc.thePlayer.isInWater() || mc.thePlayer.isInLava()) && !mc.gameSettings.keyBindSneak.isKeyDown()) {
         event.setJump(true);
      }
   }

}
