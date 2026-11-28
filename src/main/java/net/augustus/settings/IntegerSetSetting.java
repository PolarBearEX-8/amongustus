package net.augustus.settings;

import net.augustus.settings.property.IntegerSetProperty;
import java.util.HashSet;
import java.util.Set;
import net.augustus.modules.Module;

public class IntegerSetSetting extends AbstractSetSetting {
   private final IntegerSetProperty s;

   public IntegerSetSetting(Module module, String str, Set set) {
       super(module, str);
      HashSet set2 = new HashSet();
      this.s = new IntegerSetProperty(this, "S", set2);
      this.setSet(set);
      this.s.setDefaultValue(set);
   }

   @Override
   public void resetToDefault() {
      this.s.resetToDefault();
   }

   @Override
   public void setSet(Set set) {
      this.s.setValue(set);
   }

   public IntegerSetSetting(Module module, String str) {
       super(module, str);
      HashSet set = new HashSet();
      this.s = new IntegerSetProperty(this, "S", set);
   }

   @Override
   public Set getSet() {
      return (Set)this.s.getValue();
   }
}
