package augustus.modes.movement;

import augustus.modules.movement.LongJump;
import client.event.EventMove;
import client.event.UpdateStateListener;
import client.setting.SettingGroup;
import client.util.AuxiliaryMoveListenerAdapter;
import client.util.CombatUtil;
import client.util.MoveUtil;
import java.util.function.BiFunction;

public class LongJumpIntaveMode extends AuxiliaryMoveListenerAdapter implements UpdateStateListener {
   public boolean enabled3 = false;

   @Override
   public void updateState() {
      this.enabled3 = false;
      if (minecraft.thePlayer != null) {
         CombatUtil.updateState3();
      }
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new LongJumpIntaveMode((LongJump)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
   }

   @Override
   public void onMove(EventMove event) {
      float factor = this.enabled3 ? 1.0F : 0.0F;
      event.setScaleFactor7(factor);
   }

   @Override
   public void updateState2() {
      super.updateState2();
      byte byteValue = 43;
      this.applyIndex(byteValue);
   }

   @Override
   public void onPostUpdateState() {
      if (minecraft.thePlayer.hurtTime > 0) {
         minecraft.getTimer().scaleFactor2 = 1.2F;
         MoveUtil.processFloat(0.48F);
         minecraft.thePlayer.motionY = 0.62F;
         this.enabled3 = true;
      } else {
         minecraft.getTimer().scaleFactor2 = 1.0F;
      }

      if (this.enabled3 && minecraft.thePlayer.fallDistance > 0.0F) {
         minecraft.thePlayer.motionY = -0.1;
      }
   }

   public LongJumpIntaveMode(LongJump longJump, SettingGroup settingGroup) {
      super(longJump, settingGroup, "OldZoneCraft");
   }
}
