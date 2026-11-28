package net.augustus.cosmetics.molang.ast;

public class LogicalNotExpression implements MolangValue {
   public MolangValue value;

   public LogicalNotExpression(MolangValue molangValue) {
      this.value = molangValue;
   }

   public double get() {
      return this.value.get() == 0.0 ? 1.0 : 0.0;
   }

   @Override
   public String toString() {
      return "!" + this.value.toString();
   }
}
