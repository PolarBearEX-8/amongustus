package client.setting;

import client.event.SupplementalListener;
import client.module.Module;
import client.util.BooleanSupplier;
import client.util.RecordNode16Helper;
import com.google.gson.JsonObject;
import java.util.concurrent.ThreadLocalRandom;

public class InternalSettingAdapter extends Setting {
   public NumberSetting settingFrequency;
   public float factor3 = (float)ThreadLocalRandom.current().nextDouble();
   public NumberSetting settingStrength;
   public CombatChoiceProperty combatChoiceProperty2;
   public float factor;
   public float factor2;
   public boolean enabled2;
   public NumberSetting settingRateOfChange;
   public ModeSetting settingType;
   public CombatChoiceProperty combatChoiceProperty;

   private boolean isActiveAsFlag() {
      return this.settingType.getSelected().equals("Noise");
   }

   public double computeValue(float factor4) {
      return this.settingType.getSelected().equals("Noise")
         ? (double)RecordNode16Helper.computeFactor(this.factor2, factor4)
         : Math.sin((double)(this.factor2 * 2.0F) * Math.PI + (double)factor4);
   }

   public CombatChoiceProperty getCombatChoiceProperty() {
      return this.combatChoiceProperty;
   }

   @Override
   public JsonObject resolveJsonObject(SupplementalListener supplementalListener) {
      return null;
   }

   public void applyDoubleByDouble(double offset) {
      this.settingFrequency.applyDouble(offset);
   }

   private boolean isActive() {
      return this.settingType.getSelected().equals("Sinus");
   }

   public void setFactor3(float factor4) {
      this.factor3 = factor4;
   }

   public double getValue() {
      float factor4 = this.factor3;
      return this.computeValue(factor4);
   }

   @Override
   public void updateState() {
   }

   public void applyDouble(double offset) {
      this.settingRateOfChange.applyDouble(offset);
   }

   public void setFactor(float factor4) {
      this.factor = factor4;
   }

   public float getFactor3() {
      return this.factor3;
   }

   public double getValueAsDouble() {
      return !this.settingStrength.isVisible() ? 1.0 : this.settingStrength.getDouble2(true);
   }

   @Override
   public void updateSupplementalListener(SupplementalListener supplementalListener, JsonObject jsonObject) {
   }

   private boolean isActive3() {
      return this.settingType.getSelected().equals("Noise");
   }

   public void applyDouble2(double offset) {
      this.settingStrength.applyDouble(offset);
   }

   public void applyText(String text) {
      this.settingType.setSelected(text);
   }

   public void setFactor2(float factor4) {
      this.factor2 = factor4;
   }

   private boolean isActive2() {
      return this.settingType.getSelected().equals("Sinus");
   }

   @Override
   public void updateState3() {
   }

   public CombatChoiceProperty getCombatChoiceProperty2() {
      return this.combatChoiceProperty2;
   }

   public void updateState7() {
      if (this.enabled2) {
         if (this.settingType.getSelected().equals("Noise")) {
            this.combatChoiceProperty.updateState8();
            this.factor2 = this.factor2 + (float)this.combatChoiceProperty.getValue() * this.factor / 4000.0F;
         } else {
            this.combatChoiceProperty2.updateState8();
            this.factor2 = this.factor2 + (float)this.combatChoiceProperty2.getValue() * this.factor / 1000.0F;
         }
      } else {
         if (this.settingType.getSelected().equals("Noise")) {
            this.factor2 = this.factor2 + (float)this.settingRateOfChange.getDouble2(true) * this.factor / 4000.0F;
         } else {
            this.factor2 = this.factor2 + (float)this.settingFrequency.getDouble2(true) * (this.factor / 1000.0F);
            this.factor2 %= 1.0F;
         }
      }
   }

   public InternalSettingAdapter(Module module, SettingGroup settingGroup, String text, BooleanSupplier booleanSupplier, int count, boolean flag) {
      super(module, text);
      SettingGroup settingGroup2 = new SettingGroup(module, text + "_layer_" + count, settingGroup);
      String text2 = "Layer " + count;
      SettingGroup setting = (SettingGroup)settingGroup2.getSetting(text2);
      ModeSetting setting2 = new ModeSetting(module, text + "_type", "Noise", new String[]{"Noise", "Sinus"}, setting);
      String text3 = "Type";
      this.settingType = setting2.getModeSetting(text3);
      NumberSetting setting3 = new NumberSetting(module, text + "_strength", 0.0, 0.1, 100.0, 0, setting);
      String text4 = "Strength";
      this.settingStrength = setting3.getNumberSetting(text4).getNumberSetting(booleanSupplier);
      if (flag) {
         CombatChoiceProperty combatChoiceProperty3 = new CombatChoiceProperty(module, text + "_rateofchange", setting, false);
         String text5 = "Rate of change";
         this.combatChoiceProperty = combatChoiceProperty3.resolveCombatChoiceProperty(text5);
         CombatChoiceProperty combatChoiceProperty4 = new CombatChoiceProperty(module, text + "_frequency", setting, false);
         String text6 = "Frequency";
         this.combatChoiceProperty2 = combatChoiceProperty4.resolveCombatChoiceProperty(text6);
         double offset = 1.0;
         double offset2 = 1.0;
         double offset3 = 25.0;
         double offset4 = 0.1;
         this.combatChoiceProperty2.resolveCombatChoiceProperty(offset4, offset3, offset2, offset, 2);
         double offset5 = 1.0;
         double offset6 = 1.0;
         double offset7 = 25.0;
         double offset8 = 0.1;
         this.combatChoiceProperty.resolveCombatChoiceProperty(offset8, offset7, offset6, offset5, 2);
         this.combatChoiceProperty.updateState7();
         this.combatChoiceProperty2.updateState7();
         CombatChoiceProperty combatChoiceProperty5 = this.combatChoiceProperty;
         BooleanSupplier booleanSupplier2 = this::isActive3;
         combatChoiceProperty5.resolveExtendedSettingAdapter(booleanSupplier2);
         CombatChoiceProperty combatChoiceProperty6 = this.combatChoiceProperty2;
         BooleanSupplier booleanSupplier3 = this::isActive;
         combatChoiceProperty6.resolveExtendedSettingAdapter(booleanSupplier3);
         this.enabled2 = true;
      } else {
         NumberSetting setting4 = new NumberSetting(module, text + "_rateOfChange", 1.0, 0.1, 25.0, 2, setting);
         String text7 = "Rate of change";
         NumberSetting numberSetting = setting4.getNumberSetting(text7);
         BooleanSupplier booleanSupplier4 = this::isActiveAsFlag;
         this.settingRateOfChange = numberSetting.getNumberSetting(booleanSupplier4);
         NumberSetting setting5 = new NumberSetting(module, text + "_frequency", 1.0, 0.1, 10.0, 2, setting);
         String text8 = "Frequency";
         NumberSetting numberSetting2 = setting5.getNumberSetting(text8);
         BooleanSupplier booleanSupplier5 = this::isActive2;
         this.settingFrequency = numberSetting2.getNumberSetting(booleanSupplier5);
      }
   }
}
