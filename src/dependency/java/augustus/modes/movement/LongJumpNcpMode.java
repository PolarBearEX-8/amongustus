package augustus.modes.movement;

import augustus.modules.movement.LongJump;
import client.event.UpdateStateListener;
import client.setting.SettingGroup;
import client.util.AuxiliaryMoveListenerAdapter;
import client.util.CombatUtil;
import client.util.MoveUtil;
import java.util.function.BiFunction;

public class LongJumpNcpMode extends AuxiliaryMoveListenerAdapter implements UpdateStateListener {
   public int count2 = 0;

   @Override
   public void updateState() {
      if (minecraft.thePlayer != null && minecraft.thePlayer.onGround) {
         CombatUtil.updateState();
      }
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new LongJumpNcpMode((LongJump)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      super.updateState2();
      byte byteValue = 43;
      this.applyIndex(byteValue);
   }

   @Override
   public void onPostUpdateState() {
      minecraft.thePlayer.scaleFactor10 = 0.028F;
      if (minecraft.thePlayer.onGround) {
         if (minecraft.thePlayer.hurtTime != 0) {
            this.count2 = 0;
            minecraft.thePlayer.motionY = 0.6;
            MoveUtil.processAction(0.3, false);
         }
      } else {
         if (this.count2 == 1) {
            MoveUtil.processAction(0.2, false);
         }

         minecraft.getTimer().scaleFactor2 = 0.7F;
         if (minecraft.thePlayer.motionY > 0.0) {
            minecraft.thePlayer.motionY += 0.06F;
         } else if (minecraft.thePlayer.motionY > -0.4) {
            minecraft.thePlayer.motionY += 0.042;
         }
      }

      this.count2++;
   }

   public LongJumpNcpMode(LongJump longJump, SettingGroup settingGroup) {
      super(longJump, settingGroup, "OldCubeCraft");
   }
}
