package net.augustus.cosmetics.molang.ast;

public class ConstantValue implements MolangValue {
   private final double value;

   public static ConstantValue parseFloat(String str) {
      return new ConstantValue((double)Float.parseFloat(str));
   }

   public static ConstantValue fromFloat(float f) {
      return new ConstantValue((double)f);
   }

   public ConstantValue(double d) {
      this.value = d;
   }

   public static ConstantValue fromDouble(double d) {
      return new ConstantValue(d);
   }

   public static ConstantValue subtract(MolangValue molangValue, MolangValue molangValue2) {
      return fromDouble(molangValue.get() - molangValue2.get());
   }

   @Override
   public double get() {
      return this.value;
   }

   public static ConstantValue parseDouble(String str) {
      return new ConstantValue(Double.parseDouble(str));
   }
}
