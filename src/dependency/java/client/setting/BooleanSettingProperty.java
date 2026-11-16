package client.setting;

import client.module.Module;

public class BooleanSettingProperty {
   public BooleanSettingValue booleanSettingValue;
   public SettingGroup settingGroup;
   public Module module;

   public Module getModule() {
      return this.module;
   }

   public BooleanSettingValue getBooleanSettingValue() {
      return this.booleanSettingValue;
   }

   public SettingGroup getSettingGroup() {
      return this.settingGroup;
   }

   public BooleanSettingProperty resolveBooleanSettingPropertyBySettingGroup(SettingGroup settingGroup2) {
      return new BooleanSettingProperty(this.module, settingGroup2, this.booleanSettingValue);
   }

   public BooleanSettingProperty(Module module2, SettingGroup settingGroup2, BooleanSettingValue booleanSettingValue2) {
      this.module = module2;
      this.settingGroup = settingGroup2;
      this.booleanSettingValue = booleanSettingValue2;
   }

   public BooleanSettingProperty(Module module2, SettingGroup settingGroup2) {
      this(module2, settingGroup2, null);
   }
}
