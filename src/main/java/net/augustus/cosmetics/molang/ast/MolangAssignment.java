package net.augustus.cosmetics.molang.ast;


import net.augustus.cosmetics.molang.MolangParser;
public class MolangAssignment extends MolangExpression {
   public MolangVariable variable;
   public MolangValue expression;

   @Override
   public String toString() {
      return this.variable.getName() + " = " + this.expression.toString();
   }

   @Override
   public double get() {
      double d = this.expression.get();
      this.variable.setValue(d);
      return d;
   }

   public MolangAssignment(MolangParser molangParser, MolangVariable molangVariable, MolangValue molangValue) {
      super(molangParser);
      this.variable = molangVariable;
      this.expression = molangValue;
   }
}
