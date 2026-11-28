package net.augustus.modules.modes.velocity;

import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.RangeSetting;
import net.augustus.settings.SettingVisibilityCondition;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.SettingGroup;

public class PushGroundVelocityMode extends VelocityMode implements UpdateListener {
   private final RangeSetting endOnGroundPush;

   public PushGroundVelocityMode(Velocity velocity, SettingGroup doubleValue) {
       super(velocity, doubleValue, "PushGround");
      RangeSetting rangeSetting = (new RangeSetting(this.parentModule, "EndOnGroundPush", "StartOnGroundPush", 1.0, 10.0, 1.0, 10.0, 0, this.modeSetting)).withMaxFirst();
      String str = "EndHurtTime";
      this.endOnGroundPush = rangeSetting.withBoundNames("StartHurtTime", str).visibleWhen(() -> this.isSelected());
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

   public static BiFunction factory() {
      return (obj, obj2) -> new PushGroundVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPreUpdate() {
      if (this.canApplyVelocity()) {
         if ((double)mc.thePlayer.hurtTime >= this.endOnGroundPush.getMinValue()) {
            if (((double)mc.thePlayer.hurtTime) <= this.endOnGroundPush.getMaxValue()) {
               mc.thePlayer.onGround = true;
            }
         }
      }
   }

}
