package net.augustus.settings;

import net.augustus.settings.property.DoubleSetProperty;
import java.util.HashSet;
import java.util.Set;
import net.augustus.modules.Module;

public class DoubleSetSetting extends AbstractSetSetting {
   private final DoubleSetProperty setProperty;

   public DoubleSetSetting(Module module, String str) {
       super(module, str);
      this.setProperty = new DoubleSetProperty(this, "S", new HashSet());
   }

   public DoubleSetSetting(Module module, String str, Set set) {
       super(module, str);
      this.setProperty = new DoubleSetProperty(this, "S", new HashSet());
      this.setSet(set);
      this.setProperty.setDefaultValue(set);
   }

   @Override
   public void setSet(Set set) {
      this.setProperty.setValue(set);
   }

   @Override
   public Set getSet() {
      return (Set)this.setProperty.getValue();
   }

   @Override
   public void resetToDefault() {
      this.setProperty.resetToDefault();
   }
}
