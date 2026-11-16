package client.util;

public class Controller17 {
   public final double value;
   public final double value2;
   public final boolean enabled;

   public Controller17(double offset, double offset2, boolean flag) {
      this.value = offset;
      this.value2 = offset2;
      this.enabled = flag;
   }

   public double getValue2() {
      return this.value2;
   }

   public double getValue() {
      return this.value;
   }

   public boolean isEnabled() {
      return this.enabled;
   }
}
