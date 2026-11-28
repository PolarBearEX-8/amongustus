package net.augustus.modules.modes.noweb;

import net.augustus.events.listeners.UpdateListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.MoveUtil;
import net.augustus.modules.movement.NoWeb;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.SettingGroup;

public class Intave1477NoWebMode extends AbstractNoWebMode implements UpdateListener {
   private final BooleanValue intave1477StopOnHit;

   public Intave1477NoWebMode(NoWeb noWeb, SettingGroup doubleValue) {
       super(noWeb, doubleValue, "Intave 14.7.7");
      BooleanValue booleanValue = new BooleanValue(this.parentModule, "Intave1477StopOnHit", false, this.modeSetting);
      booleanValue = (BooleanValue)booleanValue.withDisplayName("StopOnHit");
      this.intave1477StopOnHit = (BooleanValue)booleanValue.withVisibility(() -> this.isSelected());
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
      if (!mc.thePlayer.isInWeb()) {
         this.timeHelper.reset();
      }

      if (!mc.thePlayer.isInWeb() && this.wasInWeb) {
         mc.thePlayer.setSpeedInAir(0.02F);
      } else {
         if (MoveUtil.isMoving() && mc.thePlayer.ticksExisted % 4 != 0) {
            if (this.timeHelper.reached(250L)) {
               if (mc.thePlayer.hurtTime > 0) {
                  if (!this.intave1477StopOnHit.getValue()) {
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
   }

}
