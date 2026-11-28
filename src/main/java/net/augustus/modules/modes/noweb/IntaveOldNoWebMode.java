package net.augustus.modules.modes.noweb;

import net.augustus.events.EventMoveInput;
import net.augustus.events.EventOrientCamera;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.OrientCameraListener;
import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.movement.NoWeb;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;

public class IntaveOldNoWebMode extends AbstractNoWebMode implements UpdateListener, MoveInputListener, OrientCameraListener {
   public final BooleanValue intaveOldHideSneakAnimation;

   public IntaveOldNoWebMode(NoWeb noWeb, SettingGroup doubleValue) {
      super(noWeb, doubleValue, "IntaveOld");
      BooleanValue booleanValue = new BooleanValue(this.parentModule, "IntaveOldHideSneakAnimation", true, this.modeSetting);
      booleanValue = (BooleanValue)booleanValue.withDisplayName("HideSneakAnimation");
      this.intaveOldHideSneakAnimation = (BooleanValue)booleanValue.withVisibility(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(24);
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate() {
      if (mc.thePlayer.isInWeb()) {
         mc.thePlayer.onGround = false;
         if (mc.thePlayer.isCollidedVertically) {
            if (mc.thePlayer.movementInput.moveStrafe == 0.0F) {
               if (mc.gameSettings.keyBindForward.isKeyDown()) {
                  mc.thePlayer.jumpMovementFactor = 0.74F;
                  return;
               }
            }

            mc.thePlayer.jumpMovementFactor = 0.2F;
            mc.thePlayer.onGround = true;
         } else {
            mc.thePlayer.jumpMovementFactor = 1.0F;
         }
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (mc.thePlayer.isCollidedVertically) {
         if (mc.thePlayer.isInWeb() && event.getMoveStrafe() == 0.0F && mc.gameSettings.keyBindForward.isKeyDown()) {
            event.setMoveForward(mc.thePlayer.ticksExisted % 2 == 0 ? 0.0F : 1.0F);
         }
      } else if (mc.thePlayer.isInWeb()) {
         if (mc.thePlayer.isSprinting()) {
            event.setMoveForward(0.0F);
         }

         event.setSneak(true);
      }
   }

   @Override
   public void onOrientCamera(EventOrientCamera event) {
      if (mc.thePlayer.isInWeb() && this.intaveOldHideSneakAnimation.getValue()) {
         event.setEyeHeight(1.62F);
      }
   }

}
