package net.augustus.settings;

import net.augustus.utils.TuplePair;
import java.util.ArrayList;
import java.util.List;

public class SettingComposite {
   private SettingVisibilityCondition visibilityCondition;
   private final List childSettings = new ArrayList();

   public SettingComposite withDisplayNamePrefix(String displayNamePrefix) {
      this.childSettings.forEach(obj -> applyDisplayNamePrefix(displayNamePrefix, (TuplePair)obj));
      return this;
   }

   public Setting registerSetting(Setting setting) {
      this.childSettings.add(new TuplePair(null, setting));
      SettingVisibilityCondition condition = () -> {
         return this.visibilityCondition == null || this.visibilityCondition.isVisible();
      };
      setting.addVisibilityCondition(condition);
      return setting;
   }

   public SettingComposite withVisibility(SettingVisibilityCondition condition) {
      this.visibilityCondition = condition;
      return this;
   }

   public SettingComposite() {
      this.visibilityCondition = null;
   }

   private static void applyDisplayNamePrefix(String str, TuplePair tuplePair) {
      String first = (String)tuplePair.getFirst();
      if (first != null) {
         ((Setting)tuplePair.getSecond()).withDisplayName(str + first);
      }
   }

   public Setting registerNamedSetting(String str, Setting setting) {
      this.childSettings.add(new TuplePair(str, setting));
      SettingVisibilityCondition condition = () -> {
         return this.visibilityCondition == null || this.visibilityCondition.isVisible();
      };
      setting.addVisibilityCondition(condition);
      return setting;
   }

   public SettingComposite markAllVisualOnly() {
      this.childSettings.forEach(obj -> {
         ((Setting)((TuplePair)obj).getSecond()).markVisualOnly();
      });
      return this;
   }




}
