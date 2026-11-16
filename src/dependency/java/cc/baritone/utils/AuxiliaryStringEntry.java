package cc.baritone.utils;

import cc.Settings;
import cc.SettingsUtil;
import java.lang.reflect.Type;

public final class AuxiliaryStringEntry {
   public Object dataRef;
   public final Object dataRef2;
   public String labelText;
   public boolean enabled;
   public final Settings settings;

   public Class getClassAsClass() {
      return this.dataRef.getClass();
   }

   public void updateState2() {
      this.dataRef = this.dataRef2;
   }

   private void updateState() {
      SettingsTxtManager.getAuxiliaryMapEntry().list.add(this);
      SettingsTxtManager.getAuxiliaryMapEntry().map.put(this.labelText.toLowerCase(), this);
      SettingsTxtManager.getAuxiliaryMapEntry().map2.put(this, this.dataRef.getClass());
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public Type get() {
      return (Type)this.settings.settingTypes.get(this);
   }

   public String getLabelText() {
      return this.labelText;
   }

   public AuxiliaryStringEntry(Settings settings2, String text, Object entry, boolean flag) {
      this.settings = settings2;
      this.labelText = text;
      this.dataRef = entry;
      this.dataRef2 = entry;
      this.enabled = flag;
      this.updateState();
   }

   public AuxiliaryStringEntry(Settings settings2, String text, Object entry) {
      this.settings = settings2;
      this.labelText = text;
      this.dataRef = entry;
      this.dataRef2 = entry;
      this.enabled = false;
      this.updateState();
   }

   @Override
   public String toString() {
      return SettingsUtil.settingToString(this);
   }
}
