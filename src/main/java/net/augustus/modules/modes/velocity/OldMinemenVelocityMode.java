package net.augustus.modules.modes.velocity;

import net.augustus.events.EventMoveInput;
import net.augustus.events.listeners.MoveInputListener;
import net.augustus.utils.MoveUtil;
import java.util.function.BiFunction;
import net.augustus.modules.combat.Velocity;
import net.augustus.settings.SettingGroup;

public class OldMinemenVelocityMode extends VelocityMode implements MoveInputListener {
   private int hurtCounter = 0;

   public OldMinemenVelocityMode(Velocity velocity, SettingGroup doubleValue) {
      super(velocity, doubleValue, "OldMinemen");
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(21);
   }

   @Override
   public void onEnable() {
      this.hurtCounter = 0;
   }

   @Override
   public void onDisable() {
   }

   public static BiFunction factory() {
      return (obj, obj2) -> new OldMinemenVelocityMode((Velocity)obj, (SettingGroup)obj2);
   }

   @Override
   public void onPreMoveInput(EventMoveInput event) {
      if (this.canApplyVelocity() && mc.thePlayer != null) {
         if (mc.thePlayer.hurtTime == 9 && this.hurtCounter++ % 4 == 0) {
            MoveUtil.setSpeed(0.2F);
         }

         if (mc.thePlayer.hurtTime == 5) {
            MoveUtil.setSpeed(0.1F);
         }
      }
   }

}
