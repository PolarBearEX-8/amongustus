package net.augustus.settings;

import net.augustus.settings.property.IntegerArrayProperty;
import net.augustus.modules.Module;

public class IntegerArraySetting extends AbstractArraySetting {
   private final IntegerArrayProperty a;

   @Override
   public Object[] getValues() {
      return null;
   }

   public IntegerArraySetting(Module module, String str, Integer[] nums) {
       super(module, str);
      this.a = new IntegerArrayProperty(this, "A", new Integer[0]);
      this.setIntegerValues(nums);
      this.a.setDefaultValue(nums);
   }

   public Integer[] getIntegerValues() {
      return (Integer[])this.a.getValue();
   }

   public IntegerArraySetting(Module module, String str) {
       super(module, str);
      this.a = new IntegerArrayProperty(this, "A", new Integer[0]);
   }

   @Override
   public void resetToDefault() {
      this.a.resetToDefault();
   }

   public void setIntegerValues(Integer[] nums) {
      this.a.setValue(nums);
   }

   @Override
   public void setValues(Object[] array) {
      this.setIntegerValues((Integer[])array);
   }
}
