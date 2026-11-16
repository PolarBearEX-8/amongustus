package client.util;

import augustus.modules.movement.NoWeb;
import client.event.EventStateUpdate;
import client.event.UpdateStateListener;
import client.setting.ModeOptionSupplier;
import client.setting.SettingGroup;

public abstract class CommonUpdateStateListenerAdapter extends ModeOptionSupplier implements UpdateStateListener {
   public boolean enabled2 = false;
   public final TimeHelper timer = new TimeHelper();

   @Override
   public void updateState() {
      this.enabled2 = false;
   }

   public static int[] getIndexArray() {
      return null;
   }

   @Override
   public void onUpdateState(EventStateUpdate event) {
      getIndexArray();
      if (!MinecraftAccessor.minecraft.thePlayer.isInWeb()) {
         this.timer.reset();
      }

      if (MinecraftAccessor.minecraft.thePlayer.isInWeb() && ((NoWeb)super.module).settingUpDown.isEnabled() && this.timer.hasReached(200L)) {
         if (MinecraftAccessor.minecraft.gameSettings.keyBindJump.pressed) {
            MinecraftAccessor.minecraft.thePlayer.motionY = ((NoWeb)super.module).settingUpSpeed.getValue();
         } else if (MinecraftAccessor.minecraft.gameSettings.keyBindSneak.pressed) {
            MinecraftAccessor.minecraft.thePlayer.motionY = -((NoWeb)super.module).settingDownSpeed.getValue();
         }
      }

      if (!MinecraftAccessor.minecraft.thePlayer.isInWeb() && this.enabled2) {
         if ((double)MinecraftAccessor.minecraft.thePlayer.getScaleFactor2() != 0.2) {
            MinecraftAccessor.minecraft.thePlayer.setScaleFactor2(0.02F);
         }

         if ((double)MinecraftAccessor.minecraft.thePlayer.scaleFactor10 > 0.03) {
            MinecraftAccessor.minecraft.thePlayer.scaleFactor10 = 0.02F;
         }
      }

      this.enabled2 = MinecraftAccessor.minecraft.thePlayer.isInWeb();
   }

   @Override
   public void updateState2() {
      byte byteValue = 43;
      this.applyIndex(byteValue);
   }

   public CommonUpdateStateListenerAdapter(NoWeb noWeb, SettingGroup settingGroup, String text) {
      super(noWeb, settingGroup, text);
   }
}
