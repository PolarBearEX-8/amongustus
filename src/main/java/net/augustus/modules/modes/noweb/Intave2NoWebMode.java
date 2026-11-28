package net.augustus.modules.modes.noweb;

import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.NoWeb;
import net.augustus.settings.SettingGroup;

public class Intave2NoWebMode extends AbstractNoWebMode implements UpdateListener, MoveInputListener {
   public Intave2NoWebMode(NoWeb noWeb, SettingGroup doubleValue) {
      super(noWeb, doubleValue, "Intave2");
   }

   @Override
   public void onEnable() {
      super.onEnable();
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(21);
   }

   @Override
   public void onDisable() {
   }

   public void onPreUpdate() {
      if (mc.thePlayer.isInWeb() && MoveUtil.isMoving()) {
         if (((NoWeb)this.parentModule).blockPlaceTimer.reached(600L) && mc.thePlayer.ticksExisted % this.getSlowTickInterval() != 0) {
            MoveUtil.setSpeed(mc.thePlayer.isSprinting() ? 0.7F : 0.51F);
         }
      }
   }

   public void onPreMoveInput(EventMoveInput event) {
      if (((NoWeb)this.parentModule).blockPlaceTimer.reached(600L) && mc.thePlayer.isInWeb() && event.getMoveStrafe() == 0.0F && mc.gameSettings.keyBindForward.isKeyDown()) {
         event.setMoveForward(mc.thePlayer.ticksExisted % this.getSlowTickInterval() == 0 ? 0.0F : 1.0F);
      }
   }

   private int getSlowTickInterval() {
      return mc.thePlayer.isSprinting() ? 5 : 7;
   }

}
