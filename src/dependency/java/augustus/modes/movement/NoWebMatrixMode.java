package augustus.modes.movement;

import augustus.modules.movement.NoWeb;
import client.event.EventMove;
import client.event.MoveListener;
import client.event.UpdateStateListener;
import client.setting.SettingGroup;
import client.util.CommonUpdateStateListenerAdapter;
import client.util.MinecraftAccessor;

public class NoWebMatrixMode extends CommonUpdateStateListenerAdapter implements UpdateStateListener, MoveListener {
   @Override
   public void clear() {
   }

   @Override
   public void onMove(EventMove event) {
      CommonUpdateStateListenerAdapter.getIndexArray();
      if (((NoWeb)super.module).timer.hasReached(600L)
         && MinecraftAccessor.minecraft.thePlayer.isInWeb()
         && event.getScaleFactor2() == 0.0F
         && MinecraftAccessor.minecraft.gameSettings.keyBindForward.isKeyDown()) {
         float factor = MinecraftAccessor.minecraft.thePlayer.ticksExisted % 5 == 0 ? 0.0F : 1.0F;
         event.setScaleFactor7(factor);
      }
   }

   @Override
   public void updateState2() {
      super.updateState2();
      byte byteValue = 21;
      this.applyIndex(byteValue);
   }

   @Override
   public void onPostUpdateState() {
      CommonUpdateStateListenerAdapter.getIndexArray();
      if (MinecraftAccessor.minecraft.thePlayer.isInWeb() && ((NoWeb)super.module).timer.hasReached(600L)) {
         MinecraftAccessor.minecraft.thePlayer.onGround = false;
         if (MinecraftAccessor.minecraft.thePlayer.secondaryFloatEntry.moveStrafe == 0.0F
            && MinecraftAccessor.minecraft.gameSettings.keyBindForward.isKeyDown()) {
            MinecraftAccessor.minecraft.thePlayer.scaleFactor10 = MinecraftAccessor.minecraft.thePlayer.isCollidedVertically ? 0.74F : 0.68F;
         } else {
            MinecraftAccessor.minecraft.thePlayer.scaleFactor10 = 0.02F;
         }
      }
   }

   public NoWebMatrixMode(NoWeb noWeb, SettingGroup settingGroup) {
      super(noWeb, settingGroup, "Intave");
   }
}
