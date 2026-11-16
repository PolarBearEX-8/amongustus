package augustus.modes.combat;

import augustus.modules.combat.Velocity;
import client.event.EventMove;
import client.event.MoveListener;
import client.setting.SettingGroup;
import client.util.InternalStringArrayEntry;
import client.util.MoveUtil;
import java.util.function.BiFunction;

public class VelocityCustomMode extends InternalStringArrayEntry implements MoveListener {
   public int count2 = 0;

   public static BiFunction getBiFunction() {
      return ((item, group) -> new VelocityCustomMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void updateState() {
      this.count2 = 0;
   }

   @Override
   public void clear() {
   }

   @Override
   public void onMove(EventMove event) {
      if (minecraft.thePlayer.hurtTime == 9 && this.count2++ % 4 == 0) {
         MoveUtil.processFloat(0.2F);
      }

      if (minecraft.thePlayer.hurtTime == 5) {
         MoveUtil.processFloat(0.1F);
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 21;
      this.applyIndex(byteValue);
   }

   public VelocityCustomMode(Velocity velocity, SettingGroup settingGroup) {
      super(velocity, settingGroup, "OldMinemen");
   }
}
