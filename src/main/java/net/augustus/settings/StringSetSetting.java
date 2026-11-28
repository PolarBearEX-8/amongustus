package net.augustus.settings;

import net.augustus.settings.property.StringSetProperty;
import java.util.HashSet;
import java.util.Set;
import net.augustus.modules.Module;

public class StringSetSetting extends AbstractSetSetting {
   private final StringSetProperty setProperty;

   @Override
   public Set getSet() {
      return (Set)this.setProperty.getValue();
   }

   public StringSetSetting(Module module, String str) {
       super(module, str);
      this.setProperty = new StringSetProperty(this, "S", new HashSet());
   }

   @Override
   public void resetToDefault() {
      this.setProperty.resetToDefault();
   }

   @Override
   public void setSet(Set set) {
      this.setProperty.setValue(set);
   }

   public StringSetSetting(Module module, String str, Set set) {
       super(module, str);
      this.setProperty = new StringSetProperty(this, "S", new HashSet());
      this.setSet(set);
      this.setProperty.setDefaultValue(set);
   }
}
