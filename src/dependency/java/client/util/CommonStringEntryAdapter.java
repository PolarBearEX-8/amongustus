package client.util;

import client.Client;
import client.setting.Setting;

public abstract class CommonStringEntryAdapter extends AuxiliaryStringEntry {
   @Override
   public void applyText(String text) {
      if (MinecraftAccessor.minecraft.currentScreen instanceof SharedStringEntry) {
         Client.getClient().getDraggableWindowHeader();
      } else {
         super.applyText(text);
      }
   }

   public static Setting[] getSettingArray2() {
      return null;
   }

   public CommonStringEntryAdapter(String text) {
      super(text);
   }
}
