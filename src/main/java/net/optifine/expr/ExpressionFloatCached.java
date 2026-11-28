package net.optifine.expr;


public class ExpressionFloatCached implements IExpressionFloat, IExpressionCached {
   private IExpressionFloat expression;
   private boolean cached;
   private float value;

   @Override
   public ExpressionType getFloatValue() {
      return ExpressionType.FLOAT;
   }

   @Override
   public float eval() {
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
   public void reset() {
      this.cached = false;
   }

   public ExpressionFloatCached(IExpressionFloat expressionFloat) {
      this.expression = expressionFloat;
   }
}
