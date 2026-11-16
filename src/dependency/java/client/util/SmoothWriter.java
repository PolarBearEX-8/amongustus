package client.util;

import client.setting.ActionSetting;
import client.setting.BooleanSettingProperty;
import client.setting.BooleanSettingValue;
import client.setting.CombatChoiceProperty;
import client.setting.InternalSettingAdapter;
import client.setting.SettingGroup;
import net.minecraft.entity.EntityLivingBaseEntry;

public abstract class SmoothWriter implements MinecraftAccessor {
   public String labelText2;
   public boolean enabled = false;
   public BooleanSettingProperty booleanSettingProperty;
   public SettingGroup setting;
   public CombatChoiceProperty combatChoiceProperty;

   public void applyDouble(double offset, double offset2) {
      if (this.setting == null) {
         SettingGroup settingGroup = this.combatChoiceProperty.settingNoisegroup;
         String text = this.labelText2 + " Strength (min=" + PlayerUtil.getDouble5(offset * 100.0, 2) + "%, max=" + PlayerUtil.getDouble5(offset2 * 100.0, 2) + "%)";
         settingGroup.setState2(text);
      } else {
         SettingGroup setting2 = this.setting;
         String text2 = this.labelText2 + " (min=" + PlayerUtil.getDouble5(offset * 100.0, 2) + "%, max=" + PlayerUtil.getDouble5(offset2 * 100.0, 2) + "%)";
         setting2.setState2(text2);
      }
   }

   public abstract double computeValue(EntityLivingBaseEntry entityLivingBaseEntry);

   public CombatChoiceProperty getCombatChoiceProperty() {
      return this.combatChoiceProperty;
   }

   public void setEnabled(boolean enabled2) {
      this.enabled = enabled2;
   }

   public SettingGroup getSetting() {
      return this.setting;
   }

   public void updateState() {
   }

   public double computeValueByEntityLivingBase(EntityLivingBaseEntry entityLivingBaseEntry) {
      this.combatChoiceProperty.updateState8();
      return this.combatChoiceProperty.getValue();
   }

   public String getLabelText2() {
      return this.labelText2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   private void applyBooleanSettingProperty(BooleanSettingProperty booleanSettingProperty2) {
      BooleanSettingValue booleanSettingValue = booleanSettingProperty2.getBooleanSettingValue();
      boolean enabled2 = this.enabled;
      booleanSettingValue.setEnabled(enabled2);
   }

   public SmoothWriter(BooleanSettingProperty booleanSettingProperty2) {
      this.booleanSettingProperty = booleanSettingProperty2;
   }

   public SmoothWriter(BooleanSettingProperty booleanSettingProperty2, String text) {
      this(booleanSettingProperty2, text, false);
   }

   public SmoothWriter(BooleanSettingProperty booleanSettingProperty2, String text, boolean flag) {
        super();
      
      this.enabled = false;
      this.labelText2 = text;
      this.booleanSettingProperty = booleanSettingProperty2;
      if (flag) {
         this.setting = (SettingGroup)(new SettingGroup(booleanSettingProperty2.getModule(), booleanSettingProperty2.getSettingGroup().getString() + "_" + text, booleanSettingProperty2.getSettingGroup()))
            .getSetting(text);
         this.combatChoiceProperty = new CombatChoiceProperty(booleanSettingProperty2.getModule(), booleanSettingProperty2.getSettingGroup().getString() + "_" + text, this.setting, false);
      } else {
         this.combatChoiceProperty = new CombatChoiceProperty(booleanSettingProperty2.getModule(), booleanSettingProperty2.getSettingGroup().getString() + "_" + text, booleanSettingProperty2.getSettingGroup(), false);
      }

      double offset = 0.0;
      double offset2 = 0.0;
      double offset3 = 100.0;
      double offset4 = 0.0;
      this.combatChoiceProperty.resolveCombatChoiceProperty(offset4, offset3, offset2, offset, 0);
      String text2 = text + " Strength";
      this.combatChoiceProperty.resolveCombatChoiceProperty(text2);
      CombatChoiceProperty combatChoiceProperty2 = this.combatChoiceProperty;
      InternalSettingAdapter internalSettingAdapter = this.combatChoiceProperty.getBooleanSupplierEntry().getInternalSettingAdapter();
      combatChoiceProperty2.add(internalSettingAdapter);
      ActionSetting actionSetting = this.combatChoiceProperty.actionSetting;
      Runnable task = () -> this.applyBooleanSettingProperty(booleanSettingProperty2);
      actionSetting.applyRunnable(task);
   }
}
