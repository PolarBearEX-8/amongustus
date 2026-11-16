package client.setting;

import client.ClientGlobals;
import client.event.SupplementalListener;
import client.module.Module;
import com.google.gson.JsonObject;

public abstract class AbstractSupplementalListenerSetting extends Setting {
   public AbstractSupplementalListenerSetting(Module module, String text) {
      super(module, text);
      ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
   }

   @Override
   public void updateState15() {
      this.setState1(true);
      super.updateState15();
   }

   @Override
   public void updateState3() {
   }

   public abstract void applyTargetArray(Object[] entryArray);

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return null;
   }

   public AbstractSupplementalListenerSetting(String text) {
      super(text);
   }

   public abstract Object[] getTargetArray();
}
