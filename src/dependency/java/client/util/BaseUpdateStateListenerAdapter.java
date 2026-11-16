package client.util;

import augustus.modules.movement.HighJump;
import client.event.UpdateStateListener;
import client.module.Module;
import client.setting.BooleanSetting;
import client.setting.ModeOptionSupplier;
import client.setting.SettingGroup;

public abstract class BaseUpdateStateListenerAdapter extends ModeOptionSupplier implements UpdateStateListener {
   public BooleanSetting settingAutoDisable;
   public boolean enabled2;
   public int count2;

   @Override
   public void updateState() {
      this.count2 = 0;
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   @Override
   public void updateState2() {
      byte byteValue = 43;
      this.applyIndex(byteValue);
   }

   @Override
   public void onPostUpdateState() {
      this.enabled2 = true;
      if (minecraft.thePlayer.onGround) {
         if (this.count2 > 0 && this.settingAutoDisable.isEnabled()) {
            ((Module)((HighJump)this.module)).setActive(false);
            this.enabled2 = false;
            return;
         }

         this.count2++;
      }
   }

   public BaseUpdateStateListenerAdapter(HighJump highJump, SettingGroup settingGroup, String text) {
        super(highJump, settingGroup, text);
      
      this.enabled2 = false;
      this.count2 = 0;
      BooleanSetting setting = new BooleanSetting(highJump, "AutoDisable" + text, true, settingGroup);
      String text2 = "AutoDisable";
      setting = (BooleanSetting)setting.getSetting(text2);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingAutoDisable = (BooleanSetting)setting.getSetting(booleanSupplier);
   }
}
