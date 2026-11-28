package net.optifine.shaders.config;

import java.util.Map;
import net.optifine.config.Config;
import net.optifine.expr.ConstantFloat;
import net.optifine.expr.FunctionBool;
import net.optifine.expr.FunctionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionResolver;

public class MacroExpressionResolver implements IExpressionResolver {
   private Map mapMacroValues = null;

   public MacroExpressionResolver(Map map) {
      this.mapMacroValues = map;
   }

   @Override
   public IExpression getExpression(String str) {
      String str2 = "defined_";
      if (str.startsWith(str2)) {
         String str3 = str.substring(str2.length());
         return this.mapMacroValues.containsKey(str3)
            ? new FunctionBool(FunctionType.TRUE, (IExpression[])null)
            : new FunctionBool(FunctionType.FALSE, (IExpression[])null);
      } else {
         while (this.mapMacroValues.containsKey(str)) {
            String str4 = (String)this.mapMacroValues.get(str);
            if (str4 == null || str4.equals(str)) {
               break;
            }

            str = str4;
         }

         int n = Config.parseInt(str, Integer.MIN_VALUE);
         if (n == Integer.MIN_VALUE) {
            Config.warn("Unknown macro value: " + str);
            return new ConstantFloat(0.0F);
         } else {
            return new ConstantFloat((float)n);
         }
      }
   }
}
