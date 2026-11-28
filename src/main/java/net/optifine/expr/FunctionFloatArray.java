package net.optifine.expr;


public class FunctionFloatArray implements IExpressionFloatArray {
   private FunctionType type;
   private IExpression[] arguments;

   @Override
   public ExpressionType getFloatValue() {
      return ExpressionType.FLOAT_ARRAY;
   }

   @Override
   public String toString() {
      return "" + this.type + "()";
   }

   @Override
   public float[] eval() {
      return this.type.evalFloatArray(this.arguments);
   }

   public FunctionFloatArray(FunctionType functionType, IExpression[] expressions) {
      this.type = functionType;
      this.arguments = expressions;
   }
}
