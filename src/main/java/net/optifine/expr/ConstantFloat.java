package net.optifine.expr;

public class ConstantFloat implements IExpressionFloat {
   private float value;

   @Override
   public String toString() {
      return "" + this.value;
   }

   @Override
   public ExpressionType getFloatValue() {
      return ExpressionType.FLOAT;
   }

   @Override
   public float eval() {
      return this.value;
   }

   public ConstantFloat(float f) {
      this.value = f;
   }
}
