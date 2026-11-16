package augustus.modes.movement;

import augustus.modules.movement.NoWeb;
import client.event.EventMove;
import client.event.MoveListener;
import client.event.UpdateStateListener;
import client.setting.SettingGroup;
import client.util.CommonUpdateStateListenerAdapter;
import client.util.MinecraftAccessor;
import client.util.MoveUtil;

public class NoWebAACMode extends CommonUpdateStateListenerAdapter implements UpdateStateListener, MoveListener {
   @Override
   public void updateState() {
      super.updateState();
   }

   private int getCount2() {
      CommonUpdateStateListenerAdapter.getIndexArray();
      return MinecraftAccessor.minecraft.thePlayer.isRiding() ? 5 : 7;
   }

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
         float factor = MinecraftAccessor.minecraft.thePlayer.ticksExisted % this.getCount2() == 0 ? 0.0F : 1.0F;
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
      if (MinecraftAccessor.minecraft.thePlayer.isInWeb()
         && MoveUtil.canExecute()
         && ((NoWeb)super.module).timer.hasReached(600L)
         && MinecraftAccessor.minecraft.thePlayer.ticksExisted % this.getCount2() != 0) {
         MoveUtil.processFloat(MinecraftAccessor.minecraft.thePlayer.isRiding() ? 0.7F : 0.51F);
      }
   }

   public NoWebAACMode(NoWeb noWeb, SettingGroup settingGroup) {
      super(noWeb, settingGroup, "Intave2");
   }
}
