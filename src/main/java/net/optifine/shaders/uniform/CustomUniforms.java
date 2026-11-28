package net.optifine.shaders.uniform;

import java.util.ArrayList;
import java.util.Map;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionCached;

public class CustomUniforms {
   private CustomUniform[] uniforms;
   private IExpressionCached[] expressionsCached;

   public void reset() {
      for (int i = 0; i < this.uniforms.length; i++) {
         this.uniforms[i].reset();
      }
   }

   private void resetCache() {
      for (int i = 0; i < this.expressionsCached.length; i++) {
         this.expressionsCached[i].reset();
      }
   }

   public void update() {
      this.resetCache();

      for (int i = 0; i < this.uniforms.length; i++) {
         this.uniforms[i].update();
      }
   }

   public CustomUniforms(CustomUniform[] customUniforms, Map map) {
      this.uniforms = customUniforms;
      ArrayList list = new ArrayList();

      for (String str : (Iterable<String>)(map.keySet())) {
         IExpression expression = (IExpression)map.get(str);
         if (expression instanceof IExpressionCached) {
            list.add((IExpressionCached)expression);
         }
      }

      this.expressionsCached = (IExpressionCached[])list.toArray(new IExpressionCached[list.size()]);
   }

   public void setProgram(int n) {
      for (int i = 0; i < this.uniforms.length; i++) {
         this.uniforms[i].setProgram(n);
      }
   }
}
