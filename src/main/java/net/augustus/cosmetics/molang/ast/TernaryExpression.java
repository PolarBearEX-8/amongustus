package net.augustus.cosmetics.molang.ast;


public class TernaryExpression implements MolangValue {
   public MolangValue ifFalse;
   public MolangValue condition;
   public MolangValue ifTrue;

   @Override
   public String toString() {
      return this.condition.toString() + " ? " + this.ifTrue.toString() + " : " + this.ifFalse.toString();
   }

   public TernaryExpression(MolangValue molangValue, MolangValue molangValue2, MolangValue molangValue3) {
      this.condition = molangValue;
      this.ifTrue = molangValue2;
      this.ifFalse = molangValue3;
   }

   public double get() {
      return this.condition.get() != 0.0 ? this.ifTrue.get() : this.ifFalse.get();
   }

}
