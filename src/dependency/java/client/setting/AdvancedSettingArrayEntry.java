package client.setting;

import client.util.DynamicDoubleEntry;
import client.util.StaticFloatEntry;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

public class AdvancedSettingArrayEntry {
   public static StaticFloatEntry resolveStaticFloatEntry(float[] factorArray) {
      Validate.validIndex(ArrayUtils.toObject(factorArray), 2);
      return new StaticFloatEntry(factorArray[0], factorArray[1], factorArray[2]);
   }

   public static DynamicDoubleEntry resolveDynamicDoubleEntry(double[] offsetArray) {
      Validate.validIndex(ArrayUtils.toObject(offsetArray), 2);
      return new DynamicDoubleEntry(offsetArray[0], offsetArray[1], offsetArray[2]);
   }

   public static StaticFloatEntry resolveStaticFloatEntry(DynamicDoubleEntry dynamicDoubleEntry) {
      return new StaticFloatEntry((float)dynamicDoubleEntry.value2, (float)dynamicDoubleEntry.value3, (float)dynamicDoubleEntry.value);
   }

   public static Setting[] getSettingArray() {
      return null;
   }

   public static StaticFloatEntry resolveStaticFloatEntry(StaticFloatEntry staticFloatEntry) {
      return new StaticFloatEntry(staticFloatEntry.getFactor2(), staticFloatEntry.getFactor3(), staticFloatEntry.getFactor());
   }
}
