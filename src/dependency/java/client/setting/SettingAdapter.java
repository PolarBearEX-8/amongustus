package client.setting;

import client.ClientGlobals;
import client.event.SupplementalListener;
import client.module.Module;
import com.google.gson.JsonObject;
import java.util.Set;

public abstract class SettingAdapter extends Setting {
   public void updateState7() {
      this.getSet().clear();
   }

   public int getCount() {
      return this.getSet().size();
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return null;
   }

   public boolean matchesTarget(Object entry) {
      return this.getSet().contains(entry);
   }

   public void applyObject(Object entry) {
      this.getSet().add(entry);
   }

   public boolean checkObject2(Object entry) {
      return this.getSet().remove(entry);
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
   }

   public abstract Set getSet();

   public abstract void applySet(Set set);

   @Override
   public void updateState3() {
   }

   @Override
   public int getCount10() {
      return 0;
   }

   @Override
   public void updateState15() {
      this.setState1(true);
      super.updateState15();
   }

   public SettingAdapter(Module module, String text) {
      super(module, text);
      ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
   }

   public SettingAdapter(String text) {
      super(text);
   }
}
