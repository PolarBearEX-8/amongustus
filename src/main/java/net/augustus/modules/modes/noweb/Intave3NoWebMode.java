package net.augustus.modules.modes.noweb;

import net.augustus.events.EventMoveInput;
import net.augustus.events.EventOrientCamera;
import net.augustus.events.EventWebSlowDown;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.events.listeners.OrientCameraListener;
import net.augustus.events.listeners.WebSlowDownListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.movement.NoWeb;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;

public class Intave3NoWebMode extends AbstractNoWebMode implements MoveInputListener, WebSlowDownListener, OrientCameraListener {
   public final BooleanValue intave3HideSneakAnimation;

   public Intave3NoWebMode(NoWeb noWeb, SettingGroup doubleValue) {
       super(noWeb, doubleValue, "Intave3");
      BooleanValue booleanValue = new BooleanValue(this.parentModule, "Intave3HideSneakAnimation", true, this.modeSetting);
      booleanValue = (BooleanValue)booleanValue.withDisplayName("HideSneakAnimation");
      this.intave3HideSneakAnimation = (BooleanValue)booleanValue.withVisibility(() -> this.isSelected());
   }

   @Override
   public void onEnable() {
      super.onEnable();
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
      this.toggleEventSubscription(21);
      this.toggleEventSubscription(49);
      this.toggleEventSubscription(24);
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (mc.thePlayer.isInWeb()) {
         event.setSneak(true);
      }
   }

   @Override
   public void onWebSlowDown(EventWebSlowDown event) {
      event.setKeepHorizontalMotion(true);
      event.setHorizontalFactor(1.0);
   }

   @Override
   public void onOrientCamera(EventOrientCamera event) {
      if (mc.thePlayer.isInWeb() && this.intave3HideSneakAnimation.getValue()) {
         event.setEyeHeight(1.62F);
      }
   }

}
