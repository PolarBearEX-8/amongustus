package net.augustus.modules.modes.killaura.unoise3;

import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.modules.Module;
import net.augustus.settings.SettingGroup;

public class UNoise3SettingContext {
   private Module module;
   private String keyPrefix = "";
   private SettingVisibilityCondition visibilityCondition;
   private SettingGroup parentGroup;
   private UNoise3PointSelector uNoise3PointSelector;

   public UNoise3SettingContext(Module module2, SettingGroup doubleValue) {
      this(module2, doubleValue, null, null);
   }

   public UNoise3SettingContext(Module module2, SettingGroup doubleValue, SettingVisibilityCondition condition) {
      this(module2, doubleValue, null, condition);
   }

   public SettingGroup getParentGroup() {
      return this.parentGroup;
   }

   public String getKeyPrefix() {
      return this.keyPrefix;
   }

   public UNoise3SettingContext withKeyPrefix(String str) {
      this.keyPrefix = str;
      return this;
   }

   public Module getModule() {
      return this.module;
   }

   public UNoise3PointSelector getUNoise3PointSelector() {
      return this.uNoise3PointSelector;
   }

   public SettingVisibilityCondition getVisibilityCondition() {
      return this.visibilityCondition;
   }

   public UNoise3SettingContext(Module module2, SettingGroup doubleValue, UNoise3PointSelector uNoise3PointSelector2, SettingVisibilityCondition condition) {
      this.module = module2;
      this.parentGroup = doubleValue;
      this.uNoise3PointSelector = uNoise3PointSelector2;
      this.visibilityCondition = condition;
   }

   public UNoise3SettingContext withParentGroup(SettingGroup parentGroup2) {
      return new UNoise3SettingContext(this.module, parentGroup2, this.uNoise3PointSelector, this.visibilityCondition);
   }



}
