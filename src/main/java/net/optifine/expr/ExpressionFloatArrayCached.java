package net.optifine.expr;


public class ExpressionFloatArrayCached implements IExpressionCached, IExpressionFloatArray {
   private IExpressionFloatArray expression;
   private boolean cached;
   private float[] value;

   public ExpressionFloatArrayCached(IExpressionFloatArray expressionFloatArray) {
      this.expression = expressionFloatArray;
   }

   @Override
   public float[] eval() {
      if (!this.cached) {
         this.value = this.expression.eval();
         this.cached = true;
      }

      return this.value;
   }

   @Override
   public String toString() {
      return "cached(" + this.expression + ")";
   }

   @Override
   public ExpressionType getFloatValue() {
      return ExpressionType.FLOAT;
   }

   @Override
   public void reset() {
      this.cached = false;
   }
}
