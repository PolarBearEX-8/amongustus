package augustus.modes.combat;

import augustus.modules.combat.Velocity;
import client.event.UpdateStateListener;
import client.setting.SettingGroup;
import client.util.InternalStringArrayEntry;
import client.util.MoveUtil;
import java.util.function.BiFunction;

public class VelocityIntaveMode extends InternalStringArrayEntry implements UpdateStateListener {
   public static BiFunction getBiFunction() {
      return ((item, group) -> new VelocityIntaveMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void updateState() {
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      byte byteValue = 43;
      this.applyIndex(byteValue);
   }

   @Override
   public void onPostUpdateState() {
      if (this.isActive()) {
         if (minecraft.thePlayer.hurtTime != 0 && minecraft.thePlayer.fallDistance < 3.0F && minecraft.thePlayer.isCollidedHorizontally) {
            MoveUtil.processFloat(0.195F);
         }
      }
   }

   public VelocityIntaveMode(Velocity velocity, SettingGroup settingGroup) {
      super(velocity, settingGroup, "Intave13");
   }
}
