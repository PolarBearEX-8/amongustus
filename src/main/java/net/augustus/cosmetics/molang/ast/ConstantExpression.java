package net.augustus.cosmetics.molang.ast;

public class ConstantExpression implements MolangValue {
   private double value;

   public ConstantExpression(double d) {
      this.value = d;
   }

   public void setValue(double d) {
      this.value = d;
   }

   @Override
   public double get() {
      return this.value;
   }

   @Override
   public String toString() {
      return String.valueOf(this.value);
   }
}
