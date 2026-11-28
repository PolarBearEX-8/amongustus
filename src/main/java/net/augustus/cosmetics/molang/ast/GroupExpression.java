package net.augustus.cosmetics.molang.ast;

public class GroupExpression implements MolangValue {
   private MolangValue value;

   @Override
   public String toString() {
      return "(" + this.value.toString() + ")";
   }

   @Override
   public double get() {
      return this.value.get();
   }

   public GroupExpression(MolangValue molangValue) {
      this.value = molangValue;
   }
}
