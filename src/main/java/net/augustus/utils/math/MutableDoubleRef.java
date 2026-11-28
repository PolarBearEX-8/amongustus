package net.augustus.utils.math;

public class MutableDoubleRef {
   private double value;

   public void setValue(double d) {
      this.value = d;
   }

   public double getValue() {
      return this.value;
   }

   public MutableDoubleRef() {
   }

   public MutableDoubleRef(double d) {
      this.value = d;
   }
}
