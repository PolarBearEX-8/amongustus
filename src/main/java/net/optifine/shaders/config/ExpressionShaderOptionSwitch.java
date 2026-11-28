package net.optifine.shaders.config;

import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionBool;

public class ExpressionShaderOptionSwitch implements IExpressionBool {
   private ShaderOptionSwitch shaderOption;

   @Override
   public boolean eval() {
      return ShaderOptionSwitch.isTrue(this.shaderOption.getValue());
   }

   @Override
   public String toString() {
      return "" + this.shaderOption;
   }

   public ExpressionShaderOptionSwitch(ShaderOptionSwitch shaderOptionSwitch) {
      this.shaderOption = shaderOptionSwitch;
   }

   @Override
   public ExpressionType getFloatValue() {
      return ExpressionType.BOOL;
   }
}
