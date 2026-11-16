package augustus.modes.movement;

import augustus.modules.movement.Jesus;
import client.event.UpdateStateListener;
import client.setting.SettingGroup;
import client.util.CoreStringEntry;
import client.util.MoveUtil;

public class JesusNcpMode extends CoreStringEntry implements UpdateStateListener {
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
      if (this.isActive() && minecraft.thePlayer.isInWater() && MoveUtil.canExecute() && minecraft.thePlayer.ticksExisted % 3 == 0 && minecraft.thePlayer.hurtTime < 7) {
         MoveUtil.processFloat(0.26F);
      }
   }

   public JesusNcpMode(Jesus jesus, SettingGroup settingGroup) {
      super(jesus, settingGroup, "Intave");
   }
}
