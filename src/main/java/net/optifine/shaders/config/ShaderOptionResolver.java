package net.optifine.shaders.config;

import java.util.HashMap;
import java.util.Map;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionResolver;

public class ShaderOptionResolver implements IExpressionResolver {
   private Map mapOptions = new HashMap();

   @Override
   public IExpression getExpression(String str) {
      return (ExpressionShaderOptionSwitch)this.mapOptions.get(str);
   }

   public ShaderOptionResolver(ShaderOption[] shaderOptions) {
      for (int i = 0; i < shaderOptions.length; i++) {
         ShaderOption shaderOption = shaderOptions[i];
         if (shaderOption instanceof ShaderOptionSwitch) {
            ShaderOptionSwitch shaderOptionSwitch = (ShaderOptionSwitch)shaderOption;
            ExpressionShaderOptionSwitch obj = new ExpressionShaderOptionSwitch(shaderOptionSwitch);
            this.mapOptions.put(shaderOption.getName(), obj);
         }
      }
   }
}
