package net.augustus.modules.modes.noweb;

import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.NoWeb;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;

public class Intave1473b2NoWebMode extends AbstractNoWebMode implements UpdateListener {
   private final BooleanValue intave1473b2StopOnHit;

   public Intave1473b2NoWebMode(NoWeb noWeb, SettingGroup doubleValue) {
       super(noWeb, doubleValue, "Intave 14.7.3-b2");
      BooleanValue booleanValue = new BooleanValue(this.parentModule, "Intave1473b2StopOnHit", false, this.modeSetting);
      booleanValue = (BooleanValue)booleanValue.withDisplayName("StopOnHit");
      this.intave1473b2StopOnHit = (BooleanValue)booleanValue.withVisibility(() -> this.isSelected());
   }

   @Override
   public void updateEventSubscriptions() {
      super.updateEventSubscriptions();
   }

   @Override
   public void onEnable() {
      super.onEnable();
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void onPreUpdate() {
      if (mc.thePlayer.isInWeb() && MoveUtil.isMoving() && mc.thePlayer.ticksExisted % 4 != 0 && this.wasInWeb) {
         if (mc.thePlayer.hurtTime > 0) {
            if (!this.intave1473b2StopOnHit.getValue()) {
               MoveUtil.setSpeed(0.2F, false);
               mc.thePlayer.setSpeedInAir(0.02F);
            }
         } else {
            MoveUtil.setSpeed(0.3F, false);
            mc.thePlayer.setSpeedInAir(0.08F);
         }
      }
   }

}
