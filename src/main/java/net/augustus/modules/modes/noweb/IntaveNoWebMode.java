package net.augustus.modules.modes.noweb;

import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.modules.movement.NoWeb;
import net.augustus.settings.SettingGroup;

public class IntaveNoWebMode extends AbstractNoWebMode implements UpdateListener, MoveInputListener {
   public IntaveNoWebMode(NoWeb noWeb, SettingGroup doubleValue) {
      super(noWeb, doubleValue, "Intave");
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(21);
   }

   public void onPreUpdate() {
      if (mc.thePlayer.isInWeb()) {
         if (((NoWeb)this.parentModule).blockPlaceTimer.reached(600L)) {
            mc.thePlayer.onGround = false;
            if (mc.thePlayer.movementInput.moveStrafe == 0.0F) {
               if (mc.gameSettings.keyBindForward.isKeyDown()) {
                  mc.thePlayer.jumpMovementFactor = mc.thePlayer.isCollidedVertically ? 0.74F : 0.68F;
                  return;
               }
            }

            mc.thePlayer.jumpMovementFactor = 0.02F;
         }
      }
   }

   public void onPreMoveInput(EventMoveInput event) {
      if (((NoWeb)this.parentModule).blockPlaceTimer.reached(600L) && mc.thePlayer.isInWeb() && event.getMoveStrafe() == 0.0F && mc.gameSettings.keyBindForward.isKeyDown()) {
         event.setMoveForward(mc.thePlayer.ticksExisted % 5 == 0 ? 0.0F : 1.0F);
      }
   }

}
