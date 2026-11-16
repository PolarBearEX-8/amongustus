package client.util;

import client.module.Module;
import client.setting.CombatChoiceProperty;
import client.setting.InternalSettingAdapter;
import client.setting.SettingGroup;

public class BooleanSupplierEntry {
   public BooleanSupplier booleanSupplier;
   public String labelText3;
   public int count;
   public String labelText = "Sinus";
   public double value;
   public double value2 = 0.0;
   public Module module;
   public float factor;
   public boolean enabled;
   public double value3;
   public SettingGroup settingGroup;

   public InternalSettingAdapter getInternalSettingAdapter() {
      InternalSettingAdapter internalSettingAdapter = new InternalSettingAdapter(
         this.module, this.settingGroup, this.labelText3 + "_layer_" + this.count, this.booleanSupplier, this.count, this.enabled
      );
      String labelText2 = this.labelText;
      internalSettingAdapter.applyText(labelText2);
      double value5 = this.value2;
      internalSettingAdapter.applyDouble2(value5);
      float factor2 = this.factor;
      internalSettingAdapter.setFactor(factor2);
      if (this.enabled) {
         CombatChoiceProperty combatChoiceProperty = internalSettingAdapter.getCombatChoiceProperty2();
         double value6 = this.value3;
         double value7 = this.value3;
         combatChoiceProperty = combatChoiceProperty.resolveCombatChoiceProperty(value7, value6);
         byte byteValue = 1;
         combatChoiceProperty.applyIndexByIndex(byteValue);
         combatChoiceProperty = internalSettingAdapter.getCombatChoiceProperty();
         double value8 = this.value;
         double value9 = this.value;
         combatChoiceProperty = combatChoiceProperty.resolveCombatChoiceProperty(value9, value8);
         byte byteValue2 = 1;
         combatChoiceProperty.applyIndexByIndex(byteValue2);
      } else {
         double value10 = this.value3;
         internalSettingAdapter.applyDoubleByDouble(value10);
         double value11 = this.value;
         internalSettingAdapter.applyDouble(value11);
      }

      return internalSettingAdapter;
   }

   public BooleanSupplierEntry withValue2(double offset) {
      this.value2 = offset;
      return this;
   }

   public BooleanSupplierEntry withValue(double offset) {
      this.value = offset;
      return this;
   }

   public BooleanSupplierEntry withFactor(float factor2) {
      this.factor = factor2;
      return this;
   }

   public BooleanSupplierEntry withValue3(double offset) {
      this.value3 = offset;
      return this;
   }

   public BooleanSupplierEntry withEnabled(boolean flag) {
      this.enabled = flag;
      return this;
   }

   public BooleanSupplierEntry(Module module2, SettingGroup settingGroup2, String text, int count2, BooleanSupplier booleanSupplier2) {
      this.value = 1.0;
      this.value3 = 1.0;
      this.factor = 50.0F;
      this.enabled = false;
      this.module = module2;
      this.settingGroup = settingGroup2;
      this.labelText3 = text;
      this.count = count2;
      this.booleanSupplier = booleanSupplier2;
   }
}
