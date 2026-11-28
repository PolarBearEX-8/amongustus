package net.optifine.entity.model.anim;

import net.optifine.expr.IExpression;

public class RenderResolverEntity implements IRenderResolver {
   @Override
   public IExpression getParameter(String str) {
      RenderEntityParameterBool bool = RenderEntityParameterBool.parse(str);
      if (bool != null) {
         return bool;
      } else {
         RenderEntityParameterFloat obj = RenderEntityParameterFloat.parse(str);
         return obj != null ? obj : null;
      }
   }
}
