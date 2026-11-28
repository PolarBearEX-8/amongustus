package net.optifine.shaders.uniform;

import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionFloat;

public class ShaderParameterIndexed implements IExpressionFloat {
   private ShaderParameterFloat type;
   private int index1;
   private int index2;

   @Override
   public float eval() {
      return this.type.eval(this.index1, this.index2);
   }

   public ShaderParameterIndexed(ShaderParameterFloat shaderParameterFloat, int n, int n2) {
      this.type = shaderParameterFloat;
      this.index1 = n;
      this.index2 = n2;
   }

   @Override
   public ExpressionType getFloatValue() {
      return ExpressionType.FLOAT;
   }

   public ShaderParameterIndexed(ShaderParameterFloat shaderParameterFloat, int n) {
      this(shaderParameterFloat, n, 0);
   }

   @Override
   public String toString() {
      return this.type.getIndexNames1() == null
         ? "" + this.type
         : (
            this.type.getIndexNames2() == null
               ? "" + this.type + "." + this.type.getIndexNames1()[this.index1]
               : "" + this.type + "." + this.type.getIndexNames1()[this.index1] + "." + this.type.getIndexNames2()[this.index2]
         );
   }

   public ShaderParameterIndexed(ShaderParameterFloat shaderParameterFloat) {
      this(shaderParameterFloat, 0, 0);
   }
}
