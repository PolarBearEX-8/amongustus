package net.optifine.shaders.uniform;

import net.optifine.shaders.SMCLog;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.expr.ConstantFloat;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionResolver;

public class ShaderExpressionResolver implements IExpressionResolver {
   private Map mapExpressions = new HashMap();

   public boolean hasExpression(String str) {
      return this.mapExpressions.containsKey(str);
   }

   public boolean registerExpression(String str, IExpression expression) {
      if (this.mapExpressions.containsKey(str)) {
         SMCLog.warning("Expression already defined: " + str);
         return false;
      } else {
         this.mapExpressions.put(str, expression);
         return true;
      }
   }

   private void addParameterFloat(Map map, ShaderParameterFloat shaderParameterFloat) {
      String[] indexNames1 = shaderParameterFloat.getIndexNames1();
      if (indexNames1 == null) {
         map.put(shaderParameterFloat.getName(), new ShaderParameterIndexed(shaderParameterFloat));
      } else {
         for (int i = 0; i < indexNames1.length; i++) {
            String str = indexNames1[i];
            String[] indexNames2 = shaderParameterFloat.getIndexNames2();
            if (indexNames2 == null) {
               map.put(shaderParameterFloat.getName() + "." + str, new ShaderParameterIndexed(shaderParameterFloat, i));
            } else {
               for (int j = 0; j < indexNames2.length; j++) {
                  String str2 = indexNames2[j];
                  map.put(shaderParameterFloat.getName() + "." + str + "." + str2, new ShaderParameterIndexed(shaderParameterFloat, i, j));
               }
            }
         }
      }
   }

   public ShaderExpressionResolver(Map map) {
      this.registerExpressions();

      for (String str : (Iterable<String>)(map.keySet())) {
         this.registerExpression(str, (IExpression)map.get(str));
      }
   }

   @Override
   public IExpression getExpression(String str) {
      return (IExpression)this.mapExpressions.get(str);
   }

   private void registerExpressions() {
      ShaderParameterFloat[] values = ShaderParameterFloat.values();

      for (int i = 0; i < values.length; i++) {
         ShaderParameterFloat shaderParameterFloat = values[i];
         this.addParameterFloat(this.mapExpressions, shaderParameterFloat);
      }

      ShaderParameterBool[] values2 = ShaderParameterBool.values();

      for (int j = 0; j < values2.length; j++) {
         ShaderParameterBool shaderParameterBool = values2[j];
         this.mapExpressions.put(shaderParameterBool.getName(), shaderParameterBool);
      }

      for (BiomeGenBase biomeGenBase : (Iterable<BiomeGenBase>)(BiomeGenBase.BIOME_ID_MAP.values())) {
         String str = biomeGenBase.biomeName.trim();
         str = "BIOME_" + str.toUpperCase().replace(' ', '_');
         int biomeID = biomeGenBase.biomeID;
         this.registerExpression(str, new ConstantFloat((float)biomeID));
      }
   }
}
