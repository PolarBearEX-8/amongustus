package client.setting;

import client.ClientGlobals;
import client.event.SupplementalListener;
import client.module.Module;
import com.google.gson.JsonObject;
import java.util.List;

public abstract class SecondarySettingAdapter extends Setting {
   public Object resolveTarget(int count) {
      return this.getList().get(count);
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return null;
   }

   public int getCountAsIndex() {
      return this.getList().size();
   }

   public void applyTarget(Object entry) {
      this.getList().add(entry);
   }

   public boolean matchesTarget(Object entry) {
      return this.getList().remove(entry);
   }

   public Object resolveTargetByIndex(int count) {
      return this.getList().remove(count);
   }

   public abstract List getList();

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
   }

   public boolean matchesTargetByTarget(Object entry) {
      return this.getList().contains(entry);
   }

   @Override
   public void updateState3() {
   }

   public void updateState7() {
      this.getList().clear();
   }

   @Override
   public int getCount10() {
      return 0;
   }

   public abstract void applyList(List list);

   @Override
   public void updateState15() {
      this.setState1(true);
      super.updateState15();
   }

   public SecondarySettingAdapter(String text) {
      super(text);
   }

   public SecondarySettingAdapter(Module module, String text) {
      super(module, text);
      ClientGlobals.auxiliaryCopyOnWriteArrayListEntry.applySettingBySetting(this);
   }
}
