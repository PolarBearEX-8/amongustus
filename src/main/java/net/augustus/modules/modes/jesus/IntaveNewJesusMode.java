package net.augustus.modules.modes.jesus;

import net.augustus.events.EventMoveInput;
import net.augustus.events.EventOrientCamera;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.OrientCameraListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.MoveUtil;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.modules.movement.Jesus;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;

public class IntaveNewJesusMode extends AbstractJesusMode implements TickListener, MoveInputListener, OrientCameraListener {
   public final BooleanValue hideSneakAnimation;

   public IntaveNewJesusMode(Jesus jesus, SettingGroup doubleValue) {
       super(jesus, doubleValue, "IntaveNew");
      this.hideSneakAnimation = (BooleanValue)new BooleanValue(this.parentModule, "HideSneakAnimation", true, this.modeSetting).withVisibility(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(24);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onTick() {
      if (this.shouldApplyJesus()
         && mc.thePlayer.onGround
         && !mc.thePlayer.isCollidedHorizontally
         && mc.thePlayer.isInWater()
         && RotationUtil.isNearCardinalYaw((float)Math.toDegrees(MoveUtil.getDirection(mc.thePlayer.rotationYaw)))
         && MoveUtil.isMoving()
         && mc.thePlayer.hurtTime < 7) {
         mc.thePlayer.moveFlying(mc.thePlayer.moveStrafing, mc.thePlayer.moveForward, 0.0995F);
      }
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.shouldApplyJesus()
         && mc.thePlayer.isInWater()
         && mc.thePlayer.hurtTime < 7
         && RotationUtil.isNearCardinalYaw((float)Math.toDegrees(MoveUtil.getDirection(mc.thePlayer.rotationYaw)))
         && (event.getMoveForward() != 0.0F || event.getMoveStrafe() != 0.0F)) {
         event.setSneak(true);
         event.setSneakForwardMultiplier(0.6);
      }
   }

   @Override
   public void onOrientCamera(EventOrientCamera event) {
      if (this.shouldApplyJesus() && mc.thePlayer.isInWater() && this.hideSneakAnimation.getValue()) {
         event.setEyeHeight(1.62F);
      }
   }

}
