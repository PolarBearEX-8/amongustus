package client.setting;

import client.event.SupplementalListener;
import client.module.Module;
import com.google.gson.JsonObject;

public class InternalNumberSettingEntry extends ExtendedSettingAdapter {
   public NumberSetting numberSetting2;
   public Exception exception = new Exception();
   public NumberSetting numberSetting;

   public InternalNumberSettingEntry resolveInternalNumberSettingEntry(double posX, double posY, double posZ) {
      NumberSetting setting = new NumberSetting(this.getModule(), this.getString() + "_variance", posX, posY, posZ, 0, this.settingNoisegroup);
      String text = "Variance";
      this.numberSetting = setting.getNumberSetting(text);
      return this;
   }

   public double computeValue(double offset, double offset2) {
      boolean flag = true;
      return this.computeValue(flag) * offset2 * this.numberSetting.getDouble2(true) / this.numberSetting.getCoordOffset1() + offset;
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return null;
   }

   @Override
   public void updateState() {
   }

   @Override
   public Setting getSetting(String text) {
      return this.resolveInternalNumberSettingEntry(text);
   }

   public InternalNumberSettingEntry resolveInternalNumberSettingEntry(String text) {
      super.getSetting(text);
      this.settingNoisegroup.getSetting(text);
      if (this.numberSetting2 != null) {
         this.numberSetting2.getNumberSetting(text);
      }

      return this;
   }

   public double computeValueByDouble(double offset, double offset2) {
      boolean flag = true;
      return this.computeValue(flag) * offset2 + offset;
   }

   public InternalNumberSettingEntry resolveInternalNumberSettingEntry(double posX, double posY, double posZ, int count) {
      NumberSetting setting = new NumberSetting(this.getModule(), this.getString() + "_strength", posX, posY, posZ, count, this.settingNoisegroup);
      String text = this.getText4();
      this.numberSetting2 = setting.getNumberSetting(text);
      return this;
   }

   public double computeValue(boolean flag, float factor) {
      if (this.numberSetting2 == null && !flag) {
         this.exception.printStackTrace();
         return 0.0;
      } else {
         double offset = 0.0;
         double offset2 = 0.0;

         for (InternalSettingAdapter internalSettingAdapter : (Iterable<InternalSettingAdapter>) this.arrayList) {
            double valueAsDouble = internalSettingAdapter.getValueAsDouble();
            if (valueAsDouble != 0.0) {
               float factor2 = internalSettingAdapter.getFactor3() + factor;
               offset += internalSettingAdapter.computeValue(factor2) * valueAsDouble;
               offset2 += valueAsDouble;
            }
         }

         if (offset2 == 0.0) {
            offset2 = 1.0;
         }

         double offset3 = offset / offset2;
         if (!flag) {
            offset3 *= this.numberSetting2.getDouble2(true);
         }

         return offset3;
      }
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
   }

   public double getValueAsDouble() {
      boolean flag = true;
      return this.computeValue(flag) * 0.5 + 0.5;
   }

   @Override
   public void updateState3() {
   }

   public double computeValue(boolean flag) {
      float factor = 0.0F;
      return this.computeValue(flag, factor);
   }

   @Override
   public double getValue() {
      float factor = 0.0F;
      boolean enabled = this.enabled3;
      return this.computeValue(enabled, factor);
   }

   public InternalNumberSettingEntry(BooleanSettingProperty booleanSettingProperty, String text, boolean flag) {
      super(booleanSettingProperty.getModule(), booleanSettingProperty.getSettingGroup().getString() + "_" + text, booleanSettingProperty.getSettingGroup(), flag);
   }

   public InternalNumberSettingEntry(Module module, String text, SettingGroup settingGroup, boolean flag) {
      super(module, text, settingGroup, flag);
   }

   public InternalNumberSettingEntry(BooleanSettingProperty booleanSettingProperty, boolean flag) {
      super(booleanSettingProperty.getModule(), booleanSettingProperty.getSettingGroup().getString() + "_noise", booleanSettingProperty.getSettingGroup(), flag);
   }
}
