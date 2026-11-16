package client.setting;

import client.event.SupplementalListener;
import client.module.Module;
import client.util.BooleanSupplier;
import client.util.BooleanSupplierEntry;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class ExtendedSettingAdapter extends Setting {
   public ArrayList arrayList = new ArrayList();
   public SettingGroup settingNoisegroup;
   public boolean enabled3;

   public void updateState8() {
      Iterator iterator = this.arrayList.iterator();

      while (iterator.hasNext()) {
         ((InternalSettingAdapter)iterator.next()).updateState7();
      }
   }

   public void updateState7() {
      this.arrayList.add(this.getBooleanSupplierEntry().getInternalSettingAdapter());
   }

   public BooleanSupplierEntry getBooleanSupplierEntry() {
      return new BooleanSupplierEntry(this.getModule(), this.settingNoisegroup, this.getString(), this.arrayList.size() + 1, this::size);
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return null;
   }

   public ExtendedSettingAdapter resolveExtendedSettingAdapter(BooleanSupplier booleanSupplier) {
      this.settingNoisegroup.getSettingGroup(booleanSupplier);
      return this;
   }

   @Override
   public void updateState() {
   }

   public ArrayList getArrayList() {
      return this.arrayList;
   }

   public void add(InternalSettingAdapter internalSettingAdapter) {
      this.arrayList.add(internalSettingAdapter);
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
   }

   @Override
   public void updateState3() {
   }

   private boolean size() {
      return this.arrayList.size() > 1;
   }

   public abstract double getValue();

   public void applyFloat(float factor) {
      Iterator iterator = this.arrayList.iterator();

      while (iterator.hasNext()) {
         ((InternalSettingAdapter)iterator.next()).setFactor2(factor);
      }
   }

   @Override
   public Setting getSetting(BooleanSupplier booleanSupplier) {
      return this.resolveExtendedSettingAdapter(booleanSupplier);
   }

   public ExtendedSettingAdapter(Module module, String text, SettingGroup settingGroup, boolean flag) {
      super(module, text);
      SettingGroup settingGroup2 = new SettingGroup(module, text + "_noisegroup", settingGroup);
      String text2 = this.getText4();
      this.settingNoisegroup = (SettingGroup)settingGroup2.getSetting(text2);
      this.enabled3 = flag;
   }
}
