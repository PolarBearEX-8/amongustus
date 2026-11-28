package net.augustus.modules.modes.jesus;

import net.augustus.events.listeners.UpdateListener;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.Jesus;
import net.augustus.settings.SettingGroup;

public class IntaveJesusMode extends AbstractJesusMode implements UpdateListener {
   public IntaveJesusMode(Jesus jesus, SettingGroup doubleValue) {
      super(jesus, doubleValue, "Intave");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(43);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   public void onPreUpdate() {
      if (this.shouldApplyJesus() && mc.thePlayer.isInWater() && MoveUtil.isMoving() && mc.thePlayer.ticksExisted % 3 == 0 && mc.thePlayer.hurtTime < 7) {
         MoveUtil.setSpeed(0.26F);
      }
   }

}
