package net.augustus.cosmetics.molang.ast;

public class NegateExpression implements MolangValue {
   public MolangValue value;

   public NegateExpression(MolangValue molangValue) {
      this.value = molangValue;
   }

   @Override
   public String toString() {
      return "-" + this.value.toString();
   }

   @Override
   public double get() {
      return -this.value.get();
   }
}
