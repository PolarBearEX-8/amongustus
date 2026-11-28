package net.optifine.expr;

public class FunctionBool implements IExpressionBool {
   private FunctionType type;
   private IExpression[] arguments;

   @Override
   public boolean eval() {
      return this.type.evalBool(this.arguments);
   }

   @Override
   public ExpressionType getFloatValue() {
      return ExpressionType.BOOL;
   }

   @Override
   public String toString() {
      return "" + this.type + "()";
   }

   public FunctionBool(FunctionType functionType, IExpression[] expressions) {
      this.type = functionType;
      this.arguments = expressions;
   }
}
