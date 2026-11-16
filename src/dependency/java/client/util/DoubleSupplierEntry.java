package client.util;

import client.setting.Setting;
import java.util.function.DoubleSupplier;

public class DoubleSupplierEntry {
   public DoubleSupplier doubleSupplier;
   public int count;
   public Object dataRef;

   public Object getDataRef() {
      return this.dataRef;
   }

   public static Setting[] getSettingArray() {
      return null;
   }

   public void applyTarget(Object entry) {
      if (this.count == 0) {
         this.dataRef = entry;
         this.count = (int)this.doubleSupplier.getAsDouble();
      } else {
         if (this.count > 0) {
            this.count--;
         }
      }
   }

   public void updateState() {
      this.count = 0;
   }

   public DoubleSupplierEntry(DoubleSupplier doubleSupplier2) {
      this.doubleSupplier = doubleSupplier2;
   }
}
